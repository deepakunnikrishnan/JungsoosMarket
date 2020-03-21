package com.example.jungsoosmarket.di.data;

import android.content.Context;

import com.example.jungsoosmarket.data.BasketRepository;
import com.example.jungsoosmarket.data.local.basket.BasketLocalDataSource;
import com.example.jungsoosmarket.data.local.dao.BasketDao;
import com.example.jungsoosmarket.data.local.database.AppDatabase;
import com.example.jungsoosmarket.data.remote.basket.BasketRemoteDataSource;

import static com.example.jungsoosmarket.di.data.DatabaseModule.provideAppDatabase;

/**
 * Class provides the dependencies related to the BasketModule in the project.
 */
public class BasketModule {

    public static BasketRepository provideBasketRepository(Context context) {
        BasketLocalDataSource basketLocalDataSource = provideBasketLocalDataSource(context);
        BasketRemoteDataSource basketRemoteDataSource = provideBasketRemoteDataSource(context);
        return new BasketRepository(basketLocalDataSource,basketRemoteDataSource);
    }

    public static BasketRemoteDataSource provideBasketRemoteDataSource(Context context) {
        return new BasketRemoteDataSource();
    }

    public static BasketLocalDataSource provideBasketLocalDataSource(Context context) {
        BasketDao basketDao = provideBasketDao(context);
        return new BasketLocalDataSource(basketDao);
    }

    public static BasketDao provideBasketDao(Context context) {
        AppDatabase database = provideAppDatabase(context);
        return database.basketDao();
    }
}
