package com.f112992.digitalgallery.database;


import android.content.ContentValues;

import androidx.annotation.Nullable;

import java.util.List;

public class SourceDBModel {

    @Nullable public int ID;
    public String name;
    public SourceDBModel(String name) {
        this.name = name;
    }

    public SourceDBModel(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public static String TABLE_NAME = "SOURCES";
    public static String SQL_TABLE_CREATION_STR =
            "CREATE TABLE SOURCES (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME TEXT NOT NULL);";

    public ContentValues generateContentValues() {
        ContentValues values = new ContentValues();
        values.put("NAME", this.name);

        return values;
    }
}
