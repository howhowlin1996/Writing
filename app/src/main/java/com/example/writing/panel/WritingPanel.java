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
import com.example.writing.memo.MemoSelect;
import com.example.writing.puzzle.Puzzle;


public class WritingPanel extends AppCompatActivity implements View.OnClickListener {
    //Panel mPanel;
     long lastTime =0;
    int split_code=0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);                                         //import layout.xml
        final Button saveButton = findViewById(R.id.SaveButton);                 //get two button id in layout.xml saveButton and deleteButton
        final Button deleteButton= findViewById(R.id.DeleteButton);
        final Panel mPanel =findViewById(R.id.panel);                            //get the panel id in layout.xml
        final Button helpButton=findViewById(R.id.help_writing);
        final Button memo=findViewById(R.id.memo_writing);

        saveButton.setOnClickListener(this);                                     // if user click the button call function Onclick
        deleteButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        memo.setOnClickListener(this);
        mPanel.setBackground(getDrawable(R.drawable.space));
        split_code=getIntent().getExtras().getInt("num");


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        final Panel mPanel =findViewById(R.id.panel);
        long newTime = System.currentTimeMillis();                              //set time limit to avoid users hitting the button too many times in a short period
        if(v.getId()==R.id.help_writing){
            Intent intent = new  Intent(this, Puzzle.class);
            intent.putExtra("num",split_code);
            startActivity(intent);
            this.finish();

        }
        if(v.getId()==R.id.memo_writing){
            Intent intent = new  Intent(this, MemoSelect.class);
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
                Intent intent = new  Intent(this, Badge.class);
                startActivity(intent);

            }
            else if (v.getId()==R.id.DeleteButton){
                mPanel.resetCanvas();
                mPanel.setBackground(getDrawable(R.drawable.space));
            }
            lastTime=newTime;
        }
        else{
            Toast.makeText(WritingPanel.this,"停",Toast.LENGTH_LONG).show();
        }


    }


}