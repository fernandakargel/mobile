package br.com.nandak.;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends Fragment {

    public MenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mae:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new br.com.nandak..mae.MainFragment()).commit();
                break;
            case R.id.menu_lista:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new br.com.nandak..lista.MainFragment()).commit();
                break;
            case R.id.menu_palavra:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new br.com.nandak..palavra.MainFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}