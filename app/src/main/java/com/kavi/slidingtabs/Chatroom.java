package com.kavi.slidingtabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chatroom extends AppCompatActivity {

    private Button sendbtn;
    private EditText msgtxt;
    private TextView chatconv;
    private String username, name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);


        sendbtn = (Button) findViewById(R.id.sendbtn);
        msgtxt = (EditText) findViewById(R.id.msgtxt);
        chatconv = (TextView) findViewById(R.id.chatconv);

        username = getIntent().getExtras().get("txtusrname").toString();
        name = getIntent().getExtras().get("username").toString();
        setTitle("Room - "+ username);

        root = FirebaseDatabase.getInstance().getReference().child(username);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference msg_root = root.child(temp_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", name);
                map2.put("msg", msgtxt.getText().toString());
                msg_root.updateChildren(map2);

            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conv(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conv(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private String chat_msg, chat_usrname;

    private void append_chat_conv(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){
            chat_msg = (String)((DataSnapshot)i.next()).getValue();
            chat_usrname = (String)((DataSnapshot)i.next()).getValue();

            chatconv.append(chat_usrname + " : "+ chat_msg + "\n");

        }
    }

}
