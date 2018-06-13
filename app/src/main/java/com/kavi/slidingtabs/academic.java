package com.kavi.slidingtabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kavi on 26/2/18.
 */

public class academic extends Fragment {

    FloatingActionButton fab;


    ListView listViewaca;

    List<academicupdate> academicList;
    DatabaseReference databaseacademic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.academic, container, false);

        databaseacademic = FirebaseDatabase.getInstance().getReference("Academic");

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(),"edit",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), academic_write.class);
                startActivityForResult(intent,0);
            }
        });


        listViewaca = (ListView) rootView.findViewById(R.id.listviewacademic);

        academicList = new ArrayList<>();

        return rootView;


    }
    @Override
    public void onStart() {
        super.onStart();

        databaseacademic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                academicList.clear();

                for (DataSnapshot ideasnapshot: dataSnapshot.getChildren()){
                    academicupdate aca= ideasnapshot.getValue(academicupdate.class);


                    academicList.add(aca);


                }
                academiclist adapter = new academiclist((Activity) getContext(), academicList);
                listViewaca.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }









}
