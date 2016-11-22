package ru.nsu.fit.lobkov.alias;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lobkov on 22.11.2016.
 */

public class SQLDataGetter implements DataGetter {
    private SQLiteOpenHelper dbHelper;
    private Cursor cursor;
    public SQLDataGetter(Context ctx) {
        dbHelper = new DictionaryHelper(ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DictionaryHelper.ID,
                DictionaryHelper.WORD
        };

        String sortOrder = DictionaryHelper.WORD + " DESC";

        cursor = db.query(
                DictionaryHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        cursor.moveToFirst();
    }
    @Override
    public String getNextWord() {
        if (!cursor.moveToNext()) {
            cursor.moveToFirst();
        }
        String word = cursor.getString(cursor.getColumnIndex(DictionaryHelper.WORD));
        return word;
    }

    @Override
    public String getCurrentWord() {
        return cursor.getString(cursor.getColumnIndex(DictionaryHelper.WORD));
    }

    private class DictionaryHelper extends SQLiteOpenHelper {
        public static final String TABLE_NAME = "easy_english";
        public static final String ID = "id";
        public static final String WORD = "word";
        public DictionaryHelper(Context ctx) {
            super(ctx, "dictionary.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
                    ID + " integer primary key autoincrement, " +
                    WORD + " text" +
                    ");");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + WORD + ") VALUES " +
                    "('bottle')," +
                    "('mouse')," +
                    "('notebook')," +
                    "('windows');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
