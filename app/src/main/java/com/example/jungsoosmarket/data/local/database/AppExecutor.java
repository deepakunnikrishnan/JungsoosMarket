package com.example.jungsoosmarket.data.local.database;

import java.util.concurrent.ExecutorService;

public class AppExecutor {

    private static final ExecutorService IO_EXECUTOR = java.util.concurrent.Executors.newSingleThreadExecutor();

    public static void execute(Runnable runnable){
        IO_EXECUTOR.submit(runnable);
    }
}
