package com.example.prueba.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SegurDialog extends DialogFragment {

    private TextView ito;
    private Button buttonbtn1;
    private Button buttonbtn2;
    private String txt;

    DatabaseReference bbbddd;

    public SegurDialog(){

    }

    public static SegurDialog newInstance(String texto){
        SegurDialog frag = new SegurDialog();
        Bundle args = new Bundle();
        args.putString("texto",texto);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         txt = getArguments().getString("titl");
        return inflater.inflate(R.layout.dialog_segur, null);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ito = (TextView) view.findViewById(R.id.juegorradotxt);
        ito.setText(txt);
        bbbddd = FirebaseDatabase.getInstance().getReference("juegos");
        buttonbtn1 = (Button) view.findViewById(R.id.btnsiborr);
        buttonbtn2 = (Button) view.findViewById(R.id.btnoborr);
        buttonbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query q = bbbddd.orderByChild("titulo").equalTo(txt);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dtanpShot: dataSnapshot.getChildren()){
                            String key=dtanpShot.getKey();
                            DatabaseReference ref = bbbddd.child(key);
                            ref.removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast toast = Toast.makeText(getContext(),"Borrado con éxito",Toast.LENGTH_SHORT);
                toast.show();
                dismiss();
            }
        });
        buttonbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });

//        String texto = getArguments().getString("texto","holi");
      //  getDialog().setTitle(texto);
     //   imputito.requestFocus();
      //  getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }


    }
