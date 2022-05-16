package com.example.baitaplon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp_page extends AppCompatActivity {
    private EditText editTextName;
    private  EditText editTextEmail;
    private  EditText editTextPassword;
    private  EditText editTextConfirm;
    private Button btn_signup;
    private TextView signupGoogle;
    private TextView signInHere;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirm = findViewById(R.id.editTextPasswordConfirm);
        btn_signup = findViewById(R.id.btn_signup);
        signupGoogle = findViewById(R.id.signupGoogle);
        signInHere = findViewById(R.id.textSignInHere);

        mAuth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        signInHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp_page.this, signIn_page.class));
            }
        });
    }

    private  void createUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String name = editTextName.getText().toString();
        String confirm = editTextConfirm.getText().toString();
        if(TextUtils.isEmpty(email)){
            editTextEmail.setError("Email không được bỏ trống");
            editTextEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            editTextPassword.setError("Mật khẩu không được bỏ trống");
            editTextPassword.requestFocus();
        } else if (TextUtils.isEmpty(name)){
            editTextName.setError("Tên không được bỏ trống");
            editTextName.requestFocus();
        } else if(!confirm.equals(password)){
            editTextConfirm.setError("Mật khẩu chưa khớp");
            editTextConfirm.requestFocus();
        } else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(signUp_page.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signUp_page.this, signIn_page.class));
                    } else{
                        Toast.makeText(signUp_page.this, "Lỗi: Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}