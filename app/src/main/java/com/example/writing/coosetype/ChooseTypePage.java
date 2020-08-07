package com.example.writing.coosetype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;
import java.util.Set;
import java.util.TreeSet;

import main.FirstScene;


public class ChooseTypePage extends AppCompatActivity implements View.OnClickListener {
    Set<String> defaultSet=new TreeSet<String>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepage);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        final Button singleButton = findViewById(R.id.SingleButton);                 //get character type id from choosepage.xml
        final Button updownButton = findViewById(R.id.UpDownButton);
        final Button inoutButton = findViewById(R.id.InOutButton);
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        int num;
        num=getSharedPreferences("num",0).getInt(key_name,0);
        int position;
        position=getSharedPreferences("num",0).getInt(key_name+new String("_position"),0);

        if(num==0){
            Set<String> setkeyname=new TreeSet<String>();
            setkeyname=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet);
            setkeyname.remove(key_name);
            if (setkeyname.iterator().hasNext()){
                key_name=setkeyname.iterator().next();
                Log.d("number",key_name);
            }

            if (setkeyname.size()==0){
                //Toast.makeText(this,"end",Toast.LENGTH_SHORT).show();
                getSharedPreferences("num",0).edit().putStringSet("chartypenum",defaultSet).clear().commit();
                Intent intent = new  Intent(this, FirstScene.class);
                startActivity(intent);
            }

        }
        else{
            setQuestion(key_name,(position-1)*5+5-num);
        }




        singleButton.setOnClickListener(this);
        updownButton.setOnClickListener(this);
        inoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Integer idnum=v.getId();
        if (idnum==R.id.SingleButton){
            Intent intent = new  Intent(ChooseTypePage.this, SinglePage.class);             //change activity to the activity that every button matches
            startActivity(intent);
        }
        else if (idnum==R.id.UpDownButton){
            Intent intent = new  Intent(ChooseTypePage.this, UpDownPage.class);
            startActivity(intent);
        }
        else  if (idnum==R.id.InOutButton){
            Intent intent = new  Intent(ChooseTypePage.this, InOutPage.class);
            startActivity(intent);
        }
        this.finish();



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setQuestion(String type, int num){
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final ImageView charquestionright=findViewById(R.id.characterQright_choose);
        final ImageView charquestionleft=findViewById(R.id.characterQleft_choose);
        final ImageView charquestionmiddle=findViewById(R.id.characterQmiddle_choose);
        final ImageView phoquestionright=findViewById(R.id.phoneticQright_choose);
        final ImageView phoquestionleft=findViewById(R.id.phoneticQleft_choose);
        final ImageView phoquestionmiddle=findViewById(R.id.phoneticQmiddle_chooose);

        String chartype="00";
        String question_num;
        String qadecision="1";
        //Toast.makeText(this,type,Toast.LENGTH_SHORT).show();
        if(type.equals("Single")){
            chartype="11";
        }
        else if(type.equals("ThreeEle")){
            chartype="41";
        }
        else if (type.equals("UpDown")){
            chartype="21";
        }
        else if (type.equals("UpDown3")){
            chartype="22";
        }
        else if (type.equals("LeftRight2")){
            chartype="31";
        }
        else if (type.equals("LeftRight3")){
            chartype="32";
        }
        else if (type.equals("RightUp")){
            chartype="51";
        }
        else if (type.equals("RightDown")){
            chartype="52";
        }
        else if(type.equals("RightMiddle")){
            chartype="53";
        }
        else if (type.equals("MiddleMiddle")){
            chartype="54";
        }

        else if (type.equals("MiddleDown")){
            chartype="55";
        }
        else if (type=="LeftDown"){
            chartype="56";
        }
        if(num/100!=0){
            question_num=new String("0"+num);
        }
        else if(num/10!=0){
            question_num=new String("00"+num);
        }
        else{
            question_num=new String("000"+num);
        }

        String identift_code=chartype+question_num+qadecision;

        //Toast.makeText(this,"cha" +identift_code+"0",Toast.LENGTH_SHORT).show();







        Resources here_r=this.getResources();
        if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"2"),"drawable",this.getPackageName())!=0){
            int answer_num=1;
            int answer_position=2;
            phoquestionright.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"2"),"drawable",this.getPackageName()));
            charquestionright.setBackground(getDrawable(R.drawable.questionblock));
            storeinform.edit().putString("right",chartype+question_num+qadecision+"2").commit();

            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())!=0){
                phoquestionleft.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName()));
                charquestionleft.setBackground(getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                answer_num++;
                answer_position=20;
            }
            else{
                charquestionleft.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName()));
                phoquestionleft.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName()));
                storeinform.edit().putString("right",chartype+question_num+"0"+"0").commit();


            }
            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                phoquestionmiddle.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName()));
                charquestionmiddle.setBackground(getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("middle",chartype+question_num+qadecision+"1").commit();

                if (answer_num==2){
                    answer_position=210;
                }
                else {
                    answer_position=21;
                }
            }
            else{
                charquestionmiddle.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName()));
                phoquestionmiddle.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName()));
                storeinform.edit().putString("right",chartype+question_num+"0"+"1").commit();

            }


                storeinform.edit().putInt("answer_position",answer_position).commit();



        }
        else if(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())!=0){
            charquestionright.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName()));
            phoquestionright.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName()));
            storeinform.edit().putString("right",chartype+question_num+"0"+"2").commit();
            int answer_num=0;
            int answer_position=-1;

            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())!=0){
                phoquestionleft.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName()));
                charquestionleft.setBackground(getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                answer_num++;
                answer_position=0;
            }
            else{
                charquestionleft.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName()));
                phoquestionleft.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName()));
                storeinform.edit().putString("left",chartype+question_num+"0"+"0").commit();


            }
            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                phoquestionmiddle.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName()));
                charquestionmiddle.setBackground(getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("middle",chartype+question_num+qadecision+"1").commit();
                if (answer_num==1){
                    answer_position=10;
                }
                else {
                    answer_position=1;
                }
            }
            else{
                charquestionmiddle.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName()));
                phoquestionmiddle.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName()));
                storeinform.edit().putString("middle",chartype+question_num+"0"+"1").commit();

            }
            storeinform.edit().putInt("answer_position",answer_position).commit();

        }
        else{
            if(here_r.getIdentifier("cha" +chartype+question_num+qadecision+"0","drawable",this.getPackageName())!=0){
                Log.d("decision","left");
                charquestionmiddle.setVisibility(View.GONE);
                phoquestionmiddle.setVisibility(View.GONE);
                //charquestionleft.setImageResource(R.drawable.white);
                charquestionleft.setBackground(getDrawable(R.drawable.questionblock));
                phoquestionleft.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName()));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                qadecision="0";
                charquestionright.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName()));
                phoquestionright.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName()));
                storeinform.edit().putString("right",chartype+question_num+qadecision+"1").commit();
                storeinform.edit().putString("middle","0").commit();
                storeinform.edit().putInt("answer_position",0).commit();

            }
            else if(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                Log.d("decision","Right");
                charquestionmiddle.setVisibility(View.GONE);
                phoquestionmiddle.setVisibility(View.GONE);
                //charquestionright.setImageResource(R.drawable.white);
                charquestionright.setBackground(getDrawable(R.drawable.questionblock));
                phoquestionright.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName()));
                storeinform.edit().putString("right",chartype+question_num+qadecision+"1").commit();
                qadecision="0";
                charquestionleft.setImageResource(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName()));
                phoquestionleft.setImageResource(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName()));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                storeinform.edit().putString("middle","0").commit();
                storeinform.edit().putInt("answer_position",1).commit();
            }

        }






    }
}
