package com.example.se_implementation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Order.class}, version = 1)
public abstract class OrderDatabase extends RoomDatabase {

    private static OrderDatabase instance;

    public abstract OrderDao orderDao();

    public static synchronized OrderDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OrderDatabase.class, "order_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDao orderDao;

        private PopulateDBAsyncTask(OrderDatabase db) {
            orderDao = db.orderDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDao.insert(new Order("BMW", "3 series",
                    "Destroyed rear tires"));
            orderDao.insert((new Order("Opel", "Astra Diesel",
                    "Does not start")));
            return null;
        }
    }
}
