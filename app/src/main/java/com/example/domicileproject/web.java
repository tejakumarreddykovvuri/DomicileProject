package com.example.domicileproject;

//This is the code for web activity
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class web extends AppCompatActivity{

    //Defining webview
    private WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //Declaring and receiving the data from the Educationalinstitutions, banks, and Telecomms activities
        String deakin = getIntent().getStringExtra("deakin");
        Log.d("deakin", String.valueOf(deakin));
        String Common = getIntent().getStringExtra("Common");
        Log.d("Common", String.valueOf(Common));
        String Opt = getIntent().getStringExtra("Opt");
        Log.d("Optus", String.valueOf(Opt));

        //Initializing webview
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        //Checking whether the user clicked the Deakin University link. Then if deakin has a value other the null, the following code will be executed
        if (deakin != null) {

            //Loading the url usnig webview - opens the website in webview
            webView.loadUrl("https://studentconnect.deakin.edu.au/connect/webconnect");

            //writing and passing javascript codes through a variable
            //Give your username and password of Deakin University in the username and password fields
            final String js = "javascript:document.getElementById('username').value='';"
                    + " document.getElementById('password').value='';"
                    + "document.getElementsByTagName('button')[0].click();";

            //Using the setWebViewClient for webView
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (Build.VERSION.SDK_INT >= 19) {
                        view.evaluateJavascript(js, new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {

                            }
                        });
                    }

                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                }
            });

        }

        //Checking whether the user clicked the commonwealth bank link. Then if Common has a value other the null, the following code will be executed
        else if (Common != null) {
            webView.loadUrl("https://www.commbank.com.au/digital/MyDetails/");
        }

        //Checking whether the user clicked the Optus link. Then if Opt has a value other the null, the following code will be executed
        else if (Opt != null) {
            webView.loadUrl("https://www.optus.com.au/my-account-login");
        }

    }
}