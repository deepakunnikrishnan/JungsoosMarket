package com.example.jungsoosmarket.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.jungsoosmarket.data.local.entity.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ProductsDao extends BaseDao<Product> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Product... products);

    @Query("DELETE FROM products")
    Completable deleteAll();

    @Query("SELECT * from products ORDER BY name ASC")
    Flowable<List<Product>> getProducts();

    @Query("SELECT * from products WHERE id= :id")
    Single<Product> getProduct(String id);
}
