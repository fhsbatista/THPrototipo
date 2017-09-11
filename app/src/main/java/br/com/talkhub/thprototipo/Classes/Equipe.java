package br.com.talkhub.thprototipo.Classes;

/**
 * Created by ferna on 26/08/2017.
 */
public class Equipe {

    private String nome;
    private String descricao;
    private String[] administradores;

    public Equipe(String nome, String descricao, String[] administradores) {
        this.nome = nome;
        this.descricao = descricao;
        this.administradores = administradores;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String[] getAdministradores() {
        return administradores;
    }



}
