package com.example.domicileproject;

//This is the code for Educational Institutions activity
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

    //Declaring the variables
    private TextView fp_back;
    private EditText fp_email;
    private Button fp_Resetpassword;

    //Declaring the FirebaseAuthentication
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        //Initializing the variables
        fp_back = (TextView) findViewById(R.id.fp_back);
        fp_back.setOnClickListener(this);
        fp_email = (EditText) findViewById(R.id.fp_email);

        fp_Resetpassword = (Button) findViewById(R.id.fp_resetpassword);
        fp_Resetpassword.setOnClickListener(this);

        //Initializing the FirebaseAuthentication instance
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //If the user clicks the back textview, the following code will be executed to navigate the user to the Login activity
            case R.id.fp_back:
                startActivity(new Intent(this, Login.class));
                break;

            //If the user clicks the resetpassword button, the following code will be executed to navigate the user to the resetpassword activity
            case R.id.fp_resetpassword:
                forgotPassword();
                break;

        }
    }

    private void forgotPassword() {
        String email = fp_email.getText().toString().trim();

        //Checking whether email filed is empty or not
        if(email.isEmpty()){
            fp_email.setError("Please Enter Email Address");
            fp_email.requestFocus();
            return;
        }

        //Checking whether the entered email is valid or not
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            fp_email.setError("Please Enter Valid Email Address");
            fp_email.requestFocus();
            return;
        }

        //The following code sends the email to reset the password from firebase to the relevant email provider
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this, "Please Check your email!!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(forgotpassword.this, "Please try again!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}