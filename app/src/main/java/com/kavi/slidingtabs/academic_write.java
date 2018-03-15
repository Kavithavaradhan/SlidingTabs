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

public class academic_write extends AppCompatActivity {

    ImageButton sendbtn;
    EditText academicline;

    DatabaseReference databaseidea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_write);

        databaseidea = FirebaseDatabase.getInstance().getReference("Academic");


        academicline = (EditText) findViewById(R.id.academicline);
        sendbtn = (ImageButton) findViewById(R.id.sendbtn);


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aca();
            }
        });
    }



    private void aca(){
        String line = academicline.getText().toString().trim();

        if (!TextUtils.isEmpty(line)){

            String id = databaseidea.push().getKey();

            academicupdate ac = new academicupdate(id, line);

            databaseidea.child(id).setValue(ac);
            Toast.makeText(this, "Info Added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter Info",Toast.LENGTH_SHORT).show();
        }
    }
}
