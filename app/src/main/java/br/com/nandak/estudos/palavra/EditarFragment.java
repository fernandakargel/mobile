package br.com.nandak.estudos.palavra;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditarFragment extends Fragment {

        // spLista = v.findViewById(R.id.spinnerLista););
        // etPalavra = v.findViewById(R.id.editTextPalavra);
        // etDescricao = v.findViewById(R.id.editTextDescricao);
        // etPronuncia = v.findViewById(R.id.editTextPronuncia);
        // etExemplo = v.findViewById(R.id.editTextExemplo);


    EditText etPalavra;
    EditText etDescricao;
    EditText etPronuncia;
    EditText etExemplo;
    Spinner spLista;
    ArrayList<Integer> listListaId;
    ArrayList<String> listListaName;
    DatabaseHelper databaseHelper;
    Palavra b;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.palavra_fragment_editar, container, false);
        Bundle bundle = getArguments();
        int id_palavra = bundle.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        b = databaseHelper.getByIdPalavra(id_palavra);

        spLista = v.findViewById(R.id.spinnerLista);
        etPalavra = v.findViewById(R.id.editTextPalavra);
        etDescricao = v.findViewById(R.id.editTextDescricao);
        etPronuncia = v.findViewById(R.id.editTextPronuncia);
        etExemplo = v.findViewById(R.id.editTextExemplo);

        listListaId = new ArrayList<>();
        listListaName = new ArrayList<>();
        databaseHelper.getAllNameLista(listListaId, listListaName);

        ArrayAdapter<String> spListaArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listListaName);
        spLista.setAdapter(spListaArrayAdapter);


        etPalavra.setText(b.getPalavra());
        etDescricao.setText(b.getDescricao());
        etPronuncia.setText(String.valueOf(b.getPronuncia()));
        etExemplo.setText(String.valueOf(b.getExemplo()));
        spLista.setSelection(listListaId.indexOf(b.getId_lista()));

        Button btnEditar = v.findViewById(R.id.buttonEditar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_palavra);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluir);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.dialog_excluir_palavra);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_palavra);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return v;
    }

    private void editar (int id) {
        if (etPalavra.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a palavra!", Toast.LENGTH_LONG).show();
        } else if (etDescricao.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a descrição!", Toast.LENGTH_LONG).show();
        } else if (etPronuncia.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a pronuncia!", Toast.LENGTH_LONG).show();
        } else if (etExemplo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o exemplo!", Toast.LENGTH_LONG).show();
        } else {
            b = new Palavra();
            b.setId(id);
            String nomeLista = spLista.getSelectedItem().toString();
            b.setId_lista(listListaId.get(listListaName.indexOf(nomeLista)));
            b.setPalavra(etPalavra.getText().toString());
            b.setDescricao(etDescricao.getText().toString());
            b.setPronuncia(etPronuncia.getText().toString());
            b.setExemplo(etExemplo.getText().toString());
            databaseHelper.updatePalavra(b);
            Toast.makeText(getActivity(), "Palavra editada!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framePalavra, new ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        b = new Palavra();
        b.setId(id);
        databaseHelper.deletePalavra(b);
        Toast.makeText(getActivity(), "Palavra excluída!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framePalavra, new ListarFragment()).commit();
    }
}