package com.example.domicileproject;

//This is the code for banks activity
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class banks extends AppCompatActivity implements View.OnClickListener{

    //Declaring the variables
    private TextView Comm, back, logout;
    String Common = "Common";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        //Initializing the variables
        back = (TextView) findViewById(R.id.Banks_back);
        back.setOnClickListener(this);

        Comm = (TextView) findViewById(R.id.Banks_Comm);

        //If user clicks the Common wealth bank textview, then the following code will be executed
        Comm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(banks.this, web.class);

                //The Common variable is passed to the web activity, this makes sure that Commonwealth bank link will be executed
                intent.putExtra("Common",Common);
                startActivity(intent);
            }
        });

        logout = (TextView) findViewById(R.id.Banks_logout);

        //if user clicks the logout textview, then the following code will be executed
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Signing out of firebase in the application
                FirebaseAuth.getInstance().signOut();

                //User will be navigated to Login activity
                startActivity(new Intent(banks.this, Login.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //if user clicks the back textview, then the following code will be executed to navigate the user to the references activity
            case R.id.Banks_back:
                startActivity(new Intent(banks.this, references.class));
                break;
        }
    }
}