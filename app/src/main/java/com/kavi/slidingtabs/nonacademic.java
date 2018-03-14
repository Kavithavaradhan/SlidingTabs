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

public class nonacademic extends Fragment {

    FloatingActionButton fab,fab1,fab2,fab3;
    Animation fabopen,fabclose,rotatefwd,rotatebackwrd;
    boolean isopen = false;

    ListView listViewinfo;

    List<nonacademicupdate> InfoList;
    DatabaseReference databasenonac;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.nonacademic, container, false);

        databasenonac = FirebaseDatabase.getInstance().getReference("Nonacademic");



        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) rootView.findViewById(R.id.fab3);


        fabopen = AnimationUtils.loadAnimation(getContext(),R.anim.fab_open);
        fabclose= AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);

        rotatefwd= AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotatebackwrd= AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatefab();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File imagefile =new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test,jpg");

                Uri pictureUri = Uri.fromFile(imagefile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, 0);

            }

        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Folder",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), nonacademic_write.class);
                startActivityForResult(intent,0);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "image",Toast.LENGTH_SHORT).show();
            }
        });

        listViewinfo = (ListView) rootView.findViewById(R.id.listviewinfo);

        InfoList = new ArrayList<>();


        return rootView;


    }

    @Override
    public void onStart() {
        super.onStart();

        databasenonac.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                InfoList.clear();

                for (DataSnapshot ideasnapshot: dataSnapshot.getChildren()){
                    nonacademicupdate non= ideasnapshot.getValue(nonacademicupdate.class);


                    InfoList.add(non);


                }
                nonacademic_list adapter = new nonacademic_list((Activity) getContext(), InfoList);
                listViewinfo.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void animatefab(){
        if (isopen){
            fab.startAnimation(rotatefwd);
            fab1.startAnimation(fabclose);
            fab2.startAnimation(fabclose);
            fab3.startAnimation(fabclose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isopen=false;

        } else {
            fab.startAnimation(rotatebackwrd);
            fab1.startAnimation(fabopen);
            fab2.startAnimation(fabopen);
            fab3.startAnimation(fabopen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isopen=true;

        }
    }








}
