package br.com.nandak.estudos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.nandak.estudos.lista.Lista;
import br.com.nandak.estudos.palavra.Palavra;


public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "estudos";
    private static final String TABLE_LISTA = "lista";
    private static final String TABLE_PALAVRA = "palavra";

    private static final String CREATE_TABLE_LISTA = "CREATE TABLE " + TABLE_LISTA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100))";
    private static final String CREATE_TABLE_PALAVRA = "CREATE TABLE " + TABLE_PALAVRA + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_lista INTEGER, " +
            "palavra VARCHAR(100), " +
            "dadescricao TEXT, " +
            "pronuncia VARCHAR(100), " +
            "exemplo VARCHAR(250), " +
            "CONSTRAINT fk_palavra_lista FOREIGN KEY (id_lista) REFERENCES lista (id))";

    private static final String DROP_TABLE_LISTA = "DROP TABLE IF EXISTS " + TABLE_LISTA;
    private static final String DROP_TABLE_PALAVRA = "DROP TABLE IF EXISTS " + TABLE_PALAVRA;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LISTA);
        db.execSQL(CREATE_TABLE_PALAVRA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_LISTA);
        db.execSQL(DROP_TABLE_PALAVRA);
        onCreate(db);
    }

    public void closeDBConnection() {
        db.close();
    }


    /* Início CRUD Lista */
    public void createLista(Lista m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        long id = db.insert(TABLE_LISTA, null, cv);
        db.close();
    }

    public long updateLista(Lista m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        long rows = db.update(TABLE_LISTA, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return rows;
    }

    public long deleteLista(Lista m) {
        db = this.getWritableDatabase();
        long rows = db.delete(TABLE_LISTA, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return rows;
    }

    public Lista getByIdLista (int id) {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_LISTA, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Lista m = new Lista();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        data.close();
        db.close();
        return m;
    }

    public Cursor getAllLista () {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        return db.query(TABLE_LISTA, columns, null, null, null,
                null, "nome");
    }

    public void getAllNameLista (ArrayList<Integer> listListaId, ArrayList<String> listListaName) {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_LISTA, columns, null, null, null,
                null, "nome");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listListaId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int nameColumnIndex = data.getColumnIndex("nome");
            listListaName.add(data.getString(nameColumnIndex));
        }
        db.close();
    }
    /* Fim CRUD MListaédico */

    /* Início CRUD Palavra */
    public long createPalavra(Palavra b) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_lista", b.getId_lista());
        cv.put("palavra", b.getPalavra());
        cv.put("descricao", b.getDescricao());
        cv.put("pronuncia", b.getPronuncia());
        cv.put("exemplo", b.getExemplo());
        long id = db.insert(TABLE_PALAVRA, null, cv);
        db.close();
        return id;
    }

    public long updatePalavra(Palavra b) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("_id", b.getId());
        cv.put("palavra", b.getPalavra());
        cv.put("id_lista", b.getId_lista());
        cv.put("descricao", b.getDescricao());
        cv.put("pronuncia", b.getPronuncia());
        cv.put("exemplo", b.getExemplo());  
        long rows = db.update(TABLE_PALAVRA, cv, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return rows;
    }

    public long deletePalavra(Palavra b) {
        db = this.getWritableDatabase();
        long rows = db.delete(TABLE_PALAVRA, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return rows;
    }

    public Palavra getByIdPalavra (int id) {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "id_lista", "palavra", "descricao", "pronuncia", "exemplo"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_PALAVRA, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Palavra b = new Palavra();
        b.setId(data.getInt(0));
        b.setId_lista(data.getInt(1));
        b.setPalavra(data.getString(2));
        b.setDescricao(data.getString(3));
        b.setPronuncia(data.getString(4));
        b.setExemplo(data.getString(5));
        data.close();
        db.close();
        return b;
    }

    public Cursor getAllPalavra () {
        db = this.getWritableDatabase();
        String[] columns = {"_id", "palavra", "id_lista"};
        return db.query(TABLE_PALAVRA, columns, null, null, null,
                null, "palavra");
    }
    /* Fim CRUD Palavra */
}
