package com.example.jungsoosmarket.data.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class BasketItemWithProduct {

    @Embedded
    public BasketItem basketItem;

    @Relation(parentColumn = "productId",
        entityColumn = "id")
    public Product product;
}
