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

public class projectidea extends Fragment {

    FloatingActionButton fab;


    ListView listViewideas;

    List<idea> ideaList;
    DatabaseReference databaseidea;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.projectidea, container, false);

        databaseidea = FirebaseDatabase.getInstance().getReference("Projectideas");



        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Folder",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), IdeaWrite.class);
                startActivityForResult(intent,0);
            }
        });



        listViewideas = (ListView) rootView.findViewById(R.id.listviewideas);

        ideaList = new ArrayList<>();

        return rootView;


    }
    @Override
    public void onStart() {
        super.onStart();

        databaseidea.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ideaList.clear();

                for (DataSnapshot ideasnapshot: dataSnapshot.getChildren()){
                    idea idea = ideasnapshot.getValue(idea.class);

                    ideaList.add(idea);
                }

                idealist adapter = new idealist((Activity) getContext(), ideaList);
                listViewideas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }











}


