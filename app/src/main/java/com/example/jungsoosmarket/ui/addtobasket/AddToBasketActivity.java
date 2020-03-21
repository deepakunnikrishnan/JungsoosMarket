package com.example.jungsoosmarket.ui.addtobasket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.jungsoosmarket.R;
import com.example.jungsoosmarket.databinding.ActivityAddToBasketBinding;
import com.example.jungsoosmarket.di.Injection;
import com.example.jungsoosmarket.ui.common.BaseActivity;
import com.google.zxing.Result;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * AddToBasketActivity is responsible for adding a new item to the basket.
 *
 * User has two options to add an item to the basket.
 * 1. Type the QR code of the item in the EditText and tap on Add
 * 2. Scan the QR code using the Scanner displayed in the screen.
 *
 * For scanning the QR code the app leverages the ZXingScannerView, a third party
 * library for scanning barcodes & QR codes.
 */
public class AddToBasketActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 10;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ActivityAddToBasketBinding basketBinding;
    private AddToBasketViewModel addToBasketViewModel;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basketBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_to_basket);
        AddToBasketViewModelProviderFactory factory = Injection.provideAddToBasketViewModelProviderFactory(this);
        addToBasketViewModel = new ViewModelProvider(this,factory).get(AddToBasketViewModel.class);

        bindAddButtonClick();
        checkPermissionAndAddScanner();
    }

    /**
     * Method check the CAMERA permission provided by the user.
     * If the permission is NOT GRANTED, then request the permission.
     * If the permission is already granted, then add the ScannerView in the activity.
     */
    private void checkPermissionAndAddScanner() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!hasCameraPermission()){
                requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST_CODE);
            }else{
                addScannerView();
            }
        }else{
            addScannerView();
        }
    }

    /**
     * Method to bind to click events of the button.
     * In this method, we throttle the consecutive clicks that happen
     * on the button
     */
    private void bindAddButtonClick() {
        Disposable disposable = RxView.clicks(basketBinding.button)
                .throttleFirst(2, TimeUnit.SECONDS)
                .map(unit -> basketBinding.editTextQrCode.getText().toString())
                .filter(productId -> !TextUtils.isEmpty(productId))
                .subscribe(this::addItemToBasket);
        compositeDisposable.add(disposable);
    }

    /**
     * Creates the view for the ZXingScannerView and adds it to the activity container.
     */
    private void addScannerView(){
        mScannerView = new ZXingScannerView(this);
        basketBinding.layoutScannerContainer.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(hasCameraPermission()){
            bindAndStartCamera();
        }
    }

    /**
     * Bind the ResultHandler & start the HardwareCamera.
     */
    private void bindAndStartCamera() {
        if(hasCameraPermission()){
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    private boolean hasCameraPermission(){
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(hasCameraPermission()){
            mScannerView.stopCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                addScannerView();
                bindAndStartCamera();
            }
        }
    }


    @Override
    public void handleResult(Result result) {
        addItemToBasket(result.getText());
    }

    /**
     * Method to add item to basket.
     * Passes the scanned text and subscribes for the completion
     * of adding the item to basket.
     * @param text
     */
    private void addItemToBasket(String text) {
        hideKeyboard();
        Disposable disposable = addToBasketViewModel.insertProductToBasket(text)
                .subscribe(this::onItemAddedToBasket, this::onItemAddFailed);
        compositeDisposable.add(disposable);
    }

    /**
     * Action when the adding the item to Basket completed.
     */
    private void onItemAddedToBasket(){
        Toast.makeText(this,"Item added to your basket",Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Action when the adding the item to Basket failed.
     * @param throwable
     */
    private void onItemAddFailed(Throwable throwable){
        Log.e("AddToBasketActivity",throwable.getMessage());
        Toast.makeText(this,"We were unable to add it to your basket",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
