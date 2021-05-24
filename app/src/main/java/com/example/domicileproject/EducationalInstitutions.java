package com.example.domicileproject;

//This is the code for Educational Institutions activity
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class EducationalInstitutions extends AppCompatActivity implements View.OnClickListener {

    //Declaring the variables
    private TextView Logout, EI_back, Deakin;
    String deakin = "deakin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_institutions);

        //Initializing the variables
        EI_back = (TextView) findViewById(R.id.EI_back);
        EI_back.setOnClickListener(this);

        Deakin = (TextView) findViewById(R.id.EI_Deakin);

        //If user clicks the Deakin University textview, then the following code will be executed
        Deakin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EducationalInstitutions.this, web.class);

                //The deakin variable is passed to the web activity, this makes sure that Deakin University link will be executed
                intent.putExtra("deakin",deakin);
                startActivity(intent);
            }
        });

        Logout = (TextView) findViewById(R.id.EI_logout);

        //if user clicks the logout textview, then the following code will be executed
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Signing out of firebase in the application
                FirebaseAuth.getInstance().signOut();

                //User will be navigated to Login activity
                startActivity(new Intent(EducationalInstitutions.this, Login.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //if user clicks the back textview, then the following code will be executed to navigate the user to the references activity
            case R.id.EI_back:
                startActivity(new Intent(EducationalInstitutions.this, references.class));
                break;
        }
    }
}