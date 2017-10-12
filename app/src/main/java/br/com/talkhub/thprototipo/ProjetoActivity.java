package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.talkhub.thprototipo.Classes.Projeto;

public class ProjetoActivity extends AppCompatActivity {
    
    //// TODO: 04/10/2017 Declarar campos desta activity, e chamar o método de inserção
    private EditText mNomeProjeto;
    private EditText mDescProjeto;
    private Button mSalvarProjeto;
    private List<String> administradores;
    private List<String> membros;
    private FirebaseAuth mAuth;
    private String usuarioCriador;
    private String idUsuarioLogado;
    private DatabaseReference mRefUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);

        mRefUsuario = FirebaseDatabase.getInstance().getReference().child("usuarios");
        Query query = mRefUsuario.orderByChild("email").limitToFirst(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    idUsuarioLogado = item.getKey().toString();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mNomeProjeto = (EditText) findViewById(R.id.tv_nome_projeto);
        mDescProjeto = (EditText) findViewById(R.id.tv_desc_projeto);
        mSalvarProjeto = (Button) findViewById(R.id.btn_salvar_projeto);


        mAuth = FirebaseAuth.getInstance();

        administradores = new ArrayList<String>();
        membros = new ArrayList<String>();
        administradores.add(mAuth.getCurrentUser().getEmail().toString());
        membros.add(mAuth.getCurrentUser().getEmail().toString());
        usuarioCriador = mAuth.getCurrentUser().getEmail().toString();

        Bundle bundle = getIntent().getExtras();
        final String idEquipe = bundle.getString("idEquipe");


        mSalvarProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Projeto projeto = new Projeto(mNomeProjeto.getText().toString(), mDescProjeto.getText().toString(),
                  usuarioCriador,  administradores, membros );

                projeto.novoProjeto(idUsuarioLogado, idEquipe);
                Intent intent = new Intent(ProjetoActivity.this, HomeEquipeActivity.class);
                intent.putExtra("idEquipe", idEquipe);
                startActivity(intent);

            }
        });

    }
}
