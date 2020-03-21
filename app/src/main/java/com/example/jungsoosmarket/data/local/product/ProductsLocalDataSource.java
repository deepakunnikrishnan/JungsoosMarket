package com.example.jungsoosmarket.data.local.product;

import com.example.jungsoosmarket.data.IProductsDataSource;
import com.example.jungsoosmarket.data.local.dao.ProductsDao;
import com.example.jungsoosmarket.data.local.entity.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * The class represents the Local Data Source for data related to the products of the business.
 * In this app, the ProductsLocalDataSource provides data from the SQLite data storage
 * used by the app.
 * The ProductsLocalDataSource interacts with the SQLite Database using the DAO class provided
 * by the ROOM database.
 */

public class ProductsLocalDataSource implements IProductsDataSource {

    private final ProductsDao productsDao;

    public ProductsLocalDataSource(ProductsDao productsDao){
        this.productsDao = productsDao;
    }

    @Override
    public Completable insert(Product product) {
        return productsDao.insert(product);
    }

    @Override
    public Completable insert(Product... products) {
        return productsDao.insert(products);
    }

    @Override
    public Completable update(Product product) {
        return productsDao.update(product);
    }

    @Override
    public Single<Product> getProduct(String productId) {
        return productsDao.getProduct(productId);
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return productsDao.getProducts();
    }

    @Override
    public Completable delete(Product product) {
        return productsDao.delete(product);
    }

    @Override
    public Completable deleteAll() {
        return productsDao.deleteAll();
    }
}
