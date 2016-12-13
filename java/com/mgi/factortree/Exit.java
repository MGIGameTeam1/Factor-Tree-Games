package com.mgi.factortree;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Exit extends AppCompatActivity {

    Button btnYes, btnNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_as);

        btnYes = (Button) findViewById(R.id.btnYes);
        btnNo = (Button) findViewById(R.id.btnNo);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Exit.this, MainMenu.class));
            }
        });

    }
}
