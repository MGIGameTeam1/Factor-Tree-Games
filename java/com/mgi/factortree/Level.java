package com.mgi.factortree;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level extends AppCompatActivity {
    MediaPlayer mpLevel;
    Button btnEasy,btnMedium,btnHard;
    MediaPlayer mpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        mpLevel=MediaPlayer.create(Level.this,R.raw.tingkatkesulitan);
        mpLevel.start();
        btnEasy=(Button)findViewById(R.id.btnEasy);
        btnMedium=(Button)findViewById(R.id.btnMedium);
        btnHard=(Button)findViewById(R.id.btnHard);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InGame.MODE=QuestionGenerator.MODE_EASY;
                mpButton=MediaPlayer.create(Level.this,R.raw.ayomulai);
                mpButton.start();
                startActivity(new Intent(Level.this,InGame.class));
            }
        });

        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpButton=MediaPlayer.create(Level.this,R.raw.ayomulai);
                mpButton.start();
                InGame.MODE=QuestionGenerator.MODE_NORMAL;
                startActivity(new Intent(Level.this,InGame.class));
            }
        });

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpButton=MediaPlayer.create(Level.this,R.raw.ayomulai);
                mpButton.start();
                InGame.MODE=QuestionGenerator.MODE_HARD;
                startActivity(new Intent(Level.this,InGame.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mpLevel.stop();
        finish();
    }
}
