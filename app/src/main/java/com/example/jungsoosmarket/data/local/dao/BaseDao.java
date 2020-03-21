package com.example.jungsoosmarket.data.local.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.Completable;

public interface BaseDao<T> {

    @Insert
    Completable insert(T... item);

    @Update
    Completable update(T... item);

    @Delete
    Completable delete(T... item);
}
