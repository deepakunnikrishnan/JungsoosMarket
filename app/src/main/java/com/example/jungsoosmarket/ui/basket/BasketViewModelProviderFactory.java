package com.example.jungsoosmarket.ui.basket;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jungsoosmarket.data.BasketRepository;
import com.example.jungsoosmarket.data.ProductsRepository;

/**
 * Factory class for creating the BasketViewModel.
 * This class takes in the dependencies that are required for the BasketViewModel
 * and creates the ViewModel.
 */
public class BasketViewModelProviderFactory implements ViewModelProvider.Factory {

    private final BasketRepository basketRepository;

    private final ProductsRepository productsRepository;

    public BasketViewModelProviderFactory(ProductsRepository productsRepository, BasketRepository basketRepository){
        this.basketRepository = basketRepository;
        this.productsRepository = productsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(BasketViewModel.class)){
            return (T)new BasketViewModel(productsRepository,basketRepository);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }
}
