package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {


    private DatabaseReference mRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private TextView mLabelNomeUsuario;
    private Button mNovaEquipe;
    private ListView mListaEquipes;
    private String user;
    private String idUser;
    private FirebaseListAdapter<String> firebaseListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNovaEquipe = (Button) findViewById(R.id.bt_nova_equipe);
        mListaEquipes = (ListView) findViewById(R.id.lv_equipes);
        mLabelNomeUsuario = (TextView) findViewById(R.id.tv_nome_usuario);

        user = mAuth.getCurrentUser().getEmail().toString();
        mRef = FirebaseDatabase.getInstance().getReference();
        Query query = mRef.child("usuarios").orderByChild("email").equalTo(user);




            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        idUser = item.getKey().toString();
                        carregarLista(idUser);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        mNovaEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir activity da inserção de novo cartão
                startActivity(new Intent(HomeActivity.this, EquipeActivity.class));
            }
        });
        
        mListaEquipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String idEquipe = firebaseListAdapter.getRef(position).getKey().toString();
                Intent intent = new Intent(HomeActivity.this, HomeEquipeActivity.class);
                intent.putExtra("idEquipe", idEquipe);
                startActivity(intent);

            }
        });










    }


    //Adapter para preencher o ListView de equipes

    public void carregarLista(String idUser){
        firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                mRef.child("usuarios").child(idUser).child("equipes")

        ) {


            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);

            }
        };

        mListaEquipes.setAdapter(firebaseListAdapter);





    }







}
