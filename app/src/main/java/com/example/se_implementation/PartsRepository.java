package com.example.se_implementation;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PartsRepository {
    private PartsDao partsDao;
    private LiveData<List<Parts>> allParts;

    public PartsRepository(Application application){
         PartsDatabase database = PartsDatabase.getInstance(application);
         partsDao = database.orderDetailsPartsDao();
         allParts = partsDao.getAllParts();
    }
    public void insert(Parts parts){
        new InsertPartAsyncTask(partsDao).execute(parts);
    }
    public void update(Parts parts){
        new UpdatePartAsyncTask(partsDao).execute(parts);
    }
    public void delete(Parts parts){
        new DeletePartAsyncTask(partsDao).execute(parts);
    }
    public void deleteAllParts(){
        new DeleteAllPartAsyncTask(partsDao).execute();
    }
    public LiveData<List<Parts>> getAllParts(){
        return allParts;
    }
    private static class InsertPartAsyncTask extends AsyncTask<Parts, Void, Void>{
        private PartsDao partsDao;

        private InsertPartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }

        @Override
        protected Void doInBackground(Parts... parts){
            partsDao.insert(parts[0]);
            return null;
        }
    }

    private static class UpdatePartAsyncTask extends AsyncTask<Parts, Void, Void>{
        private PartsDao partsDao;

        private UpdatePartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }

        @Override
        protected Void doInBackground(Parts... parts){
            partsDao.update( parts[0]);
            return null;
        }
    }

    private static class DeletePartAsyncTask extends AsyncTask<Parts, Void, Void>{
        private PartsDao partsDao;

        private DeletePartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }

        @Override
        protected Void doInBackground(Parts... parts){
            partsDao.delete(parts[0]);
            return null;
        }
    }

    private static class DeleteAllPartAsyncTask extends AsyncTask<Void, Void, Void>{
        private PartsDao partsDao;

        private DeleteAllPartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            partsDao.deleteAllParts();
            return null;
        }
    }
}
