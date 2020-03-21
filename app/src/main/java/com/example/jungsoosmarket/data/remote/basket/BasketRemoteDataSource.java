package com.example.jungsoosmarket.data.remote.basket;

import com.example.jungsoosmarket.data.IBasketDataSource;
import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * This class represents the RemoteDataSource related to Basket.
 * This class forms the dataSource through which app can receive the data from a remote agent.
 * Ideally your perform network operations related to the Basket with the server.
 */
public class BasketRemoteDataSource implements IBasketDataSource {

    @Override
    public Completable insert(BasketItem item) {
        return null;
    }

    @Override
    public Completable insert(BasketItem... items) {
        return null;
    }

    @Override
    public Completable update(BasketItem basketItem) {
        return null;
    }

    @Override
    public Flowable<List<BasketItemWithProduct>> getBasket() {
        return null;
    }

    @Override
    public Flowable<Float> getBasketTotal() {
        return null;
    }

    @Override
    public Completable delete(BasketItem basketItem) {
        return null;
    }

    @Override
    public Completable delete(BasketItem... basketItems) {
        return null;
    }
}
