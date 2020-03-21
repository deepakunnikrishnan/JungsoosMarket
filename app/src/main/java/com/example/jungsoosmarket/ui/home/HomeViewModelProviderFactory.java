package com.example.jungsoosmarket.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.jungsoosmarket.data.ProductsRepository;

/**
 * Factory class for creating the HomeViewModel.
 * This class takes in the dependencies that are required for the HomeViewModel
 * and creates the ViewModel.
 */
public class HomeViewModelProviderFactory implements ViewModelProvider.Factory {

    private final ProductsRepository productsRepository;

    public HomeViewModelProviderFactory(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T)new HomeViewModel(productsRepository);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }
}
