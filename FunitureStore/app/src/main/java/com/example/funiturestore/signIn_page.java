package com.example.funiturestore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signIn_page extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btn_signin;
    private TextView signupText;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btn_signin = findViewById(R.id.btn_signin);
        signupText = findViewById(R.id.signupText);

        mAuth = FirebaseAuth.getInstance();
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signIn_page.this, com.example.baitaplon.signUp_page.class));
            }
        });
    }

    private void signInUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            editTextEmail.setError("Email không được bỏ trống");
            editTextEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            editTextPassword.setError("Mật khẩu không được bỏ trống");
            editTextPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(signIn_page.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signIn_page.this, com.example.baitaplon.product_page.class));  /*Tới trang sản phẩm*/
                    }else {
                        Toast.makeText(signIn_page.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}