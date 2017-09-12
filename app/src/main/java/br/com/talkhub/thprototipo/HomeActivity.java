package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
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
    private Button mNovaEquipe;
    private ListView mListaEquipes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mNovaEquipe = (Button) findViewById(R.id.bt_nova_equipe);
        mListaEquipes = (ListView) findViewById(R.id.lv_equipes);
        final FirebaseUser user = mAuth.getCurrentUser();
        mLabelNomeUsuario = (TextView) findViewById(R.id.tv_nome_usuario);

        Query query = mRef.child("usuarios").orderByChild("email").equalTo(user.getEmail());
        final Query listEquipesQuery = mRef.child("equipes").child("membros").equalTo(user.getEmail());
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

        mNovaEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir activity da inserção de novo cartão
                startActivity(new Intent(HomeActivity.this, EquipeActivity.class));
            }
        });

        //Adapter para preencher o ListView de equipes

        listEquipesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                mRef.child("teste")
                //todo O próximo passo é conseguir exibir as equipes em que o usuário logado está como membro
                //todo para fazer isso, acredito que o correto será criar um nó equipexusuario, onde a gente
                //o id de equipe e usuario para montar a relação, tem que fundamentar que relação n x n são
                //melhores de ser feitas criando um novo nó
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
