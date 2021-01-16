package com.example.se_implementation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {OrderDetailsParts.class}, version =  1)
public abstract class OrderDetailsPartsDatabase extends RoomDatabase {

    private static OrderDetailsPartsDatabase instance;

    public abstract OrderDetailsPartsDao orderDetailsPartsDao();

    public static OrderDetailsPartsDatabase getInstance(Context context) {
        if(instance == null){
             instance = Room.databaseBuilder(context.getApplicationContext(),
                     OrderDetailsPartsDatabase.class, "parts_database")
                     .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private OrderDetailsPartsDao orderDetailsPartsDao;

        private PopulateDbAsyncTask(OrderDetailsPartsDatabase db){
            orderDetailsPartsDao = db.orderDetailsPartsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDetailsPartsDao.insert(new OrderDetailsParts("detail", "category", "producer"));
            return null;
        }
    }
}
