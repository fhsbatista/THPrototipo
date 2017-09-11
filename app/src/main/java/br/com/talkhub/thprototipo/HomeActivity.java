package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {


    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private TextView mLabelNomeUsuario;
    private Button mNovoCartao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mNovoCartao = (Button) findViewById(R.id.bt_novo_cartao);
        FirebaseUser user = mAuth.getCurrentUser();


        mLabelNomeUsuario = (TextView) findViewById(R.id.tv_nome_usuario);

        Query query = mRef.child("usuarios").orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() == 0){
                    mLabelNomeUsuario.setText("não entrou");

                    } else{
                for (DataSnapshot child : dataSnapshot.getChildren()){

                    Map<String, Object> usuarioMapped = (Map<String, Object>) child.getValue();
                    mLabelNomeUsuario.setText(usuarioMapped.get("nome").toString());
                    }
                }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mNovoCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir activity da inserção de novo cartão
                startActivity(new Intent(HomeActivity.this, CartaoActivity.class));
            }
        });


    }


}
