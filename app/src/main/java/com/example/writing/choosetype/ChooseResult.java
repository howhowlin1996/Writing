package com.example.writing.choosetype;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.panel.WritingPanel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class ChooseResult extends AppCompatActivity implements View.OnClickListener {
    int split_code,confirmClick_time=0;
    long begin_time;
    Set<String> defaultSet=new TreeSet<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosetype_result);
        getSupportActionBar().hide(); //隱藏標題
        ImageView result=findViewById(R.id.result_choosetyperesult);
        Button confirm=findViewById(R.id.confirm_result);
        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
        split_code=num.getInt("split_code",0);
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        confirm.setOnClickListener(this);


        if (split_code==checkAnswer(key_name)){
            Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.correct);
            result.setImageBitmap(bitmap);
            //Toast.makeText(this,"開始寫字",Toast.LENGTH_SHORT).show();
        }
        else {
            Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.wrong);
            result.setImageBitmap(bitmap);
            //Toast.makeText(this,"再試一次",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        if (split_code==checkAnswer(key_name)){
            int flag=checkPassword();
            if (flag==1){
                writeFile();
                updateRecord();
                Intent intent =new Intent(getBaseContext(), WritingPanel.class);
                intent.putExtra("num",split_code);
                startActivity(intent);


            }


        }
        else {
            Intent intent =new Intent(getBaseContext(), ChooseTypePage.class);
            startActivity(intent);
        }

    }

    public void updateRecord(){
        SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
        pref.edit().putInt("singlesum",0)
                .putInt("leftrightsum",0)
                .putInt("inoutsum",0)
                .putInt("single",0)
                .putInt("updown2",0)
                .putInt("updown3",0)
                .putInt("leftright2",0)
                .putInt("leftright3",0)
                .putInt("rightup",0)
                .putInt("rightdown",0)
                .putInt("rightmiddle",0)
                .putInt("middlemiddle",0)
                .putInt("middledown",0)
                .putInt("leftdown",0)
                .commit();



    }



    public int checkAnswer(String type){

        int chartype=0;
        if(type.equals("Single")){
            chartype=11;
        }
        else if(type.equals("ThreeEle")){
            chartype=41;
        }
        else if (type.equals("UpDown")){
            chartype=21;
        }
        else if (type.equals("UpDown3")){
            chartype=22;
        }
        else if (type.equals("LeftRight2")){
            chartype=31;
        }
        else if (type.equals("LeftRight3")){
            chartype=32;
        }
        else if (type.equals("RightUp")){
            chartype=51;
        }
        else if (type.equals("RightDown")){
            chartype=52;
        }
        else if(type.equals("RightMiddle")){
            chartype=53;
        }
        else if (type.equals("MiddleMiddle")){
            chartype=54;
        }

        else if (type.equals("MiddleDown")){
            chartype=55;
        }
        else if (type=="LeftDown"){
            chartype=56;
        }

        return chartype;

    }

    public void writeFile(){
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String user_name=storeinform.getString("userkey",null);
        String file_name=user_name+"chooseresult"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/chooseresult");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
        String nowtime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String data[]={nowtime,"\n","大類(單個部件): ",Integer.toString(pref.getInt("singlesum",0)),"\n",
                "大類(上下): ",Integer.toString(pref.getInt("leftrightsum",0)),"\n",
                "大類(裡外): ",Integer.toString(pref.getInt("inoutsum",0)),"\n",
                "小類(單個部件): ",Integer.toString(pref.getInt("single",0)),"\n",
                "小類(上下2): ",Integer.toString(pref.getInt("updown2",0)),"\n",
                "小類(上下3): ",Integer.toString(pref.getInt("updown3",0)),"\n",
                "小類(左右2): ",Integer.toString(pref.getInt("leftright2",0)),"\n",
                "小類(左右3): ",Integer.toString(pref.getInt("leftright3",0)),"\n",
                "小類(右上): ",Integer.toString(pref.getInt("rightup",0)),"\n",
                "小類(右下): ",Integer.toString(pref.getInt("rightdown",0)),"\n",
                "小類(右中): ",Integer.toString(pref.getInt("rightmiddle",0)),"\n",
                "小類(中中): ",Integer.toString(pref.getInt("middlemiddle",0)),"\n",
                "小類(中下): ",Integer.toString(pref.getInt("middledown",0)),"\n",
                "小類(左下): ",Integer.toString(pref.getInt("leftdown",0)),"\n"};
                WritingCsvFile here=new WritingCsvFile(data,file_name,appDir.toString());
                here.run();



}


    private int checkPassword(){

        if (confirmClick_time==0){
            begin_time=System.currentTimeMillis();
        }

        if (System.currentTimeMillis()-begin_time>1000){

            if (confirmClick_time==2){
                confirmClick_time=0;
                return 1;
            }
            else{
                confirmClick_time=0;
                Toast.makeText(this,"不要亂按",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            confirmClick_time++;
            Toast.makeText(this,"按了"+confirmClick_time+"次",Toast.LENGTH_SHORT).show();
            return 0;
        }



        return 0;
    }


}
