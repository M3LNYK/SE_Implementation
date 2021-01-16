package com.example.se_implementation;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderDetailsPartsRepository {
    private OrderDetailsPartsDao orderDetailsPartsDao;
    private LiveData<List<OrderDetailsParts>> allParts;

    public OrderDetailsPartsRepository(Application application){
         OrderDetailsPartsDatabase database = OrderDetailsPartsDatabase.getInstance(application);
         orderDetailsPartsDao = database.orderDetailsPartsDao();
         allParts = orderDetailsPartsDao.getAllParts();
    }
    public void insert(OrderDetailsParts orderDetailsParts){
        new InsertPartAsyncTask(orderDetailsPartsDao).execute(orderDetailsParts);
    }
    public void update(OrderDetailsParts orderDetailsParts){
        new UpdatePartAsyncTask(orderDetailsPartsDao).execute(orderDetailsParts);
    }
    public void delete(OrderDetailsParts orderDetailsParts){
        new DeletePartAsyncTask(orderDetailsPartsDao).execute(orderDetailsParts);
    }
    public void deleteAllParts(){
        new DeleteAllPartAsyncTask(orderDetailsPartsDao).execute();
    }
    public LiveData<List<OrderDetailsParts>> getAllParts(){
        return allParts;
    }
    private static class InsertPartAsyncTask extends AsyncTask<OrderDetailsParts, Void, Void>{
        private OrderDetailsPartsDao orderDetailsPartsDao;

        private InsertPartAsyncTask(OrderDetailsPartsDao orderDetailsPartsDao){
            this.orderDetailsPartsDao = orderDetailsPartsDao;
        }

        @Override
        protected Void doInBackground(OrderDetailsParts... orderDetailsParts){
            orderDetailsPartsDao.insert(orderDetailsParts[0]);
            return null;
        }
    }

    private static class UpdatePartAsyncTask extends AsyncTask<OrderDetailsParts, Void, Void>{
        private OrderDetailsPartsDao orderDetailsPartsDao;

        private UpdatePartAsyncTask(OrderDetailsPartsDao orderDetailsPartsDao){
            this.orderDetailsPartsDao = orderDetailsPartsDao;
        }

        @Override
        protected Void doInBackground(OrderDetailsParts... orderDetailsParts){
            orderDetailsPartsDao.update( orderDetailsParts[0]);
            return null;
        }
    }

    private static class DeletePartAsyncTask extends AsyncTask<OrderDetailsParts, Void, Void>{
        private OrderDetailsPartsDao orderDetailsPartsDao;

        private DeletePartAsyncTask(OrderDetailsPartsDao orderDetailsPartsDao){
            this.orderDetailsPartsDao = orderDetailsPartsDao;
        }

        @Override
        protected Void doInBackground(OrderDetailsParts... orderDetailsParts){
            orderDetailsPartsDao.delete(orderDetailsParts[0]);
            return null;
        }
    }

    private static class DeleteAllPartAsyncTask extends AsyncTask<Void, Void, Void>{
        private OrderDetailsPartsDao orderDetailsPartsDao;

        private DeleteAllPartAsyncTask(OrderDetailsPartsDao orderDetailsPartsDao){
            this.orderDetailsPartsDao = orderDetailsPartsDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            orderDetailsPartsDao.deleteAllParts();
            return null;
        }
    }
}
