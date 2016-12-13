package com.mgi.factortree;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText etUsername, etEmail, etPassword, etConfirm;
    Button btnRegister;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        etUsername=(EditText)findViewById(R.id.etUsername);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etConfirm=(EditText)findViewById(R.id.etConfirm);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                String username=etUsername.getText().toString();
                String confirmPassword=etConfirm.getText().toString();

                daftarEmailPassword(email,password,username,confirmPassword);
            }
        });

    }

    public void daftarEmailPassword(final String email, String password, final String username, String confirmPassword){
        if(password.equals(confirmPassword)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        PojoUser pojoUser=new PojoUser(email,username);
                        mDatabase.child("users").child(task.getResult().getUser().getUid()).setValue(pojoUser);
                    }else{
                        Toast.makeText(Register.this,"Register Gagal Email Telah Digunakan",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            etConfirm.setError("Konfirm Password Tidak Sama");
        }
    }
}