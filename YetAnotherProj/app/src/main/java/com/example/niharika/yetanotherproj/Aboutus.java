package com.example.niharika.yetanotherproj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Aboutus extends AppCompatActivity implements View.OnClickListener {

    TextView tv1,tv2;
    Button var,nih,amar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv1=(TextView)findViewById(R.id.one);
        tv1.setText("\nSociety Scoop is an android application developed exclusively for the residents " +
                "of large co-operative housing societies. It aims to make the lives of the residents easier" +
                " by delivering important details and contacts at the click of a button.\n\n ");


        var=(Button)findViewById(R.id.var);
        nih=(Button)findViewById(R.id.nih);
        amar=(Button)findViewById(R.id.amar);

        var.setOnClickListener(this);
        nih.setOnClickListener(this);
        amar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.var:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9819994358"));
                startActivity(callIntent);
                break;

            case R.id.nih:
                Intent callIntent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9820548158"));
                startActivity(callIntent1);
                break;

            case R.id.amar:
                Intent callIntent3 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8655494978"));
                startActivity(callIntent3);
                break;

        }


    }
}
