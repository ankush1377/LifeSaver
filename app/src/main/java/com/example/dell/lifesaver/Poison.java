package com.example.dell.lifesaver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Poison extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poison);
    }

    public void Done(View v){
        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","First_Aid");
        intent.putExtra("Name",Main2Activity.sendName());
        intent.putExtra("Email",Main2Activity.sendEmail());
        startActivity(intent);
    }
}
