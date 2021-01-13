package com.example.se_implementation;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

//    @ColumnInfo(name = "Car Brand")
    private String carBrand;

    @ColumnInfo(name = "Car Name")
    private String carModel;

    private String description;

    public Order(String carBrand, String carModel, String description) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getDescription() {
        return description;
    }
}
