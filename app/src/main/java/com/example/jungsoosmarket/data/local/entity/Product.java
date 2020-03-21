package com.example.jungsoosmarket.data.local.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String id;

    @ColumnInfo(name="qrUrl")
    private String qrUrl;

    @ColumnInfo(name="thumbnail")
    private String thumbnail;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="price")
    private String price;


    public Product(@NonNull String id, String qrUrl, String thumbnail, String name, String price) {
        this.id = id;
        this.qrUrl = qrUrl;
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
