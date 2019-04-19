package com.example.chirag.rockpaperscissor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RPSGraphic extends Activity implements View.OnClickListener{
    TextView lblAIRes = null,lblComputer=null,lblScore=null;
    ImageView btnRock=null,btnScissor=null,btnPaper=null,btnAI=null;
    int pcnt = 0,acnt = 0,dcnt = 0,total = 0;

    private Integer seconds = 31; //THIS WILL RUN FOR 30 SECONDS
    private boolean stopTimer = false;
    TextView lblTime = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpsgraphic);
        setTitle("GraphicMode");

        btnRock = (ImageView)findViewById(R.id.btnRock);
        btnPaper = (ImageView)findViewById(R.id.btnPaper);
        btnScissor = (ImageView)findViewById(R.id.btnScissor);
        btnAI = (ImageView)findViewById(R.id.btnAI);

        btnRock.setOnClickListener(this);
        btnPaper .setOnClickListener(this);
        btnScissor.setOnClickListener(this);

        lblAIRes = (TextView)findViewById(R.id.lblAIRes);
        lblComputer = (TextView)findViewById(R.id.lblComputer);
        lblScore = (TextView)findViewById(R.id.lblScore);

        lblTime = (TextView) findViewById(R.id.lblTime);
        timer();
    }

    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                seconds--;
                if (seconds == 0) {
                    // DO SOMETHING WHEN TIMES UP
                    stopTimer = true;
                }
                if(stopTimer == false) {
                    handler.postDelayed(this, 1000);
                    lblTime.setText(seconds.toString());
                    ///    Toast.makeText(MainActivity.this,seconds.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    //passing score to next activity
                    Intent i = new Intent(RPSGraphic.this,result.class);
                    i.putExtra("Score",lblScore.getText().toString());
                    startActivity(i);
                    finish();
                }
            }

        });
    }
    @Override
    public void onClick(View view) {
        total++;
        String elements[]={"ROCK","PAPER","SCISSOR"};
        int index = new Random().nextInt(elements.length);
        String ran = (elements[index]);
        lblAIRes.setText(ran);

        String imgName = "a" + index;
        int id = getResources().getIdentifier(imgName, "drawable", getPackageName());
        btnAI.setImageResource(id);

        switch (view.getId()){
            case R.id.btnRock:
                if(ran.equalsIgnoreCase("ROCK"))draw();
                else if(ran.equalsIgnoreCase("PAPER"))awon();
                else if(ran.equalsIgnoreCase("SCISSOR"))pwon();
                break;
            case R.id.btnPaper:
                if(ran.equalsIgnoreCase("PAPER"))draw();
                else if(ran.equalsIgnoreCase("SCISSOR"))awon();
                else if(ran.equalsIgnoreCase("ROCK"))pwon();
                break;
            case R.id.btnScissor:
                if(ran.equalsIgnoreCase("SCISSOR"))draw();
                else if(ran.equalsIgnoreCase("ROCK"))awon();
                else if(ran.equalsIgnoreCase("PAPER"))pwon();
                break;
        }
    }
    void draw() {
        dcnt++;
        lblScore.setText("----- SCORE ----\n\nPlayer : "+pcnt+"\nComputer : "+acnt+"\nDraw : "+dcnt+"\nTotal : "+total);
        Toast.makeText(this, "Match Draw", Toast.LENGTH_SHORT).show();
    }
    void pwon() {
        pcnt++;
        lblScore.setText("----- SCORE ----\n\nPlayer : "+pcnt+"\nComputer : "+acnt+"\nDraw : "+dcnt+"\nTotal : "+total);
        Toast.makeText(this, "Player won", Toast.LENGTH_SHORT).show();
    }
    void awon() {
        acnt++;
        lblScore.setText("----- SCORE ----\n\nPlayer : "+pcnt+"\nComputer : "+acnt+"\nDraw : "+dcnt+"\nTotal : "+total);
        Toast.makeText(this, "Computer won", Toast.LENGTH_SHORT).show();
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }
}
