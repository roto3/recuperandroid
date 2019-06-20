package com.example.prueba;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.prueba.model.Juego;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {

    //ListView listatocha;
    DatabaseReference bbdd;
    Button btnreestablecer;

    RecyclerView rcvg;
    Adaptagrande rAdapterg;
    RecyclerView.LayoutManager layoutManagerg;
    List<Juego> juegostocha;

    Spinner spncons;
    Spinner spnpos;
    Spinner spnavan;
    Spinner spnvec;

    Spinner spnuevo;

    String prefs = "Preferencias";

    final String ejemplito = "list_preference_1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
       // listatocha = (ListView) findViewById(R.id.lstvwlistgrand);

        btnreestablecer = (Button) findViewById(R.id.btntitlsrc);
        rcvg = (RecyclerView) findViewById(R.id.recyclergrande);
        rcvg.setLayoutManager(new LinearLayoutManager(getApplicationContext())); //a ver si
        juegostocha = new ArrayList<>();
        rAdapterg = new Adaptagrande(getApplicationContext(),juegostocha);
        rcvg.setAdapter(rAdapterg);

        bbdd = FirebaseDatabase.getInstance().getReference("juegos");

        spncons = (Spinner) findViewById(R.id.spnplataformal);
        spnpos = (Spinner) findViewById(R.id.spnposesionl);
        spnavan = (Spinner) findViewById(R.id.spnavancel);
        spnvec = (Spinner) findViewById(R.id.spnvecesl);

        spnuevo = (Spinner) findViewById(R.id.spnuevol);

        SharedPreferences mypreferences = getSharedPreferences(prefs, Activity.MODE_PRIVATE);

        //final String manolo = mypreferences.getString("manolito","");


       final String c = PreferenceManager.getDefaultSharedPreferences(this).getString(ejemplito, "");

        /*
        spncons.setSelection(0);
        spnpos.setSelection(0);
        spnavan.setSelection(0);
        spnvec.setSelection(0);
        */

        String[] plataformas = new String[] {"Plataforma","PC", "PS2", "PS3", "PS4", "N64", "Gamecube", "Wii", "WiiU", "NSwitch", "Xbox", "Xbox360", "XboxOne", "PSone", "GameBoy", "GBA", "DS", "3DS", "PSP", "PSVita"}; //2-8 | 2.2 3.8 4.2 5.1 6.1 7.4 8.1
        String[] avance = new String[] {"Avance","Nulo", "Probado", "Intermedio", "Pasado", "Completado", "Irrelevante"}; //4-11 | 4.1 6.1 7.1 10.2 11.1
        String[] veces = new String[] {"Veces","0","1","2","3-5","+5"}; //0-3
        String[] posesion = new String[] {"Posesión","Si","No"};  //siempre igual

        String[] nuevo = new String[] {"Nuevo","Si","No"};

        ArrayAdapter<String> adaptaforms = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plataformas);
        ArrayAdapter<String> adaptavanc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, avance);
        ArrayAdapter<String> adaptveces = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, veces);
        ArrayAdapter<String> adaptapose = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posesion);

        ArrayAdapter<String> adaptanuev = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nuevo);

        spncons.setAdapter(adaptaforms);
        spnvec.setAdapter(adaptveces);
        spnavan.setAdapter(adaptavanc);
        spnpos.setAdapter(adaptapose);

        spnuevo.setAdapter(adaptanuev);



        spncons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               cosa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnpos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cosa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnavan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cosa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnvec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cosa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnuevo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cosa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnreestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spncons.setSelection(0);
                spnuevo.setSelection(0);
                spnpos.setSelection(0);
                spnavan.setSelection(0);
                spnvec.setSelection(0);
                //Toast toast = Toast.makeText(getApplicationContext(),ejemplito+" 4"+c,Toast.LENGTH_LONG);
                //toast.show();
            }
        });
    }


    protected void cosa(){
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                juegostocha.clear();
                //ArrayAdapter<String> adaptadorr;
                //ArrayList<String> lista = new ArrayList<String>();
                String busqueda = "Buscando ";

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    dataSnapshot.getValue(Juego.class);
                    Juego juego = datasnapshot.getValue(Juego.class);

                    String titulo = juego.getTitulo();
                    String plataforma = juego.getPlataforma();
                    String poses = juego.getPosesion();
                    String forma = juego.getNuevo();
                    String avanc = juego.getAvance();
                    String veces = juego.getVeces();
                    //String juegolst = titulo;

                    //esto antes iba por listview



                    //juegolst = juegolst+" |  "+"  |  "+plataforma+"  |  "+poses+"  |  "+forma+"  |  "+avanc+"  |  "+veces;


                    if (spncons.getSelectedItem().toString().equals("Plataforma")&&spnpos.getSelectedItem().toString().equals("Posesión")&&spnavan.getSelectedItem().toString().equals("Avance")&&spnvec.getSelectedItem().toString().equals("Veces")&&spnuevo.getSelectedItem().toString().equals("Nuevo")){
                        //lista.add(juegolst);
                        juegostocha.add(juego);
                        busqueda = "Buscando todos los ";
                    }else{
                        if (plataforma.equals(spncons.getSelectedItem().toString())|| spncons.getSelectedItem().toString().equals("Plataforma")){
                            if (poses.equals(spnpos.getSelectedItem().toString())|| spnpos.getSelectedItem().toString().equals("Posesión")){
                                if (forma.equals(spnuevo.getSelectedItem().toString())|| spnuevo.getSelectedItem().toString().equals("Nuevo")){
                                    if (avanc.equals(spnavan.getSelectedItem().toString())|| spnavan.getSelectedItem().toString().equals("Avance")){
                                        int numveces = Integer.parseInt(veces);

                                        if (spnvec.getSelectedItem().toString().equals("Veces")){
                                            //lista.add(juegolst);
                                            juegostocha.add(juego);
                                        }else{
                                            switch (numveces) {
                                                case 0:
                                                    if (spnvec.getSelectedItem().toString().equals("0")){
                                                        //lista.add(juegolst);
                                                        juegostocha.add(juego);
                                                    }
                                                    break;
                                                case 1:
                                                    if (spnvec.getSelectedItem().toString().equals("1")){
                                                        //lista.add(juegolst);
                                                        juegostocha.add(juego);
                                                    }
                                                    break;
                                                case 2:
                                                    if (spnvec.getSelectedItem().toString().equals("2")){
                                                        //lista.add(juegolst);
                                                        juegostocha.add(juego);
                                                    }
                                                    break;
                                                case 3:
                                                    if (spnvec.getSelectedItem().toString().equals("3-5")){
                                                        //lista.add(juegolst);
                                                        juegostocha.add(juego);
                                                    }
                                                    break;
                                                case 4:
                                                    if (spnvec.getSelectedItem().toString().equals("3-5")){
                                                        //lista.add(juegolst);
                                                        juegostocha.add(juego);
                                                    }
                                                    break;
                                                case 5:
                                                    if (spnvec.getSelectedItem().toString().equals("3-5")){
                                                        //lista.add(juegolst);
                                                        juegostocha.add(juego);
                                                    }
                                                    break;
                                                    default:
                                                        if (spnvec.getSelectedItem().toString().equals("+5")){
                                                            //lista.add(juegolst);
                                                            juegostocha.add(juego);
                                                    }
                                                        break;

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }



                }

                rAdapterg.notifyDataSetChanged();

                //adaptadorr = new ArrayAdapter<String>(Lista.this,android.R.layout.simple_list_item_1,lista);
              //  listatocha.setAdapter(adaptadorr);

                if (spncons.getSelectedItem().toString().equals("Plataforma")){
                    busqueda = busqueda + "juegos para cualquier plataforma, ";
                }else{
                    busqueda = busqueda + "juegos para "+spncons.getSelectedItem().toString()+", ";
                }
                if (spnpos.getSelectedItem().toString().equals("Si")){
                    busqueda = busqueda + "en posesión, ";
                }else{
                    if (spnpos.getSelectedItem().toString().equals("No")){
                        busqueda = busqueda + "NO en posesión, ";
                    }
                }

                busqueda = busqueda +"con un grado de avance ";
                if (spnavan.getSelectedItem().toString().equals("Avance")){
                    busqueda = busqueda+"cualquiera y pasado ";
                }else{
                    busqueda = busqueda+spnavan.getSelectedItem().toString()+" y pasado ";
                }
                if (spnvec.getSelectedItem().toString().equals("Veces")){
                    busqueda = busqueda+"cuantas veces sea";
                }else{
                    busqueda = busqueda+spnvec.getSelectedItem().toString()+" veces.";
                }

                if (spnuevo.getSelectedItem().toString().equals("Si")){
                    busqueda = busqueda + "y nuevo";
                }else{
                    if (spnuevo.getSelectedItem().toString().equals("No")){
                        busqueda = busqueda + "y usado";
                    }else{
                        busqueda = busqueda + "viejo o usado";
                    }
                }

                //Toast toast = Toast.makeText(getApplicationContext(),busqueda,Toast.LENGTH_LONG);
                //toast.show();

                //Ahora es un snackbar lo que se crea con la info. de la búsqueda, y se queda hasta que la cierres

                final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), busqueda, Snackbar.LENGTH_INDEFINITE);

                snackBar.setAction("Entendido", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackBar.dismiss();
                    }
                });
                snackBar.show();

            } //aqui acaba el on data change

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
