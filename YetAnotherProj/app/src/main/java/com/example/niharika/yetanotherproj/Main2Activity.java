package com.example.niharika.yetanotherproj;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.app.ActivityCompat.startActivity;

public class Main2Activity extends ActionBarActivity {



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Student> studentList;

    private Button btnSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnSelection = (Button) findViewById(R.id.btnShow);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        studentList = new ArrayList<Student>();

        getData();

    }

    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);

//Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.MENU_DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//Dismissing progress dialog
                        loading.dismiss();

//calling method to parse json array
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {




                    }
                });

//Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

//Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }





    private void parseData(JSONArray array){
        for(int i = 0; i<array.length(); i++) {
            Student item = new Student();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                // poll.setImageUrl(json.getString(Config.TAG_IMAGE_URL));
                item.setName(json.getString(Config.TAG_ITEM));
                item.setSelected(false);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            studentList.add(item);

        }

        // create an Object for Adapter
        mAdapter = new CardViewDataAdapter(studentList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        btnSelection.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<Student> stList = ((CardViewDataAdapter) mAdapter)
                        .getStudentist();

                for (int i = 0; i < stList.size(); i++) {
                    Student singleStudent = stList.get(i);

                    if (singleStudent.isSelected() == true) {

                        data = data + "" + singleStudent.getName().toString()+",";

                    }

                }

                Toast.makeText(Main2Activity.this,
                        "Selected Items:" + data, Toast.LENGTH_SHORT)
                        .show();

                Intent i = new Intent(Main2Activity.this,Confirm.class);
                i.putExtra("orders",data.toString());
                startActivity(i);




            }
        });

    }

}