package com.example.jungsoosmarket.data;

import com.example.jungsoosmarket.data.local.basket.BasketLocalDataSource;
import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;
import com.example.jungsoosmarket.data.remote.basket.BasketRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * The repository class for the Basket.
 * The repository forms the primary layer for providing the data related to
 * the Baskets.
 * The repository class decides on which dataSource be used for each of the method.
 * If required, the repository can combine the datasources and provide the data to the
 * subscribers.
 */
public class BasketRepository implements IBasketDataSource {

    private final BasketLocalDataSource localDataSource;
    private final BasketRemoteDataSource remoteDataSource;

    public BasketRepository(BasketLocalDataSource localDataSource, BasketRemoteDataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Completable insert(BasketItem item) {
        return localDataSource.insert(item);
    }

    @Override
    public Completable insert(BasketItem... items) {
        return localDataSource.insert(items);
    }

    @Override
    public Completable update(BasketItem basketItem) {
        return localDataSource.update(basketItem);
    }

    @Override
    public Flowable<List<BasketItemWithProduct>> getBasket() {
        return localDataSource.getBasket();
    }

    @Override
    public Flowable<Float> getBasketTotal() {
        return localDataSource.getBasketTotal();
    }

    @Override
    public Completable delete(BasketItem basketItem) {
        return localDataSource.delete(basketItem);
    }

    @Override
    public Completable delete(BasketItem... basketItems) {
        return localDataSource.delete(basketItems);
    }
}
