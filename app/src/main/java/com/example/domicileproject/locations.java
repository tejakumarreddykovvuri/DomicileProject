package com.example.domicileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class locations extends AppCompatActivity implements View.OnClickListener {
    private TextView Logout, Locations_back, Location_Deakin;
    private Button references_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Locations_back = (TextView) findViewById(R.id.Locations_back);
        Locations_back.setOnClickListener(this);

        Location_Deakin = (TextView) findViewById(R.id.Deakin);
        Location_Deakin.setMovementMethod(LinkMovementMethod.getInstance());

        references_button = (Button) findViewById(R.id.Locations_Refereneces);
        references_button.setOnClickListener(this);

        Logout = (TextView) findViewById(R.id.Locations_logout);
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(locations.this, updatedaddress.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Locations_back:
                startActivity(new Intent(locations.this, Login.class));
                break;
            case R.id.Locations_Refereneces:
                startActivity(new Intent(locations.this, references.class));
                break;
        }
    }
}