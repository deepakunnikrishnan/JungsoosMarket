package com.example.jungsoosmarket.di.data;

import android.content.Context;

import com.example.jungsoosmarket.data.ProductsRepository;
import com.example.jungsoosmarket.data.local.dao.ProductsDao;
import com.example.jungsoosmarket.data.local.database.AppDatabase;
import com.example.jungsoosmarket.data.local.product.ProductsLocalDataSource;
import com.example.jungsoosmarket.data.remote.product.ProductsRemoteDataSource;

import static com.example.jungsoosmarket.di.data.DatabaseModule.provideAppDatabase;

/**
 * This class provides dependencies related to the Products module in the app.
 */
public class ProductsModule {

    public static ProductsRepository provideProductsRepository(Context context) {
        ProductsLocalDataSource productsLocalDataSource = provideProductsLocalDataSource(context);
        ProductsRemoteDataSource productsRemoteDataSource = provideProductsRemoteDataSource(context);
        return new ProductsRepository(productsLocalDataSource,productsRemoteDataSource);
    }

    public static ProductsRemoteDataSource provideProductsRemoteDataSource(Context context) {
        return new ProductsRemoteDataSource();
    }

    public static ProductsLocalDataSource provideProductsLocalDataSource(Context context) {
        ProductsDao productsDao = provideProductsDao(context);
        return new ProductsLocalDataSource(productsDao);
    }

    public static ProductsDao provideProductsDao(Context context) {
        AppDatabase database = provideAppDatabase(context);
        return database.productsDao();
    }
}
