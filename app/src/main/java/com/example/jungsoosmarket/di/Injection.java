package com.example.jungsoosmarket.di;

import android.content.Context;

import com.example.jungsoosmarket.ui.addtobasket.AddToBasketViewModelProviderFactory;
import com.example.jungsoosmarket.ui.basket.BasketViewModelProviderFactory;
import com.example.jungsoosmarket.ui.home.HomeViewModelProviderFactory;

import static com.example.jungsoosmarket.di.data.BasketModule.provideBasketRepository;
import static com.example.jungsoosmarket.di.data.ProductsModule.provideProductsRepository;

/**
 * This class provides the dependencies in the app.
 */
public class Injection {

    public static BasketViewModelProviderFactory provideBasketViewModelFactory(Context context){
        return new BasketViewModelProviderFactory(provideProductsRepository(context),
                provideBasketRepository(context));
    }

    public static AddToBasketViewModelProviderFactory provideAddToBasketViewModelProviderFactory(Context context){
        return new AddToBasketViewModelProviderFactory(provideProductsRepository(context),
                provideBasketRepository(context));
    }

    public static HomeViewModelProviderFactory provideHomeViewModelProviderFactory(Context context){
        return new HomeViewModelProviderFactory(provideProductsRepository(context));
    }
}
