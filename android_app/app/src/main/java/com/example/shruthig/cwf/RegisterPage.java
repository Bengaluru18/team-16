package com.example.shruthig.cwf;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class RegisterPage extends AppCompatActivity {
    EditText name,empId,email,password,confirm_password;
    Button submit,cancel;

    RegClass regClass;
    String name_ed;
    String empId_ed;
    String email_ed;
    String password_ed;
    String confirm_password_ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        name = findViewById(R.id.name_reg);
        empId = findViewById(R.id.empId_reg);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.pass1_reg);
        confirm_password = findViewById(R.id.pass2_reg);

        name_ed = name.getText().toString();
        empId_ed = empId.getText().toString();
        email_ed = email.getText().toString();
        password_ed = password.getText().toString();
        confirm_password_ed = confirm_password.getText().toString();

        submit = findViewById(R.id.btn1_reg);
        cancel = findViewById(R.id.btn2_reg);





        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
       /* StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(RegisterPage.this, "response", Toast.LENGTH_SHORT).show();
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(RegisterPage.this, "That didn't work", Toast.LENGTH_SHORT).show();
            }
        });*/
/*
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       // String msg = response.toString();
                        // Display the first 500 characters of the response string.
                        Toast.makeText(RegisterPage.this, response, Toast.LENGTH_SHORT).show();
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(RegisterPage.this, "That didn't work", Toast.LENGTH_SHORT).show();
            }
        });*/


      /*  StringRequest sr = new StringRequest(Request.Method.POST, '', new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

            },

        });*/

// Add the request to the RequestQueue.
       // queue.add(stringRequest);


    /*StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://13.229.50.62:3000/login?employee_id=51&password=helo", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(RegisterPage.this, response, Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<String, String>(){
                    params.put('name',name_ed);
                    params.put('password',password);
                    params.put(employee_id, empId);
                    params.put(email, email);
            }
            return getParams();

        }
    };

    } */

/*
    public static String POST(String url, RegClass regClass){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", person.getName());
            jsonObject.accumulate("country", person.getCountry());
            jsonObject.accumulate("twitter", person.getTwitter());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
    public void GotoLogin(View view) {
        // After registering go to login page
        Intent i = new Intent(RegisterPage.this,LoginPage.class);
        startActivity(i);
    }

    public void CancelRegister(View view) {
        //cancel the registration and go to login page
        Intent i = new Intent(RegisterPage.this, LoginPage.class);
        startActivity(i);
        finish();
    } */
}
    public void  submitDetails(View view)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Create URL
                Log.d("Debugging", "Trying to create url.");
                try {
                    URL apiEndPoint = new URL("http://13.229.50.62/signup/?name="+name_ed+"&email="+email_ed+"&password="+password_ed+"&employee_id="+empId_ed);
                    Log.d("Debugging", "URL Created.");

                    try {
                        HttpsURLConnection myConnection =
                                (HttpsURLConnection) apiEndPoint.openConnection();
                        Log.d("Debugging", "Trying to create connection");

                        if (myConnection.getResponseCode() == 200) {
                            Log.d("Debugging", "Connection Created.");
                            InputStream responseBody = myConnection.getInputStream();
                            InputStreamReader responseBodyReader =
                                    new InputStreamReader(responseBody, "UTF-8");

                            String result = getStringFromInputStream(responseBody);

                            Log.d("Json", responseBodyReader.toString());


                            Log.d("HomePage Debugging", "File doesn't exist.");
                            try {
                                JSONObject json = new JSONObject(result);
                                Log.d("Debugging", "object Created.");
                                mExterior = json.getJSONObject("vehicle").getJSONObject("EXT020").getString("url");
                                mInterior = json.getJSONObject("vehicle").getJSONObject("INT1").getString("url");
                                Log.d("Json url", mExterior);
                                Log.d("Json url", mInterior);
                                ImageView carView = (ImageView) findViewById(R.id.car_view);
                                new ImageLoadTask(mExterior, carView, "exteriorImage").execute();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Log.d("Debugging", "Error from server");
                            Log.d("Error from server", String.valueOf(myConnection.getResponseCode()));
                            // Error handling code goes here
                        }
                        myConnection.disconnect();
                    } catch (IOException e) {
                        Log.d("Debugging", "Caught IOException");
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    Log.d("Debugging", "Caught malformedURL");
                    e.printStackTrace();
                }
            }});
    }

}
