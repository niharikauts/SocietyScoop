package com.example.niharika.yetanotherproj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.niharika.yetanotherproj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ThreeFragment extends Fragment{

    private EditText query;
    private EditText feedback;
    private Button sendfb;
    private RequestQueue requestQueue;


    private static final String URL = "http://societyscoop.comli.com/query.php";
    private StringRequest request;

    //Creating Views



    public ThreeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_three, container, false);
        query = (EditText) v.findViewById(R.id.query);
        feedback = (EditText) v.findViewById(R.id.feedback);
        sendfb = (Button) v.findViewById(R.id.qsend);

        requestQueue = Volley.newRequestQueue(getActivity());

        sendfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getContext(), "" + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                            else if (jsonObject.names().get(0).equals("error")) {
                                Toast.makeText(getContext(), "" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                feedback.setText(null);
                                query.setText(null);
                            }
                            else if (jsonObject.names().get(0).equals("blankid")) {
                                Toast.makeText(getContext(), "" + jsonObject.getString("blankid"), Toast.LENGTH_SHORT).show();
                                query.setText(null);
                            } else if (jsonObject.names().get(0).equals("blankqry")) {
                                Toast.makeText(getContext(), "" + jsonObject.getString("blankqry"), Toast.LENGTH_SHORT).show();
                                feedback.setText(null);
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
                        hashMap.put("qid", query.getText().toString());
                        hashMap.put("query",feedback.getText().toString());
                        return hashMap;
                    }
                };

                requestQueue.add(request);


            }
        });




        return v;
    }


}