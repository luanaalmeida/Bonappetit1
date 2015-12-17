package br.senac.pi.bonappetit;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String MODE_PRIVATE = "mode_private" ;
    EditText usuario, senha;

    private static final String MANTER_CONECTADO = "preferencias_globais";
    private static final String PREFERENCIAS = "manter_conctado";

    // novos atributos
    private SharedPreferences preferencias;
    private GoogleAccountManager accountManager;
    private Account conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountManager = new GoogleAccountManager(this);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        CheckBox manterConectado = (CheckBox) findViewById(R.id.manterConectado);
        preferencias = getSharedPreferences(PREFERENCIAS, MODE_PRIVATE);
        boolean conectado = preferencias
                .getBoolean(MANTER_CONECTADO, false);
        if (conectado) {
            iniciarDashboard();
        }
    }

    public void entrarOnClick(View v) {
        String usuarioInformado = usuario.getText().toString();
        String senhaInformada = senha.getText().toString();
        autenticar(usuarioInformado, senhaInformada);
    }

    private void autenticar(final String nomeConta, String senha) {
        conta = accountManager.getAccountByName(nomeConta);
        if (conta == null) {
            Toast.makeText(this, R.string.conta_inexistente,
                    Toast.LENGTH_LONG).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(AccountManager.KEY_ACCOUNT_NAME, nomeConta);
        bundle.putString(AccountManager.KEY_PASSWORD, senha);
        accountManager.getAccountManager()
                .confirmCredentials(conta, bundle, this,
                        new AutenticacaoCallback(), null);
    }
    private class AutorizacaoCallback implements AccountManagerCallback<Bundle> {

        @Override
        public void run(AccountManagerFuture<Bundle> future) {

            try {
                Bundle bundle = future.getResult();
                String nomeConta = bundle.getString(AccountManager.KEY_ACCOUNT_NAME);
                String tokenAcesso = bundle.getString(AccountManager.KEY_AUTHTOKEN);

                gravarTokenAcesso(nomeConta, tokenAcesso);

                iniciarDashboard();

            } catch (OperationCanceledException e) {
                //usuário cancelou a operação
            } catch (AuthenticatorException e) {
                //possível problema no autenticador
            } catch (IOException e) {
                //possível problema de comunicação
            }
        }
    }

    private void gravarTokenAcesso(String nomeConta, String tokenAcesso) {
        Editor editor = preferencias.edit();
        editor.putString(NOME_CONTA, nomeConta);
        editor.putString(TOKEN_ACESSO, tokenAcesso);
        editor.putBoolean(MANTER_CONECTADO, manterConectado.isChecked());
        editor.commit();
    }

    private class AutenticacaoCallback implements AccountManagerCallback<Bundle>{
        @Override
        public void run(AccountManagerFuture<Bundle> future) {

            try {

                Bundle bundle = future.getResult();
                if(bundle.getBoolean(AccountManager.KEY_BOOLEAN_RESULT)){

                    solicitarAutorizacao();

                }else{
                    Toast.makeText(getBaseContext(),
                            getString(R.string.erro_autenticacao),
                            Toast.LENGTH_LONG).show();
                }

            } catch (OperationCanceledException e) {
                //usuário cancelou a operação
            } catch (AuthenticatorException e) {
                //possível problema no autenticador
            } catch (IOException e) {
                //possível problema de comunicação
            }
        }
    }


    private void iniciarDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
    }

//Chamar a Classe Abrir nenu
    private View.OnClickListener AbrirMenu() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);

            };
        };
    }


    private class GoogleAccountManager {
        public GoogleAccountManager(MainActivity mainActivity) {
        }
    }
}
