package com.example.se_implementation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderDetailsPartsViewModel extends AndroidViewModel {
    protected OrderDetailsPartsRepository repository;
    private LiveData<List<OrderDetailsParts>> allParts;

     public OrderDetailsPartsViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderDetailsPartsRepository(application);
        allParts = repository.getAllParts();
    }

    public void insert(OrderDetailsParts orderDetailsParts) {
         repository.insert(orderDetailsParts);
    }
    public void update(OrderDetailsParts orderDetailsParts) {
        repository.update(orderDetailsParts);
    }
    public void delete(OrderDetailsParts orderDetailsParts) {
        repository.delete(orderDetailsParts);
    }
    public void deleteAllParts(){
         repository.deleteAllParts();
    }

    public LiveData<List<OrderDetailsParts>> getAllParts() {
         return allParts;
    }
}
