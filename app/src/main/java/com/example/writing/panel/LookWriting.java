package com.example.writing.panel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class LookWriting extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookwriting);                                         //import layout.xml
        getSupportActionBar().hide(); //隱藏標題
        Button confirm=findViewById(R.id.confirm_lookwriting);
        Button delete=findViewById(R.id.delete_lookwriting);
        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
        }
        else{
            prepareView();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected  void prepareView(){
        final LookWritingGroup group=findViewById(R.id.group_lookwriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int answer_position=storeinform.getInt("answer_position",0);
        final Panel panel=findViewById(R.id.rightDownA_lookwriting);
        panel.setBackground(getDrawable(R.drawable.white));

        final ImageView leftup=findViewById(R.id.leftUpQ_lookwriting);
        final ImageView leftdown=findViewById(R.id.leftDownQ_lookwriting);
        final ImageView charimage=findViewById(R.id.rightUpA_lookwriting);
        final ImageView lastleft=findViewById(R.id.leftLast_lookwriting);
        final ImageView lastright =findViewById(R.id.rightLast_lookwriting);
        final String rightString=storeinform.getString("right",null);
        final String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            setDimension(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));

            }
            else{
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
            }

        }
        else {
            setDimension(1,answer_position);
            if (answer_position == 0 || answer_position == 10 || answer_position == 20 || answer_position == 210) {
                leftup.setImageResource(here_r.getIdentifier("cha" + leftString, "drawable", this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                //charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                if (answer_position == 10) {
                    charimage.setBackground(getDrawable(R.drawable.white));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                } else if (answer_position == 20) {
                    charimage.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setBackground(getDrawable(R.drawable.white));
                } else if (answer_position == 210) {
                    charimage.setBackground(getDrawable(R.drawable.white));
                    ;
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setBackground(getDrawable(R.drawable.white));

                } else {
                    charimage.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));

                }


            }
            else if (answer_position == 1 || answer_position == 21) {
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                if (answer_position == 1) {
                    lastleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    lastleft.setBackground(getDrawable(R.drawable.white));
                    lastright.setBackground(getDrawable(R.drawable.white));


                }


            }
            else if(answer_position==2){
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                lastleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                lastright.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));


            }
        }


    }

    protected  void prepareViewOld(){
        final LookWritingGroup group=findViewById(R.id.group_lookwriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int answer_position=storeinform.getInt("answer_position",0);
        final Panel panel=findViewById(R.id.rightDownA_lookwriting);
        panel.setBackground(getResources().getDrawable(R.drawable.white));

        final ImageView leftup=findViewById(R.id.leftUpQ_lookwriting);
        final ImageView leftdown=findViewById(R.id.leftDownQ_lookwriting);
        final ImageView charimage=findViewById(R.id.rightUpA_lookwriting);
        final ImageView lastleft=findViewById(R.id.leftLast_lookwriting);
        final ImageView lastright =findViewById(R.id.rightLast_lookwriting);
        final String rightString=storeinform.getString("right",null);
        final String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        final Resources here_r=this.getResources();
        if(middleString.equals("0")){
            setDimension(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));

            }
            else{
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
            }

        }
        else {
            setDimension(1,answer_position);
            if (answer_position == 0 || answer_position == 10 || answer_position == 20 || answer_position == 210) {
                leftup.setImageResource(here_r.getIdentifier("cha" + leftString, "drawable", this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                //charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                if (answer_position == 10) {
                    charimage.setBackground(getResources().getDrawable(R.drawable.white));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                } else if (answer_position == 20) {
                    charimage.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setBackground(getResources().getDrawable(R.drawable.white));
                } else if (answer_position == 210) {
                    charimage.setBackground(getResources().getDrawable(R.drawable.white));
                    ;
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setBackground(getResources().getDrawable(R.drawable.white));

                } else {
                    charimage.setImageResource(here_r.getIdentifier("cha" + middleString, "drawable", this.getPackageName()));
                    lastleft.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha" + rightString, "drawable", this.getPackageName()));

                }


            }
            else if (answer_position == 1 || answer_position == 21) {
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                if (answer_position == 1) {
                    lastleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    lastright.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    lastleft.setBackground(getResources().getDrawable(R.drawable.white));
                    lastright.setBackground(getResources().getDrawable(R.drawable.white));


                }


            }
            else if(answer_position==2){
                leftup.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                leftdown.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                lastleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                lastright.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));


            }
        }


    }



    private void setDimension(int question_type,int answer_position){                                                   //question_type->0 means 2 words(without middle string),1 means 3 words (contain middle string)
        final LookWritingGroup group=findViewById(R.id.group_lookwriting);
        final  SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        group.setType(question_type,answer_position);
        group.setDimension(storeinform.getInt("button",0),storeinform.getInt("writing_panel2",0),storeinform.getInt("writing_panel3",0));

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.confirm_lookwriting)
        {
            Intent intent =new Intent(getBaseContext(), CopyWriting.class);
            startActivity(intent);
        }
        else{
            Panel panel=findViewById(R.id.rightDownA_lookwriting);
            panel.resetCanvas();

        }


    }
}
