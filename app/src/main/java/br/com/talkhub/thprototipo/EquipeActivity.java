package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.talkhub.thprototipo.Classes.Equipe;
import br.com.talkhub.thprototipo.Classes.Usuario;

public class EquipeActivity extends AppCompatActivity {

    private EditText mNomeEquipe;
    private EditText mDescEquipe;
    private Button mSalvarEquipe;
    private DatabaseReference mRefEquipe, mRefUsuario;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String usuarioCriador;
    private String idUsuarioLogado;
    private List<String> administradores;
    private List<String> membros;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);

        //Objeto de autenticação criado para recuperar dados do usuário logado
        mAuth = FirebaseAuth.getInstance();

        //Referência criada para usar nas queries que envolvem a equipe
        mRefEquipe = FirebaseDatabase.getInstance().getReference().child("equipes");

        //As listas abaixos foram criadas para serem utilizadas na inserção da equipe
        administradores = new ArrayList<String>();
        membros = new ArrayList<String>();

        //Objetos do xml da activity
        mNomeEquipe = (EditText) findViewById(R.id.et_tit_equipe);
        mDescEquipe = (EditText) findViewById(R.id.et_desc_equipe);
        mSalvarEquipe = (Button) findViewById(R.id.bt_salvar_equipe);

        //Usuario criador é o usuario que está logado no momento da criação da equipe
        usuarioCriador = mAuth.getCurrentUser().getEmail().toString();

        /*São adiconados nas listas abaixo o usuário que está logado *automaticamente este usuário
        Faz parte do grupo de administradores e membros da equipe */
        administradores.add(usuarioCriador);
        membros.add(usuarioCriador);







        /*Usuario usuario = new Usuario();
        final String idUsuarioLogado = usuario.getIdUsuario(usuario.getEmailUsuarioLogado());*/

        //Executado quando o usuário clica no botão salvar
        mSalvarEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criação do objeto "equipe" que será usada na inserção da equipe no documento
                Equipe equipe  = new Equipe(mNomeEquipe.getText().toString(),
                        mDescEquipe.getText().toString(),
                        usuarioCriador,
                        administradores,
                        membros);

                equipe.novaEquipe(idUsuarioLogado);

                //Depois de finalizada a inserção, o usuário é levado para página HOME
                startActivity(new Intent(EquipeActivity.this, HomeActivity.class));
            }
        });



    }






}
