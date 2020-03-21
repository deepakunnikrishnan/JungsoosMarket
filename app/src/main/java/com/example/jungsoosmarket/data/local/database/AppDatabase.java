package com.example.jungsoosmarket.data.local.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jungsoosmarket.data.local.dao.BasketDao;
import com.example.jungsoosmarket.data.local.dao.ProductsDao;
import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.Product;

import io.reactivex.schedulers.Schedulers;

@Database(entities = {Product.class, BasketItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductsDao productsDao();
    public abstract BasketDao basketDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "app_database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        getInstance(context).productsDao().insert(PRODUCTS)
                                .subscribeOn(Schedulers.io())
                                .doOnError(throwable -> Log.i("AppDatabase","Insertion Failed "+throwable.getMessage()))
                                .subscribe();
                    }
                })
                .build();
    }

    /**
     * Dummy Data
     */
    public static final Product[] PRODUCTS = {
            new Product("0001", "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001",
                    "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/banana_1f34c.png",
                    "Banana","$1.00"),
            new Product("0002", "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0002",
                    "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/red-apple_1f34e.png",
                    "Apple","$4.00"),
            new Product("0003", "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0003",
                    "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/sparkles_2728.png",
                    "Other Stuff","$10.00")
    };

}
