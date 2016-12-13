package com.mgi.factortree;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignAs extends AppCompatActivity {
    EditText etUsername, etPassword;
    TextView tvForgot, tvRegister;
    Button btnSignIn;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_as);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);

        tvForgot = (TextView) findViewById(R.id.tvForgot);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        btnSignIn=(Button)findViewById(R.id.btnSignAs);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etUsername.getText().toString();
                password=etPassword.getText().toString();
                new doSign().execute();
            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignAs.this, ForgotPassword.class));
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignAs.this, Register.class));
            }
        });
    }


    class doSign extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(SignAs.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(SignAs.this,MainMenu.class));
                    }else{
                        Toast.makeText(SignAs.this,"Login Gagal",Toast.LENGTH_LONG).show();
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }

    public void registerMesosfer(){

    }
}
