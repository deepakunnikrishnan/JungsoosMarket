package com.example.jungsoosmarket.data.remote.product;

import com.example.jungsoosmarket.data.IProductsDataSource;
import com.example.jungsoosmarket.data.local.entity.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * This class represents the RemoteDataSource related to Product.
 * This class forms the dataSource through which app can receive the data from a remote agent.
 * Ideally your perform network operations related to the Products with the server.
 */
public class ProductsRemoteDataSource implements IProductsDataSource {

    /**
     * Empty constructor
     */
    public ProductsRemoteDataSource(){
        //Ideally the HTTP API dependencies need to be passed on
    }

    @Override
    public Completable insert(Product product) {
        return null;
    }

    @Override
    public Completable insert(Product... products) {
        return null;
    }

    @Override
    public Completable update(Product product) {
        return null;
    }

    @Override
    public Single<Product> getProduct(String productId) {
        return null;
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return null;
    }

    @Override
    public Completable delete(Product product) {
        return null;
    }

    @Override
    public Completable deleteAll() {
        return null;
    }
}
