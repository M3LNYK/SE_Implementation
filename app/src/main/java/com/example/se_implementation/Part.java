package com.example.se_implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "parts_table")
public class Part {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String part_name;
    private String category;
    private String producer;

    public Part(String part_name, String category, String producer) {
        this.part_name = part_name;
        this.category = category;
        this.producer = producer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPart_name() {
        return part_name;
    }

    public String getCategory() {
        return category;
    }

    public String getProducer() {
        return producer;
    }
}
