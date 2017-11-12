package com.example.thibault.testandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Test extends AppCompatActivity {


    int img;
    int divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final ImageAdaptater ia = new ImageAdaptater(this);

        TextView gridtaille = (TextView) findViewById(R.id.tailleGrid);
        gridtaille.setText(getResources().getString(R.string.gridsize));

        Button play = (Button) findViewById(R.id.play);
        play.setText(getResources().getString(R.string.jouer));

        TextView imgchoix = (TextView) findViewById(R.id.Choiximg);
        imgchoix.setText(getResources().getString(R.string.imagechoix));

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(ia);

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grid_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                divide = position +3;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                img = position;
            }

        });

        Button b = (Button) findViewById(R.id.play);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Test.this, Game.class);
                intent.putExtra("img", img);
                intent.putExtra("divide" , divide);
                startActivity(intent);
            }

        });

    }


}