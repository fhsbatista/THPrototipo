package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.talkhub.thprototipo.Classes.Usuario;

public class DadosCadastraisActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private TextView mEmail;
    private EditText mNome;
    private EditText mSobreNome;
    private Button mContinuar;
    private Button mSair;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cadastrais);


        mNome = (EditText) findViewById(R.id.et_nome);
        mSobreNome = (EditText) findViewById(R.id.et_sobrenome);
        mContinuar = (Button) findViewById(R.id.bt_continuar);
        mAuth = FirebaseAuth.getInstance();

        mRef = FirebaseDatabase.getInstance().getReference().child("usuarios");

        //Cria um objeto usuario, pegando o e-mail que veio da MainActivity


        mContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                final Bundle bundle = getIntent().getExtras();
                usuario.setEmail(bundle.getString("email"));
                usuario.setNome(mNome.getText().toString());
                usuario.setSobrenome(mSobreNome.getText().toString());
                mRef.push().setValue(usuario);
                startActivity(new Intent(DadosCadastraisActivity.this, HomeActivity.class));

            }
        });

        /*O bloco abaixo mostra um exemplo de query, onde eu verificava se existia algum usuario com um email
        predeterminado
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() == 0){
                    Usuario usuario = new Usuario();
                    usuario.setEmail(bundle.get("email").toString());
                    usuario.setNome("");
                    usuario.setSobrenome("");
                    mRef.push().setValue(usuario);
                }else{
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, Object> email = (Map<String, Object>) child.getValue();

                }
            }}


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/









    }
}
