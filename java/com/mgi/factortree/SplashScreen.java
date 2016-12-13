package com.mgi.factortree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mgi.factortree.MainMenu;
import com.mgi.factortree.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ImageView imageView=(ImageView)findViewById(R.id.MgiSplash);

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try{
                    Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
                    imageView.startAnimation(startAnimation);

                    sleep(3700);

                    startActivity(new Intent(getApplicationContext(),MainMenu.class));
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}