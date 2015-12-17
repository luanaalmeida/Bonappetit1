package br.senac.pi.bonappetit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Pratos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btCarme).setOnClickListener(abrirPedido1());
    }


    private View.OnClickListener abrirPedido1() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Pratos.this,Pedido1.class);
                startActivity(intent);

            };
        };
    }
    }


