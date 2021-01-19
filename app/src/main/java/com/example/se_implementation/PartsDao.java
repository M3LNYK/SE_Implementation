package com.example.se_implementation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PartsDao {

    @Insert
    void insert(Parts parts);

    @Update
    void update(Parts parts);

    @Delete
    void delete(Parts parts);

    @Query("DELETE FROM PARTS_TABLE")
    void deleteAllParts();

    @Query("SELECT * FROM PARTS_TABLE ORDER BY category")
    LiveData<List<Parts>>  getAllParts() ;
}
