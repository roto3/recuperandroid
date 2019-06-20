package com.example.sqliteandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String pref = "mypreferences";

    Button btn;
    Button guardar;
    Button cargar;
    EditText nom;
    EditText usu;
    EditText nac;
    RadioButton hom;
    RadioButton muj;
    RadioGroup grupo;

    Button sqlito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guardar = findViewById(R.id.guardar);
        cargar = findViewById(R.id.cargar);
        btn  = findViewById(R.id.button);
        nom = findViewById(R.id.nom);
        usu = findViewById(R.id.nomUsu);
        nac = findViewById(R.id.fechaNac);
        hom = findViewById(R.id.hombre);
        muj = findViewById(R.id.mujer);
        grupo = findViewById(R.id.radioGroup);

        sqlito = findViewById(R.id.asqlito);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Limpiado", Toast.LENGTH_SHORT).show();
                nom.setText("");
                usu.setText("");
                nac.setText("");
                grupo.clearCheck();

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences mySharedPreferences = getSharedPreferences(pref, Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();

                final String nombre, user, nacim, sexo ;

                nombre = nom.getText().toString();
                user = usu.getText().toString();
                nacim = nac.getText().toString();

                if(hom.isChecked()){
                    sexo = "hombre";
                }else{
                    sexo = "mujer";
                }

                editor.putString("nombre", nombre);
                editor.putString("nomUsu", user);
                editor.putString("fecha", nacim);
                editor.putString("sexo", sexo);

                editor.commit();

                Toast.makeText(MainActivity.this, "Añadido con éxito", Toast.LENGTH_SHORT).show();
                nom.setText("");
                usu.setText("");
                nac.setText("");
                grupo.clearCheck();

            }
        });

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mySharedPreferences = getSharedPreferences(pref, Activity.MODE_PRIVATE);

                final String nombre, user, nacim, sexo ;

                 nombre = mySharedPreferences.getString("nombre","");
                 user = mySharedPreferences.getString("nomUsu", "");
                 nacim = mySharedPreferences.getString("fecha", "");
                 sexo = mySharedPreferences.getString("sexo", "");

                nom.setText(nombre);
                usu.setText(user);
                nac.setText(nacim);
                if(sexo.equals("mujer")){
                    muj.setChecked(true);
                }else{
                    hom.setChecked(true);
                }

                Toast.makeText(MainActivity.this, "El usuario "+user+" de nombre "+nombre+" nacida el "+nacim+" de género "+sexo+" es la guardada.", Toast.LENGTH_SHORT).show();
            }
        });

        sqlito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Sqlitomain.class);
                startActivity(intent);
            }
        });
    }
}
