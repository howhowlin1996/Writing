package com.example.writing.panel;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.badge.Badge;


public class WritingPanel extends AppCompatActivity implements View.OnClickListener {
    //Panel mPanel;
     long lastTime =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);                                         //import layout.xml
        final Button saveButton = findViewById(R.id.SaveButton);                 //get two button id in layout.xml saveButton and deleteButton
        final Button deleteButton= findViewById(R.id.DeleteButton);
        final Panel mPanel =findViewById(R.id.panel);                            //get the panel id in layout.xml
        final  Button badge=findViewById(R.id.BadgeButton);
        saveButton.setOnClickListener(this);                                     // if user click the button call function Onclick
        deleteButton.setOnClickListener(this);
        badge.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {            //set panel background for copying the character
            mPanel.setBackground(getDrawable(R.drawable.pic_0001_copy));
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        final Panel mPanel =findViewById(R.id.panel);
        long newTime = System.currentTimeMillis();                              //set time limit to avoid users hitting the button too many times in a short period
        if(v.getId()==R.id.BadgeButton){
            Intent intent =new Intent(WritingPanel.this, Badge.class);
            startActivity(intent);

        }
        if(newTime-lastTime>1000){
            if (v.getId()==R.id.SaveButton){                                    // distinct which the button hit by users
                if (mPanel.getBackground().getConstantState().equals(getDrawable(R.drawable.pic_0001_copy).getConstantState())&&lastTime!=0){
                    mPanel.resetCanvas();
                    mPanel.setBackground(getDrawable(R.drawable.space));
                    return;
                }
                mPanel.savePicture();
                Toast.makeText(WritingPanel.this,"儲存完畢",Toast.LENGTH_LONG).show();

            }
            else if (v.getId()==R.id.DeleteButton){
                mPanel.resetCanvas();
                mPanel.setBackground(getDrawable(R.drawable.pic_0001_copy));
            }
            lastTime=newTime;
        }
        else{
            Toast.makeText(WritingPanel.this,"停",Toast.LENGTH_LONG).show();
        }


    }


}