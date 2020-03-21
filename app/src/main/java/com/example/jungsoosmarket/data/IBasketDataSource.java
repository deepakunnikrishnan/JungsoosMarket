package com.example.jungsoosmarket.data;

import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * This interface represents the different methods for providing the BasketData
 * to other layers.
 */
public interface IBasketDataSource {

    Completable insert(BasketItem item);

    Completable insert(BasketItem... items);

    Completable update(BasketItem basketItem);

    Flowable<List<BasketItemWithProduct>> getBasket();

    Flowable<Float> getBasketTotal();

    Completable delete(BasketItem basketItem);

    Completable delete(BasketItem... basketItems);

}
