package com.example.dell.lifesaver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ogin extends AppCompatActivity {


    String Json_String;
    JSONArray jsonArray1;
    JSONObject jsonObject1;
    EditText email, pw;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogin);

        email = (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.pw);
    }

    public void Login(View v) {

        if(email.getText().toString().equals("")){
            email.setError("Enter username or email");
            email.requestFocus();
        }
        else if(pw.getText().toString().equals("")){
            pw.setError("Enter password");
            pw.requestFocus();
        }
        else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(ctx);
            backgroundWorker.execute("login", email.getText().toString(), pw.getText().toString());
        }

    }

    public void Register(View v){
        Intent i=new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }

    public class BackgroundWorker extends AsyncTask<String, Void, String> {

        Context context;

        BackgroundWorker(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];

            String register_url = "http://192.168.1.17:8080/login.php";
            if (type.equals("login")) {
                try {

                    String email = params[1];
                    String password = params[2];

                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {

            if(!result.equals("")){
                Json_String=result;
                String n = null;
                try {
                    jsonObject1=new JSONObject(Json_String);
                    jsonArray1 =jsonObject1.getJSONArray("server_responce");
                    JSONObject jo1 = jsonArray1.getJSONObject(0);

                    n=jo1.getString("name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i =new Intent(getApplicationContext(),Home.class);
                i.putExtra("Name",n);
                i.putExtra("Email",email.getText().toString());
                startActivity(i);

            }
            else{
                Toast.makeText(getApplicationContext(),"Invalid email or password!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}
