package br.com.nandak.estudos.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.nandak.lista.Lista;

import androidx.fragment.app.Fragment;

import br.com.nandak.estudos.R;
import br.com.nandak.estudos.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    EditText etNome;

    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lista_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNome);

        Button btnSalvar = v.findViewById(R.id.buttonAdicionar);
        btnSalvar.setOnClickListener(v1 -> adicionar());

        return v;
    }

    private void adicionar () {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Lista m = new Lista();
            m.setNome(etNome.getText().toString());
            databaseHelper.createLista(m);
            Toast.makeText(getActivity(), "Lista salva!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLista, new ListarFragment()).commit();
        }
    }
}