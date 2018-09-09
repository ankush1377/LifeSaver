package com.example.dell.lifesaver;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Donor_List extends AppCompatActivity {

    ArrayList<String> n=new ArrayList();
    ArrayList<String> p=new ArrayList();
    Bundle bundle;
    String bg,name2,name,email;
    ListView names,phones;
    DBHandler dbHandler;
    Cursor cursor;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__list);

        names = (ListView) findViewById(R.id.names);
        phones = (ListView) findViewById(R.id.phones);
        bundle = getIntent().getExtras();
        bg = bundle.getString("BG");
        name2=bundle.getString("Name2");
        name=bundle.getString("Name");
        email=bundle.getString("Email");


        dbHandler = new DBHandler(ctx);
        cursor = dbHandler.getDonors(dbHandler, bg);
        cursor.moveToFirst();

        do {
            if ((cursor.getString(2)).equals(bg)) {
                n.add(cursor.getString(0));
                p.add(cursor.getString(1));
            }
        } while (cursor.moveToNext());

        ArrayAdapter<String> nadapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, n);
        ArrayAdapter<String> padapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, p);
        names.setAdapter(nadapter);
        phones.setAdapter(padapter);

        phones.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                        final Dialog dialog = new Dialog(Donor_List.this);
                        dialog.setContentView(R.layout.call_dialog);
                        //dialog.setTitle("Choose an action");

                        // get the Refferences of views
                        final Button call=(Button) dialog.findViewById(R.id.call);
                        final Button msg=(Button) dialog.findViewById(R.id.msg);

                        // Set On ClickListener
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    String n=parent.getItemAtPosition(position).toString();
                                    Intent call = new Intent(Intent.ACTION_CALL);
                                    call.setData(Uri.parse("tel:"+n));
                                    startActivity(call);
                                    dialog.dismiss();
                            }
                        });

                        msg.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String number=parent.getItemAtPosition(position).toString();
                                        String message="Hi,I'm "+name2+".\n"+"I need "+bg+" blood urgently.\n"+
                                                "Would you like to donate?\n" +"Please do reply!";

                                        Intent i = new Intent(getApplicationContext(),Donor_List.class);
                                        i.putExtra("BG",bg);
                                        i.putExtra("Name2",name2);
                                        i.putExtra("Name",name);
                                        i.putExtra("Email",email);
                                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,0);

                                        SmsManager smsManager=SmsManager.getDefault();
                                        smsManager.sendTextMessage(number,null,message,pi,null);

                                        Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                }
                        );
                        dialog.show();
                    }
                }
        );
    }

    public void Done(View v){

        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("event","Blood");
        intent.putExtra("Name",name);
        intent.putExtra("Email",email);
        startActivity(intent);
    }

}
