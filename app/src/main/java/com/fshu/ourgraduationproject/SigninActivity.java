package com.fshu.ourgraduationproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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


public class SigninActivity extends AppCompatActivity {


    private String email = null;
    private String password = null;
    private FirebaseAuth mAuth;
    private final String TAG = "Sign In";
    private Button   submit;
    private EditText mailView ;
    private EditText passView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        submit = (Button) findViewById(R.id.login);
        mailView = findViewById(R.id.logEmail);
        passView = findViewById(R.id.logpassword);
        //DB auth object reference
        mAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mailView.getText().toString();
                password = passView.getText().toString();
                if(validate()){
                    signInWithEmailAndPassword(email,password);
                }
            }
        });
    }//end onCreate

    //signInUser
    private void signInWithEmailAndPassword(String mail, String pass){
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
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }//endCreateNewUser


    public boolean validate() {
        boolean valid = true;

        String email = mailView.getText().toString();
        String password = passView.getText().toString();

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

        return valid;
    }//end validate


    //if the user is not signedUp
    public void toSignUp(View view){
        Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
        startActivity(intent);
    }



}
