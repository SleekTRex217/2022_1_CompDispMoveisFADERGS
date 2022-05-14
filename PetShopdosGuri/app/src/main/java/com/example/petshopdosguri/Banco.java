package com.example.petshopdosguri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "petshop";

    public Banco (Context context) {
        super(context, NOME, null, VERSAO);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL( " CREATE TABLE IF NOT EXISTS tipopet ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " nome TEXT NOT NULL );"
        );

        db.execSQL( " CREATE TABLE IF NOT EXISTS cliente ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nome TEXT NOT NULL , " +
                " telefone TEXT NOT NULL ," +
                " nomePet TEXT NOT NULL , " +
                " email TEXT , promo TEXT ," +
                " codTipoPet INTEGER ); "
        );
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}
