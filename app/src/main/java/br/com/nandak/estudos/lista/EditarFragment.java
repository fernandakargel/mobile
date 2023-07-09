package br.com.nandak.estudos.lista;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.com.nandak.estudos.R;
import br.com.nandak.estudos.database.DatabaseHelper;
import br.com.nandak.lista.Lista;

public class EditarFragment extends Fragment {

    EditText etNome;
    EditText etEspecialidade;
    EditText etCelular;
    DatabaseHelper databaseHelper;
    Lista m;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lista_fragment_editar, container, false);
        Bundle b = getArguments();
        int id_lista = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        m = databaseHelper.getByIdLista(id_lista);

        etNome = v.findViewById(R.id.editTextNome);
        etNome.setText(m.getNome());

        Button btnEditar = v.findViewById(R.id.buttonEditar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_lista);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluir);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.dialog_excluir_lista);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_lista);
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
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else {
            m = new Lista();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            databaseHelper.updateLista(m);
            Toast.makeText(getActivity(), "Lista editado!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLista, new ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        m = new Lista();
        m.setId(id);
        databaseHelper.deleteLista(m);
        Toast.makeText(getActivity(), "Lista excluído!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLista, new ListarFragment()).commit();
    }
}