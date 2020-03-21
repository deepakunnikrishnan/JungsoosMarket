package com.example.jungsoosmarket.data;

import com.example.jungsoosmarket.data.local.entity.Product;
import com.example.jungsoosmarket.data.local.product.ProductsLocalDataSource;
import com.example.jungsoosmarket.data.remote.product.ProductsRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * The repository class for the Products.
 * The repository forms the primary layer for providing the data related to
 * the Product.
 * The repository class decides on which dataSource be used for each of the method.
 * If required, the repository can combine the dataSources and provide the data to the
 * subscribers.
 */
public class ProductsRepository implements IProductsDataSource {

    private final ProductsLocalDataSource localDataSource;
    private final ProductsRemoteDataSource remoteDataSource;

    public ProductsRepository(ProductsLocalDataSource localDataSource, ProductsRemoteDataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Completable insert(Product product) {
        return localDataSource.insert(product);
    }

    @Override
    public Completable insert(Product... products) {
        return localDataSource.insert(products);
    }

    @Override
    public Completable update(Product product) {
        return localDataSource.update(product);
    }

    @Override
    public Single<Product> getProduct(String productId) {
        return localDataSource.getProduct(productId);
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return localDataSource.getProducts();
    }


    @Override
    public Completable delete(Product product) {
        return localDataSource.delete(product);
    }

    @Override
    public Completable deleteAll() {
        return localDataSource.deleteAll();
    }
}
