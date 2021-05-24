package com.example.domicileproject;

//This is the code for Telecommunications activity
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Telecomms extends AppCompatActivity implements View.OnClickListener{

    //Declaring the variables
    private TextView back, logout, optus;
    String Opt = "Opt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telecomms);

        //Initializing the variables
        back = (TextView) findViewById(R.id.Tele_back);
        back.setOnClickListener(this);

        optus = (TextView) findViewById(R.id.Tele_optus);
        optus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Navigate user to the web activity
                Intent intent = new Intent(Telecomms.this, web.class);

                //The Opt variable is passed to the web activity, this makes sure that Deakin University link will be executed
                intent.putExtra("Opt",Opt);
                startActivity(intent);
            }
        });

        logout = (TextView) findViewById(R.id.Tele_logout);

        //if user clicks the logout textview, then the following code will be executed
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Signing out of firebase in the application
                FirebaseAuth.getInstance().signOut();

                //Navigate user to the Login activity
                startActivity(new Intent(Telecomms.this, Login.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //if the user clicks the back textview, then the following code will be executed to navigate the user to the references activity
            case R.id.Tele_back:
                startActivity(new Intent(Telecomms.this, references.class));
                break;
        }
    }
}