package com.fshu.ourgraduationproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private Intent intent;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private final String ANONYMOUS = "anonymous";
    private String mUsername, mPhotoUrl;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //tool bar//
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override//initiated AuthState Listener not attached yet
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //listener attached at onResume detached at onPause
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    onSignedIn();
                } else {
                    // Not signed in, launch the Sign In activity
                    intent = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };
    }

    //creates menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }


    //onSigningOut
    //todo:watch for changes in this method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                mFirebaseAuth.signOut();
                mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
                mUsername = ANONYMOUS;
                startActivity(new Intent(this, SigninActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSignedIn(){
        setContentView(R.layout.activity_main);
        mUsername = mFirebaseUser.getDisplayName();
        if (mFirebaseUser.getPhotoUrl() != null) {
            mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
        }
    }

    @Override
    protected void onResume() {//attach the authentication listener
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {//detach the authentication listener
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }







    //home buttons function
    public void toActivity(View view){
        switch (view.getId()){
            case R.id.subject:
                intent = new Intent(MainActivity.this, SubjectActivity.class);
                startActivity(intent);
                break;
            case R.id.grade:
                intent = new Intent(MainActivity.this, GradesActivity.class);
                startActivity(intent);
                break;
            case R.id.chat:
                intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
                break;
            case R.id.attendance:
                intent = new Intent(MainActivity.this, AttendanceActivity.class);
                startActivity(intent);
                break;
            case R.id.table:
                intent = new Intent(MainActivity.this, TableActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        // disable going back to the Signing Activity
        moveTaskToBack(true);
    }

    /*//todo:inCase of (onActivity) method failure enable this set of method here and in maun activity xml file
    //home buttons function
    public void toGrades(View view){
        Intent intent = new Intent(MainActivity.this, GradesActivity.class);
        startActivity(intent);
    }

    public void toSubject(View view){
        Intent intent = new Intent(MainActivity.this, SubjectActivity.class);
        startActivity(intent);
    }

    public void toChat(View view){
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    public void toAttendance(View view){
        Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
        startActivity(intent);
    }

    public void toTable(View view){
        Intent intent = new Intent(MainActivity.this, TableActivity.class);
        startActivity(intent);
    }
     */







}
