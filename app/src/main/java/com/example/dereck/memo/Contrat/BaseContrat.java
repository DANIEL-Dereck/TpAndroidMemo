package com.example.dereck.memo.Contrat;

import android.provider.BaseColumns;

public final class BaseContrat
{
    public static final String DATABASE_NAME = "memo.db";
    public static final Integer VERSION = 1;
    public static final String CREATE = "CREATE TABLE ";
    public static final String DROP = "DROP TABLE IF EXISTS ";

    // constructeur privé afin de ne pas instancier la classe :
    private BaseContrat() {}
    // contenu de la table "courses" :
    public static class MemoContrat implements BaseColumns
    {
        //Table
        public static final String TABLE_MEMO = "memo";

        //Columns
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_STATE = "state";

        //Create and delete

        public static final String CREATE_TABLE = BaseContrat.CREATE + TABLE_MEMO + " ( "
                        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                        + COLUMN_TEXT + " TEXT "
//                        + " , " + COLUMN_STATE + " BOOL "
                        + ");";

        public static final String DROP_TABLE = BaseContrat.DROP + TABLE_MEMO + ";";


    }
}


