package com.example.jungsoosmarket.ui.home;


import androidx.lifecycle.ViewModel;

import com.example.jungsoosmarket.data.ProductsRepository;


public class HomeViewModel extends ViewModel {

    private final ProductsRepository productsRepository;

    public HomeViewModel(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }
}
