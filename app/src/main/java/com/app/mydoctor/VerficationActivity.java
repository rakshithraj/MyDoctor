package com.app.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rakshith on 12/19/2015.
 */
public class VerficationActivity extends AppCompatActivity implements View.OnClickListener{
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verfication_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intialize();
    }

    private void intialize() {
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override

    public void onClick(View v) {
        Intent intent=new Intent(this,OneTimePasswrdActivity.class);
        this.startActivity(intent);
    }
}