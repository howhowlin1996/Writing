package com.example.writing.panel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class CopyWriting extends AppCompatActivity implements View.OnClickListener {
    Drawable reset;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copywriting);
        getSupportActionBar().hide(); //隱藏標題

        final Button confirm=findViewById(R.id.SaveButton_copy);
        final Button delete=findViewById(R.id.DeleteButton_copy);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
        }
        else{
            prepareView();
        }


        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected  void prepareView(){
        final ImageView charleft=findViewById(R.id.characterQleft_copywriting);
        final ImageView pholeft=findViewById(R.id.phoneticQleft_copywriting);
        final ImageView phoright=findViewById(R.id.phoneticQright_copywriting);
        final ImageView charmiddle=findViewById(R.id.characterQmiddle_copywriting);
        final ImageView phomiddle=findViewById(R.id.phoneticQmiddle_copywriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final  int answer_position=storeinform.getInt("answer_position",0);
        final String rightString=storeinform.getString("right",null);
        final  String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        final CopyWritingGroup group=findViewById(R.id.group_copywriting);
        final Panel mypanel=findViewById(R.id.panel_copy);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            setDimension(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                reset=getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName())));
                pholeft.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));

            }
            else{
                reset=getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            setDimension(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                reset=getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                if (answer_position==10){
                    charleft.setBackground(getDrawable(R.drawable.white));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==20){
                    charleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==210){
                    charleft.setBackground(getDrawable(R.drawable.white));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                }

            }
            else if(answer_position==1||answer_position==21){
                if (answer_position==1){
                    charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                reset=getDrawable(here_r.getIdentifier("copy"+middleString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+middleString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                reset=getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charmiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                phomiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }







        }

    }
    protected  void prepareViewOld(){
        final ImageView charleft=findViewById(R.id.characterQleft_copywriting);
        final ImageView pholeft=findViewById(R.id.phoneticQleft_copywriting);
        final ImageView phoright=findViewById(R.id.phoneticQright_copywriting);
        final ImageView charmiddle=findViewById(R.id.characterQmiddle_copywriting);
        final ImageView phomiddle=findViewById(R.id.phoneticQmiddle_copywriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final  int answer_position=storeinform.getInt("answer_position",0);
        final String rightString=storeinform.getString("right",null);
        final  String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        final CopyWritingGroup group=findViewById(R.id.group_copywriting);
        final Panel mypanel=findViewById(R.id.panel_copy);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            setDimension(0,answer_position);
            group.invalidate();
            if(answer_position==0){
                reset=getResources().getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getResources().getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName())));
                pholeft.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));

            }
            else{
                reset=getResources().getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getResources().getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            setDimension(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                reset=getResources().getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName()));
                mypanel.setBackground(getResources().getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                if (answer_position==10){
                    charleft.setBackground(getResources().getDrawable(R.drawable.white));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==20){
                    charleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getResources().getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==210){
                    charleft.setBackground(getResources().getDrawable(R.drawable.white));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getResources().getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charleft.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                }

            }
            else if(answer_position==1||answer_position==21){
                if (answer_position==1){
                    charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charmiddle.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charmiddle.setBackground(getResources().getDrawable(R.drawable.white));
                    phomiddle.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                reset=getResources().getDrawable(here_r.getIdentifier("copy"+middleString,"drawable",this.getPackageName()));
                mypanel.setBackground(getResources().getDrawable(here_r.getIdentifier("copy"+middleString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                reset=getResources().getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charmiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                phomiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                mypanel.setBackground(getResources().getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }







        }

    }


    private void setDimension(int question_type,int answer_position){                                                   //question_type->0 means 2 words(without middle string),1 means 3 words (contain middle string)
        final  CopyWritingGroup group=findViewById(R.id.group_copywriting);
        final  SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        group.setType(question_type,answer_position);
        group.setDimension(storeinform.getInt("button",0),storeinform.getInt("writing_panel2",0),storeinform.getInt("writing_panel3",0));

    }



    @Override
    public void onClick(View v) {
        Panel mypanel=findViewById(R.id.panel_copy);
        if (mypanel.points.size()!=0){
            if(v.getId()==R.id.SaveButton_copy){
                Intent intent =new Intent(this,WritingPanel.class);
                intent.putExtra("num",0);
                startActivity(intent);

            }
            else if(v.getId()==R.id.DeleteButton_copy){
                mypanel.resetCanvas();
                mypanel.setBackground(reset);
            }
        }
        else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Context context=this;
            builder.setMessage("不要偷懶喔!!老師背後有長眼睛!!!");
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setNegativeButton("我是治療師", new DialogInterface.OnClickListener() {
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
                            if (password.getText().toString().equals("850401")){
                                Intent intent =new Intent(getBaseContext(), WritingPanel.class);
                                startActivity(intent);
                            }
                            else {
                                password.getText().clear();
                                Toast.makeText(context,"密碼不對喔",Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                    );






                }
            });

            AlertDialog here =builder.create();
            here.show();
        }


    }
}
