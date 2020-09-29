package com.example.writing.choosetype;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import java.util.Set;
import java.util.TreeSet;

public class ChooseResult extends AppCompatActivity implements View.OnClickListener {
    int split_code;
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
            Toast.makeText(this,"開始寫字",Toast.LENGTH_SHORT).show();
        }
        else {
            Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.wrong);
            result.setImageBitmap(bitmap);
            Toast.makeText(this,"再試一次",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        if (split_code==checkAnswer(key_name)){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Context context=this;
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            builder.setMessage("大類(單個部件): "+pref.getInt("singlesum",0)+"\n"+
                            "大類(上下): "+pref.getInt("leftrightsum",0)+"\n"+
                            "大類(裡外): "+pref.getInt("inoutsum",0)+"\n"+
                            "小類(單個部件): "+pref.getInt("single",0)+"\n"+
                            "小類(上下2): "+pref.getInt("updown2",0)+"\n"+
                            "小類(上下3): "+pref.getInt("updown3",0)+"\n"+
                            "小類(左右2): "+pref.getInt("leftright2",0)+"\n"+
                            "小類(左右3): "+pref.getInt("leftright3",0)+"\n"+
                            "小類(右上): "+pref.getInt("rightup",0)+"\n"+
                            "小類(右下): "+pref.getInt("rightdown",0)+"\n"+
                            "小類(右中): "+pref.getInt("rightmiddle",0)+"\n"+
                            "小類(中中): "+pref.getInt("middlemiddle",0)+"\n"+
                            "小類(中下): "+pref.getInt("middledown",0)+"\n"+
                            "小類(左下): "+pref.getInt("leftdown",0)+"\n"
                    );
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    final AlertDialog.Builder here_builder = new AlertDialog.Builder(context) ;
                    here_builder.setView(R.layout.alert_skip_password);
                    here_builder.setPositiveButton("確定",null );
                    AlertDialog here =here_builder.create();
                    here.show();
                    final EditText password=here.findViewById(R.id.skip_password);
                    here.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            if (password.getText().toString().equals("0401")){
                                updateRecord();
                                Intent intent =new Intent(getBaseContext(), WritingPanel.class);
                                intent.putExtra("num",split_code);
                                startActivity(intent);
                            }
                            else {
                                password.getText().clear();
                                Toast.makeText(context,"密碼不對喔",Toast.LENGTH_SHORT).show();
                            } }

                    }
                    );
                }
            });
            AlertDialog here =builder.create();
            here.show();

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
}
