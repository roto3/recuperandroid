package com.example.prueba;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prueba.model.Juego;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Addmod extends AppCompatActivity {

    //Declaramos todos los objetos del XML mas algún string que servirá para pasar el valor luego a la BBDD

    Button btnok;
    Button btncan;
    Spinner spnplat;
    Spinner spnav;
    EditText txtitl;
    EditText txtvec;
    RadioGroup rdgpos;
    RadioGroup rdgnuev;
    RadioButton rdbtn1;
    RadioButton rdbtn2;
    String poses;
    String avanc;
    String plat;
    String nuevo;
    int unico;

    //y la referencia de base de datos

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmod);

        //Asignamos a cada cosa lo suyo

        btnok = (Button) findViewById(R.id.btnokadd);
        btncan = (Button) findViewById(R.id.btncanceladd);
        spnplat = (Spinner) findViewById(R.id.spnplatfo);
        spnav = (Spinner) findViewById(R.id.spnavan);
        txtitl = (EditText) findViewById((R.id.txtTitle));
        txtvec = (EditText) findViewById(R.id.txtveces);
        rdgpos = (RadioGroup) findViewById(R.id.rg);
        rdgnuev = (RadioGroup) findViewById(R.id.rg2);
        poses = "No";
        nuevo = "No";
        avanc = "Nulo";
        plat = "PC";
        unico = 0;

        //Le decimos dónde meter los datos en la BBDD

        bbdd = FirebaseDatabase.getInstance().getReference("juegos");

        // Creamos los listeners de los 2 botones importantes

        final Intent i = getIntent();
        final Juego jjj = i.getParcelableExtra("jue");

        if (jjj != null){
            txtitl.setText(jjj.getTitulo());
            txtvec.setText(jjj.getVeces());

        }

        btnok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // primero pasamos todos los valores a Strings

                String titlo = txtitl.getText().toString();
                String veces = txtvec.getText().toString();
                avanc = spnav.getSelectedItem().toString();
                plat = spnplat.getSelectedItem().toString();

                // comprobamos que el juego tenga título
                // si lo tiene, lo demás ya estaría puesto (aunque sea por defecto)

                if(!TextUtils.isEmpty(titlo)){


                    if (!avanc.equals("Pasado") && !avanc.equals("Completado")) {
                        txtvec.setText("0");
                        veces = "0";
                    }
                    final Juego j = new Juego(titlo,plat,poses,nuevo,avanc,veces);

                    if (jjj == null){

                        Query q = bbdd.orderByChild("titulo").equalTo(j.getTitulo());
                        q.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                unico = 0;
                                for (DataSnapshot dtsnpcht: dataSnapshot.getChildren()){
                                    unico++;


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                        if (unico == 0){
                            String clave = bbdd.push().getKey();
                            bbdd.child(clave).setValue(j);

                            Toast toast = Toast.makeText(getApplicationContext(),"Añadido con éxito",Toast.LENGTH_SHORT);
                            toast.show();
                            Addmod.this.finish();
                        }else{

                            Toast toast = Toast.makeText(getApplicationContext(),"Nombre repetido, juego existente en BBDD",Toast.LENGTH_SHORT);
                            toast.show();
                        }



                    }else{

                        Query q = bbdd.orderByChild("titulo").equalTo(jjj.getTitulo());
                        q.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot dtsnpcht: dataSnapshot.getChildren()){
                                    String clave =dtsnpcht.getKey();
                                    bbdd.child(clave).setValue(j);


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                        Toast toast = Toast.makeText(getApplicationContext(),"Modificado con éxito",Toast.LENGTH_SHORT);
                        toast.show();
                        Addmod.this.finish();
                    }

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Te falta el título",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    // El botón cancelar simplemente vuelve atrás sin realizar ningún cambio

        btncan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Operación cancelada",Toast.LENGTH_SHORT);
                toast.show();

                Addmod.this.finish();
            }
        });

    // Aquí como en principio no querríamos añadir ninguna plataforma más, pasamos los valores a los spinner a través de un array.

        String[] plataformas = new String[] {"PC", "PS2", "PS3", "PS4", "N64", "Gamecube", "Wii", "WiiU", "NSwitch", "Xbox", "Xbox360", "XboxOne", "PSone", "GameBoy", "GBA", "DS", "3DS", "PSP", "PSVita"};
        String[] avance = new String[] {"Nulo", "Probado", "Intermedio", "Pasado", "Completado", "Irrelevante"};

        ArrayAdapter<String> adaptaforms = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plataformas);
        ArrayAdapter<String> adaptavanc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, avance);

        spnplat.setAdapter(adaptaforms);
        spnav.setAdapter(adaptavanc);
        if (jjj != null){
            switch (jjj.getPlataforma()){
                case "PC":
                    spnplat.setSelection(0);
                    break;
                case "PS2":
                    spnplat.setSelection(1);
                    break;
                case "PS3":
                    spnplat.setSelection(2);
                    break;
                case "PS4":
                    spnplat.setSelection(3);
                    break;
                case "N64":
                    spnplat.setSelection(4);
                    break;
                case "Gamecube":
                    spnplat.setSelection(5);
                    break;
                case "Wii":
                    spnplat.setSelection(6);
                    break;
                case "WiiU":
                    spnplat.setSelection(7);
                    break;
                case "NSwitch":
                    spnplat.setSelection(8);
                    break;
                case "Xbox":
                    spnplat.setSelection(9);
                    break;
                case "Xbox360":
                    spnplat.setSelection(10);
                    break;
                case "XboxOne":
                    spnplat.setSelection(11);
                    break;
                case "PSone":
                    spnplat.setSelection(12);
                    break;
                case "GameBoy":
                    spnplat.setSelection(13);
                    break;
                case "GBA":
                    spnplat.setSelection(14);
                    break;
                case "DS":
                    spnplat.setSelection(15);
                    break;
                case "3DS":
                    spnplat.setSelection(16);
                    break;
                case "PSP":
                    spnplat.setSelection(17);
                    break;
                case "PSVita":
                    spnplat.setSelection(18);
                    break;
                default:
                    spnplat.setSelection(0);
            }
            switch (jjj.getAvance()){
                case "Nulo":
                    spnav.setSelection(0);
                    break;
                case "Probado":
                    spnav.setSelection(1);
                    break;
                case "Intermedio":
                    spnav.setSelection(2);
                    break;
                case "Pasado":
                    spnav.setSelection(3);
                    break;
                case "Completado":
                    spnav.setSelection(4);
                    break;
                case "Irrelevante":
                    spnav.setSelection(5);
                    break;
                    default:
                        spnav.setSelection(0);
            }

        }

    }

    // Este método recoge el valor de los dos cuatro radiobutton y se encarga de pasarle la información como String (Si o No) cada vez que cambiamos cualquiera de los dos grupos

    public void checkButton(View v){

        int rdbposId = rdgpos.getCheckedRadioButtonId();
        rdbtn1 = findViewById(rdbposId);

        int rdbnuevoId = rdgnuev.getCheckedRadioButtonId();
        rdbtn2 = findViewById(rdbnuevoId);

        //Toast toast = Toast.makeText(getApplicationContext(),"Ha seleccionado"+rdbtn1.getText()+" ",Toast.LENGTH_SHORT);
        //toast.show();
        poses = (rdbtn1.getText().toString());
        nuevo = (rdbtn2.getText().toString());


    }

    // Este método de momento es basura, pero serviría para cada vez que alguien escoge las opciones del Spinner
    // "Nulo" o "irrelevante", automáticamente ponga a 0 el número de veces pasado (por si se ha modificado)
    // y de paso, cambia el valor al string que almacena en cada momento que se cambia el valor del spinner
    /*
    public void checkplat(View v){
        avanc = spnav.getSelectedItem().toString();

        if (!avanc.equals("Pasado")||!avanc.equals("Completado")){
            txtvec.setText("0");
            txtvec.setEnabled(false);
        }else{
            txtvec.setEnabled(true);
        }
    }
    */
}
