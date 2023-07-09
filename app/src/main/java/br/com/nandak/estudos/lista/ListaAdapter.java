package br.com.nandak.estudos.lista;

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

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ListaViewHolder>{
    private final List<Lista> listas;
    private int id_lista;
    private final FragmentActivity activity;

    ListaAdapter(List<Lista> listas, FragmentActivity activity){
        this.listas = listas;
        this.activity = activity;
    }

    static class ListaViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomeView;
        private final TextView celularView;

        ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeView = itemView.findViewById(R.id.tvListListaNome);
            celularView = itemView.findViewById(R.id.tvListListaCelular);
        }
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item, parent, false);
        return new ListaViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ListaViewHolder viewHolder, int i) {
        viewHolder.nomeView.setText(listas.get(i).getNome());
        viewHolder.celularView.setText(listas.get(i).getCelular());
        id_lista = listas.get(i).getId();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id", id_lista);

                EditarFragment editarFragment = new EditarFragment();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                editarFragment.setArguments(b);
                ft.replace(R.id.frameLista, editarFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }
}
