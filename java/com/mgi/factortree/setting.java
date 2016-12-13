package com.mgi.factortree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class setting extends AppCompatActivity {

    Button btnTutorial, btnReset, btnBack;
    Switch swBGM,swSound;
    Utility util=new Utility();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnReset = (Button)findViewById(R.id.btnReset);
        btnTutorial = (Button)findViewById(R.id.btnTutorial);
        swBGM=(Switch)findViewById(R.id.swBGM);
        swSound=(Switch)findViewById(R.id.swSound);
        if(!util.isSound(setting.this)){
            swSound.setChecked(false);
        }else{
            swSound.setChecked(true);
        }
        if(!util.isBGM(setting.this)){
            swBGM.setChecked(false);
        }else{
            swBGM.setChecked(true);
        }
        swSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    util.setSettingSound(setting.this,"1");
                }else{
                    util.setSettingSound(setting.this,null);
                }
            }
        });

        swBGM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    util.setSettingBGM(setting.this,"1");
                }else{
                    util.setSettingBGM(setting.this,null);
                }
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }


}
