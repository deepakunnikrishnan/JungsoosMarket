package com.example.jungsoosmarket.data;


import com.example.jungsoosmarket.data.local.entity.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * This interface represents the different methods for providing the ProductsData
 * to other layers.
 */
public interface IProductsDataSource {

     Completable insert(Product product);

     Completable insert(Product... products);

     Completable update(Product product);

     Single<Product> getProduct(String productId);

     Flowable<List<Product>> getProducts();

     Completable delete(Product product);

     Completable deleteAll();

}
