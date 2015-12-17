package br.senac.pi.bonappetit.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by familia on 16/12/2015.
 */
public class pedidoDB extends SQLiteOpenHelper {

    private static final String TAG = "sql";

    //nome do banco ("cursoAndroid.sqlite")
    // private static final String NOME_BANCO (constante do tipo string)
    private static final String NOME_BANCO = "cursoAndroid.sqlite";
    private static final int VERSAO_BANCO = 1;

    //contrutor do SQLiteOpenHelper
public pedidoDB (Context context){
    //context nome do banco, factory, versao
    super(context, NOME_BANCO, null, VERSAO_BANCO);
}

    public void onCreate (SQLiteDatabase db){

        Log.d(TAG, "Criação da tabela Pedido");
        db.execSQL("CREATE TABLE IF NOT EXISTS pedido (_id integer primary key autoincrement,"+"prato text, valor text, quantidade);");
        Log.d(TAG, "Tabela de pedido criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long save(Pedido pedido) {

        long id = pedido.getId();
        SQLiteDatabase db = getReadableDatabase();//abre o Banco de Dados

        //metodo try tratamento de erro na entrada e saida decdados
        try {
            //ContentValues values pega e seta(inserir) os dados em cada local no caso nome e marca no caso pega com o get

            ContentValues values = new ContentValues();
            values.put("Prato", prato.getPrato());
            values.put("valor", valor.getValor());
            values.put ("quantidade", quantidade.getQtantidade());

            //
            if (id != 0) {
                String _id = String.valueOf(pedido.getId()); //pegando o _id (pegando int e transformando em string no sqline)
                String[] whereArgs = new String[]{_id}; //consulta do ID
                //update carro set values = ... where id = ?
                //SELECT * FROM carro WHERE id = 1;

                //whereArgs esta sendo pego
                int count = db.update("pedido", values, "_id =?", whereArgs);
                return count;
            }else{
                // insert into carro values {...}
                id = db.insert("pedido","",values);
                return id;
            }
            //execulta um passo necessario sempre sera execultado o que esta dentro do finally
        }finally {
            db.close();
        }
    }
    //deletar um carro
//SQLiteDatabase db e uma instancia de leitura atravez do getWritableDatabase ()
    public int delete (Pedido pedido){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //delete from carro where _id = ?
            int count = db.delete("pedido", "_id =?", new String[]{String.valueOf(pedido.getId())});
            Log.i(TAG, "Deletou[" + count + "] registro");
            return count;
        }finally {
            db.close();
        }
    }

    //consulte as listas no BD com todos os carros
    public List<Pedido> FindAli(){
//SQLiteDatabase db e uma instancia de leitura atravez do getWritableDatabase ()
        SQLiteDatabase db = getWritableDatabase();
        try{
            //SELECT * FROM carro;
            Cursor cursor = db.query("pedido", null, null, null, null, null, null, null);
            return toList(cursor);
        }finally {
            db.close();
        }
    }
    //Cursos
    private List<Pedido> toList(Cursor cursor) {
        List<Pedido> pedido = new ArrayList<Pedido>();
//mover cursor para o primeiro registro
        if (cursor.moveToFirst()){
            do {
                Pedido pedido = new Pedido();
                pedido.add(pedido);
                pedido.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                pedido.setPrato(cursor.getString(cursor.getColumnIndex("prato")));
                pedido.setValor(cursor.getString(cursor.getColumnIndex("valor")));
                pedido.setQuantidade(cursor.getString(cursor.getColumnIndex("quantidade")));
            }while (cursor.moveToNext());
        }
        return pedido;
    }
}

