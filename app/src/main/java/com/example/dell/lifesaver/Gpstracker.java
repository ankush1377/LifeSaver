package com.example.dell.lifesaver;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class Gpstracker extends Service implements LocationListener{
    private final Context context;
    boolean isGPSenbled=false;
    boolean isNetworkenbled=false;
    boolean canGetlocation=false;

    Location location;

    double longitude;
    double lattitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES=10;
    private static final long MIN_TIME_BW_UPDATES=1000*60*1;

    protected LocationManager locationmanager;

    public Gpstracker(Context context) {
        this.context=context;
        getLocation();
    }

    public Location getLocation(){
        try {
            locationmanager=(LocationManager)context.getSystemService(LOCATION_SERVICE);
            isGPSenbled=locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkenbled=locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSenbled && !isNetworkenbled) {

            } else {
                this.canGetlocation=true;
                if(isNetworkenbled){
                    locationmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);


                    if(locationmanager !=null){
                        location=locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location !=null){

                            longitude=location.getLongitude();
                            lattitude=location.getLatitude();
                        }

                    }
                }

                if(isGPSenbled){
                    if(location==null){

                        locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if(locationmanager !=null){
                            location=locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(location!=null){

                                lattitude=location.getLatitude();
                                longitude=location.getLongitude();
                            }

                        }
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return location;


    }


    public void stopUsingGps(){

        if(locationmanager !=null){

            locationmanager.removeUpdates(Gpstracker.this);
        }
    }

    public double getLattitude(){
        if(location!=null){
            lattitude=location.getLatitude();

        }
        return lattitude;


    }
    public double getLongitute(){
        if(location!=null){
            longitude=location.getLongitude();

        }
        return longitude;



    }
    public boolean canGetlocatn(){
        return this.canGetlocation;


    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(context);

        alertdialog.setTitle("GPS is Settings");

        alertdialog.setMessage("GPS is not enabled.Do you want to go to settings menu?");
        alertdialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertdialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();

            }
        });
        alertdialog.show();

    }

    @Override
    public void onLocationChanged(Location location) {




    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

}
