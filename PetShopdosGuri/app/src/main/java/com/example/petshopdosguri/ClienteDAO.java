package com.example.petshopdosguri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void inserir(Context context, Cliente cliente){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("telefone", cliente.getTelefone());
        valores.put("email", cliente.getEmail());
        valores.put("nomePet", cliente.getNomePet());
        valores.put("promo", cliente.getPromo());
        valores.put("codTipoPet", cliente.getTipoPet().getId());

        db.insert("cliente", null, valores);

        db.close();
    }

    public static void editar(Context context, Cliente cliente){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("telefone", cliente.getTelefone());
        valores.put("email", cliente.getEmail());
        valores.put("nomePet", cliente.getNomePet());
        valores.put("promo", cliente.getPromo());
        valores.put("codTipoPet", cliente.getTipoPet().getId());

        db.update("cliente", valores, "id = " + cliente.getId(), null);

        db.close();
    }

    public static void excluir(Context context, int idCliente){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("cliente", "id = " + idCliente, null);

        db.close();
    }

    public static List<Cliente> getClientes(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                " SELECT c.id, c.nome, c.telefone, c.email, c.nomePet, c.promo, tp.id, tp.nome " +
                        "FROM cliente c " +
                        "INNER JOIN tipopet tp ON tp.id = c.codTipopet " +
                        "ORDER BY c.nome ", null);
        List<Cliente> lista = new ArrayList<>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                TipoPet tp = new TipoPet();
                tp.setId( cursor.getInt(6));
                tp.setNome( cursor.getString(7));

                Cliente c = new Cliente();
                c.setId( cursor.getInt(0));
                c.setNome( cursor.getString(1));
                c.setTelefone( cursor.getString(2));
                c.setEmail( cursor.getString(3));
                c.setNomePet( cursor.getString(4));
                c.setPromo( cursor.getString(5));
                c.setTipoPet(tp);

                lista.add(c);
            } while (cursor.moveToNext());
        }
        return lista;
    }
}
