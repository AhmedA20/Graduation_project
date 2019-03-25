package com.fshu.ourgraduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;





public class SigninActivity extends AppCompatActivity {

    private String email;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);



    }//end onCreate


    //if the user is not signedUp
    public void toSignUp(View view){
        Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
        startActivity(intent);
    }



}
