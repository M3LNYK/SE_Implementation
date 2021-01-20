package com.example.se_implementation;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Part.class}, version =  1)
public abstract class PartsDatabase extends RoomDatabase {

    private static PartsDatabase instance;

    public abstract PartsDao partsDao();

    public static synchronized PartsDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PartsDatabase.class, "parts_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

        private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private PartsDao partsDao;

        private PopulateDbAsyncTask(PartsDatabase db){
            partsDao = db.partsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            partsDao.insert(new Part("detail1", "category1", "producer1"));
            partsDao.insert(new Part("detail2", "category2", "producer2"));
            partsDao.insert(new Part("detail3", "category3", "producer3"));
            return null;
        }
    }
}
