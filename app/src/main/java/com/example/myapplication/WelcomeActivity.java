package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
    LinearLayout signuplayout,signinlayout;
    TextView signin,signup;
    Button btnsignin,btnsignup;
    EditText edtmailsu,edtpasssu,edtcfpasssu,edtmailsi,edtpasssi;
    FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();
        edtmailsi = findViewById(R.id.eMail);
        edtpasssi = findViewById(R.id.passwords);
        edtmailsu = findViewById(R.id.eMails);
        edtpasssu = findViewById(R.id.passwordss);
        edtcfpasssu = findViewById(R.id.passwords01);

        signuplayout = findViewById(R.id.sigup_layout);
        signinlayout = findViewById(R.id.login_layout);
        signup = findViewById(R.id.tvsignup);
        signin = findViewById(R.id.tvlogin);
        btnsignin = findViewById(R.id.btnsignIn);
        btnsignup = findViewById(R.id.btnsignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setBackground(getResources().getDrawable(R.drawable.switch_trcks));
                signup.setTextColor(getResources().getColor(R.color.textColor));


                signin.setBackground(getResources().getDrawable(R.drawable.switch_tumbs));
                signin.setTextColor(getResources().getColor(R.color.pinkColor));
                signuplayout.setVisibility(View.VISIBLE);
                signinlayout.setVisibility(View.GONE);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin.setBackground(getResources().getDrawable(R.drawable.switch_trcks));
                signin.setTextColor(getResources().getColor(R.color.textColor));
//

                signup.setBackground(getResources().getDrawable(R.drawable.switch_tumbs));
                signup.setTextColor(getResources().getColor(R.color.pinkColor));
                signuplayout.setVisibility(View.GONE);
                signinlayout.setVisibility(View.VISIBLE);
                ;

            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                signupmethod();
            }


        });
    }

        private void signupmethod() {
            String email= edtmailsu.getText().toString();
            String pass= edtpasssu.getText().toString();
            String cfpass=edtcfpasssu.getText().toString();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(WelcomeActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(WelcomeActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(cfpass)){
                Toast.makeText(WelcomeActivity.this, "Confirm Password is empty", Toast.LENGTH_SHORT).show();
            }
            auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(WelcomeActivity.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(WelcomeActivity.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
}