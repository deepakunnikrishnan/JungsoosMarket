package com.example.jungsoosmarket.ui.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.jungsoosmarket.data.ProductsRepository;
import com.example.jungsoosmarket.data.local.database.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomeViewModel extends ViewModel {

    private final ProductsRepository productsRepository;

    public HomeViewModel(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    void loadDummyProducts(){

        /*this.productsRepository.insert(AppDatabase.PRODUCTS)
                .*/

        this.productsRepository.insert(AppDatabase.PRODUCTS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("HomeViewModel",throwable.getMessage());
                    }
                })
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i("HomeViewModel","onComplete Insert");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("HomeViewModel",throwable.getMessage());
                    }
                });

    }


}
