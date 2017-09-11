package br.com.talkhub.thprototipo.Classes;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by ferna on 26/08/2017.
 */
public class Cartao {

    private String titulo;
    private String descricao;


    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Cartao(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
