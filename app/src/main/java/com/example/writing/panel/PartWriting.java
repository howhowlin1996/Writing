package com.example.writing.panel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PartWriting extends AppCompatActivity implements View.OnClickListener {
    Drawable reset;
    int char_type=0,child_num,time=0,try_time=1;
    Panel child=null;
    ImageView view_child=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partwriting);
        getSupportActionBar().hide(); //隱藏標題

        final Button confirm=findViewById(R.id.ConfirmButton_partwriting);
        final Button delete=findViewById(R.id.DeleteButton_partwriting);
        final  PartWritngGroup group=findViewById(R.id.group_partwriting);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
        }
        else{
            prepareView();
        }
        if (child_num==3){
            ImageView image_child=new ImageView(this,null);
            view_child=image_child;
            group.addView(image_child);
        }


        Panel panel_child=new Panel(this,null);
        child=panel_child;
        group.addView(panel_child);


        group.setCharacter_type(char_type,time);
        setChildBackground();
        confirm.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected  void prepareView(){
        final ImageView charleft=findViewById(R.id.characterQleft_partwriting);
        final ImageView pholeft=findViewById(R.id.phoneticQleft_partwriting);
        final ImageView phoright=findViewById(R.id.phoneticQright_partwriting);
        final ImageView charmiddle=findViewById(R.id.characterQmiddle_partwriting);
        final ImageView phomiddle=findViewById(R.id.phoneticQmiddle_partwriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final  int answer_position=storeinform.getInt("answer_position",0);
        final String rightString=storeinform.getString("right",null);
        final  String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        final PartWritngGroup group=findViewById(R.id.group_partwriting);
        final ImageView mypanel=findViewById(R.id.panel_partwriting);
        Resources here_r=this.getResources();
        setChar_type(rightString.substring(0,2));

        if(middleString.equals("0")){
            setDimension(0,answer_position);
            Log.d("error","heretype"+" "+"hahaha");
            group.invalidate();
            if(answer_position==0){
                reset=getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName()));
                //mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName())));
                pholeft.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoright.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));

            }
            else{
                reset=getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
               // mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            setDimension(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                reset=getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName()));
                //mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+leftString,"drawable",this.getPackageName())));
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
               // mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+middleString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                reset=getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName()));
                charleft.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                pholeft.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charmiddle.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                phomiddle.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                //mypanel.setBackground(getDrawable(here_r.getIdentifier("copy"+rightString,"drawable",this.getPackageName())));
                phoright.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }







        }

    }
    protected  void prepareViewOld(){
        final ImageView charleft=findViewById(R.id.characterQleft_partwriting);
        final ImageView pholeft=findViewById(R.id.phoneticQleft_partwriting);
        final ImageView phoright=findViewById(R.id.phoneticQright_partwriting);
        final ImageView charmiddle=findViewById(R.id.characterQmiddle_partwriting);
        final ImageView phomiddle=findViewById(R.id.phoneticQmiddle_partwriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final  int answer_position=storeinform.getInt("answer_position",0);
        final String rightString=storeinform.getString("right",null);
        final  String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        final PartWritngGroup group=findViewById(R.id.group_partwriting);
        final ImageView mypanel=findViewById(R.id.panel_partwriting);
        Resources here_r = this.getResources();
        setChar_type(rightString.substring(0,2));
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
    protected  void setChar_type(String type){
     int type_here=Integer.valueOf(type);
     char_type=type_here;
        if(type_here/10==1){
            child_num=1;
        }
        else if(type_here/10==2){
            if(type_here%10==1){
              child_num=2;
            }
            else if(type_here%10==2){
                child_num=3;
            }

        }
        else if(type_here/10==3){
            if(type_here%10==1){
                child_num=2;
            }
            else if(type_here%10==2){
                child_num=3;
            }
        }
        else if(type_here/10==5){
            child_num=2;
        }


    }


    private void setDimension(int question_type,int answer_position){                                                   //question_type->0 means 2 words(without middle string),1 means 3 words (contain middle string)
        final  PartWritngGroup group=findViewById(R.id.group_partwriting);
        final SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        group.setType(question_type,answer_position);
        group.setDimension(storeinform.getInt("button",0),storeinform.getInt("writing_panel2",0),storeinform.getInt("writing_panel3",0));

    }


    private void setChildBackground(){
        final ImageView first_view=findViewById(R.id.panel_partwriting);
        final   Panel second_view=child;
        final ImageView third_view=view_child;
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String rightString=storeinform.getString("right",null);
        String partone="part"+rightString.substring(0,rightString.length()-2)+"0"+"0";
        String parttwo="part"+rightString.substring(0,rightString.length()-2)+"1"+"0";
        Resources here_r=this.getResources();

        if (child_num==3){

        }
        else{
            if (time==0){
                first_view.setBackground(getResources().getDrawable(here_r.getIdentifier(partone,"drawable",this.getPackageName())));
                second_view.setBackground(getResources().getDrawable(R.drawable.block));
            }
            else{

                first_view.setBackground(getResources().getDrawable(here_r.getIdentifier(parttwo,"drawable",this.getPackageName())));
                second_view.setBackground(getResources().getDrawable(R.drawable.block));

            }
        }







    }

    private  void resetPart(){
        final PartWritngGroup group=findViewById(R.id.group_partwriting);
        Panel mypanel=child;
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        int writing2_space= storeinform.getInt("writing_panel2",0);
        int writing3_space=storeinform.getInt("writing_panel3",0);
        final String middleString=storeinform.getString("middle",null);
        time++;
        group.setCharacter_type(char_type,time);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            mypanel.resetCanvas();
            mypanel.setBackground(getResources().getDrawable(R.drawable.block));
            View writingView=group.getChildAt(4);
            setChildBackground();
            if(middleString.equals("0")) {
                group.partSet(writingView.getLeft(), writingView.getTop(), writing2_space, writing2_space);
            }
            else {
                group.partSet(writingView.getLeft(), writingView.getTop(), writing3_space, writing3_space);
            }

        }
        else{
            mypanel.resetCanvas();
            mypanel.setBackground(getDrawable(R.drawable.block));
            View writingView=group.getChildAt(4);
            setChildBackground();
            if(middleString.equals("0")) {
                group.partSet(writingView.getLeft(), writingView.getTop(), writing2_space, writing2_space);
            }
            else {
                group.partSet(writingView.getLeft(), writingView.getTop(), writing3_space, writing3_space);
            }

        }

    }



    @Override
    public void onClick(View v) {
        final PartWritngGroup group=findViewById(R.id.group_partwriting);
        Panel mypanel=child;
        if (mypanel.points.size()!=0){
            if(v.getId()==R.id.ConfirmButton_partwriting){
                saveCorrectPicture();
                if (time+1==child_num){
                    Intent intent =new Intent(this,WritingPanel.class);
                    intent.putExtra("num",0);
                    startActivity(intent);
                }
                else{
                    resetPart();
                    try_time=11;
                }

            }
            else if(v.getId()==R.id.DeleteButton_partwriting){
                saveWrongPicture();
                mypanel.resetCanvas();
                mypanel.setBackground(getResources().getDrawable(R.drawable.block));
                try_time++;
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
                    final AlertDialog here =here_builder.create();
                    here.show();
                    final EditText password=here.findViewById(R.id.skip_password);
                    here.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            if (password.getText().toString().equals("0401")){
                                if (time+1==child_num){
                                    Intent intent =new Intent(getBaseContext(),WritingPanel.class);
                                    intent.putExtra("num",0);
                                    startActivity(intent);
                                }
                                else{
                                    resetPart();
                                    here.hide();
                                }
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
    public  void saveCorrectPicture (){
        final Panel mPanel =child;
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="partwriting"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2)+"correct"+try_time+".jpg";
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/partwriting");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, photo_name);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mPanel.vBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void saveWrongPicture (){
        final Panel mPanel =child;
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="partwriting"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2)+"wrong"+try_time+".jpg";
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/partwriting");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, photo_name);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mPanel.vBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
