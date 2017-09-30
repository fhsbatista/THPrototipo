package br.com.talkhub.thprototipo.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by ferna on 26/08/2017.
 */
public class Equipe {

    private String nome;
    private String descricao;
    private String usuarioCriador;
    private List<String> administradores;
    private List<String> membros;



    public Equipe(String nome, String descricao,
                  String usuarioCriador,
                  List<String> administradores,
                  List<String> membros) {
        this.nome = nome;
        this.descricao = descricao;
        this.usuarioCriador = usuarioCriador;
        this.administradores = administradores;
        this.membros = membros;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuarioCriador(){
        return usuarioCriador;
    }

    public List<String> getAdministradores() {
        return administradores;
    }

    public List<String> getMembros() {
        return membros;
    }









}
