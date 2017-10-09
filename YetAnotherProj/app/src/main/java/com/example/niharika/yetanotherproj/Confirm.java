package com.example.niharika.yetanotherproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Confirm extends AppCompatActivity {



    private EditText flatno,phno;
    private Button order ;
    private RequestQueue requestQueue;
    //private static final String URL = "http://192.168.0.107/trial/register.php";
    private static final String URL = "http://societyscoop.comli.com/item.php";
    private StringRequest request;
    private String orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        phno = (EditText) findViewById(R.id.phno);
        flatno = (EditText) findViewById(R.id.flatno);
        order = (Button) findViewById(R.id.order);

        Intent i = getIntent();
        if(i.hasExtra("orders")){
            orders = i.getStringExtra("orders");
        }
        else {
            orders = "Not Placed";
        }

        //final String orders = getIntent().getExtras().getString("orders");


        requestQueue = Volley.newRequestQueue(this);

        order.setOnClickListener(new View.OnClickListener() {
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
                                flatno.setText(null);

                            } else if (jsonObject.names().get(0).equals("blank")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("blank"), Toast.LENGTH_SHORT).show();
                                flatno.setText(null);
                            }
                            else if (jsonObject.names().get(0).equals("blankp")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("blank"), Toast.LENGTH_SHORT).show();
                                phno.setText(null);
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
                        hashMap.put("flatno", flatno.getText().toString());
                        hashMap.put("phno",phno.getText().toString());
                        hashMap.put("orders", orders);

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });


    }

}
