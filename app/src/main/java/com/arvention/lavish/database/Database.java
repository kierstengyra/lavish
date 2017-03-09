package com.arvention.lavish.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arvention.lavish.model.Feedback;
import com.arvention.lavish.model.Toilet;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by amcan on 3/9/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static Database database;
    private static final String db_name = "database.db";
    private static final int db_version = 1;

    private final int TRUE = 1;
    private final int FALSE = 0;

    // Tables
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

        String CREATE_TOILET_TABLE = "CREATE TABLE " + toilet_table
                + "(toiletID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "name TEXT NOT NULL, "
                + "placeID INTEGER NOT NULL, "
                + "xCoordinate REAL NOT NULL, "
                + "yCoordinate REAL NOT NULL, "
                + "hasBidet INTEGER NOT NULL, "
                + "hasFlush INTEGER NOT NULL, "
                + "hasSoap INTEGER NOT NULL, "
                + "isFree INTEGER NOT NULL, "
                + "isPWDFriendly INTEGER NOT NULL, "
                + "cubicleCount INTEGER NOT NULL, "
                + "openingHours TEXT NOT NULL)";
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

    public void addToilet(Toilet toilet) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put("name", toilet.getName());
        val.put("xCoordinate", toilet.getxCoordinate());
        val.put("yCoordinate", toilet.getyCoordinate());
        val.put("hasBidet", toilet.isHasBidet());
        val.put("hasFlush", toilet.isHasFlush());
        val.put("hasSoap", toilet.isHasSoap());
        val.put("isFree", toilet.isFree());
        val.put("isPWDFriendly", toilet.isPWDFriendly());
        val.put("cubicleCount", toilet.getCubicleCount());
        val.put("openingHours", toilet.getOpeningHours());

        db.insert(toilet_table, null, val);
        db.close();

    }

    public void addFeedback(Feedback feedback) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put("toiletID", feedback.getToiletID());
        val.put("rating", feedback.getRating());
        val.put("content", feedback.getContent());

        db.insert(feedback_table, null, val);
        db.close();

    }

    public ArrayList<Toilet> getAllToilets() {

        ArrayList<Toilet> toilets = new ArrayList<Toilet>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(toilet_table,      // table name
                null,                               // columns
                null,                               // selection
                null,                               // selection args
                null,                               // groupBy
                null,                               // having
                null);                              // orderBy

        if(cursor.moveToFirst()) {

            while(!cursor.isAfterLast()) {

                int toiletID = cursor.getInt(cursor.getColumnIndex("toiletID"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int placeID = cursor.getInt(cursor.getColumnIndex("placeID"));
                double xCoordinate = cursor.getDouble(cursor.getColumnIndex("xCoordinate"));
                double yCoordinate = cursor.getDouble(cursor.getColumnIndex("yCoordinate"));

                boolean hasBidet = false;
                if(cursor.getInt(cursor.getColumnIndex("hasBidet")) == TRUE)
                    hasBidet = true;

                boolean hasFlush = false;
                if(cursor.getInt(cursor.getColumnIndex("hasFlush")) == TRUE)
                    hasFlush = true;

                boolean hasSoap = false;
                if(cursor.getInt(cursor.getColumnIndex("hasSoap")) == TRUE)
                    hasSoap = true;

                boolean isFree = false;
                if(cursor.getInt(cursor.getColumnIndex("isFree")) == TRUE)
                    isFree = true;

                boolean isPWDFriendly = false;
                if(cursor.getInt(cursor.getColumnIndex("isPWDFriendly")) == TRUE)
                    isPWDFriendly = true;

                int cubicleCount = cursor.getInt(cursor.getColumnIndex("cubicleCount"));
                String openingHours = cursor.getString(cursor.getColumnIndex("openingHours"));
                toilets.add(new Toilet(toiletID, name, xCoordinate, yCoordinate, hasBidet,
                        hasFlush, hasSoap, isFree, isPWDFriendly, cubicleCount, openingHours));

                cursor.moveToNext();

            }

        }

        return toilets;

    }

    public ArrayList<Feedback> getAllFeedbacks() {

        ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(feedback_table,      // table name
                null,                               // columns
                null,                               // selection
                null,                               // selection args
                null,                               // groupBy
                null,                               // having
                null);                              // orderBy

        if(cursor.moveToFirst()) {

            while(!cursor.isAfterLast()) {

                int feedbackID = cursor.getInt(cursor.getColumnIndex("feedbackID"));
                int toiletID = cursor.getInt(cursor.getColumnIndex("toiletID"));
                float rating = cursor.getFloat(cursor.getColumnIndex("rating"));
                String content = cursor.getString(cursor.getColumnIndex("content"));

                feedbacks.add(new Feedback(feedbackID, toiletID, rating, content));

                cursor.moveToNext();

            }

        }

        return feedbacks;

    }

    public Toilet getToiletByID(int argToiletID) {

        Toilet toilet = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(toilet_table,                  // table name
                null,                                           // columns
                "toiletID = ?",                                 // selection
                new String[]{Integer.toString(argToiletID)},    // selection args
                null,                                           // groupBy
                null,                                           // having
                null);

        if(cursor.moveToFirst()) {

            int toiletID = cursor.getInt(cursor.getColumnIndex("toiletID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double xCoordinate = cursor.getDouble(cursor.getColumnIndex("xCoordinate"));
            double yCoordinate = cursor.getDouble(cursor.getColumnIndex("yCoordinate"));

            boolean hasBidet = false;
            if(cursor.getInt(cursor.getColumnIndex("hasBidet")) == TRUE)
                hasBidet = true;

            boolean hasFlush = false;
            if(cursor.getInt(cursor.getColumnIndex("hasFlush")) == TRUE)
                hasFlush = true;

            boolean hasSoap = false;
            if(cursor.getInt(cursor.getColumnIndex("hasSoap")) == TRUE)
                hasSoap = true;

            boolean isFree = false;
            if(cursor.getInt(cursor.getColumnIndex("isFree")) == TRUE)
                isFree = true;

            boolean isPWDFriendly = false;
            if(cursor.getInt(cursor.getColumnIndex("isPWDFriendly")) == TRUE)
                isPWDFriendly = true;

            int cubicleCount = cursor.getInt(cursor.getColumnIndex("cubicleCount"));
            String openingHours = cursor.getString(cursor.getColumnIndex("openingHours"));

            toilet = new Toilet(toiletID, name, xCoordinate, yCoordinate, hasBidet,
                    hasFlush, hasSoap, isFree, isPWDFriendly, cubicleCount, openingHours);

        }

        return toilet;

    }

    public Feedback getFeedbackByID(int argFeedbackID) {

        Feedback feedback = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(feedback_table,                // table name
                null,                                           // columns
                "feedbackID = ?",                               // selection
                new String[]{Integer.toString(argFeedbackID)},  // selection args
                null,                                           // groupBy
                null,                                           // having
                null);

        if(cursor.moveToFirst()) {

            int feedbackID = cursor.getInt(cursor.getColumnIndex("feedbackID"));
            int toiletID = cursor.getInt(cursor.getColumnIndex("toiletID"));
            float rating = cursor.getFloat(cursor.getColumnIndex("rating"));
            String content = cursor.getString(cursor.getColumnIndex("content"));

            feedback = new Feedback(feedbackID, toiletID, rating, content);

        }

        return feedback;

    }

    public ArrayList<Toilet> getToiletWithBidet() {
        return null;
    }

    public ArrayList<Toilet> getToiletWithFlush() {
        return null;
    }

    public ArrayList<Toilet> getToiletWithSoap() {
        return null;
    }

    public ArrayList<Toilet> getToiletWithFree() {
        return null;
    }

    public ArrayList<Toilet> getToiletWithPWD() {
        return null;
    }

    public ArrayList<Toilet> getToiletWithCubicleCount() {
        return null;
    }

}
