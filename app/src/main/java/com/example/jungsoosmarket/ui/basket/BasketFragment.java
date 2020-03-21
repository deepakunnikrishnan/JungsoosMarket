package com.example.jungsoosmarket.ui.basket;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jungsoosmarket.R;
import com.example.jungsoosmarket.databinding.HomeFragmentBinding;
import com.example.jungsoosmarket.di.Injection;
import com.example.jungsoosmarket.ui.common.BaseFragment;
import com.example.jungsoosmarket.ui.common.recyclerviewhelper.SwipeToDeleteCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * BasketFragment is the class responsible for displaying the
 * products added to the basket by the user.
 *
 * The features of this screen are:
 * 1. Display the items added in the basket.
 * 2. Display the total for the items in the basket.
 * 3. User can swipe on each item to delete it from the basket.
 *
 * BasketFragment makes use of the BasketViewModel to get the data
 * required for the screen.
 * */
public class BasketFragment extends BaseFragment {

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private BasketViewModel mViewModel;
    private HomeFragmentBinding binding;

    private BasketListAdapter basketListAdapter;


    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BasketViewModelProviderFactory factory = Injection.provideBasketViewModelFactory(getContext());
        mViewModel = new ViewModelProvider(this,factory).get(BasketViewModel.class);
        setupBasketList();
        subscribeToDeleteEvents();
        subscribeToBasketTotalChange();
        getItemsInBasket();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposable.clear();
    }

    /**
     * Subscribes to get the items in the basket.
     * When there is a change in the items, the subscriber receives the new data set
     * and the items will be passed to the adapter.
     */
    private void getItemsInBasket(){
        Disposable disposable = mViewModel.getItemsInBasket()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e("BasketFragment",throwable.getMessage()))
                .subscribe(basketItems -> basketListAdapter.submitList(basketItems));
        mDisposable.add(disposable);
    }

    /**
     * Set the baskets list recyclerview.
     * Creates the LayoutManager and configures the recyclerview with the
     * required adapter.
     */
    private void setupBasketList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerViewVirtualBasket.setLayoutManager(linearLayoutManager);
        binding.recyclerViewVirtualBasket.setHasFixedSize(true);
        basketListAdapter = new BasketListAdapter();
        binding.recyclerViewVirtualBasket.setAdapter(basketListAdapter);
        binding.recyclerViewVirtualBasket.setItemAnimator(new DefaultItemAnimator());
        bindSwipeToDelete();
    }

    /**
     * Method to listen for changes in the basket total.
     */
    private void subscribeToBasketTotalChange() {
        mDisposable.add(
                mViewModel.getBasketTotal()
                        .subscribeOn(Schedulers.io())
                        .doOnError(throwable -> Log.e("BasketFragment",throwable.getMessage()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onBasketTotalChanged,this::onBasketTotalFailed)

        );
    }

    /**
     * Method to listen for DELETE events in the baskets.
     */
    private void subscribeToDeleteEvents() {
        mDisposable.add(basketListAdapter.deleteItemSubject
                .observeOn(Schedulers.io())
                .flatMapCompletable(basketItemWithProduct -> mViewModel.deleteItemFromBasket(basketItemWithProduct.basketItem))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onItemDeleted, this::onItemDeleteFailed)
        );
    }

    /**
     * Method to bind the Swipe to Delete functionality.
     */
    private void bindSwipeToDelete() {
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(getContext(),basketListAdapter));
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewVirtualBasket);
    }

    /**
     * Update the Total value displayed in the UI.
     * @param total
     */
    private void onBasketTotalChanged(String total){
        binding.textViewTotalValue.setText(String.format("$%s", total));
    }

    /**
     * Action when the Observable for BasketTotal fails
     * @param throwable
     */
    private void onBasketTotalFailed(Throwable throwable){
        Log.i("BasketFragment",throwable.getMessage());
    }

    /**
     * Action when the Deleting of an item fails.
     * @param throwable
     */
    private void onItemDeleteFailed(Throwable throwable) {
        Log.i("BasketFragment",throwable.getMessage());
    }

    /**
     * Action when the item is deleted from the basket.
     */
    private void onItemDeleted() {
        Log.i("BasketFragment","deleted");
    }

}
