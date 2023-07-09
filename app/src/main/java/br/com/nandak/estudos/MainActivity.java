package br.com.nandak.estudos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.nandak.estudos.mae.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, new MainFragment()).commit();
        }
    }
}