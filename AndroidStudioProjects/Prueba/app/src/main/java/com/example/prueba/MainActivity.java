package com.example.prueba;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prueba.fragments.ButprinFragment;
import com.example.prueba.fragments.ListFragment;

public class MainActivity extends AppCompatActivity implements ButprinFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener {

    private FragmentManager fm;
    private Fragment f1;

    public static final String preff = "Preferencias";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        ListFragment listFragment = new ListFragment();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        fragmentTransaction.add(R.id.hueco,listFragment);
        fragmentTransaction.commit();

        SharedPreferences mypreferences = getSharedPreferences(preff, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = mypreferences.edit();

        edit.putString("manolito","holi");

        edit.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}