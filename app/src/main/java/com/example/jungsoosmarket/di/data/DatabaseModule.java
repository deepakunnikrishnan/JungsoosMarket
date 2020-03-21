package com.example.jungsoosmarket.di.data;

import android.content.Context;

import com.example.jungsoosmarket.data.local.database.AppDatabase;

/**
 * Class provides dependencies of the Database used in the app.
 */
public class DatabaseModule {

    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context);
    }
}
