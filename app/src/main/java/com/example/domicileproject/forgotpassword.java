package com.example.domicileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity implements View.OnClickListener {
    private TextView fp_back;
    private EditText fp_email;
    private Button fp_Resetpassword;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        fp_back = (TextView) findViewById(R.id.fp_back);
        fp_back.setOnClickListener(this);
        fp_email = (EditText) findViewById(R.id.fp_email);

        fp_Resetpassword = (Button) findViewById(R.id.fp_resetpassword);
        fp_Resetpassword.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fp_back:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.fp_resetpassword:
                forgotPassword();
                break;

        }
    }

    private void forgotPassword() {
        String email = fp_email.getText().toString().trim();

        if(email.isEmpty()){
            fp_email.setError("Please Enter Email Address");
            fp_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            fp_email.setError("Please Enter Valid Email Address");
            fp_email.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this, "Please Check your email ID!!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(forgotpassword.this, "Please try again!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}