package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.AdminActivity;
import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeActivity extends AppCompatActivity {
    LinearLayout signuplayout, signinlayout;
    TextView signin, signup;
    Button btnsignin, btnsignup;
    EditText edtphonesu, edtpasssu, edtnamesu, edtPassword, edtPhone,edtdiachisu,edtmailsu;
    FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        auth = FirebaseAuth.getInstance();
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtphonesu = findViewById(R.id.edt_su_phone);
        edtnamesu = findViewById(R.id.edt_su_name);
        edtpasssu = findViewById(R.id.edt_su_pass);
        edtdiachisu = findViewById(R.id.edt_su_diachi);
        edtmailsu = findViewById(R.id.edt_su_mail);

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
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            if(validateInfor(edtnamesu.getText().toString(),edtpasssu.getText().toString(),edtphonesu.getText().toString(),edtmailsu.getText().toString(),edtdiachisu.getText().toString())){
                ProgressDialog mDialog = new ProgressDialog(WelcomeActivity.this);
                mDialog.setMessage("Đang xử lí");
                mDialog.show();
                users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(edtphonesu.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(WelcomeActivity.this, "Phone number already register", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            //
                            User user = new User(edtnamesu.getText().toString(), edtpasssu.getText().toString(),edtmailsu.getText().toString(),edtdiachisu.getText().toString());
                            users.child(edtphonesu.getText().toString()).setValue(user);
                            Toast.makeText(WelcomeActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                            signin.setBackground(getResources().getDrawable(R.drawable.switch_trcks));
                            signin.setTextColor(getResources().getColor(R.color.textColor));



                            Intent i= new Intent(WelcomeActivity.this,WelcomeActivity.class);
                            startActivity(i);
                            signup.setBackground(getResources().getDrawable(R.drawable.switch_tumbs));
                            signup.setTextColor(getResources().getColor(R.color.pinkColor));
                            signuplayout.setVisibility(View.GONE);
                            signinlayout.setVisibility(View.VISIBLE);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i= new Intent(WelcomeActivity.this, AdminActivity.class);
//                startActivity(i);
//                finish();
//                if (validateData()) {
//                    ProgressDialog mDialog = new ProgressDialog(WelcomeActivity.this);
//                    mDialog.setMessage("Đang xử lí");
//                    mDialog.show();
//                    final String localPhone = edtPhone.getText().toString();
//                    final String localPassword = edtPassword.getText().toString();
//                    users.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.child(edtPhone.getText().toString()).exists()) {
//                                mDialog.dismiss();
//                                User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
//                                user.setPhone(localPhone);
//
//                                if (user.getPassword().equals(edtPassword.getText().toString())) {
//
//                                    if (Boolean.parseBoolean(user.getIsAdmin())) {
//                                        Toast.makeText(WelcomeActivity.this, "Giao dien server", Toast.LENGTH_SHORT).show();
//                                        Intent i= new Intent(WelcomeActivity.this, AdminActivity.class);
//                                        startActivity(i);
//                                        finish();
//                                        // Tại đây thêm giao diện server
//                                    } else {
//                                        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
//                                        startActivity(i);
//                                        Common.currentUser=user;
//                                    }
//
//                                } else {
//                                    Toast.makeText(WelcomeActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
////                                mDialog.dismiss();
//                                Toast.makeText(WelcomeActivity.this, "User không tồn tại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
                Intent i= new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }

            private boolean validateData() {
                if (edtPhone.getText().toString().isEmpty()) {
                    edtPhone.setError("Vui lòng nhập số điện thoại");
                    return false;
                }
                if (edtPassword.getText().toString().isEmpty()) {
                    edtPassword.setError("Vui lòng nhập mật khẩu");
                    return false;
                }
                return true;
            }

        });

    }
    private boolean validateInfor(String name,String pass,String phone,String mail,String address){
        if(phone.length()==0){
            edtphonesu.requestFocus();
            edtphonesu.setError("Vui lòng nhập số điện thoại");
            return false;
        }else if(!phone.matches("^0\\d{9}$")){
            edtphonesu.requestFocus();
            edtphonesu.setError("Nhập sai số điện thoại");
            return false;
        }else if(name.length()==0){
            edtnamesu.requestFocus();
            edtnamesu.setError("Vui lòng nhập tên");
            return false;
        }else if(!name.matches("^[a-zA-ZÀ-ỹ ]+$")){
            edtnamesu.requestFocus();
            edtnamesu.setError("Tên phải bắt đầu bằng chữ hoa và không có kí tự đặc biệt");
            return false;
        }else  if(mail.length()==0){
            edtmailsu.requestFocus();
            edtmailsu.setError("Vui lòng nhập mail");
            return false;
        }else if(!mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            edtmailsu.requestFocus();
            edtmailsu.setError("Nhập sai mail");
            return false;
        }
        else if(address.length()==0){
            edtpasssu.requestFocus();
            edtpasssu.setError("Vui lòng nhập địa chỉ");
            return false;
        }else if(!pass.matches("^.{6,}$")){
            edtpasssu.requestFocus();
            edtpasssu.setError("Mật khẩu phải có ít nhất 6 kí tự");
            return false;
        }else if(pass.length()==0){
            edtpasssu.requestFocus();
            edtpasssu.setError("Vui lòng nhập pass");
            return false;
        }else if(!pass.matches("^.{6,}$")){
            edtpasssu.requestFocus();
            edtpasssu.setError("Mật khẩu phải có ít nhất 6 kí tự");
            return false;
        }else{
            return true;
        }

    }
}