package com.f112992.digitalgallery.database;

import android.content.ContentValues;

import com.f112992.digitalgallery.ArtData;

import java.sql.Date;
import java.util.List;

public class ArtPieceDBModel {

    public int ID;
    public String externalID;
    public String title;
    public int sourceID;
    public Date dateAdded;
    public int rating;
    public boolean isDaily;

    public ArtPieceDBModel(ArtData data) {
        this.ID = data.ID;
        this.externalID = data.externalID;
        this.title = data.title;
        this.sourceID = data.sourceID;
        this.dateAdded = data.dateAdded;
        this.rating = data.rating;
        this.isDaily = data.isDaily;
    }

    public ArtPieceDBModel(int ID, String externalID, String title, int sourceID, Date dateAdded, int rating, boolean isDaily) {
        this.ID = ID;
        this.externalID = externalID;
        this.title = title;
        this.sourceID = sourceID;
        this.dateAdded = dateAdded;
        this.rating = rating;
        this.isDaily = isDaily;
    }

    public static String TABLE_NAME = "ART_PIECES";
    public static String SQL_TABLE_CREATION_STR =
            "CREATE TABLE ART_PIECES (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "EXTERNAL_ID INTEGER, " +
                    "TITLE TEXT NOT NULL, " +
                    "SOURCE_ID INTEGER, " +
                    "DATE_ADDED TEXT NOT NULL, " +
                    "RATING INTEGER, " +
                    "IS_DAILY INTEGER, " +
                    "FOREIGN KEY(SOURCE_ID) REFERENCES SOURCES(ID))";


    public ContentValues generateContentValues() {
        ContentValues values = new ContentValues();
        values.put("EXTERNAL_ID", this.externalID);
        values.put("TITLE", this.title);
        values.put("SOURCE_ID", this.sourceID);
        values.put("DATE_ADDED", this.dateAdded.toString());
        values.put("RATING", this.rating);
        values.put("IS_DAILY", this.isDaily);

        return values;
    }
}
