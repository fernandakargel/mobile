package br.com.nandak.estudos.lista;

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
        View v = inflater.inflate(R.layout.lista_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        RecyclerView recyclerViewListas = v.findViewById(R.id.recyclerViewListas);

        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        recyclerViewListas.setLayoutManager(manager);
        recyclerViewListas.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewListas.setHasFixedSize(true);

        Cursor dataLista = databaseHelper.getAllLista();
        List<Lista> listas = new ArrayList<Lista>();
        while (dataLista.moveToNext()) {
            Lista m = new Lista();
            int idColumnIndex = dataLista.getColumnIndex("_id");
            m.setId(Integer.parseInt(dataLista.getString(idColumnIndex)));
            int nameColumnIndex = dataLista.getColumnIndex("nome");
            m.setNome(dataLista.getString(nameColumnIndex));
            listas.add(m);
        }
        dataLista.close();
        //No método getAllLista() apenas abre a conexão
        databaseHelper.closeDBConnection();

        ListaAdapter adapterListas = new ListaAdapter(listas, getActivity());
        recyclerViewListas.setAdapter(adapterListas);
        return v;
    }
}