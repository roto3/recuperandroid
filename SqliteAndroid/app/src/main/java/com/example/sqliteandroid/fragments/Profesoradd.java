package com.example.sqliteandroid.fragments;

import android.content.Context;
import android.content.Intent;
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


public class Profesoradd extends Fragment {


    private MyDBAdapter dbAdapter;


    private OnFragmentInteractionListener mListener;

    public Profesoradd() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profesoradd,container,false);

        Button btnaddp = (Button) v.findViewById(R.id.btnguarprof);
        Button clear = (Button) v.findViewById(R.id.btnlimprof);
        final EditText nom = (EditText) v.findViewById(R.id.txtnomp);
        final EditText edad= (EditText) v.findViewById(R.id.txtedadp);
        final EditText curso= (EditText) v.findViewById(R.id.txtcursop);
        final EditText ciclo= (EditText) v.findViewById(R.id.txtciclop);
        final EditText despacho= (EditText) v.findViewById(R.id.txtdespach);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom.setText("");
                edad.setText("");
                curso.setText("");
                ciclo.setText("");
                despacho.setText("");

            }
        });

        btnaddp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nom.getText().toString();
                String cicl = ciclo.getText().toString();
                String desp = despacho.getText().toString();
                String eda = edad.getText().toString();
                String cur = curso.getText().toString();



                if(nombre.equals("")||cicl.equals("")||desp.equals("")||eda.equals("")||cur.equals("")){
                    Toast.makeText(getContext(), "Te faltan datos", Toast.LENGTH_SHORT).show();
                }else{

                    int ed = Integer.parseInt(eda);
                    int curs = Integer.parseInt(cur);
                    //Toast.makeText(getContext(), "Oki "+nombre+" "+cicl+" "+desp+" "+ed+" "+curs, Toast.LENGTH_SHORT).show();
                    nuevoprof(nombre,cicl,desp,ed,curs);
                }

            }
        });

        return v;
    }

    private void nuevoprof(String nombre, String cicl, String desp, int ed, int curs) {

        dbAdapter = new MyDBAdapter(getContext());
        dbAdapter.open();



        dbAdapter.insertaprof(nombre,ed,cicl,curs,desp);

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
