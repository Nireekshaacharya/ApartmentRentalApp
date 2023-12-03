package com.example.ganapa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "apartment_database";
    private static final String TABLE_NAME = "apartment_table";
    private static final String COL_ID = "ID";
    private static final String COL_IMAGE = "IMAGE";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_NAME = "NAME";
    private static final String COL_ADDRESS = "ADDRESS";
    private static final String COL_LOCATION = "LOCATION";
    private static final String COL_DISTRICT = "DISTRICT";
    private static final String COL_ROOM = "ROOM";
    private static final String COL_RENT = "RENT";
    private static final String COL_ACCOMMODATION = "ACCOMMODATION";
    private static final String COL_DESCRIPTION = "DESCRIPTION";

    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_IMAGE + " BLOB, " +
                COL_EMAIL + " TEXT PRIMARY KEY, " +
                COL_NAME + " TEXT PRIMARY KEY, " +
                COL_ADDRESS + " TEXT, " +
                COL_LOCATION + " TEXT, " +
                COL_DISTRICT + " TEXT, " +
                COL_ROOM + " TEXT, " +
                COL_RENT + " TEXT, " +
                COL_ACCOMMODATION + " TEXT, " +
                COL_DESCRIPTION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    public boolean add_apartment(byte[] image, String email, String name, String address, String location, String district, String room, String rent, String accommodation, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_IMAGE, image);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_LOCATION, location);
        contentValues.put(COL_DISTRICT, district);
        contentValues.put(COL_ROOM, room);
        contentValues.put(COL_RENT, rent);
        contentValues.put(COL_ACCOMMODATION, accommodation);
        contentValues.put(COL_DESCRIPTION, description);


        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

}
