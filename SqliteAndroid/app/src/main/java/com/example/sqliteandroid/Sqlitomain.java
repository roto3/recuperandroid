package com.example.sqliteandroid;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sqliteandroid.fragments.Alumnoadd;
import com.example.sqliteandroid.fragments.CargaFragment;
import com.example.sqliteandroid.fragments.Profesoradd;

public class Sqlitomain extends AppCompatActivity {

    Button alumn;
    Button prof;
    Button carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlitomain);

        alumn  = findViewById(R.id.btnalumn);
        prof  = findViewById(R.id.btnprof);
        carga = findViewById(R.id.btncarg);

        alumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumnoadd frag = new Alumnoadd();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.hueco,frag);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profesoradd frag = new Profesoradd();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.hueco,frag);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        carga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaFragment fragment = new CargaFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.hueco,fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
