package com.kavi.slidingtabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class nonacademic_write extends AppCompatActivity {

    ImageButton sendbtn;
    EditText nonacline;

    DatabaseReference databasenonac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonacademic_write);

        databasenonac = FirebaseDatabase.getInstance().getReference("Nonacademic");


        nonacline = (EditText) findViewById(R.id.nonacline);
        sendbtn = (ImageButton) findViewById(R.id.sendbtn);


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonac();
            }
        });
    }



    private void nonac() {
        String line = nonacline.getText().toString().trim();

        if (!TextUtils.isEmpty(line)){

            String id = databasenonac.push().getKey();

            nonacademicupdate non = new nonacademicupdate(id, line);

            databasenonac.child(id).setValue(non);

            Toast.makeText(this, "Information Added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter Information",Toast.LENGTH_SHORT).show();
        }
    }
}
