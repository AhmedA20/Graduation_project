package com.fshu.ourgraduationproject;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class GradesActivity extends AppCompatActivity {


    private TextView gradesText;
    private Spinner spinner;
    private ScrollView scroll;

    private FirebaseDatabase mFireDB;
    private DatabaseReference mDBreference;
    private gradesImport myGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_activity);
        this.populateSpinner();
        this.setBackground();
        //TODO:enable these options on fully implementing
        //this.fireBase();
        //this.setFields();
    }

    //spinner data added
    //TODO: modify this function to get the spinner data from the DB
    protected void populateSpinner(){
        //spinner modification
        spinner = (Spinner) findViewById(R.id.subject);
        /* Create an ArrayAdapter using the string array and a default spinner layout
           -->ArrayAdapter<CharSequence> adapter
              *creating ArrayAdapter object*
           -->android.R.layout.simple_spinner_item
              *defines spinner layout*
         */
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Subject, android.R.layout.simple_spinner_item);

        //  Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    //background setter
    protected void setBackground(){
        //grades page background
        //A general purpose Resource listing
        Resources res = getResources();//gets resources data
        //drawable object: A drawable resource is a general concept for a graphic that can be drawn to the screen
        Drawable shape = ResourcesCompat.getDrawable(res, R.drawable.gradient_4, getTheme());
        Drawable child = ResourcesCompat.getDrawable(res, R.drawable.layout_shape, getTheme());
        scroll = (ScrollView)findViewById(R.id.grades_page);
        LinearLayout parent = (LinearLayout)findViewById(R.id.parent);
        scroll.setBackground(shape);
        parent.setBackground(child);
    }

    //fire base initializer
    public void fireBase(){
        /*
         *gets instance of the class
         *acts as the main access point
         */
        mFireDB = FirebaseDatabase.getInstance();
        /*
         *gives a reference the root node(--myFire.getReference()--)
         *using the reference we specify the messages node(--child("messages")--)
         */
        //TODO:change this to your root db location
        mDBreference = mFireDB.getReference().child("messages");
    }

    //

    //textView value Setter
    protected void setFields(){

        //initial implementation
        String setter = "Oral Marks  : "+Double.toString(myGrades.getOral());
        gradesText = (TextView)findViewById(R.id.oralVal);
        gradesText.setText(setter);

        setter = "Attendance Marks : "+Double.toString(myGrades.getAttendance());
        gradesText = (TextView)findViewById(R.id.attendanceVal);
        gradesText.setText(setter);

        setter = "Mid Term Marks : "+Double.toString(myGrades.getMidTerm());
        gradesText = (TextView)findViewById(R.id.midtermVal);
        gradesText.setText(setter);

        setter = "Final Exam Marks : "+Double.toString(myGrades.getFinalExam());
        gradesText = (TextView)findViewById(R.id.finalexamVal);
        gradesText.setText(setter);


    }
}
