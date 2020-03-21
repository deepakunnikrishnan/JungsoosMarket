package com.example.jungsoosmarket.data.local.basket;

import com.example.jungsoosmarket.data.IBasketDataSource;
import com.example.jungsoosmarket.data.local.dao.BasketDao;
import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * The class represents the Local Data Source for data related to the basket
 * of the user.
 * In this app, the BasketLocalDataSource provides data from the SQLite data storage
 * used by the app.
 *
 * The BasketLocalDataSource interacts with the SQLite Database using the DAO class provided
 * by the ROOM database.
 */
public class BasketLocalDataSource implements IBasketDataSource {

    private final BasketDao basketDao;
    public BasketLocalDataSource(BasketDao basketDao){
        this.basketDao = basketDao;
    }

    @Override
    public Completable insert(BasketItem item) {
        return basketDao.insert(item);
    }

    @Override
    public Completable insert(BasketItem... items) {
        return basketDao.insert(items);
    }

    @Override
    public Completable update(BasketItem basketItem) {
        return basketDao.update(basketItem);
    }

    @Override
    public Flowable<List<BasketItemWithProduct>> getBasket() {
        return basketDao.getBasket();
    }

    @Override
    public Flowable<Float> getBasketTotal() {
        return basketDao.getBasketTotal();
    }

    @Override
    public Completable delete(BasketItem basketItem) {
        return basketDao.delete(basketItem);
    }

    @Override
    public Completable delete(BasketItem... basketItems) {
        return basketDao.delete(basketItems);
    }
}
