package br.com.talkhub.thprototipo.Classes;

import com.google.android.gms.tasks.Task;
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

    public void novaEquipe(String idUsuarioLogado){

        DatabaseReference mRefEquipe = FirebaseDatabase.getInstance().getReference().child("equipes");
        DatabaseReference mRefUsuario = FirebaseDatabase.getInstance().getReference().child("usuarios");

        //Cria um id para a equipe
        String keyEquipe = mRefEquipe.push().getKey();
        //Adiciona a nova equipe no documento de equipes
        mRefEquipe.child(keyEquipe).setValue(this);

        //Embeda no documento "usuario" o id e o nome da equipe

        mRefUsuario.child(idUsuarioLogado).child("equipes").child(keyEquipe).setValue(this.nome);



    }

    public void novoMembro(String idEquipe, String idUsuario, Boolean administrador){


        DatabaseReference mRefEquipe = FirebaseDatabase.getInstance().getReference().child(idEquipe).child("membros");
        DatabaseReference mRefUsuario = FirebaseDatabase.getInstance().getReference().child(idUsuario).child("equipes");

        //Embeda no documento "usuario" o id e o nome da equipe
        mRefUsuario.child(idUsuario).child("equipes").child(idEquipe).setValue(this.nome);
        






    }







}
