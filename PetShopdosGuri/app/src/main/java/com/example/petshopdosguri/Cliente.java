package com.example.petshopdosguri;

import android.telecom.StatusHints;

public class Cliente {

    public int id;
    public String nome, telefone, email, nomePet, promo;
    public TipoPet tipoPet;

    public Cliente(){
    }

    public Cliente(String nome, String telefone, String email, String nomePet, String promo, TipoPet tipoPet){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.nomePet = nomePet;
        this.promo = promo;
        this.tipoPet = tipoPet;
    }

    public Cliente(int id, String nome, String telefone, String email, String nomePet, String promo, TipoPet tipoPet){
        this.id  = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.nomePet = nomePet;
        this.promo = promo;
        this.tipoPet = tipoPet;
    }

    public String toString(){
        return nome + '\n' + telefone + '\n' + nomePet + " - " + tipoPet;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNomePet(){
        return nomePet;
    }

    public void setNomePet(String nomePet){
        this.nomePet = nomePet;
    }

    public String getPromo(){
        return promo;
    }

    public void setPromo(String promo){
        this.promo = promo;
    }

    public TipoPet getTipoPet(){
        return tipoPet;
    }

    public void setTipoPet(TipoPet tipoPet){
        this.tipoPet = tipoPet;
    }
}
