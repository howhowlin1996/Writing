package com.example.writing.puzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PuzzleCombine {

    Bitmap leftpart=null,middlepart=null,rightpart=null;
    int type;
    String photo_name;

    public PuzzleCombine(int puzzletype, Drawable left, Drawable middle, Drawable right){
        type=puzzletype;
        if (left!=null){
            leftpart=((BitmapDrawable) left).getBitmap();
        }
        if (right!=null){
            rightpart=((BitmapDrawable) right).getBitmap();
        }
        if (middle!=null){
            middlepart=((BitmapDrawable) middle).getBitmap();
        }
    }

    public void Combine(){
        if (type/10==1){

        }
        else if (type/10==2){

        }
        else if (type/10==3){
           if (type%10==1){
               saveTwoPicture();
               Log.d("HERE",photo_name);
           }
        }
        else if(type/10==4){

        }
        else if(type/10==5){

        }


    }

    public void saveTwoPicture(){

        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = photo_name+ ".jpg";
        File file = new File(appDir, fileName);
        Bitmap bmpcombine=Bitmap.createBitmap(leftpart.getWidth()*3,leftpart.getHeight()*3,leftpart.getConfig());
        Log.d("Hi",leftpart.getWidth()+" "+leftpart.getHeight());
        try {
            Log.d("Hi",photo_name);
            Canvas canvas=new Canvas (bmpcombine);
            Log.d("Hi",canvas.getWidth()+" "+canvas.getHeight());
            canvas.drawColor(Color.WHITE);
            int bmpWidth = leftpart.getWidth();
            int bmpHeight=leftpart.getHeight();
            float newWidth=leftpart.getWidth()/2;
            float newHeight=leftpart.getHeight();
            Matrix matrix=new Matrix();
            matrix.postScale((float)0.5,1);
            int rightWidth = rightpart.getWidth();
            int rightHeight=rightpart.getHeight();
            float newrightWidth=rightpart.getWidth()/2;
            float newrightHeight=rightpart.getHeight();
            Matrix rightmatrix=new Matrix();
            rightmatrix.postScale((float)0.5,1);
            leftpart=Bitmap.createBitmap(leftpart,0,0,bmpWidth,bmpHeight,matrix,true);
            rightpart=Bitmap.createBitmap(rightpart,0,0,rightWidth,rightHeight,rightmatrix,true);
            Log.d("Hi",leftpart.getWidth()+" "+leftpart.getHeight());
            canvas.drawBitmap(leftpart,0,0,null);
            canvas.drawBitmap(rightpart,bmpWidth,0,null);
            FileOutputStream fos = new FileOutputStream(file);
            bmpcombine.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Log.d("hahaha","no file");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("hahaha","error");
            e.printStackTrace();
        }





    }
}
