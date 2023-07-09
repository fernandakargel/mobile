package br.com.nandak.estudos.palavra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import br.com.nandak.estudos.R;
import br.com.nandak.estudos.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    EditText etPalavra;
    EditText etDescricao;
    EditText etPronuncia;
    EditText etExemplo;
    Spinner spLista;
    
    ArrayList<Integer> listListaId;
    ArrayList<String> listListaName;
    DatabaseHelper databaseHelper;

    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.palavra_fragment_adicionar, container, false);

        spLista = v.findViewById(R.id.spinnerLista);
        etPalavra = v.findViewById(R.id.editTextPalavra);
        etDescricao = v.findViewById(R.id.editTextDescricao);
        etPronuncia = v.findViewById(R.id.editTextPronuncia);
        etExemplo = v.findViewById(R.id.editTextExemplo);

        databaseHelper = new DatabaseHelper(getActivity());

        listListaId = new ArrayList<>();
        listListaName = new ArrayList<>();
        databaseHelper.getAllNameLista(listListaId, listListaName);

        ArrayAdapter<String> spListaArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listListaName);
        spLista.setAdapter(spListaArrayAdapter);

        Button btnSalvar = v.findViewById(R.id.buttonAdicionar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        if (spLista.getSelectedItem() == null) {
            Toast.makeText(getActivity(), "Por favor, selecione a lista!", Toast.LENGTH_LONG).show();
        } else if (etPalavra.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a palavra!", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição!", Toast.LENGTH_LONG).show();
        } else if (etPronuncia.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a pronuncia!", Toast.LENGTH_LONG).show();
        } else if (etExemplo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o exemplo!", Toast.LENGTH_LONG).show();
        } else {
            Palavra b = new Palavra();
            String nomeLista = spLista.getSelectedItem().toString();
            b.setId_lista(listListaId.get(listListaName.indexOf(nomeLista)));
            b.setPalavra(etPalavra.getText().toString());
            b.setDescricao(etDescricao.getText().toString());
            b.setPronuncia(etPronuncia.getText().toString());
            b.setExemplo(etExemplo.getText().toString());
            databaseHelper.createPalavra(b);
            Toast.makeText(getActivity(), "Palavra salva!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framePalavra, new ListarFragment()).commit();
        }
    }
}