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

public class article_write extends AppCompatActivity {

    ImageButton sendbtnart;
    EditText articleline;

    DatabaseReference databaseidea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_write);

        databaseidea = FirebaseDatabase.getInstance().getReference("Article");


        articleline = (EditText) findViewById(R.id.articleline);
        sendbtnart = (ImageButton) findViewById(R.id.sendbtnart);


        sendbtnart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article();
            }
        });
    }



    private void article(){
        String line = articleline.getText().toString().trim();

        if (!TextUtils.isEmpty(line)){

            String id = databaseidea.push().getKey();

            articleupdate art = new articleupdate(id, line);

            databaseidea.child(id).setValue(art);

            Toast.makeText(this, "Article Added",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Enter Article",Toast.LENGTH_SHORT).show();
        }
    }
}
