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

public class article extends Fragment {

    FloatingActionButton fab;

    ListView listViewarticle;

    List<articleupdate> articleList;
    DatabaseReference databasearticle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.article, container, false);
        databasearticle = FirebaseDatabase.getInstance().getReference("Article");

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Folder",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), article_write.class);
                startActivityForResult(intent,0);
            }
        });

        listViewarticle = (ListView) rootView.findViewById(R.id.listviewarticle);

        articleList = new ArrayList<>();
        return rootView;


    }
    @Override
    public void onStart() {
        super.onStart();

        databasearticle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                articleList.clear();

                for (DataSnapshot ideasnapshot: dataSnapshot.getChildren()){
                    articleupdate art= ideasnapshot.getValue(articleupdate.class);


                    articleList.add(art);


                }
                article_list adapter = new article_list((Activity) getContext(), articleList);
                listViewarticle.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }










}