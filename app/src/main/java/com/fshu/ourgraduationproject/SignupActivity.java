package com.fshu.ourgraduationproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.jar.Attributes;


public class SignupActivity extends AppCompatActivity {

    private String email = null;
    private String password = null;
    private String rePassword =null;
    private userInfo info;
    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    private final String TAG = "Sign Up";
    private Button submit;
    private EditText name,mailView,passView,rePassView,year,dep,sub_dep;




//TODO ACCESS AND STORE THE REST OF THE USER FIELD DATA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.Name);
        mailView = findViewById(R.id.Email);
        passView = findViewById(R.id.password);
        rePassView = findViewById(R.id.repassword);
        year = findViewById(R.id.Year);
        dep = findViewById(R.id.dep);
        sub_dep = findViewById(R.id.sub_dep);


        //authentication obj reference
        mAuth = FirebaseAuth.getInstance();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mailView.getText().toString();
                password = passView.getText().toString();
                rePassword = rePassView.getText().toString();

                if(validate()){
                    createNewUser(email,password);
                    mFirebaseAuth = FirebaseAuth.getInstance();
                    mFirebaseUser = mFirebaseAuth.getCurrentUser();
                    UpdateUserInfo();
                }
            }
        });


    }//end onCreate

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }



    //signupUser
    private void createNewUser(String mail, String pass){
        mAuth.createUserWithEmailAndPassword(email, password)//the main signUp method
                //checks whether the operation succeeded
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    //todo modify the state flow
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }//endCreateNewUser


    //User Credentials validation
    private boolean validate() {
        boolean valid = true;

        String name            = this.name.getText().toString();
        String email           = mailView.getText().toString();
        String password        = passView.getText().toString();
        String reEnterPassword = rePassView.getText().toString();
        String year            = this.year.getText().toString();
        String department      = dep.getText().toString();
        String subDepartment   = sub_dep.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            this.name.setError("at least 3 characters");
            valid = false;
        } else {
            this.name.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mailView.setError("enter a valid email address");
            valid = false;
        } else {
            mailView.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passView.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passView.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            rePassView.setError("Password Do not match");
            valid = false;
        } else {
            rePassView.setError(null);
        }


        if (year.isEmpty()) {
            this.year.setError("Enter Valid Address");
            valid = false;
        } else {
            this.year.setError(null);
        }


        if (department.isEmpty()) {
            dep.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            dep.setError(null);
        }

        if (subDepartment.isEmpty()) {
            sub_dep.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            sub_dep.setError(null);
        }

        return valid;
    }//end validation

    private void UpdateUserInfo(){
        String name            = this.name.getText().toString();
        String year            = this.year.getText().toString();
        String department      = dep.getText().toString();
        String subDepartment   = sub_dep.getText().toString();
        info = new userInfo();
        info.setName(name);
        info.setDepartment(department);
        info.setSubDepartment(subDepartment);
        info.setYear(year);
        info.setID(mFirebaseUser.getUid());//id of the firebase user *****use carefully**
    }

}
