package com.kavi.slidingtabs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TabActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    //FloatingActionButton fab,fab1,fab2;
    //Animation fabopen,fabclose,rotatefwd,rotatebackwrd;
    //boolean isopen = false;
    ImageView camImage;
    File imagefile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        camImage = (ImageView) findViewById(R.id.image);

       /* fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fabopen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabclose= AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotatefwd= AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotatebackwrd= AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

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
                Toast.makeText(TabActivity.this,"Folder",Toast.LENGTH_SHORT).show();
            }
        });*/


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.idea);
        tabLayout.getTabAt(1).setIcon(R.drawable.article);
        tabLayout.getTabAt(2).setIcon(R.drawable.nonacademic);
        tabLayout.getTabAt(3).setIcon(R.drawable.academic);
        tabLayout.getTabAt(4).setIcon(R.drawable.query);


    }

  /*  @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data){
        if (requestcode == 0){
            switch (resultcode){
                case AppCompatActivity.RESULT_OK:
                    if (imagefile.exists()){
                        Toast.makeText(this,"The file was saved at" + imagefile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,"There was an error saving the file",Toast.LENGTH_SHORT).show();
                    }
                break;
                case AppCompatActivity.RESULT_CANCELED:
                    break;
                default: break;
            }
        }

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mfragmentList= new ArrayList<>();
        private final List<String> mfragmentTitleList= new ArrayList<>();



        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mfragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mfragmentList.size();
        }
        public void addFragment(Fragment fragment, String title){
            mfragmentList.add(fragment);
            mfragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mfragmentTitleList.get(position);
        }

    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0: projectidea idea= new projectidea();
                        return idea;
                case 1: article art= new article();
                        return art;
                case 2: nonacademic nonac= new nonacademic();
                        return nonac;
                case 3: academic aca = new academic();
                        return aca;
                case 4: queries query= new queries();
                        return query;
                default: return null;
            }


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "";
                case 1: return "";
                case 2: return "";
                case 3: return "";
                case 4: return "";
            }
            return null;
        }
    }
    /*private void animatefab(){
        if (isopen){
            fab.startAnimation(rotatefwd);
            fab1.startAnimation(fabclose);
            fab2.startAnimation(fabclose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isopen=false;

        } else {
            fab.startAnimation(rotatebackwrd);
            fab1.startAnimation(fabopen);
            fab2.startAnimation(fabopen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isopen=true;

        }
    }*/
}

