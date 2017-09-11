package br.com.talkhub.thprototipo.Classes;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by ferna on 26/08/2017.
 */
public class Cartao {

    private String titulo;
    private String descricao;
    private String emailUsuario;


    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Cartao(String titulo, String descricao, String emailUsuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.emailUsuario = emailUsuario;
    }
}
