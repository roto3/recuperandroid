package com.example.prueba.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prueba.Adaptarecycler;
import com.example.prueba.R;
import com.example.prueba.model.Juego;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListFragment extends Fragment implements View.OnClickListener{

    private RecyclerView rcv;
    private Adaptarecycler rAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Juego> juegoss;
    //private ListView lstvw;

    DatabaseReference ddbbr;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        rcv = (RecyclerView) v.findViewById(R.id.recyclermini);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        juegoss = new ArrayList<>();
        rAdapter = new Adaptarecycler(getContext(),juegoss);
        rcv.setAdapter(rAdapter);
        recarga();


        /*
        lstvw = v.findViewById(R.id.lstvwlistamini);
        ddbbr = FirebaseDatabase.getInstance().getReference("juegos");
        ddbbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adapter;
                ArrayList<String> list = new ArrayList<String>();

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()) {

                    Juego juego =  datasnapshot.getValue(Juego.class);

                    String titulo = juego.getTitulo();
                    list.add(titulo);

                }
                adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
                lstvw.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        */

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {


    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void recarga(){

        ddbbr = FirebaseDatabase.getInstance().getReference("juegos");
        ddbbr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                juegoss.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Juego j1 = dataSnapshot1.getValue(Juego.class);
                    juegoss.add(j1);
                }
                Collections.reverse(juegoss);
                rAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}



// ImageView imageView = (ImageView) getView().findViewById(R.id.fragment2);



