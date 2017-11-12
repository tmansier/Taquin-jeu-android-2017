package com.example.thibault.testandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ImageDecoupeAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Bitmap> smallimages;
    private int imageWidth, imageHeight;
    private int niveau;
    private Bitmap unlockImg ;
    private ArrayList<Bitmap> smallimageverif;
    Random randomGenerator = new Random();
    private int vide = randomGenerator.nextInt(9);
    private int[] testCase = {0,0,0,0};
    private float destinationX = 0;
    private float destinationY = 0;

    public ImageDecoupeAdapter(Context c, ArrayList<Bitmap> images , int level ){

        mContext = c;
        smallimages = images;
        imageWidth = images.get(0).getWidth()/2;
        imageHeight = images.get(0).getHeight()/2;
        Bitmap tmpBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.images);
        unlockImg =  Bitmap.createScaledBitmap(tmpBitmap , imageWidth  , imageHeight, true);

        niveau = level;
        smallimages.set(vide, unlockImg);

        smallimageverif = (ArrayList<Bitmap>)smallimages.clone();

        melangebase();


    }

    public int getRandomInt()
    {
        return vide;
    }


    public int getCount() {

        return smallimages.size();

    }

    public Object getItem(int position) {

        return smallimages.get(position);

    }


    public long getItemId(int position) {

        return position;

    }



    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView image;

        if(convertView == null){

            image = new ImageView(mContext);


            image.setLayoutParams(new GridView.LayoutParams(imageWidth, imageHeight));

        }else{

            image = (ImageView) convertView;

        }

        image.setImageBitmap(smallimages.get(position));

        return image;

    }

    public void change( int pos ){


        Integer[] testCase = {null,null,null,null};

        if(pos >= 0 && pos < smallimages.size()){

            if(pos+1 != 0 && (pos+1)%niveau != 0 ){
                testCase[0] = pos+1;
            }
            else if (pos+1 == 0 )
            {
                testCase[0] = 0;
            }
            if(pos+niveau != 0){
                testCase[1] = pos+niveau;
            }
            else if (pos+niveau == 0)
            {
                testCase[1] = 0;
            }
            if(pos-1 != 0 && (pos-1)%niveau != niveau-1 ){
                testCase[2] = pos-1;
            }
            else if (pos-1 == 0)
            {
                testCase[2] = 0;
            }
            if(pos-niveau != 0 ){
                testCase[3] = pos-niveau;
            }
            else if (pos-niveau == 0)
            {
                testCase[3] = 0;
            }
            for(int i=0; i<4; i++) {
                if (testCase[i] != null) {
                    if (testCase[i] == vide) {
                        int tmp = vide;
                        smallimages.set(vide, smallimages.get(pos));
                        vide = pos;
                        smallimages.set(pos, unlockImg);
                        pos = tmp;

                    }
                }
            }

    }


    }

    public void melangebase() {
        int i = 0;
        int r;
        while (i < 500) {
            r = this.randomGenerator.nextInt(niveau*niveau);
            this.change(r);
            i++;
        }
    }

    public boolean verif()
    {
        if(smallimages.equals(smallimageverif))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}