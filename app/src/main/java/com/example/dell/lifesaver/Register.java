package com.example.dell.lifesaver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText Name, email, pw, cpw;
    RadioButton radioM, radioF;
    Spinner state, city;
    int a, b;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    Name = (EditText) findViewById(R.id.Name1);
    email = (EditText) findViewById(R.id.email1);
    pw = (EditText) findViewById(R.id.pw1);
    cpw = (EditText) findViewById(R.id.cpw1);
    radioM = (RadioButton) findViewById(R.id.radioM1);
    radioF = (RadioButton) findViewById(R.id.radioF1);
    state = (Spinner) findViewById(R.id.state1);
    city = (Spinner) findViewById(R.id.city1);


    radioM.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            radioF.setChecked(false);
        }
    }
    );

    radioF.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            radioM.setChecked(false);
        }
    }
    );


    String states[] = {"Select State","Chandigarh","Haryana","Punjab"};
    SpinnerAdapter ads = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, states);
    state.setAdapter(ads);

    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View
        view, int position, long id) {
            if ((state.getSelectedItem().toString()).equals("Select State")) {
                String cities[] = {"Select City"};
                SpinnerAdapter adc = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cities);
                city.setAdapter(adc);
            }
            if ((state.getSelectedItem().toString()).equals("Punjab")) {
                String cities[] = {"Select City","Mohali","Moga","Bathinda"};
                SpinnerAdapter adc = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cities);
                city.setAdapter(adc);
            }
            if ((state.getSelectedItem().toString()).equals("Haryana")) {

                String cities[] ={"Select City","Panchkula","Rohtak"};
                SpinnerAdapter adc = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cities);
                city.setAdapter(adc);
            }
            if ((state.getSelectedItem().toString()).equals("Chandigarh")) {

                String cities[] ={"Select City","Chandigarh"};
                SpinnerAdapter adc = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cities);
                city.setAdapter(adc);
            }
        }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
    );
}

    public void Register(View v) {

        if(!validateName(Name.getText().toString()))
        {
            Name.setError("Enter your name");
            Name.requestFocus();
        }
        else if(!validateEmail(email.getText().toString()))
        {
            email.setError("Enter valid email : abc@example.com");
            email.requestFocus();
            email.setText("");
        }else if(!validatePassword(pw.getText().toString())) {
            pw.setError("Enter password");
            pw.requestFocus();
        }else if(pw.getText().toString().length()<6) {
            pw.setError("Password length greater than 6 characters");
            pw.requestFocus();
            pw.setText("");
        }else if(!pw.getText().toString().equals(cpw.getText().toString())) {
            cpw.setError("Password does not match");
            cpw.requestFocus();
        }else if(!radioM.isChecked() && !radioF.isChecked()) {
            radioM.setError("Select gender");
        }
        else if(state.getSelectedItem().toString().equals("Select State")){
            Toast.makeText(getApplicationContext(),"Select State",Toast.LENGTH_LONG).show();
        }
        else if(city.getSelectedItem().toString().equals("Select City")){
            Toast.makeText(getApplicationContext(),"Select City",Toast.LENGTH_LONG).show();
        }
        else {
            String g;
            if(radioM.isChecked()) g="Male";
            else g="Female";

            BackgroundWorker backgroundWorker = new BackgroundWorker(ctx);
            backgroundWorker.execute("register",Name.getText().toString(), email.getText().toString(), pw.getText().toString(),
                    g,state.getSelectedItem().toString(),city.getSelectedItem().toString());
        }
    }

    private boolean validateEmail(String email) {
        String emailpattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern= Pattern.compile(emailpattern);
        Matcher matcher=pattern.matcher(email);
        return  matcher.matches();
    }

    private boolean validatePassword(String password) {
        if(!password.equals("")){
            return true;
        }else
            return  false;
    }

    private boolean validateName(String name) {
        if (name != null && name.length() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        String register_url = "http://192.168.1.17:8080/register.php";
        if (type.equals("register")) {
            try {

                String name = params[1];
                String email = params[2];
                String password = params[3];
                String gender = params[4];
                String state = params[5];
                String city = params[6];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8")+ "&"
                        + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8") + "&"
                        + URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8");
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

            if(result.equals("1")){
                Intent i =new Intent(getApplicationContext(),Home.class);
                i.putExtra("Name",Name.getText().toString());
                i.putExtra("Email",email.getText().toString());
                startActivity(i);
            }
            else
                Toast.makeText(getApplicationContext(),"Could not Register.Please try again!", Toast.LENGTH_LONG).show();
        }
    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }
}

}
