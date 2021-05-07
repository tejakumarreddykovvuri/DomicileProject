package com.example.domicileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class references extends AppCompatActivity implements View.OnClickListener {

    private TextView Logout, references_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        references_back = (TextView) findViewById(R.id.Ref_back);
        references_back.setOnClickListener(this);

        Logout = (TextView) findViewById(R.id.Ref_logout);
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(references.this, Login.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Ref_back:
                startActivity(new Intent(references.this, locations.class));
                break;
        }
    }
}