package com.example.domicileproject;

//This is the code for login activity
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

    //Declaring the variables
    private TextView Login_back, Login_fp;
    private EditText Login_email, Login_password;
    private Button Login_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing Firebase authentication instance
        mAuth = FirebaseAuth.getInstance();

        //Initializing the variables
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

            //If the user clicks on back textview, then the following code will be executed to naviage the user to the MainActivity (i.e., Signup) activity
            case R.id.Login_back:
                startActivity(new Intent(this, MainActivity.class));
                break;

            //If the user clicks forgot password textview, then the following code will be executed to navigate the user to the forgotpassword
            case R.id.Login_fp:
            startActivity(new Intent(this, forgotpassword.class));
            break;

            //If the user clicks on login button, then the following code will be executed
            case R.id.Login_button:
                userLogIn();
                break;
        }
    }

    private void userLogIn() {
        String email = Login_email.getText().toString().trim();
        String password = Login_password.getText().toString().trim();

        //Checking whether email field is empty or not
        if(email.isEmpty()){
            Login_email.setError("Please Enter Email Address");
            Login_email.requestFocus();
            return;
        }

        //Checking whether password field is empty or not
        if(password.isEmpty()){
            Login_password.setError("Please Enter Password");
            Login_password.requestFocus();
            return;
        }

        //Checking whether the entered email is valid or no
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Login_email.setError("Please Enter Valid Email Address");
            Login_email.requestFocus();
            return;
        }

        //Checking whether length of password is longer than 6 characters
        if(password.length() < 6){
            Login_password.setError("Password should be more than 6 characters");
            Login_password.requestFocus();
            return;
        }

        //Performing the Firebase Authentication by passing email and password
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    //redirect to Updated Address
                    startActivity(new Intent(Login.this, updatedaddress.class));
                }else{
                    Toast.makeText(Login.this, "Login is failed! Please try again!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}