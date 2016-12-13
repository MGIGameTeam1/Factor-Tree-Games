package com.mgi.factortree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CekEmail extends AppCompatActivity {
    Button btnBacktoMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_email);

        btnBacktoMainMenu = (Button) findViewById(R.id.btnBacktoMainMenu);

        btnBacktoMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CekEmail.this, MainMenu.class));
            }
        });
    }
}
