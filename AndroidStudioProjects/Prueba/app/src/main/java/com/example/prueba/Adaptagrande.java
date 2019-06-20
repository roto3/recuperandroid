package com.example.prueba;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.model.Juego;

import java.util.List;

public class Adaptagrande extends RecyclerView.Adapter<Adaptagrande.jHolder>{

    List<Juego> jueguiss;
    private Context micontext;

    public Adaptagrande(Context micontext, List<Juego> jueguiss){
        this.jueguiss = jueguiss;
        this.micontext = micontext;
    }

    @NonNull
    @Override
    public jHolder onCreateViewHolder(ViewGroup grupo, int i){
        View vv = LayoutInflater.from(grupo.getContext()).inflate(R.layout.layout_juegofull, grupo, false);
        jHolder holdeer = new jHolder(vv);

        return holdeer;
    }

    @Override
    public void onBindViewHolder(final jHolder holder, final int pos){
        final Juego j1 = jueguiss.get(pos);
        holder.titxtlist.setText(j1.getTitulo());
        holder.platxtlist.setText(j1.getPlataforma());
        holder.posetxtlist.setText(j1.getPosesion());
        holder.formatxtlist.setText(j1.getNuevo());
        holder.avatxtlist.setText(j1.getAvance());
        holder.vectxtlist.setText(j1.getVeces());
        cargapref(holder);
    }

    private void cargapref(jHolder holder) {

        final String backgrndcolor = PreferenceManager.getDefaultSharedPreferences(micontext).getString("list_preference_1", "");
        final String textcolor = PreferenceManager.getDefaultSharedPreferences(micontext).getString("list_preference_2","");


        switch (backgrndcolor){
            case "Naranja" :
                holder.contenedor.setBackgroundColor(Color.argb(255,255, 139, 30));
                break;
            case "Rojo" :
                holder.contenedor.setBackgroundColor(Color.argb(255,255, 0, 0));
                break;
            case "Amarillo" :
                holder.contenedor.setBackgroundColor(Color.argb(255,255, 255, 0));
                break;
            case "Verde" :
                holder.contenedor.setBackgroundColor(Color.argb(255,0, 255, 0));
                break;
            case "Azul" :
                holder.contenedor.setBackgroundColor(Color.argb(255,0, 0, 255));
                break;
            case "Morado" :
                holder.contenedor.setBackgroundColor(Color.argb(255,255, 0, 255));
                break;
            case "Blanco" :
                holder.contenedor.setBackgroundColor(Color.argb(255,255, 255, 255));
                break;
            case "Negro" :
                holder.contenedor.setBackgroundColor(Color.argb(255,0, 0, 0));
                break;
        }

        switch (textcolor) {
            case "Naranja":
                holder.titxtlist.setTextColor(Color.argb(255, 252, 164, 40));
                holder.platxtlist.setTextColor(Color.argb(255, 252, 164, 40));
                holder.posetxtlist.setTextColor(Color.argb(255, 252, 164, 40));
                holder.formatxtlist.setTextColor(Color.argb(255, 252, 164, 40));
                holder.avatxtlist.setTextColor(Color.argb(255, 252, 164, 40));
                holder.vectxtlist.setTextColor(Color.argb(255, 252, 164, 40));
                break;
            case "Rojo":
                holder.titxtlist.setTextColor(Color.argb(255, 252, 30, 30));
                holder.platxtlist.setTextColor(Color.argb(255, 252, 30, 30));
                holder.posetxtlist.setTextColor(Color.argb(255, 252, 30, 30));
                holder.formatxtlist.setTextColor(Color.argb(255, 252, 30, 30));
                holder.avatxtlist.setTextColor(Color.argb(255, 252, 30, 30));
                holder.vectxtlist.setTextColor(Color.argb(255, 252, 30, 30));
                break;
            case "Amarillo":
                holder.titxtlist.setTextColor(Color.argb(255, 255, 240, 76));
                holder.platxtlist.setTextColor(Color.argb(255, 255, 240, 76));
                holder.posetxtlist.setTextColor(Color.argb(255, 255, 240, 76));
                holder.formatxtlist.setTextColor(Color.argb(255, 255, 240, 76));
                holder.avatxtlist.setTextColor(Color.argb(255, 255, 240, 76));
                holder.vectxtlist.setTextColor(Color.argb(255, 255, 240, 76));
                break;
            case "Verde":
                holder.titxtlist.setTextColor(Color.argb(255, 42, 211, 56));
                holder.platxtlist.setTextColor(Color.argb(255, 42, 211, 56));
                holder.posetxtlist.setTextColor(Color.argb(255, 42, 211, 56));
                holder.formatxtlist.setTextColor(Color.argb(255, 42, 211, 56));
                holder.avatxtlist.setTextColor(Color.argb(255, 42, 211, 56));
                holder.vectxtlist.setTextColor(Color.argb(255, 42, 211, 56));
                break;
            case "Azul":
                holder.titxtlist.setTextColor(Color.argb(255, 32, 232, 225));
                holder.platxtlist.setTextColor(Color.argb(255, 32, 232, 225));
                holder.posetxtlist.setTextColor(Color.argb(255, 32, 232, 225));
                holder.formatxtlist.setTextColor(Color.argb(255, 32, 232, 225));
                holder.avatxtlist.setTextColor(Color.argb(255, 32, 232, 225));
                holder.vectxtlist.setTextColor(Color.argb(255, 32, 232, 225));
                break;
            case "Morado":
                holder.titxtlist.setTextColor(Color.argb(255, 182, 104, 255));
                holder.platxtlist.setTextColor(Color.argb(255, 182, 104, 255));
                holder.posetxtlist.setTextColor(Color.argb(255, 182, 104, 255));
                holder.formatxtlist.setTextColor(Color.argb(255, 182, 104, 255));
                holder.avatxtlist.setTextColor(Color.argb(255, 182, 104, 255));
                holder.vectxtlist.setTextColor(Color.argb(255, 182, 104, 255));
                break;
            case "Blanco":
                holder.titxtlist.setTextColor(Color.argb(255, 255, 255, 255));
                holder.platxtlist.setTextColor(Color.argb(255, 255, 255, 255));
                holder.posetxtlist.setTextColor(Color.argb(255, 255, 255, 255));
                holder.formatxtlist.setTextColor(Color.argb(255, 255, 255, 255));
                holder.avatxtlist.setTextColor(Color.argb(255, 255, 255, 255));
                holder.vectxtlist.setTextColor(Color.argb(255, 255, 255, 255));
                break;
            case "Negro":
                holder.titxtlist.setTextColor(Color.argb(255, 0,0,0));
                holder.platxtlist.setTextColor(Color.argb(255, 0,0,0));
                holder.posetxtlist.setTextColor(Color.argb(255, 0,0,0));
                holder.formatxtlist.setTextColor(Color.argb(255, 0,0,0));
                holder.avatxtlist.setTextColor(Color.argb(255, 0,0,0));
                holder.vectxtlist.setTextColor(Color.argb(255, 0,0,0));
                break;

        }

    }


    @Override
    public int getItemCount() {
        return jueguiss.size();
    }

    public static class jHolder extends RecyclerView.ViewHolder{

        public TextView titxtlist;
        public TextView platxtlist;
        public TextView posetxtlist;
        public TextView formatxtlist;
        public TextView avatxtlist;
        public TextView vectxtlist;
        public LinearLayout contenedor;

        //SharedPreferences preferences;

        public jHolder(View itemViewer){
            super(itemViewer);
            titxtlist=(TextView) itemView.findViewById(R.id.titxtlist);
            platxtlist=(TextView) itemView.findViewById(R.id.platxtlist);
            posetxtlist=(TextView) itemView.findViewById(R.id.posetxtlist);
            formatxtlist=(TextView) itemView.findViewById(R.id.formatxtlist);
            avatxtlist=(TextView) itemView.findViewById(R.id.avatxtlist);
            vectxtlist=(TextView) itemView.findViewById(R.id.vectxtlist);
            contenedor=(LinearLayout) itemView.findViewById(R.id.contene2);


        }
    }
}