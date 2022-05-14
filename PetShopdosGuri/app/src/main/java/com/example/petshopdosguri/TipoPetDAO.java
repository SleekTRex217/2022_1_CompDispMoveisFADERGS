package com.example.petshopdosguri;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TipoPetDAO {

    public static void inserir(Context context, String nome){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.execSQL( " INSERT INTO tipopet (nome) VALUES ( '" + nome + "')");
        db.close();
    }

    public static List<TipoPet> getTipoPet(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        Cursor cursor = db.rawQuery( " SELECT id, nome FROM tipopet ORDER BY nome", null);

        List<TipoPet> lista = new ArrayList<>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                TipoPet tp = new TipoPet();
                tp.setId( cursor.getInt(0));
                tp.setNome( cursor.getString(1));
                lista.add(tp);
            } while (cursor.moveToNext());
        }
        return lista;
    }
}
