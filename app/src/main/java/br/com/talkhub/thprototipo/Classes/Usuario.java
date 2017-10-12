package br.com.talkhub.thprototipo.Classes;

import android.widget.NumberPicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ferna on 26/08/2017.
 */
public class Usuario {

    private String email;
    private String nome;
    private String sobrenome;


    private String nomeReferenciaUsuario;
   //Utilizado quando é necessário saber o ID de um usuário
    private  String keyUsuario;



    private DatabaseReference mRef;



    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeReferenciaUsuario() {
        return nomeReferenciaUsuario;
    }

    public void setNomeReferenciaUsuario(String companhia) {
        this.nomeReferenciaUsuario = companhia;
    }








}
