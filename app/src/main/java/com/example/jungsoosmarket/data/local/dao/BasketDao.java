package com.example.jungsoosmarket.data.local.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BasketDao extends BaseDao<BasketItem> {

    @Query("DELETE FROM basket")
    Completable deleteAll();

    @Query("SELECT * FROM basket")
    Flowable<List<BasketItemWithProduct>> getBasket();

    @Query("SELECT COALESCE(sum(CAST(substr(amount,2) AS REAL)),0.0)from basket")
    Flowable<Float> getBasketTotal();
}
