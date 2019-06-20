package com.example.sqliteandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDBAdapter {

    private static final String DATABASE_NAME = "clase.db";
    private static final String DATABASE_TABLE_PROFE = "profes";
    private static final String DATABASE_TABLE_ALUMNO = "alumnos";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE_PROFE = "CREATE TABLE "+DATABASE_TABLE_PROFE+" (_id integer primary key autoincrement, nom text, edad integer,ciclo text,curso integer,despacho text);";
    private static final String DATABASE_CREATE_ALUMNO = "CREATE TABLE "+DATABASE_TABLE_ALUMNO+" (_id integer primary key autoincrement, nom text, edad integer,ciclo text,curso integer,notamedia float);";
    private static final String DATABASE_DROP_PROFE = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFE+";";
    private static final String DATABASE_DROP_ALUMNO = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ALUMNO+";";


    private final Context context;
    private MyDbHelper dbHelper;
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context,DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void open(){
       try{
           db = dbHelper.getWritableDatabase() ;
       }catch (SQLiteException e){
           db = dbHelper.getReadableDatabase();
       }
    }

    public void insertalumno(String nom, int edad,String ciclo,int curso,float notamedia){

        ContentValues newValues = new ContentValues();

        newValues.put("nom",nom);
        newValues.put("edad",edad);
        newValues.put("ciclo",ciclo);
        newValues.put("curso",curso);
        newValues.put("notamedia",notamedia);

        db.insert(DATABASE_TABLE_ALUMNO,null,newValues);

    }

    public void insertaprof(String nom, int edad,String ciclo,int curso,String despacho){
        ContentValues newValues = new ContentValues();

        newValues.put("nom",nom);
        newValues.put("edad",edad);
        newValues.put("ciclo",ciclo);
        newValues.put("curso",curso);
        newValues.put("despacho",despacho);

        db.insert(DATABASE_TABLE_PROFE,null,newValues);
    }

    public ArrayList<String> recuprofes(){
        ArrayList<String> profes = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_PROFE,null,null,null,null,null,null);

        if (cursor != null && cursor.moveToFirst()){
            do{
                profes.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));

            }while (cursor.moveToNext());
        }

        return profes;
    }
    public ArrayList<String> recualumnos(){
        ArrayList<String> alumn = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,null);

        if (cursor != null && cursor.moveToFirst()){
            do{
                alumn.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));

            }while (cursor.moveToNext());
        }

        return alumn;
    }

    public ArrayList <String> recutodo(){
        ArrayList<String> todomezcla = new ArrayList<>();

        todomezcla = recualumnos();
        todomezcla.addAll(recuprofes());

        return todomezcla;
    }

    public ArrayList<String> alumcurso(String curso){
        ArrayList<String> alumnoscurso = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,"curso");

        if (cursor != null && cursor.moveToFirst()){
            do{
                String temporal = cursor.getString(4);
                if (temporal.trim().equalsIgnoreCase(curso)){
                    alumnoscurso.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));

                }
            }while (cursor.moveToNext());
        }


        return alumnoscurso;
    }

    public ArrayList<String> alumciclo(String ciclo){
        ArrayList<String> alumnosciclo = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,"curso");

        if (cursor != null && cursor.moveToFirst()){
            do{
                String temporal = cursor.getString(3);
                if (temporal.trim().equalsIgnoreCase(ciclo)){
                    alumnosciclo.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));

                }
            }while (cursor.moveToNext());
        }


        return alumnosciclo;
    }

    public ArrayList<String> alumnombre(String nom){
        ArrayList<String> alumnombres = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,"nom");

        if (cursor != null && cursor.moveToFirst()){
            do{
                String temporal = cursor.getString(1);
                if (temporal.trim().equalsIgnoreCase(nom)){
                    alumnombres.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));

                }
            }while (cursor.moveToNext());
        }


        return alumnombres;
    }

    private static class MyDbHelper extends SQLiteOpenHelper{


        public MyDbHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_PROFE);
            db.execSQL(DATABASE_CREATE_ALUMNO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROFE);
            db.execSQL(DATABASE_DROP_ALUMNO);
            onCreate(db);
        }
    }
}
