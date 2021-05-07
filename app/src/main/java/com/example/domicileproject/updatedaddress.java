package com.example.domicileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class updatedaddress extends AppCompatActivity implements View.OnClickListener {
    private TextView Logout;
    private Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedaddress);

        Save = (Button) findViewById(R.id.UA_save);
        Save.setOnClickListener(this);

        Logout = (TextView) findViewById(R.id.UA_logout);
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(updatedaddress.this, Login.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.UA_save:
                startActivity(new Intent(updatedaddress.this, locations.class));
                break;
        }
    }
}