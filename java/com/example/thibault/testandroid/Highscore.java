package com.example.thibault.testandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Highscore extends AppCompatActivity {


    private int tempsdebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        SharedPreferences sp = this.getSharedPreferences("Best_Chrono", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        Intent intent = new Intent();
        intent = getIntent();
        TextView scorelb = (TextView) findViewById(R.id.scorelb);
        scorelb.setText(getResources().getString(R.string.score));

        TextView bestlb = (TextView) findViewById(R.id.bestlb);
        bestlb.setText(getResources().getString(R.string.bestscores));

        String scoretps = intent.getStringExtra("score");
        int niveau = intent.getIntExtra("niveau", 0);
        TextView scorenow = (TextView) findViewById(R.id.scorenow);

        Button play = (Button) findViewById(R.id.replay);
        play.setText(getResources().getString(R.string.rejouer));

        String [] ms = scoretps.split(":");

        if(niveau == 3)
        {
            tempsdebase = 500;
        }
        else if (niveau == 4)
        {
            tempsdebase = 1000;
        }
        else
        {
            tempsdebase = 5000;
        }

        int scoreenpoints =  tempsdebase - (Integer.parseInt(ms[0])*60+Integer.parseInt(ms[1]));
        scorenow.setText(scoreenpoints + " pts / " + scoretps);
        String score_max = sp.getString(niveau+"x"+niveau+"_time","00:00");
        int scoreenpoints_max = sp.getInt(niveau+"x"+niveau+"_points",0);

        if(scoreenpoints>scoreenpoints_max){

            ed.putInt(niveau+"x"+niveau+"_points",scoreenpoints);
            ed.putString(niveau+"x"+niveau+"_time",scoretps);
            ed.commit();


            score_max = sp.getString(niveau+"x"+niveau+"_time","00:00");
            scoreenpoints_max = sp.getInt(niveau+"x"+niveau+"_points",0);


            String nbs = getResources().getString(R.string.newbestscore);
            Toast toast = Toast.makeText(Highscore.this, nbs + scoreenpoints_max , Toast.LENGTH_SHORT);
            toast.show();
        }

        TextView scorebest = (TextView) findViewById(R.id.bestscore);
        scorebest.setText(scoreenpoints_max+ " pts / " + score_max);


        Button rejouer = (Button) this.findViewById(R.id.replay);
        rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
                Intent intent = new Intent(Highscore.this,Test.class);
               
                startActivity(intent);
            }
        });



    }
}
