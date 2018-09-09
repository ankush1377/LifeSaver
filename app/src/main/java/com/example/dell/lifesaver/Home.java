package com.example.dell.lifesaver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView name;
    Bundle bundle;
    String n,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name=(TextView) findViewById(R.id.name);
        bundle=getIntent().getExtras();
        n=bundle.getString("Name");
        e=bundle.getString("Email");
        name.setText(n);
    }

    public void Details(View v){

        Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","Details");
        intent.putExtra("Name",n);
        intent.putExtra("Email",e);
        startActivity(intent);
    }
    public void Call(View v){

        Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","Call");
        intent.putExtra("Name",n);
        intent.putExtra("Email",e);
        startActivity(intent);
    }
    public void Location(View v){

        Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","Location");
        intent.putExtra("Name",n);
        intent.putExtra("Email",e);
        startActivity(intent);
    }
    public void Blood(View v){

        Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","Blood");
        intent.putExtra("Name",n);
        intent.putExtra("Email",e);
        startActivity(intent);
    }
    public void SOS(View v){

        Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","SOS");
        intent.putExtra("Name",n);
        intent.putExtra("Email",e);
        startActivity(intent);
    }
    public void First_Aid(View v){

        Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","First_Aid");
        intent.putExtra("Name",n);
        intent.putExtra("Email",e);
        startActivity(intent);
    }
}
