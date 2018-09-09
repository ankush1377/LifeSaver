package com.example.dell.lifesaver;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,details_f.OnFragmentInteractionListener,call_f.OnFragmentInteractionListener,
        firstaid_f.OnFragmentInteractionListener,sos_f.OnFragmentInteractionListener,blood_f.OnFragmentInteractionListener,location_f.OnFragmentInteractionListener {

    Bundle bundle;
    Toolbar toolbar = null;
    NavigationView navigationView = null;
    static String n=null;
    static String e=null;
    String s=null;
    final int cap = 0, sel = 1;
    static Bitmap bmp=null;
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    TextView name, email;
    firstaid_f fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bundle = getIntent().getExtras();
        s = bundle.getString("event");
        n = bundle.getString("Name");
        e = bundle.getString("Email");

        if (s.equals("Details")) {

            details_f fragment = new details_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (s.equals("Call")) {
            call_f fragment = new call_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (s.equals("Location")) {
            location_f fragment = new location_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (s.equals("Blood")) {
            blood_f fragment = new blood_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (s.equals("First_Aid")) {
            fragment = new firstaid_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (s.equals("SOS")) {
            sos_f fragment = new sos_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main2, null);

        name = (TextView) header.findViewById(R.id.name);
        email = (TextView) header.findViewById(R.id.email);
        profile_image = (de.hdodenhof.circleimageview.CircleImageView) header.findViewById(R.id.profile_image);

        name.setText(n);
        email.setText(e);
        if(bmp!=null)
            profile_image.setImageBitmap(bmp);

        profile_image.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(Main2Activity.this);
                        dialog.setContentView(R.layout.image_dialog);
                        //dialog.setTitle("Choose an action");

                        // get the Refferences of views
                        final Button camera = (Button) dialog.findViewById(R.id.camera);
                        final Button gallery = (Button) dialog.findViewById(R.id.gallery);

                        // Set On ClickListener
                        camera.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(photo, cap);
                                dialog.dismiss();

                            }
                        });

                        gallery.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent pick = new Intent(Intent.ACTION_PICK);
                                        pick.setType("image/");
                                        startActivityForResult(pick, sel);
                                        dialog.dismiss();

                                    }
                                }
                        );
                        dialog.show();
                    }
                }
        );
        navigationView.addHeaderView(header);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.details) {

            details_f fragment = new details_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.contacts) {
            call_f fragment = new call_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.location) {
            location_f fragment = new location_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.blood) {
            blood_f fragment = new blood_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.firstaid) {
            fragment = new firstaid_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.sos) {
            sos_f fragment = new sos_f();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onClick(View v) {
        fragment.onClick(v);
    }

    public static String sendName(){
        return n;
    }
    public static String sendEmail(){
        return e;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case cap:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    bmp = (Bitmap) bundle.get("data");
                    profile_image.setImageBitmap(bmp);
                }
                break;

            case sel:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageuri = data.getData();
                        final InputStream inputStream = getContentResolver().openInputStream(imageuri);
                        bmp = BitmapFactory.decodeStream(inputStream);
                        profile_image.setImageBitmap(bmp);
                    } catch (Exception e) {}
                }
                break;
        }

    }
}