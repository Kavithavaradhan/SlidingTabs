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

public class IdeaWrite extends AppCompatActivity {

    ImageButton sendbtn;
    EditText projectline;

    DatabaseReference databaseidea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_write);

        databaseidea = FirebaseDatabase.getInstance().getReference("Projectideas");


        projectline = (EditText) findViewById(R.id.projectline);
        sendbtn = (ImageButton) findViewById(R.id.sendbtn);


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idea();
            }
        });
    }



    private void idea(){
        String line = projectline.getText().toString().trim();

        if (!TextUtils.isEmpty(line)){

            String id = databaseidea.push().getKey();

            idea Idea = new idea(id, line);

            databaseidea.child(id).setValue(Idea);

            Toast.makeText(this, "Idea Added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter Idea",Toast.LENGTH_SHORT).show();
        }
    }
}
