package com.example.anant.jhack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dot extends AppCompatActivity {
    private Button submit;
    private EditText login,pass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG,id,password;

    // this code is to start listener of email authentication
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language l = (new Language());
        try{

            l.setLocale(dot.this);
        }
        catch (NullPointerException e){

        }
        setContentView(R.layout.activity_dot);
        mAuth = FirebaseAuth.getInstance();

        init();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(dot.this, "Welcome to DoT app "+user.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(dot.this, dot_connection.class);
                    startActivity(i);
                    finish();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Toast.makeText(dot.this, "singn in first ", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id= login.getText().toString();
                password= pass.getText().toString();

                if (id.equals(null) && password.equals(null)) {

                    Toast.makeText(dot.this, "Enter id & password", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(id, password)
                        .addOnCompleteListener(dot.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(dot.this, "Invalid",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    Log.w("checkUserSignInStatus", "signInWithEmail:failed", task.getException());
                                    Toast.makeText(dot.this, " correct",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });
        methodListener();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_me,menu);
        return true;
    }

    private void init()
    {
        login = (EditText) findViewById(R.id.login);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);
    }

    private void methodListener()
    {



    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
