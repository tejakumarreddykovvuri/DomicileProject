package com.example.domicileproject;

//This is the code for Sign Up activity
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

    //Declaring the variables
    private FirebaseAuth mAuth;
    private TextView Signup_login, Signup_fp;
    private EditText Signup_name, Signup_email, Signup_password, Signup_cp;
    private Button Signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Firebase authentication instance
        mAuth = FirebaseAuth.getInstance();

        //Initializing the variables
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

            //If the user clicks on login textview, then the following code will be executed to navigate the user to the Login activity
            case R.id.Signup_login:
                startActivity(new Intent(this, Login.class));
                break;

            //If the user clicks on forgot password textview, then the following code will be executed to navigate the user to the forgotpassword activity
            case R.id.Signup_fp:
                startActivity(new Intent(this, forgotpassword.class));
                break;

            //If the user clicks on Sign Up button, then the following code will be executed
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

        //Checking whether name field is empty or not
        if(name.isEmpty()){
            Signup_name.setError("Please Enter Name");
            Signup_name.requestFocus();
            return;
        }

        //Checking whether email field is empty or not
        if(email.isEmpty()){
            Signup_email.setError("Please Enter Email Address");
            Signup_email.requestFocus();
            return;
        }

        //Checking whether password field is empty or not
        if(password.isEmpty()){
            Signup_password.setError("Please Enter Password");
            Signup_password.requestFocus();
            return;
        }

        //Checking whether confirm password field is empty or not
        if(confirm_password.isEmpty()){
            Signup_cp.setError("Please Enter Confirm Password");
            Signup_cp.requestFocus();
            return;
        }

        //Checking whether the entered email is valid or no
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Signup_email.setError("Please Enter Valid Email Address");
            Signup_email.requestFocus();
            return;
        }

        //Checking password is equal to confirm password
        if(!password.equals(confirm_password)){
            Signup_cp.setError("Passwords do not match");
            Signup_cp.requestFocus();
            return;
        }

        //Checking whether length of password is longer than 6 characters
        if(password.length() < 6){
            Signup_password.setError("Password should be more than 6 characters");
            Signup_password.requestFocus();
            return;
        }

        //Performing the Firebase new user creation
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        User user = new User(name, email);

                            //Saving Data to Users node in Firebase Realtime Database
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