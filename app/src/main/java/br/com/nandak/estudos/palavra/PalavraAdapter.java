package br.com.nandak.estudos.palavra;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.nandak.estudos.R;
import br.com.nandak.estudos.database.DatabaseHelper;
import br.com.nandak.estudos.lista.Lista;

public class PalavraAdapter extends RecyclerView.Adapter<PalavraAdapter.PalavraViewHolder>{
    private final List<Palavra> palavras;
    private int id_palavra;
    private final FragmentActivity activity;

    PalavraAdapter(List<Palavra> palavras, FragmentActivity activity){
        this.palavras = palavras;
        this.activity = activity;
    }

    static class PalavraViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomeView;
        private final TextView nomeListaView;

        PalavraViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeView = itemView.findViewById(R.id.tvListPalavraNome);
            nomeListaView = itemView.findViewById(R.id.tvListPalavraListaNome);
        }
    }

    @NonNull
    @Override
    public PalavraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.palavra_item, parent, false);
        return new PalavraViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PalavraViewHolder viewHolder, int i) {
        viewHolder.nomeView.setText(palavras.get(i).getPalavra());
        DatabaseHelper databaseHelper = new DatabaseHelper(activity);
        Lista m = databaseHelper.getByIdLista(palavras.get(i).getId_lista());
        viewHolder.nomeListaView.setText(m.getNome());
        id_palavra = palavras.get(i).getId();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id", id_palavra);

                EditarFragment editarFragment = new EditarFragment();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                editarFragment.setArguments(b);
                ft.replace(R.id.framePalavra, editarFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return palavras.size();
    }
}
