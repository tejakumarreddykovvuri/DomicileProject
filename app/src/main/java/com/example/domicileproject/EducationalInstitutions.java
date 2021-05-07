package com.example.domicileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class EducationalInstitutions extends AppCompatActivity implements View.OnClickListener {
    private TextView Logout, EI_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_institutions);

        EI_back = (TextView) findViewById(R.id.EI_back);
        EI_back.setOnClickListener(this);

        Logout = (TextView) findViewById(R.id.EI_logout);
        Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(EducationalInstitutions.this, Login.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EI_back:
                startActivity(new Intent(EducationalInstitutions.this, references.class));
                break;
        }
    }
}