package com.example.writing;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Panel mPanel;
     long lastTime =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final Button saveButton = findViewById(R.id.SaveButton);
        final Button deleteButton= findViewById(R.id.DeleteButton);
        final Panel mPanel =findViewById(R.id.panel);
        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPanel.setBackground(getDrawable(R.drawable.longlong));
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        final Panel mPanel =findViewById(R.id.panel);
        long newTime = System.currentTimeMillis();
        if(newTime-lastTime>1000){
            if (v.getId()==R.id.SaveButton){
                if (mPanel.getBackground().getConstantState().equals(getDrawable(R.drawable.longlong_copy).getConstantState())&&lastTime!=0){
                    //Toast.makeText(MainActivity.this,"correct",Toast.LENGTH_LONG).show();
                    mPanel.resetCanvas();
                    mPanel.setBackground(getDrawable(R.drawable.longlong));
                    return;
                }
                mPanel.savePicture();
                Toast.makeText(MainActivity.this,"儲存完畢",Toast.LENGTH_LONG).show();

            }
            else if (v.getId()==R.id.DeleteButton){
                mPanel.resetCanvas();
                mPanel.setBackground(getDrawable(R.drawable.longlong_copy));
            }
            lastTime=newTime;
        }
        else{
            Toast.makeText(MainActivity.this,"停",Toast.LENGTH_LONG).show();
        }


    }


}