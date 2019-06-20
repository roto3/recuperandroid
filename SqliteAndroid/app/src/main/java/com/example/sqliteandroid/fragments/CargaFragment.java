package com.example.sqliteandroid.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteandroid.MyDBAdapter;
import com.example.sqliteandroid.R;

import java.util.ArrayList;
import java.util.Iterator;


public class CargaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private MyDBAdapter dbAdapter;

    public CargaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_carga, container, false);

        Button btntodoprofes = (Button) v.findViewById(R.id.btntodoprofe);
        Button btntodoalum = (Button) v.findViewById(R.id.btntodoalumno);
        Button btntodos = (Button) v.findViewById(R.id.btnroyale);
        Button btnombre= (Button) v.findViewById(R.id.btnombrealum);
        Button btnciclo= (Button) v.findViewById(R.id.btncicloalum);
        final EditText txtnom = (EditText) v.findViewById(R.id.buscapornombre);
        final EditText txtciclo = (EditText) v.findViewById(R.id.buscaporciclo);
        final TextView txtcarga = (TextView) v.findViewById(R.id.textocarga);

        dbAdapter = new MyDBAdapter(getContext());
        dbAdapter.open();

        btntodoprofes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> recuperaprofes = dbAdapter.recuprofes();
                String cosa = "";

                Iterator<String> it = recuperaprofes.iterator();

                while (it.hasNext()){
                    cosa = cosa+it.next()+"\n";
                }

                txtcarga.setText(cosa);
            }
        });
        btntodoalum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> recuperalumn = dbAdapter.recualumnos();
                String cosa = "";

                Iterator<String> it = recuperalumn.iterator();

                while (it.hasNext()){
                    cosa = cosa+it.next()+"\n";
                }

                txtcarga.setText(cosa);

            }
        });
        btntodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> recuperatodo = dbAdapter.recutodo();
                String cosa = "";

                Iterator<String> it = recuperatodo.iterator();

                while (it.hasNext()){
                    cosa = cosa+it.next()+"\n";
                }

                txtcarga.setText(cosa);
            }
        });

        btnombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = txtnom.getText().toString();
                if (nom.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Pon el nombre", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<String> renombre = dbAdapter.alumnombre(nom);
                    String cosa = "";

                    Iterator<String> it = renombre.iterator();

                    while (it.hasNext()){
                        cosa = cosa+it.next()+"\n";
                    }

                    txtcarga.setText(cosa);
                }
            }
        });

        btnciclo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cicl = txtciclo.getText().toString();
                if (cicl.equalsIgnoreCase("")){
                    Toast.makeText(getContext(), "Pon el ciclo", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<String> reciclo = dbAdapter.alumciclo(cicl);
                    String cosa = "";

                    Iterator<String> it = reciclo.iterator();

                    while (it.hasNext()){
                        cosa = cosa+it.next()+"\n";
                    }

                    txtcarga.setText(cosa);
                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
