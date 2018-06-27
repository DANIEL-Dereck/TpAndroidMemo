package com.example.dereck.memo.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dereck.memo.Contrat.BaseContrat;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String LOGTAG = this.getClass().toString();

    public DatabaseHelper(Context context)
    {
        super(context, BaseContrat.DATABASE_NAME, null, BaseContrat.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOGTAG, "Create Tables");
        db.execSQL(BaseContrat.MemoContrat.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(LOGTAG, "Update Tables");
        db.execSQL(BaseContrat.MemoContrat.DROP_TABLE);
        onCreate(db);
    }
}
