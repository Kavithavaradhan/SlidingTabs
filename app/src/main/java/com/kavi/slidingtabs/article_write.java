package com.kavi.slidingtabs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class article_write extends AppCompatActivity {

    ImageButton sendbtnart;
    EditText articleline;
    FloatingActionButton fab, fab1, fab2, fab3;
    Animation fabopen, fabclose, rotatefwd, rotatebackwrd;
    boolean isopen = false;
    ImageView targetImage;
    TextView textTargetUri;

    DatabaseReference databaseidea;
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_write);

        databaseidea = FirebaseDatabase.getInstance().getReference("Article");


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        targetImage = (ImageView) findViewById(R.id.targetimage);
        textTargetUri = (TextView)findViewById(R.id.targeturi);



        fabopen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabclose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        rotatefwd = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotatebackwrd = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

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
                File imagefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "test,jpg");

                Uri pictureUri = Uri.fromFile(imagefile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, 0);

            }

        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "image",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionsAndOpenFilePicker();

            }
        });



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

    private void checkPermissionsAndOpenFilePicker() {
        String permission = android.Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withTitle("Sample title")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);


            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void animatefab() {

        if (isopen) {

            fab.startAnimation(rotatefwd);
            fab1.startAnimation(fabclose);
            fab2.startAnimation(fabclose);
            fab3.startAnimation(fabclose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isopen = false;

        } else {

            fab.startAnimation(rotatebackwrd);
            fab1.startAnimation(fabopen);
            fab2.startAnimation(fabopen);
            fab3.startAnimation(fabopen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isopen = true;
        }
    }
}
