package com.example.anant.jhack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anant.jhack.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import static android.R.attr.phoneNumber;

public class User extends AppCompatActivity {
private EditText adhar,mob,otp;
 private Button submit,submit2;

    String aadhar,phoneNumber,verification_code;


    FirebaseAuth auth;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        auth = FirebaseAuth.getInstance();
        mCallback= new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verification_code=s;
                Toast.makeText(getApplicationContext(), "Code sent to the number", Toast.LENGTH_SHORT).show();
            }
        };

        adhar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submit.setEnabled(!TextUtils.isEmpty(s.toString().trim()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
     /*   submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aadhar = adhar.getText().toString();
                phoneNumber = mob.getText().toString();

                if (aadhar.equals(null) && phoneNumber.equals(null)) {
                    Toast.makeText(User.this, "Enter all the feilds", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(User.this, userResult.class);
                    i.putExtra("aadhar", aadhar);
                    startActivity(i);

                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_me,menu);
        return true;
    }

    public void setSubmit(View view)
    {

        phoneNumber = mob.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60,TimeUnit.SECONDS,this,mCallback);
    }

    public void SigninwithPhone(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    aadhar = adhar.getText().toString();

                    Toast.makeText(getApplicationContext(), "User Signed in successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(User.this, userResult.class);
                    i.putExtra("aadhar", aadhar);
                    startActivity(i);
                }
                else if(task.getException() instanceof FirebaseAuthUserCollisionException)
                {
                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong code. please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verify(View view)
    {
        String input_code=otp.getText().toString();
        if(verification_code!=null)
        {

            PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verification_code,input_code);
            SigninwithPhone(credential);
        }
    }

    public void verifyPhoneNumber(String verifyCode, String input_code)
    {

    }

    public void init()
    {
        adhar= (EditText)findViewById(R.id.adhar);
        mob= (EditText)findViewById(R.id.mob);
        otp= (EditText)findViewById(R.id.otp2);
        submit= (Button) findViewById(R.id.submit);
        submit2= (Button) findViewById(R.id.submit2);
        auth=FirebaseAuth.getInstance();
    }


}
