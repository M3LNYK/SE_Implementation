package com.example.se_implementation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PartsViewModel extends AndroidViewModel {
    protected PartsRepository repository;
    private LiveData<List<Parts>> allParts;

     public PartsViewModel(@NonNull Application application) {
        super(application);
        repository = new PartsRepository(application);
        allParts = repository.getAllParts();
    }

    public void insert(Parts parts) {
         repository.insert(parts);
    }
    public void update(Parts parts) {
        repository.update(parts);
    }
    public void delete(Parts parts) {
        repository.delete(parts);
    }
    public void deleteAllParts(){
         repository.deleteAllParts();
    }

    public LiveData<List<Parts>> getAllParts() {
         return allParts;
    }
}
