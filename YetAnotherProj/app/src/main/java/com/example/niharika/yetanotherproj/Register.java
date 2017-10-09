package com.example.niharika.yetanotherproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText username,password,cnfpwd,flat;
    private Button register;
    private RequestQueue requestQueue;



    //private static final String URL = "http://192.168.0.107/trial/register.php";
    private static final String URL = "http://societyscoop.comli.com/register.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        cnfpwd = (EditText) findViewById(R.id.cnfpwd);
        flat = (EditText) findViewById(R.id.flat);


        requestQueue = Volley.newRequestQueue(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else if (jsonObject.names().get(0).equals("error")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                username.setText(null);
                                password.setText(null);
                                cnfpwd.setText(null);
                                flat.setText(null);

                            }else if (jsonObject.names().get(0).equals("pwd")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("pwd"), Toast.LENGTH_SHORT).show();
                                password.setText(null);
                                cnfpwd.setText(null);

                            } else if (jsonObject.names().get(0).equals("blank")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("blank"), Toast.LENGTH_SHORT).show();
                                username.setText(null);
                                password.setText(null);

                            } else if (jsonObject.names().get(0).equals("exist")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("exist"), Toast.LENGTH_SHORT).show();
                                username.setText(null);
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
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("username", username.getText().toString());
                        hashMap.put("password", password.getText().toString());
                        hashMap.put("cnfpwd", cnfpwd.getText().toString());
                        hashMap.put("flat", flat.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"Selected "+item,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



