package com.f112992.digitalgallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.f112992.digitalgallery.database.ArtPieceDBModel;
import com.f112992.digitalgallery.database.SourceDBModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ArtDB.db";
    private static final int DB_VERSION = 1;

    public static SourceDBModel harvardSourceModel;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void config() {
        harvardSourceModel = getSource(1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SourceDBModel.SQL_TABLE_CREATION_STR);
        db.execSQL(ArtPieceDBModel.SQL_TABLE_CREATION_STR);

        insertSource(new SourceDBModel("Harvard Art Museum"));
        insertSource(new SourceDBModel("European Arts"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == newVersion) {
            return;
        }

        db.execSQL("DROP TABLE IF EXISTS " + ArtPieceDBModel.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SourceDBModel.TABLE_NAME);

        onCreate(db);
    }

    public SourceDBModel getSource(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SourceDBModel.TABLE_NAME + " WHERE ID == " + id;
        Cursor cursor = db.rawQuery(query, null);
        SourceDBModel source = null;
        if (cursor.moveToFirst()) {
            source = new SourceDBModel(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
        }
        db.close();
        return source;
    }

    public List<SourceDBModel> getAllSources() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SourceDBModel.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<SourceDBModel> sources = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                sources.add(new SourceDBModel(
                        cursor.getInt(0),
                        cursor.getString(1)
                ));
            }
            while (cursor.moveToNext());
        }
        db.close();
        return sources;
    }
    public ArtPieceDBModel getArtPiece(String objID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ArtPieceDBModel.TABLE_NAME + " WHERE ID == " + objID;
        Cursor cursor = db.rawQuery(query, null);
        ArtPieceDBModel source = null;
        if (cursor.moveToFirst()) {
            source = new ArtPieceDBModel(cursor);
        }
        db.close();
        return source;
    }

    public ArtPieceDBModel getDailyArtPiece() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ArtPieceDBModel.TABLE_NAME + " WHERE DATE_ADDED='" + new Date(System.currentTimeMillis()) + "'";
        Cursor cursor = db.rawQuery(query, null);
        ArtPieceDBModel source = null;
        if (cursor.moveToFirst()) {
            source = new ArtPieceDBModel(cursor);
        }
        db.close();
        return source;
    }

    public List<ArtPieceDBModel> getAllArtPieces() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ArtPieceDBModel.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<ArtPieceDBModel> artPieces = new ArrayList<>();
        if (cursor.moveToFirst()) {

            do {
                artPieces.add(new ArtPieceDBModel(cursor));
            }
            while (cursor.moveToNext());
        }
        db.close();
        return artPieces;
    }
    public long insertSource(SourceDBModel entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = entry.generateContentValues();

        long insertedID = db.insert(SourceDBModel.TABLE_NAME, null, values);
        db.close();
        return insertedID;
    }

    public long insertArtPiece(ArtPieceDBModel entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = entry.generateContentValues();

        long insertedID = db.insert(ArtPieceDBModel.TABLE_NAME, null, values);
        db.close();
        return insertedID;
    }

}
