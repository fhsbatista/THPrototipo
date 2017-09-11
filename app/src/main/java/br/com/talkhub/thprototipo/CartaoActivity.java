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

import br.com.talkhub.thprototipo.Classes.Cartao;

public class CartaoActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private Button mSalvarCartao;
    private EditText mTituloCartao;
    private EditText mDescricaoCartao;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String mEmailUsuario;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);


        mSalvarCartao = (Button) findViewById(R.id.bt_salvar_cartao);
        mTituloCartao = (EditText) findViewById(R.id.et_tit_cartao);
        mDescricaoCartao = (EditText) findViewById(R.id.et_desc_cartao);

        mRef = FirebaseDatabase.getInstance().getReference().child("cartoes");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               mEmailUsuario = firebaseAuth.getCurrentUser().getEmail().toString();

            }
        };

        mSalvarCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cartao cartao = new Cartao(mTituloCartao.getText().toString(),
                    mDescricaoCartao.getText().toString(),
                    mEmailUsuario);

                mRef.push().setValue(cartao);

                startActivity(new Intent(CartaoActivity.this, HomeActivity.class));


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
