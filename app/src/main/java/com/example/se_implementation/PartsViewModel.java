package com.example.se_implementation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PartsViewModel extends AndroidViewModel {
    protected PartsRepository repository;
    private LiveData<List<Part>> allParts;

     public PartsViewModel(@NonNull Application application) {
        super(application);
        repository = new PartsRepository(application);
        allParts = repository.getAllParts();
    }

    public void insert(Part part) {
         repository.insert(part);
    }
    public void update(Part part) {
        repository.update(part);
    }
    public void delete(Part part) {
        repository.delete(part);
    }
    public void deleteAllParts(){
         repository.deleteAllParts();
    }

    public LiveData<List<Part>> getAllParts() {
         return allParts;
    }
}
