package com.example.se_implementation;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Order.class}, version = 1)
public abstract class OrderDatabase extends RoomDatabase {

    private static OrderDatabase instance;

    public abstract OrderDao orderDao();

    public static synchronized OrderDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OrderDatabase.class, "order_databse")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
