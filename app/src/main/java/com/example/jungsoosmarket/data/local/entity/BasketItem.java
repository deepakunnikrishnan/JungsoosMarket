package com.example.jungsoosmarket.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.jungsoosmarket.data.local.DateConverter;



@Entity(tableName = "basket")
@TypeConverters(DateConverter.class)
public class BasketItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int basketItemId;

    @ForeignKey(entity = Product.class,
            parentColumns = "id",
            childColumns = "productId",
            onDelete = ForeignKey.CASCADE)
    @ColumnInfo
    private String productId;

    @ColumnInfo
    private int quantity;

    @ColumnInfo
    private String amount;

    public BasketItem() {
    }

    @Ignore
    public BasketItem(String productId, int quantity, String amount) {
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public int getBasketItemId() {
        return basketItemId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setBasketItemId(int basketItemId) {
        this.basketItemId = basketItemId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
