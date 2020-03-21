package com.example.jungsoosmarket.ui.addtobasket;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jungsoosmarket.data.BasketRepository;
import com.example.jungsoosmarket.data.ProductsRepository;

/**
 * Factory class for creating the AddToBasketViewModel.
 * This class takes in the dependencies that are required for the AddToBasketViewModel
 * and creates the ViewModel.
 */
public class AddToBasketViewModelProviderFactory implements ViewModelProvider.Factory {

    private final BasketRepository basketRepository;

    private final ProductsRepository productsRepository;

    public AddToBasketViewModelProviderFactory(ProductsRepository productsRepository, BasketRepository basketRepository){
        this.basketRepository = basketRepository;
        this.productsRepository = productsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(AddToBasketViewModel.class)){
            return (T)new AddToBasketViewModel(productsRepository,basketRepository);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }
}
