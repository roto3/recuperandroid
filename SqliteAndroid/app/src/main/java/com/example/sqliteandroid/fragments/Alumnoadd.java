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
import android.widget.Toast;

import com.example.sqliteandroid.MyDBAdapter;
import com.example.sqliteandroid.R;


public class Alumnoadd extends Fragment {

    private MyDBAdapter dbAdapter;

    private OnFragmentInteractionListener mListener;

    public Alumnoadd() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alumnoadd,container,false);

        Button btnadda = (Button) v.findViewById(R.id.btnadda);
        Button clear = (Button) v.findViewById(R.id.btnclerar);
        final EditText nom = (EditText) v.findViewById(R.id.txtnoma);
        final EditText edad= (EditText) v.findViewById(R.id.txtedada);
        final EditText curso= (EditText) v.findViewById(R.id.txtcursoa);
        final EditText ciclo= (EditText) v.findViewById(R.id.txtcicloa);
        final EditText notamedia= (EditText) v.findViewById(R.id.txtnota);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom.setText("");
                edad.setText("");
                curso.setText("");
                ciclo.setText("");
                notamedia.setText("");

            }
        });

        btnadda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nom.getText().toString();
                String cicl = ciclo.getText().toString();
                String nota = notamedia.getText().toString();
                String eda = edad.getText().toString();
                String cur = curso.getText().toString();

                if(nombre.equals("")||cicl.equals("")||nota.equals("")||eda.equals("")||cur.equals("")) {
                    Toast.makeText(getContext(), "Te faltan datos", Toast.LENGTH_SHORT).show();
                }else{
                    int ed = Integer.parseInt(eda);
                    int curs = Integer.parseInt(cur);
                    int notam = Integer.parseInt(nota);

                    nuevoalumn(nombre,cicl,notam,ed,curs);
                }

            }

        });

        return v;
    }

    private void nuevoalumn(String nombre, String cicl, int nota, int ed, int curs) {

        dbAdapter = new MyDBAdapter(getContext());
        dbAdapter.open();

        dbAdapter.insertalumno(nombre,ed,cicl,curs,nota);

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
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
