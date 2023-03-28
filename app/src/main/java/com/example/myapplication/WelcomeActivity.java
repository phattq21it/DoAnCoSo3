package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {
    LinearLayout signuplayout,signinlayout;
    TextView signin,signup;
    Button btnsignin,btnsignup;
    EditText edtmailsu,edtpasssu,edtcfpasssu,edtPassword,edtPhone;
    FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        auth = FirebaseAuth.getInstance();
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtmailsu = findViewById(R.id.eMails);
        edtpasssu = findViewById(R.id.passwordss);
        edtcfpasssu = findViewById(R.id.passwords01);

        signuplayout = findViewById(R.id.sigup_layout);
        signinlayout = findViewById(R.id.login_layout);
        signup = findViewById(R.id.tvsignup);
        signin = findViewById(R.id.tvlogin);
        btnsignin = findViewById(R.id.btnsignIn);
        btnsignup = findViewById(R.id.btnsignup);

        // init Firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference users = db.getReference("User");
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
                if(validateData()){
                    ProgressDialog mDialog  = new ProgressDialog(WelcomeActivity.this);
                    mDialog.setMessage("Đang xử lí");
                    mDialog.show();
                    final String localPhone = edtPhone.getText().toString();
                    final String localPassword = edtPassword.getText().toString();
                    users.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(edtPhone.getText().toString()).exists()) {
                                mDialog.dismiss();
                                User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                user.setPhone(localPhone);

                                if (user.getPassword().equals(edtPassword.getText().toString())) {

                                    if(Boolean.parseBoolean(user.getIsAdmin())){
                                        Toast.makeText(WelcomeActivity.this, "Giao dien server", Toast.LENGTH_SHORT).show();
                                        // Tại đây thêm giao diện server
                                    }
                                    else {
                                        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                                        startActivity(i);
                                    }

                                } else {
                                    Toast.makeText(WelcomeActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(WelcomeActivity.this, "User không tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

               }
            private boolean validateData() {
                if(edtPhone.getText().toString().isEmpty()){
                    edtPhone.setError("Vui lòng nhập số điện thoại");
                    return false;
                }
                if(edtPassword.getText().toString().isEmpty()){
                    edtPassword.setError("Vui lòng nhập mật khẩu");
                    return false;
                }
                return true;
            }
        });
    }
        }