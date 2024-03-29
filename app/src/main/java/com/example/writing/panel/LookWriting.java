package com.example.writing.panel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.writing.badge.Badge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LookWriting extends AppCompatActivity implements View.OnClickListener {
     int time=1;
     int confirmClick_time;
     long begin_time=0;
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
    public void onClick(View v)  {
        Panel writimg_panel=findViewById(R.id.rightDownA_lookwriting);
        if (writimg_panel.points.size()!=0){
            if(v.getId()==R.id.confirm_lookwriting)
            {   /*final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final Context context=this;
                builder.setMessage("等等其他人喔"
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
                                                                                                       saveCorrectPicture();
                                                                                                       Intent intent =new Intent(getBaseContext(), PartWriting.class);
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
                here.show();*/
             int flag=checkPassword();
             if (flag==1){
                 saveCorrectPicture();
                 Intent intent =new Intent(getBaseContext(), PartWriting.class);
                 startActivity(intent);

             }



            }
            else{
                saveWrongPicture();
                Panel panel=findViewById(R.id.rightDownA_lookwriting);
                panel.resetCanvas();
                time++;

            }
        }
        else{

            /*final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                            if (password.getText().toString().equals("0401")){
                                Intent intent =new Intent(getBaseContext(), PartWriting.class);
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
            here.show();*/
            Intent intent =new Intent(getBaseContext(), PartWriting.class);
            startActivity(intent);
        }



    }

    public  void saveCorrectPicture (){
        final Panel mPanel =findViewById(R.id.rightDownA_lookwriting);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="lookwriting"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2)+"correct"+time+".jpg";
        String user_name=storeinform.getString("userkey",null);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/lookwriting");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/lookwriting"+"/correct");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/lookwriting"+"/correct/"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2));
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
        final Panel mPanel =findViewById(R.id.rightDownA_lookwriting);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="lookwriting"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2)+"wrong"+time+".jpg";
        String user_name=storeinform.getString("userkey",null);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/lookwriting");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/lookwriting"+"/wrong");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/lookwriting"+"/wrong/"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2));
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
