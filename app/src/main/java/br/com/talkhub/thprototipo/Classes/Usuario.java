package br.com.talkhub.thprototipo.Classes;

/**
 * Created by ferna on 26/08/2017.
 */
public class Usuario {

    private String email;
    private String nome;
    private String sobrenome;

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Usuario(){
        //Construtor criado apenas para receber um objeto que poderá ser usado no objeto do Firebase
    }

    public Usuario(String email, String nome, String sobrenome) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }


}
