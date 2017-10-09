package com.example.niharika.yetanotherproj;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



import com.example.niharika.yetanotherproj.R;


public class OneFragment extends Fragment{

    private List<Offers> listOffers;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Config config;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }


    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);



//Initializing Views

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//Initializing our superheroes list
        listOffers = new ArrayList<>();


        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);

// Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

// if connected with internet
//Calling method to get data
            getData();

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED ) {

            Toast.makeText(getActivity(), " No Internet Connection !!", Toast.LENGTH_LONG).show();

        }

        // Inflate the layout for this fragment
        return v;
    }

    private void getData(){
//Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Loading Data", "Please wait...",false,false);

//Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.OFFERS_DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response1) {
//Dismissing progress dialog
                        loading.dismiss();

//calling method to parse json array
                        parseData(response1);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

//Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

//Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void parseData(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            Offers offer = new Offers();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                offer.setImageUrl(json.getString(Config.TAG_IMAGE_URL));




            } catch (JSONException e) {
                e.printStackTrace();
            }
            listOffers.add(offer);
        }

//Finally initializing our adapter
        adapter = new CardAdapter(listOffers, getActivity());

//Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

}