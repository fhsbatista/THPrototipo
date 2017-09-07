package br.com.talkhub.thprototipo.Classes;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ferna on 26/08/2017.
 */
public class Usuario {

    private String email;
    private String nome;
    private String sobrenome;



    private DatabaseReference mRef;
    private FirebaseAuth mAuth;


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

    public Usuario(){
        //Construtor criado apenas para receber um objeto que poderá ser usado no objeto do Firebase
    }

    public Usuario(String email, String nome, String sobrenome) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public void addUserDatabase(){
        mRef = FirebaseDatabase.getInstance().getReference("usuarios");
        mRef.push().setValue(this);
    }






}
