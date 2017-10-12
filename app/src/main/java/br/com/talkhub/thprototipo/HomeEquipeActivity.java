package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HomeEquipeActivity extends AppCompatActivity {


    private Button mNovoProjeto;
    private FirebaseListAdapter<String> firebaseListAdapter;
    private DatabaseReference mRefEquipe;
    private ListView mListaProjetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_equipe);

        Bundle bundle = getIntent().getExtras();
        final String idEquipe = bundle.getString("idEquipe");
        mRefEquipe = FirebaseDatabase.getInstance().getReference().child("equipes");
        mListaProjetos = (ListView) findViewById(R.id.lv_lista_projetos);
        carregarLista(idEquipe);

        mNovoProjeto = (Button) findViewById(R.id.btn_novo_projeto);

        mNovoProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeEquipeActivity.this, ProjetoActivity.class);
                intent.putExtra("idEquipe", idEquipe);
                startActivity(intent);
            }
        });





    }

    public void carregarLista(String idEquipe){

        firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                mRefEquipe.child(idEquipe).child("projetos")

        ) {

            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);

            }
        };

        mListaProjetos.setAdapter(firebaseListAdapter);

    }
}
