package com.example.se_implementation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {OrderDetailsParts.class}, version =  1)
public abstract class OrderDetailsPartsDatabase extends RoomDatabase {

    private static OrderDetailsPartsDatabase instance;

    public abstract OrderDetailsPartsDao orderDetailsPartsDao();

    public static OrderDetailsPartsDatabase getInstance(Context context) {
        if(instance == null){
             instance = Room.databaseBuilder(context.getApplicationContext(),
                     OrderDetailsPartsDatabase.class, "parts_database")
                     .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
