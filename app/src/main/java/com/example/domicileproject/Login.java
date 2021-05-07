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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView Login_back, Login_fp;
    private EditText Login_email, Login_password;
    private Button Login_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Login_back = (TextView) findViewById(R.id.Login_back);
        Login_back.setOnClickListener(this);
        Login_fp = (TextView) findViewById(R.id.Login_fp);
        Login_fp.setOnClickListener(this);

        Login_button = (Button) findViewById(R.id.Login_button);
        Login_button.setOnClickListener(this);

        Login_email = (EditText) findViewById(R.id.Login_email);
        Login_password = (EditText) findViewById(R.id.Login_password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login_back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Login_fp:
                startActivity(new Intent(this, forgotpassword.class));
                break;
            case R.id.Login_button:
                userLogIn();
                break;
        }
    }

    private void userLogIn() {
        String email = Login_email.getText().toString().trim();
        String password = Login_password.getText().toString().trim();

        if(email.isEmpty()){
            Login_email.setError("Please Enter Email Address");
            Login_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Login_password.setError("Please Enter Password");
            Login_password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Login_email.setError("Please Enter Valid Email Address");
            Login_email.requestFocus();
            return;
        }

        if(password.length() < 6){
            Login_password.setError("Password should be more than 6 characters");
            Login_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //redirect to Updated Address
                    startActivity(new Intent(Login.this, updatedaddress.class));
                }else{
                    Toast.makeText(Login.this, "Login is falied! Please try again!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}