package com.example.se_implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDetailsDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "order_details_database";
    public static final String ORDER_DETAILS_TABLE_NAME = "order_detail";
    public static final String PART_COLUMN_ID = "id";
    public static final String PART_COLUMN_NAME = "part";
    public static final String PART_COLUMN_CATEGORY = "category";
    public static final String PART_COLUMN_PRODUCER = "producer";
//    public static final String DEADLINE_COLUMN_DATE = "deadline";
//    public static final String PERSONAL_NOTE_COLUMN = "personal_note";
    public static final String WORKS_COLUMN_ID = "work_id";

    public OrderDetailsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ORDER_DETAILS_TABLE_NAME + " (" +
                PART_COLUMN_ID + " ID PRIMARY KEY AUTOINCREMENT, " +
                PART_COLUMN_NAME + " TEXT NOT NULL, " +
                PART_COLUMN_CATEGORY + " TEXT NOT NULL, " +
                PART_COLUMN_PRODUCER + " TEXT, " +
//                DEADLINE_COLUMN_DATE + " DATE, " +
//                PERSONAL_NOTE_COLUMN + "TEXT, " +
                WORKS_COLUMN_ID + " TEXT NOT NULL, " +
                "PRIMARY KEY (" + PART_COLUMN_ID + ")," +
                "FOREIGN KEY (" + WORKS_COLUMN_ID + ") REFERENCES " + ORDER_DETAILS_TABLE_NAME +
                " (" + PART_COLUMN_ID +
//                "), "
//               + "FOREIGN KEY (" + DEADLINE_COLUMN_DATE + ") REFERENCES " +
//                WORKS_COLUMN_ID + "," +
//                "FOREIGN KEY (" + PERSONAL_NOTE_COLUMN + ") REFERENCES " + WORKS_COLUMN_ID +
                "))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER_DETAILS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean addPart(String id, String part, String category, String producer, String rating){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PART_COLUMN_ID, id);
        contentValues.put(PART_COLUMN_NAME, part);
        contentValues.put(PART_COLUMN_CATEGORY, category);
        contentValues.put(PART_COLUMN_PRODUCER, producer);
//        contentValues.put(DEADLINE_COLUMN_DATE, deadline);
//        contentValues.put(PERSONAL_NOTE_COLUMN, personal_note);
//        contentValues.put(WORKS_COLUMN_ID, work_id);

        return sqLiteDatabase.insert(ORDER_DETAILS_TABLE_NAME, null, contentValues) != -1;
    }

    Cursor getAllParts() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + ORDER_DETAILS_TABLE_NAME, null);
    }

    Cursor getAllParts(String ownerId) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + ORDER_DETAILS_TABLE_NAME
                + " WHERE " + WORKS_COLUMN_ID + "=" + ownerId, null);
    }

    boolean updatePart(String id, String part, String category, String producer, String rating, String number_of_reviews, String deadline, String personal_note, String work_id) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PART_COLUMN_ID, id);
        contentValues.put(PART_COLUMN_NAME, part);
        contentValues.put(PART_COLUMN_CATEGORY, category);
        contentValues.put(PART_COLUMN_PRODUCER, producer);
//        contentValues.put(DEADLINE_COLUMN_DATE, deadline);
//        contentValues.put(PERSONAL_NOTE_COLUMN, personal_note);
        contentValues.put(WORKS_COLUMN_ID, work_id);
        return sqLiteDatabase.update(ORDER_DETAILS_TABLE_NAME, contentValues,
                PART_COLUMN_ID + "=?",
                new String[] { id }) == 1;
    }

    boolean deletePart(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(ORDER_DETAILS_TABLE_NAME,
                PART_COLUMN_ID + "=?" ,
                new String[] { id }) == 1;
    }
    void deleteAll() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER_DETAILS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
