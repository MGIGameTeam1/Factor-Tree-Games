package com.mgi.factortree;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;


import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InGame extends AppCompatActivity {
    private final int MARGIN = 50;
    private final int HEIGHT = 300;
    private final int NODE_HEIGHT= 100;
    private final int NODE_WIDTH = 100;

    ArrayList<Integer> divisors, quotients;
    ArrayList<TextView> tvOptions=new ArrayList<>();
    RelativeLayout rvLayout;
    LinearLayout lnLayout;
    int root;
    int clickCount=0;
    int answered=0;
    int answeredKey=0;

    private static TextView tvAnswer;
    public static int MODE;
    MediaPlayer mpMusicGame;
    Utility util=new Utility();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        MainMenu.mp1.stop();
        mpMusicGame=MediaPlayer.create(InGame.this,R.raw.musikingame);
        if(util.isBGM(InGame.this)){

            mpMusicGame.start();
            mpMusicGame.setLooping(true);
        }

        rvLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        lnLayout=(LinearLayout)findViewById(R.id.linearLayout);

        TextView textView= (TextView) findViewById(R.id.tvOption1);
        tvOptions.add(textView);

        textView = (TextView) findViewById(R.id.tvOption2);
        tvOptions.add(textView);

        textView = (TextView) findViewById(R.id.tvOption3);
        tvOptions.add(textView);

        textView = (TextView) findViewById(R.id.tvOption4);
        tvOptions.add(textView);

        textView = (TextView) findViewById(R.id.tvOption5);
        tvOptions.add(textView);

        createTree();
        createOptions(1);
    }

    public void createTree(){
        TextView textView;

        //---------------------------Setting Tree------------------------------
        QuestionGenerator questionGenerator = new QuestionGenerator();
        root = questionGenerator.generateNumber(MODE);
        ArrayList<ArrayList<Integer>> solution = questionGenerator.getSolution(root);
        divisors = solution.get(0);
        quotients = solution.get(1);
        final int levelTree = quotients.size()+1;
        int nodeWidth = (getWindowManager().getDefaultDisplay().getWidth()-2*MARGIN - NODE_WIDTH)/levelTree;
        int widthGanjil = MARGIN;
        int widthGenap = MARGIN + nodeWidth;


        //---------------------------Setting Root------------------------------
        textView = new TextView(InGame.this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(NODE_WIDTH,NODE_HEIGHT);
        layoutParams.leftMargin = widthGenap;
        layoutParams.topMargin = MARGIN;
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setLayoutParams(layoutParams);
        textView.setText(root+"");
        textView.setTextSize(35);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setBackgroundResource(R.drawable.diselect_apple);
        rvLayout.addView(textView);
        widthGenap+= nodeWidth;


        //----------------------------Setting Child------------------------------
        for(int i=1, id=1; i<levelTree; i++){
            //-------------Left Child--------------
            //id left child: ganjil, kalo mau manggil pake (1+2*(level-1))
            textView = new TextView(InGame.this);
            textView.setId(id++);
            textView.setBackgroundResource(R.drawable.diselect_apple);
            layoutParams = new RelativeLayout.LayoutParams(NODE_WIDTH,NODE_HEIGHT);
            layoutParams.topMargin = i*HEIGHT;
            layoutParams.leftMargin= widthGanjil;
            textView.setLayoutParams(layoutParams);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(35);
            textView.setTextColor(Color.WHITE);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            widthGanjil+= nodeWidth;
            rvLayout.addView(textView);

            //-------------Right Child---------------
            //ID right child: genap, kalo mau manggil pake 2* level;
            textView = new TextView(InGame.this);
            textView.setId(id++);
            textView.setBackgroundResource(R.drawable.diselect_apple);
            layoutParams = new RelativeLayout.LayoutParams(NODE_WIDTH,NODE_HEIGHT);
            layoutParams.topMargin = i*HEIGHT;
            layoutParams.leftMargin= widthGenap;
            textView.setLayoutParams(layoutParams);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(35);
            textView.setTextColor(Color.WHITE);
            final int position=i;
            final TextView finalTextView = textView;
            final int finalId = id;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCount++;
                    answeredKey=quotients.get(position-1);
                    if(clickCount==2){
                        if(answered==answeredKey){
                            Toast.makeText(InGame.this,"Jawaban Benar",Toast.LENGTH_SHORT).show();
                            finalTextView.setText(tvAnswer.getText());
                            tvAnswer.setBackgroundResource(R.drawable.diselect_apple);
                            tvAnswer = null;
                            clickCount=0;
                            answered = 0;
                            if(position != levelTree-1 && (finalId-1) %2==0)
                                createOptions(position+1);
                            else if(position == levelTree-1){
                                //Jika sudah menjawab jawaban akhir
                            }
                        }else{
                            Toast.makeText(InGame.this,"Jawaban Salah",Toast.LENGTH_SHORT).show();
                            tvAnswer.setBackgroundResource(R.drawable.diselect_apple);
                            clickCount=0;
                        }
                    }
                }
            });
            widthGenap+= nodeWidth;
            rvLayout.addView(textView);
        }
    }

    public void createOptions(int level){

        QuestionGenerator questionGenerator = new QuestionGenerator();

        int n = level==1?root:quotients.get(level-2);
        int divisor = level==1?divisors.get(0):divisors.get(level-1);
        ArrayList<Integer> options = questionGenerator.generateOptions(n, divisor);

        TextView textView;

        for(int i=0, option; i<options.size(); i++){
            option = options.get(i);
            textView = tvOptions.get(i);
            textView.setText(option+"");
            textView.setBackgroundResource(R.drawable.diselect_apple);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            final TextView finalTextView = textView;
            final int finalOption = option;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clickCount<=0){
                        answered= finalOption;
                        clickCount++;
                        tvAnswer = finalTextView;
                        finalTextView.setBackgroundResource(R.drawable.select_apple);

                    }else{
                        if(tvAnswer.getText().toString().equals(finalTextView.getText().toString())) {
                            answered = 0;
                            clickCount = 0;
                            tvAnswer.setBackgroundResource(R.drawable.select_apple);
                        }
                        else{
                            tvAnswer.setBackgroundResource(R.drawable.diselect_apple);
                            tvAnswer = finalTextView;
                            finalTextView.setBackgroundResource(R.drawable.select_apple);;
                            answered = finalOption;
                        }
                    }
                }
            });
        }
        textView = (TextView) findViewById(1+2*(level-1));
        textView.setText(divisor+"");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mpMusicGame.stop();
        MainMenu.mp1=MediaPlayer.create(InGame.this,R.raw.mainmenubgm);
        MainMenu.mp1.start();
        finish();
    }
}
