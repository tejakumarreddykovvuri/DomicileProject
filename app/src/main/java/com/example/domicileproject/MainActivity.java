package com.example.domicileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView Signup_login, Signup_fp;
    private EditText Signup_name, Signup_email, Signup_password, Signup_cp;
    private Button Signup_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Signup_login = (TextView) findViewById(R.id.Signup_login);
        Signup_login.setOnClickListener(this);
        Signup_fp = (TextView) findViewById(R.id.Signup_fp);
        Signup_fp.setOnClickListener(this);

        Signup_name = (EditText) findViewById(R.id.Signup_name);
        Signup_email = (EditText) findViewById(R.id.Signup_email);
        Signup_password = (EditText) findViewById(R.id.Signup_password);
        Signup_cp = (EditText) findViewById(R.id.Signup_cp);

        Signup_button = (Button) findViewById(R.id.Signup_button);
        Signup_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Signup_login:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.Signup_fp:
                startActivity(new Intent(this, forgotpassword.class));
                break;
            case R.id.Signup_button:
                signUp();
                break;

        }
    }

    private void signUp() {
        String name = Signup_name.getText().toString().trim();
        String email = Signup_email.getText().toString().trim();
        String password = Signup_password.getText().toString().trim();
        String confirm_password = Signup_cp.getText().toString().trim();

        if(name.isEmpty()){
            Signup_name.setError("Please Enter Name");
            Signup_name.requestFocus();
            return;
        }

        if(email.isEmpty()){
            Signup_email.setError("Please Enter Email Address");
            Signup_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Signup_password.setError("Please Enter Password");
            Signup_password.requestFocus();
            return;
        }

        if(confirm_password.isEmpty()){
            Signup_cp.setError("Please Enter Confirm Password");
            Signup_cp.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Signup_email.setError("Please Enter Valid Email Address");
            Signup_email.requestFocus();
            return;
        }

        if(!password.equals(confirm_password)){
            Signup_cp.setError("Passwords do not match");
            Signup_cp.requestFocus();
            return;
        }

        if(password.length() < 6){
            Signup_password.setError("Password should be more than 6 characters");
            Signup_password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        User user = new User(name, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                        //redirect to login layout!
                                        startActivity(new Intent(MainActivity.this, Login.class));
                                    }else{
                                        Toast.makeText(MainActivity.this, "Signup is unsuccessful! Please try again!!! ", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        }else{
                            Toast.makeText(MainActivity.this, "Signup is unsuccessful!!! Please try again!!! ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}