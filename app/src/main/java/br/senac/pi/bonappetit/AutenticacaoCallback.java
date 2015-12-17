package br.senac.pi.bonappetit;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.os.Bundle;
import android.support.v4.os.OperationCanceledException;
import android.widget.Toast;

import java.io.IOException;


private class AutenticacaoCallback
    implements AccountManagerCallback<Bundle>{

    {
        @Override
        public void run(AccountManagerFuture <Bundle> future){
        try {
            Bundle bundle = future.getResult();
            if (bundle.getBoolean(AccountManager.KEY_BOOLEAN_RESULT)) {
                iniciarDashboard();

            } else {
                Toast.makeText(getBaseContext(),
                        getString(R.string.erro_autenticacao),
                        Toast.LENGTH_LONG).show();
            }
        } catch (OperationCanceledException e) {
//usuário cancelou a operação
        } catch (AuthenticatorException e) {
//possível falha no autenticador
        } catch (IOException e) {
//possível falha de comunicação
        }
    }
    }


}


