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
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDao orderDao;

        private PopulateDBAsyncTask(OrderDatabase db) {
            orderDao = db.orderDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDao.insert(new Order("Car Brand Example", "Car Model Example",
                    "Description Example"));
            orderDao.insert((new Order("Opel", "Astra Diesel",
                    "Does not start")));
            orderDao.insert(new Order("3rd Brand", "3rd model", "3rd Descr."));
            return null;
        }
    }
}
