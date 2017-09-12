package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.talkhub.thprototipo.Classes.Equipe;

public class EquipeActivity extends AppCompatActivity {

    private EditText mNomeEquipe;
    private EditText mDescEquipe;
    private Button mSalvarEquipe;
    private DatabaseReference mRefEquipe, mRefEquipeUsuarios;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String usuarioCriador;
    private List<String> administradores;
    private List<String> membros;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);

        mAuth = FirebaseAuth.getInstance();
        mRefEquipe = FirebaseDatabase.getInstance().getReference().child("equipes");
        mRefEquipeUsuarios = FirebaseDatabase.getInstance().getReference().child("equipesUsuarios");
        administradores = new ArrayList<String>();
        membros = new ArrayList<String>();

        mNomeEquipe = (EditText) findViewById(R.id.et_tit_equipe);
        mDescEquipe = (EditText) findViewById(R.id.et_desc_equipe);
        mSalvarEquipe = (Button) findViewById(R.id.bt_salvar_equipe);

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                usuarioCriador = firebaseAuth.getCurrentUser().getEmail().toString();
                administradores.add(usuarioCriador);
                membros.add(usuarioCriador);
            }
        };

        mSalvarEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipe equipe  = new Equipe(mNomeEquipe.getText().toString(),
                        mDescEquipe.getText().toString(),
                        usuarioCriador,
                        administradores,
                        membros);

                String keyEquipe = mRefEquipe.push().getKey();
                mRefEquipe.child(keyEquipe).setValue(equipe);
                /*todo o próximo passo é conseguir recuperar o id do usuário logado para fazer a inserção
                da linha abaixo*/
                mRefEquipeUsuarios.child(keyEquipe).child().setValue(usuarioCriador);

                startActivity(new Intent(EquipeActivity.this, HomeActivity.class));
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
