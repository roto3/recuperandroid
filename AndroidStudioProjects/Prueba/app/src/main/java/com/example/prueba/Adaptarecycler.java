package com.example.prueba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.prueba.dialogs.SegurDialog;
import com.example.prueba.model.Juego;

import java.util.List;

public class Adaptarecycler extends RecyclerView.Adapter<Adaptarecycler.ViewHolder> {

    List<Juego> jueguis;
    private Context mcontext;

    public Adaptarecycler(Context mcontext,List<Juego> jueguis){

        this.jueguis = jueguis;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_juego, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder( final ViewHolder viewHolder,final int i) {
        final Juego j1 = jueguis.get(i);
        viewHolder.titltxt.setText(j1.getTitulo());
        viewHolder.paltmaini.setText(j1.getPlataforma());
        viewHolder.aancemini.setText(j1.getAvance());


        viewHolder.btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentActivity activity = (FragmentActivity)(mcontext);
                FragmentManager manager = activity.getSupportFragmentManager();

                SegurDialog mdialog = new SegurDialog();
                Bundle b = new Bundle();
                b.putString("titl",j1.getTitulo());
                mdialog.setArguments(b);

                mdialog.show(manager,"SegurDialog");
            }
        });

        viewHolder.titltxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, Addmod.class);

                //Bundle b = new Bundle();
                //b.putParcelable("juego",j1);

                i.putExtra("jue",j1);
                mcontext.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return jueguis.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titltxt;
        public ImageButton btndelet;
        public TextView paltmaini;
        public TextView aancemini;
        public ViewHolder( View itemView) {
            super(itemView);
            titltxt=(TextView) itemView.findViewById(R.id.titltxt);
            btndelet=(ImageButton) itemView.findViewById(R.id.btndelet);
            paltmaini=(TextView) itemView.findViewById(R.id.plataformaini);
            aancemini=(TextView) itemView.findViewById(R.id.avancemini);
        }
    }
}
