package com.example.thibault.testandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    Chronometer c;
    ArrayList<Bitmap> smallImage;
    ImageDecoupeAdapter ida;
    int div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = new Intent();
        intent = getIntent();
        div = intent.getIntExtra("divide" , 0);
        int img = intent.getIntExtra("img" , 0);

        c = (Chronometer) findViewById(R.id.timertext);

        c.start();

        TextView v = (TextView) findViewById(R.id.dividetext);
        v.setText("Taquin : " + div + " x " + div);
        ImageAdaptater ia = new ImageAdaptater(Game.this);
        int smallimage_Numbers = div*div;
        ImageView i = new ImageView(Game.this);
        i.setImageResource(ia.mThumbIds[img]);
        ArrayList<Bitmap> smallImage = splitImage(i, smallimage_Numbers);
        ida =  new ImageDecoupeAdapter(this, smallImage , div);
        final GridView gridjeu = (GridView) findViewById(R.id.gridimage);
        gridjeu.setAdapter( ida );
        gridjeu.setNumColumns((int) Math.sqrt(smallImage.size()));
        gridjeu.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ida.change(position);
                gridjeu.invalidateViews();
                gridjeu.setAdapter(ida);
                if(ida.verif() == true)
                {

                    c.stop();
                    String win = getResources().getString(R.string.win);
                    Toast toast = Toast.makeText(Game.this, win + c.getText() , Toast.LENGTH_SHORT);
                    toast.show();
                    Intent newintent = new Intent(Game.this, Highscore.class);
                    newintent.putExtra("score", c.getText());
                    newintent.putExtra("niveau" , div);
                    startActivity(newintent);

                }


            }

        });



    }


    private ArrayList<Bitmap> splitImage(ImageView image, int smallimage_Numbers) {

        //For the number of rows and columns of the grid to be displayed
        int rows, cols;
        //For height and width of the small image smallimage_s
        int smallimage_Height, smallimage_Width;
        //To store all the small image smallimage_s in bitmap format in this list
        ArrayList<Bitmap> smallimages = new ArrayList<Bitmap>(smallimage_Numbers);
        //Getting the scaled bitmap of the source image
        BitmapDrawable mydrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = mydrawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(smallimage_Numbers);


        smallimage_Height = bitmap.getHeight() / rows;
        smallimage_Width = bitmap.getWidth() / cols;

        //xCo and yCo are the pixel positions of the image smallimage_s
        int yCo = 0;
        for (int x = 0; x < rows; x++) {
            int xCo = 0;
            for (int y = 0; y < cols; y++) {

                smallimages.add(Bitmap.createBitmap(scaledBitmap, xCo, yCo, smallimage_Width, smallimage_Height));
                xCo += smallimage_Width;
            }
            yCo += smallimage_Height;
        }


        return smallimages;
    }






}
