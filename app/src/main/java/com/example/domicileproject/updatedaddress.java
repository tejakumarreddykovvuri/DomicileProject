package com.example.domicileproject;

//This is the code for the updatedaddress activity
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updatedaddress extends AppCompatActivity implements View.OnClickListener {

    //Declaring the variables
    private TextView Logout;
    private Button Save;
    private EditText UA_usn, UA_sn, UA_sr, UA_pin;
    private DatabaseReference rootDatabaseref;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedaddress);

        //Using the ClipBoard Manager to save the data entered in the edittext fields
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Initializing Firebase Database instance
        rootDatabaseref = FirebaseDatabase.getInstance().getReference("Users");

        //Initializing the variables
        Save = (Button) findViewById(R.id.UA_save);
        Save.setOnClickListener(this);

        UA_usn = (EditText) findViewById(R.id.UA_usn);
        UA_sn = (EditText) findViewById(R.id.UA_sn);
        UA_sr = (EditText) findViewById(R.id.UA_sr);
        UA_pin = (EditText) findViewById(R.id.UA_pin);


        Logout = (TextView) findViewById(R.id.UA_logout);

        //if user clicks the logout textview, then the following code will be executed
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Signing out of firebase in the application
                FirebaseAuth.getInstance().signOut();

                //Navigate user to the Login activity
                startActivity(new Intent(updatedaddress.this, Login.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //if the user clicks the Save button, then the following code will be executed
            case R.id.UA_save:
                updateAddress();
                break;
        }
    }

    private void updateAddress() {

    //Getting the data from the edit text fields to new variables and converting to strings to save into Firebase Database
    String usn = UA_usn.getText().toString();
    String sn = UA_sn.getText().toString();
    String sr = UA_sr.getText().toString();
    String pin = UA_pin.getText().toString();
    String task = "true";

        //Checking whether Number and Street field is empty or not
        if(usn.isEmpty()){
            UA_usn.setError("Please Enter New Unit and Street Number Address");
            UA_usn.requestFocus();
            task = "false";
            return;
        }

        //if the Number and Street field is not empty (i.e., user entered data), then copy the text to ClipBoard
        else{
            ClipData usnumber = ClipData.newPlainText("usn", usn);
            clipboardManager.setPrimaryClip(usnumber);
            Log.d("usnumber",String.valueOf(usnumber));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Checking whether Suburb/Town field is empty or not
        if(sn.isEmpty()){
            UA_sn.setError("Please Enter New Street Name Address");
            UA_sn.requestFocus();
            task = "false";
            return;
        }

        //if the Suburb/Town field is not empty (i.e., user entered data), then copy the text to ClipBoard
        else{
            ClipData sname = ClipData.newPlainText("sn", sn);
            clipboardManager.setPrimaryClip(sname);
            Log.d("snumber",String.valueOf(sname));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Checking whether State/Region field is empty or not
        if(sr.isEmpty()){
            UA_sr.setError("Please Enter New State/Region Address");
            UA_sr.requestFocus();
            task = "false";
            return;
        }

        //if the State/Region field is not empty (i.e., user entered data), then copy the text to ClipBoard
        else {
            ClipData region = ClipData.newPlainText("sr", sr);
            clipboardManager.setPrimaryClip(region);
            Log.d("area/region",String.valueOf(region));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Checking whether Pin field is empty or not
        if(pin.isEmpty()){
            UA_pin.setError("Please Enter the New Pin code");
            UA_pin.requestFocus();
            task = "false";
            return;
        }

        //if the Pin field is not empty (i.e., user entered data), then copy the text to ClipBoard
        else{
            ClipData areapin = ClipData.newPlainText("pin", pin);
            clipboardManager.setPrimaryClip(areapin);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    //passing the data to addresschange activity to store in the variables
    addresschange obj = new addresschange(usn,sn,sr,pin);

    //Once the data is stored in the variables in addresschange activity, the following code will be executed to save the data in Firebase Database
    rootDatabaseref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(obj);

    if(task == "true")
    {
        startActivity(new Intent(updatedaddress.this, references.class));
    }

    }
}