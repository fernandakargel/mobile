package br.com.nandak.estudos.palavra;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.nandak.estudos.R;
import br.com.nandak.estudos.database.DatabaseHelper;

public class ListarFragment extends Fragment {

    public ListarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.palavra_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        RecyclerView recyclerViewPalavras = v.findViewById(R.id.recyclerViewPalavras);

        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        recyclerViewPalavras.setLayoutManager(manager);
        recyclerViewPalavras.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewPalavras.setHasFixedSize(true);

        Cursor dataPalavra = databaseHelper.getAllPalavra();
        List<Palavra> palavras = new ArrayList<Palavra>();
        while (dataPalavra.moveToNext()) {
            Palavra b = new Palavra();
            int idColumnIndex = dataPalavra.getColumnIndex("_id");
            b.setId(Integer.parseInt(dataPalavra.getString(idColumnIndex)));
            int nameColumnIndex = dataPalavra.getColumnIndex("nome");
            b.setPalavra(dataPalavra.getString(nameColumnIndex));
            int idListaColumnIndex = dataPalavra.getColumnIndex("id_lista");
            b.setId_lista(Integer.parseInt(dataPalavra.getString(idListaColumnIndex)));
            palavras.add(b);
        }
        dataPalavra.close();
        //No método getAllPalavra() apenas abre a conexão
        databaseHelper.closeDBConnection();

        PalavraAdapter adapterPalavras = new PalavraAdapter(palavras, getActivity());
        recyclerViewPalavras.setAdapter(adapterPalavras);
        return v;
    }
}