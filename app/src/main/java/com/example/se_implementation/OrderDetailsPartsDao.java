package com.example.se_implementation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDetailsPartsDao {

    @Insert
    void insert(OrderDetailsParts orderDetailsParts);

    @Update
    void update(OrderDetailsParts orderDetailsParts);

    @Delete
    void delete(OrderDetailsParts orderDetailsParts);

    @Query("DELETE FROM parts_table")
    void deleteAllParts();

    @Query("SELECT * FROM parts_table ORDER BY category")
    LiveData<List<OrderDetailsParts>>  getAllParts() ;
}
