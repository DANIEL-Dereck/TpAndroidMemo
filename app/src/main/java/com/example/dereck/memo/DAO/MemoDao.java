package com.example.dereck.memo.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dereck.memo.Contrat.BaseContrat;
import com.example.dereck.memo.Entity.Memo;
import com.example.dereck.memo.Manager.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class MemoDao {

    public int insert(Memo memo)
    {
        int newId = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        if (memo != null) {
            ContentValues values = new ContentValues();
            values.put(BaseContrat.MemoContrat.COLUMN_TEXT, memo.getText());
//            values.put(BaseContrat.MemoContrat.COLUMN_STATE, memo.getState());

            newId = (int)db.insert(BaseContrat.MemoContrat.TABLE_MEMO, null, values);
        }
        DatabaseManager.getInstance().closeDatabase();
        return newId;
    }

    public void insert(List<Memo> memos)
    {
        for (Memo memo : memos)
        {
            this.insert(memo);
        }
    }

    public Memo selectById(int id)
    {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Memo memo = new Memo();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {BaseContrat.MemoContrat._ID, BaseContrat.MemoContrat.COLUMN_TEXT};

        // filtre (clause WHERE) :
        String selection = BaseContrat.MemoContrat._ID + " = ? ";
        String[] selectionArgs = {String.valueOf(id)};

        // requête :
        Cursor cursor = db.query(
                false, // true si SELECT DISTINCT
                BaseContrat.MemoContrat.TABLE_MEMO, // table sur laquelle faire la requète
                projection, // colonnes à retourner
                selection, // colonnes pour la clause WHERE
                selectionArgs, // valeurs pour la clause WHERE
                null, // GROUP BY (inactif)
                null, // HAVING (inactif)
                null, // ordre de tri
                null); // LIMIT (inactif)

        if (cursor != null)
        {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        memo = new Memo();
                        memo.setId(cursor.getInt(cursor.getColumnIndex(BaseContrat.MemoContrat._ID)))
                                .setText(cursor.getString(cursor.getColumnIndex(BaseContrat.MemoContrat.COLUMN_TEXT)));
                    } while (cursor.moveToNext());
                }
            } catch (Exception exception)
            {
                exception.printStackTrace();
            } finally
            {
            }
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return memo;
    }

    public List<Memo> selectAll()
    {
        List<Memo> memos = new ArrayList<>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {BaseContrat.MemoContrat._ID, BaseContrat.MemoContrat.COLUMN_TEXT};

        // requête :
        Cursor cursor = db.query(
                false, // true si SELECT DISTINCT
                BaseContrat.MemoContrat.TABLE_MEMO, // table sur laquelle faire la requète
                projection, // colonnes à retourner
                null, // colonnes pour la clause WHERE
                null, // valeurs pour la clause WHERE
                null, // GROUP BY (inactif)
                null, // HAVING (inactif)
                null, // ordre de tri
                null); // LIMIT (inactif)

        if (cursor != null)
        {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Memo memo = new Memo();
                        memo.setId(cursor.getInt(cursor.getColumnIndex(BaseContrat.MemoContrat._ID)))
                                .setText(cursor.getString(cursor.getColumnIndex(BaseContrat.MemoContrat.COLUMN_TEXT)));
                        memos.add(memo);
                    } while (cursor.moveToNext());
                }
            } catch (Exception exception)
            {
                exception.printStackTrace();
            } finally
            {
            }
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return memos;
    }

    public int countAll()
    {
        return selectAll().size();
    }

    public Boolean delete(Memo memo)
    {
        Boolean result = false;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "DELETE FROM " + BaseContrat.MemoContrat.TABLE_MEMO + " WHERE " +
                BaseContrat.MemoContrat._ID + " = " + memo.getId() + ";";
        db.execSQL(query);

        DatabaseManager.getInstance().closeDatabase();
        return result;
    }

    public Boolean deleteById(int id)
    {
        Boolean result = false;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "DELETE FROM " + BaseContrat.MemoContrat.TABLE_MEMO + " WHERE " +
                BaseContrat.MemoContrat._ID + " = " +id + ";";
        db.execSQL(query);

        DatabaseManager.getInstance().closeDatabase();
        return result;
    }

}
