package br.com.talkhub.thprototipo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.talkhub.thprototipo.Classes.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mSenha;
    private Button mEntrar;
    private Button mCadastrar;

    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mRef = FirebaseDatabase.getInstance().getReference();
        mEmail = (EditText) findViewById(R.id.et_email_login);
        mSenha = (EditText) findViewById(R.id.et_senha_login);
        mEntrar = (Button) findViewById(R.id.bt_entrar_login);
        mCadastrar = (Button) findViewById(R.id.bt_cadastrar_login);

        //Evento de clique no botão "Logar"
        mEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String senha = mSenha.getText().toString();

                logar(email, senha);

            }
        });
        //Evento de clique no botão "Cadastrar"
        mCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String senha = mSenha.getText().toString();
                cadastrar(email, senha);
            }
        });

    }

    //Este método faz a validação dos campos e tenta fazer o login no firebase
    public void logar(String email, String senha){
        //Se algum campo do login tiver ficado em branco, será dada a mensagem abaixo.
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)){
            Toast.makeText(MainActivity.this, "Campos em branco", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
                    } else{
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                }
            });
        }
    }
    //Este método faz o cadastro de um novo usuário, e já salva dados no database também
    public void cadastrar(final String email, final String senha){

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)){
            Toast.makeText(MainActivity.this, "Campos em branco", Toast.LENGTH_SHORT).show();
        }
        else{

            mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Algo deu errado", Toast.LENGTH_SHORT);
                    } else{

                        //Listener que verifica status da autenticação
                        mAuthListener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                //Se o usuário estiver logado, é executada a ação dentro deste if
                                if(firebaseAuth.getCurrentUser() != null){
                                    Usuario usuario = new Usuario();
                                    usuario.setEmail(email);
                                    Intent intent = new Intent(MainActivity.this, DadosCadastraisActivity.class);
                                    intent.putExtra("email",usuario.getEmail());
                                    startActivity(intent);
                                }
                            }
                        };
                        mAuth.addAuthStateListener(mAuthListener);



                    }
                }
            });



        }

    }


}
