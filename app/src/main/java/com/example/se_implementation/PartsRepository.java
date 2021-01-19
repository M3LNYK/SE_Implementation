package com.example.se_implementation;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PartsRepository {
    private PartsDao partsDao;
    private LiveData<List<Part>> allParts;

    public PartsRepository(Application application){
         PartsDatabase database = PartsDatabase.getInstance(application);
         partsDao = database.partsDao();
         allParts = partsDao.getAllParts();
    }
    public void insert(Part part){
        new InsertPartAsyncTask(partsDao).execute(part);
    }
    public void update(Part part){
        new UpdatePartAsyncTask(partsDao).execute(part);
    }
    public void delete(Part part){
        new DeletePartAsyncTask(partsDao).execute(part);
    }
    public void deleteAllParts(){
        new DeleteAllPartAsyncTask(partsDao).execute();
    }
    public LiveData<List<Part>> getAllParts(){
        return allParts;
    }

    private static class InsertPartAsyncTask extends AsyncTask<Part, Void, Void>{
        private PartsDao partsDao;

        private InsertPartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }
 
        @Override
        protected Void doInBackground(Part... parts){
            partsDao.insert(parts[0]);
            return null;
        }
    }

    private static class UpdatePartAsyncTask extends AsyncTask<Part, Void, Void>{
        private PartsDao partsDao;

        private UpdatePartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }

        @Override
        protected Void doInBackground(Part... parts){
            partsDao.update( parts[0]);
            return null;
        }
    }

    private static class DeletePartAsyncTask extends AsyncTask<Part, Void, Void>{
        private PartsDao partsDao;

        private DeletePartAsyncTask(PartsDao partsDao){
            this.partsDao = partsDao;
        }

        @Override
        protected Void doInBackground(Part... parts){
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
