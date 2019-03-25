package com.fshu.ourgraduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;




public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tool bar//
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }







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
}
