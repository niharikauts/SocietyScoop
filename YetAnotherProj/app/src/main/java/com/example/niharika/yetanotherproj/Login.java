package com.example.niharika.yetanotherproj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText username,password;
    private Button sign_in_register;
    private TextView register,textView;
    private RequestQueue requestQueue;
    private boolean loggedIn=false;


    //private static final String URL = "http://192.168.0.107/trial/user_control.php";
    private static final String URL = "http://societyscoop.comli.com/user_control.php";
    private StringRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

      //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);
        sign_in_register = (Button) findViewById(R.id.sign_in_register);

         textView = (TextView) findViewById(R.id.socieryscp);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Academic M54.ttf");

        textView.setTypeface(custom_font);


        requestQueue = Volley.newRequestQueue(this);

        sign_in_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String uname = username.getText().toString().trim();
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF,true);
                                editor.putString(Config.Uname_SHARED_PREF, uname);

                                editor.commit();


                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else if (jsonObject.names().get(0).equals("error")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                username.setText(null);
                                password.setText(null);
                            } else if (jsonObject.names().get(0).equals("blank")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("blank"), Toast.LENGTH_SHORT).show();
                                username.setText(null);
                                password.setText(null);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String,String>();
                        hashMap.put("username", username.getText().toString());
                        hashMap.put("password", password.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(Login.this, Register.class);
                startActivity(i2);

            }
        });
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if(loggedIn) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

    }

}



   /* }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}*/
