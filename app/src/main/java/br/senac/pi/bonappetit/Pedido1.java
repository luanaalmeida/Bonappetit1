package br.senac.pi.bonappetit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Pedido1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.bntButtonSomar).setOnClickListener(somar());
    }

    //continuaçao do botton implementando o listener e soma
    private View.OnClickListener somar(){
        return new View.OnClickListener(){
            public void onClick (View v){
               // EditText edtPrimeiroNumero = (EditText) findViewById(R.id.edtPrimeiroNumero);
                EditText somarCarne = (EditText) findViewById(R.id.edtSomaCarne);
                //double primeiroNumero  = Double.parseDouble(edtPrimeiroNumero.getText().toString());
                double segundoNumero  = Double.parseDouble(somarCarne.getText().toString());
                double total = 25.00 + somarCarne;
                Toast.makeText(activity_pedido1.this, getString(R.string.total) + total, Toast.LENGTH_LONG).show();
            }
        };

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

    }

}
