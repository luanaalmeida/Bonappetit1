package br.senac.pi.bonappetit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

        findViewById(R.id.btPratos).setOnClickListener(abrirPratos());
        findViewById(R.id.btSobremesas).setOnClickListener(abrirSobremesas());
        findViewById(R.id.btBebidas).setOnClickListener(abrirBebidas());
        findViewById(R.id.btMeuPedido).setOnClickListener(abrirMeuPedido());
    }


    private View.OnClickListener abrirPratos() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Menu.this,Pratos.class);
                startActivity(intent);

            };
        };
    }
    private View.OnClickListener abrirSobremesas() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Menu.this,Sobremesas.class);
                startActivity(intent);

            };
        };
    }

    private View.OnClickListener abrirBebidas() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Menu.this,Bebidas.class);
                startActivity(intent);

            };
        };
    }

    private View.OnClickListener abrirMeuPedido() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Menu.this,MeuPedido.class);
                startActivity(intent);

            };
        };
    }
}
