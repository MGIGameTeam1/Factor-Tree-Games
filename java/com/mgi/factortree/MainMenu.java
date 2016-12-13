package com.mgi.factortree;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Button btnPlay, btnLeaderboard, btnSetting, btnSignAs,btnExit;
    public static MediaPlayer mp1;
    Utility util=new Utility();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnLeaderboard = (Button) findViewById(R.id.btnLeaderBoard);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSignAs = (Button) findViewById(R.id.btnSignAs);
        btnExit=(Button)findViewById(R.id.btnExit);
        if(util.isSound(MainMenu.this)){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.selamatdatang);
            mp.start();
        }
        mp1=MediaPlayer.create(this,R.raw.mainmenubgm);
        if(util.isBGM(MainMenu.this)){

            mp1.start();
            mp1.setLooping(true);
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, Level.class));
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, setting.class));
            }
        });

        btnSignAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, SignAs.class));
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mpExit=MediaPlayer.create(MainMenu.this,R.raw.apakahkamuyakin);
                if(util.isSound(MainMenu.this)){
                    mpExit.start();
                }

                final Dialog customDialog=new Dialog(MainMenu.this);
                customDialog.setContentView(R.layout.custom_dialog);

                TextView tvJudul=(TextView)customDialog.findViewById(R.id.tvJudulDialog);
                Button btnOK=(Button)customDialog.findViewById(R.id.btnOk);
                Button btnCancel=(Button)customDialog.findViewById(R.id.btnCancel);
                tvJudul.setText("Apakah Kamu Ingin Keluar?");
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mpExit.stop();
                        customDialog.dismiss();
                        mp1.stop();
                        if(util.isSound(MainMenu.this)){
                            MediaPlayer mpSampaiJumpa=MediaPlayer.create(MainMenu.this,R.raw.sampaijumpa);
                            mpSampaiJumpa.start();

                        }
                        finish();


                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        customDialog.dismiss();
                    }
                });
                customDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        final MediaPlayer mpExit=MediaPlayer.create(MainMenu.this,R.raw.apakahkamuyakin);
        if(util.isSound(MainMenu.this)){
            mpExit.start();
        }

        final Dialog customDialog=new Dialog(MainMenu.this);
        customDialog.setContentView(R.layout.custom_dialog);

        TextView tvJudul=(TextView)customDialog.findViewById(R.id.tvJudulDialog);
        Button btnOK=(Button)customDialog.findViewById(R.id.btnOk);
        Button btnCancel=(Button)customDialog.findViewById(R.id.btnCancel);
        tvJudul.setText("Apakah Kamu Ingin Keluar?");
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpExit.stop();
                customDialog.dismiss();
                mp1.stop();
                if(util.isSound(MainMenu.this)){
                    MediaPlayer mpSampaiJumpa=MediaPlayer.create(MainMenu.this,R.raw.sampaijumpa);
                    mpSampaiJumpa.start();

                }
                finish();


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customDialog.dismiss();
            }
        });
        customDialog.show();
    }
}
