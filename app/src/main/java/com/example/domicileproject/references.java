package com.example.domicileproject;

//This is the code for references activity
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class references extends AppCompatActivity implements View.OnClickListener {

    //Declaring the variables
    private TextView Logout, references_back, references_EI, references_banks, references_Tele;
    private Button Address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        //Initializing the variables
        references_back = (TextView) findViewById(R.id.Ref_back);
        references_back.setOnClickListener(this);

        references_EI = (TextView) findViewById(R.id.Locations_EI);
        references_EI.setOnClickListener(this);

        references_Tele = (TextView) findViewById(R.id.Locations_TC);
        references_Tele.setOnClickListener(this);

        references_banks = (TextView) findViewById(R.id.Locations_banks);
        references_banks.setOnClickListener(this);


        Logout = (TextView) findViewById(R.id.Ref_logout);

        //if user clicks the logout textview, then the following code will be executed
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Signing out of firebase in the application
                FirebaseAuth.getInstance().signOut();

                //Navigate the user to the Login activity
                startActivity(new Intent(references.this, Login.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //if user clicks the back textview, then the following code will be executed to navigate the user to the updatedaddress activity
            case R.id.Ref_back:
                startActivity(new Intent(references.this, updatedaddress.class));
                break;

            //if user clicks the Educational Institutions textview, then the following code will be executed to navigate the user to the EducationalInstitutions activity
            case R.id.Locations_EI:
                startActivity(new Intent(references.this, EducationalInstitutions.class));
                break;

            //if user clicks the Telecommunications textview, then the following code will be executed to navigate the user to the Telecomms activity
            case R.id.Locations_TC:
                startActivity(new Intent(references.this, Telecomms.class));
                break;

            //if user clicks the banks textview, then the following code will be executed to navigate the user to the banks activity
            case R.id.Locations_banks:
                startActivity(new Intent(references.this, banks.class));
                break;
        }
    }
}