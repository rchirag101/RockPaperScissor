package com.example.chirag.rockpaperscissor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener{

    TextView lblPChoice=null,lblPC=null,lblRes=null;
    Button btnRock=null,btnScissor=null,btnPaper=null;
    int pcnt = 0,acnt = 0,dcnt = 0,total = 0;


    private Integer seconds = 31; //THIS WILL RUN FOR 30 SECONDS
    private boolean stopTimer = false;
    TextView lblTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SimpleMode");

        lblPC = (TextView)findViewById(R.id.lblPC);
        lblPChoice = (TextView)findViewById(R.id.lblPChoice);
        lblRes = (TextView)findViewById(R.id.lblRes);

        btnRock = (Button)findViewById(R.id.btnRock);
        btnPaper = (Button)findViewById(R.id.btnPaper);
        btnScissor = (Button)findViewById(R.id.btnScissor);

        btnRock.setOnClickListener(this);
        btnPaper.setOnClickListener(this);
        btnScissor.setOnClickListener(this);

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
                    Intent i = new Intent(MainActivity.this,result.class);
                    i.putExtra("Score",lblPC.getText().toString());
                    startActivity(i);
                    finish();
                }
            }

        });
    }
    @Override
    public void onClick(View view) {
        String elements[]={"ROCK","PAPER","SCISSOR"};
        int index = new Random().nextInt(elements.length);
        String ran = (elements[index]);
        lblPChoice.setText(ran);
        total++;
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
        lblRes.setText("Match Draw \uD83D\uDE10");
        dcnt++;
        lblPC.setText("----- SCORE ----\n\nPlayer : "+pcnt+"\n\nComputer : "+acnt+"\n\nDraw : "+dcnt+"\n\nTotal : "+total);
    }
    void pwon() {
        lblRes.setText("Player won");
        pcnt++;
        lblPC.setText("----- SCORE ----\n\nPlayer : "+pcnt+" \uD83D\uDE0A\n\nComputer : "+acnt+"\n\nDraw : "+dcnt+"\n\nTotal : "+total);
    }
    void awon() {
        lblRes.setText("Computer won");
        acnt++;
        lblPC.setText("----- SCORE ----\n\nPlayer : "+pcnt+"\n\nComputer : "+acnt+" \uD83D\uDE0A\n\nDraw : "+dcnt+"\n\nTotal : "+total);
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }
}
