package com.example.chirag.rockpaperscissor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class result extends Activity  implements View.OnClickListener {

    TextView lblResFinal = null;
    ImageView btnPlay = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        lblResFinal = (TextView) findViewById(R.id.lblResFinal);
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        Intent i = getIntent();
        String result = i.getExtras().getString("Score");
        lblResFinal.setText(result);
    }
    //disable back button
    @Override
    public void onBackPressed(){    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnPlay) {
            Intent i = new Intent(this, HomeScreen.class);
            startActivity(i);
            finish();
        }
    }
}
