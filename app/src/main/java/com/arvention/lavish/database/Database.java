package com.arvention.lavish.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arvention.lavish.model.Feedback;
import com.arvention.lavish.model.Place;
import com.arvention.lavish.model.Toilet;

/**
 * Created by amcan on 3/9/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static Database database;
    private static final String db_name = "database.db";
    private static final int db_version = 1;

    // Tables
    private static final String place_table = "place";
    private static final String toilet_table = "toilet";
    private static final String feedback_table = "feedback";

    public static Database getInstance(Context context) {

        if (database == null) {
            database = new Database(context.getApplicationContext());
        }

        return database;
    }

    private Database(Context context) {

        super(context, db_name, null, db_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PLACE_TABLE = "CREATE TABLE " + place_table
                + "(placeID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "name TEXT NOT NULL, "
                + "xCoordinate REAL NOT NULL, "
                + "yCoordinate REAL NOT NULL, "
                + "openingHours TEXT NOT NULL)";
        db.execSQL(CREATE_PLACE_TABLE);

        String CREATE_TOILET_TABLE = "CREATE TABLE " + toilet_table
                + "(toiletID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "placeID INTEGER NOT NULL, "
                + "xCoordinate REAL NOT NULL, "
                + "yCoordinate REAL NOT NULL, "
                + "hasBidet INTEGER NOT NULL, "
                + "hasFlush INTEGER NOT NULL, "
                + "hasSoap INTEGER NOT NULL, "
                + "isFree INTEGER NOT NULL, "
                + "isPWDFriendly INTEGER NOT NULL, "
                + "cubicleCount INTEGER NOT NULL)";
        db.execSQL(CREATE_TOILET_TABLE);

        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + feedback_table
                + "(feedbackID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "toiletID INTEGER NOT NULL, "
                + "rating REAL NOT NULL, "
                + "content TEXT NOT NULL)";
        db.execSQL(CREATE_FEEDBACK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addPlace(Place place) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put("name", place.getName());
        val.put("xCoordinate", place.getxCoordinate());
        val.put("yCoordinate", place.getyCoordinate());
        val.put("openingHours", place.getOpeningHours());

        db.insert(place_table, null, val);
        db.close();

    }

    public void addToilet(Toilet toilet) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put("placeID", toilet.getPlaceID());
        val.put("xCoordinate", toilet.getxCoordinate());
        val.put("yCoordinate", toilet.getyCoordinate());
        val.put("hasBidet", toilet.isHasBidet());
        val.put("hasFlush", toilet.isHasFlush());
        val.put("hasSoap", toilet.isHasSoap());
        val.put("isFree", toilet.isFree());
        val.put("isPWDFriendly", toilet.isPWDFriendly());
        val.put("cubicleCount", toilet.getCubicleCount());

        db.insert(place_table, null, val);
        db.close();

    }

    public void addFeedback(Feedback feedback) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put("toiletID", feedback.getToiletID());
        val.put("rating", feedback.getRating());
        val.put("content", feedback.getContent());

        db.insert(place_table, null, val);
        db.close();

    }



}
