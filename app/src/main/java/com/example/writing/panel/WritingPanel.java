package com.example.writing.panel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.badge.Badge;
import com.example.writing.choosetype.ChooseTypePage;
import com.example.writing.memo.MemoEditPic;
import com.example.writing.puzzle.Puzzle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;


public class WritingPanel extends AppCompatActivity implements View.OnClickListener {
    int confirmClick_time=0;
    long begin_time;
    Set<String> defaultSet=new TreeSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);                                         //import layout.xml
        getSupportActionBar().hide(); //隱藏標題
        final WritingPanelGroup group=findViewById(R.id.group_writing);
        final Button saveButton = findViewById(R.id.SaveButton);                 //get two button id in layout.xml saveButton and deleteButton
        final Button deleteButton= findViewById(R.id.DeleteButton);
        final Panel mPanel =findViewById(R.id.panel);                            //get the panel id in layout.xml
        final Button helpButton=findViewById(R.id.help_writing);
        final Button memo=findViewById(R.id.memo_writing);


        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
        }
        else{
            prepareView();
        }

        saveButton.setOnClickListener(this);                                     // if user click the button call function Onclick
        deleteButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        memo.setOnClickListener(this);


        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            mPanel.setBackground(getResources().getDrawable(R.drawable.space));
        }
        else{
            mPanel.setBackground(getDrawable(R.drawable.space));
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected  void prepareView(){
        final WritingPanelGroup group=findViewById(R.id.group_writing);
        final ImageView charimage=findViewById(R.id.characterQleft_writing);
        final Panel charpanel=findViewById(R.id.panel);
        final ImageView phoimage=findViewById(R.id.phoneticleftQ_writing);
        final ImageView phopanel=findViewById(R.id.phoneticQright_writing);
        final ImageView charlast=findViewById(R.id.characterQlast_writing);
        final ImageView pholast=findViewById(R.id.phoneticQlast_writing);
        final  SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final int answer_position=storeinform.getInt("answer_position",0);
        final String rightString=storeinform.getString("right",null);
        final String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            setDimension(0,answer_position);
            group.invalidate();
            charlast.setVisibility(View.GONE);
            pholast.setVisibility(View.GONE);
            if(answer_position==0){
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
            }
            else{

                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            setDimension(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                if (answer_position==10){
                    charimage.setBackground(getDrawable(R.drawable.white));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==20){
                    charimage.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setBackground(getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==210){
                    charimage.setBackground(getDrawable(R.drawable.white));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setBackground(getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charimage.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                }

            }
            else if(answer_position==1||answer_position==21){
                if (answer_position==1){
                    charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charlast.setBackground(getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charlast.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                pholast.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                charpanel.setBackground(getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }






    }
    protected  void prepareViewOld(){
        final WritingPanelGroup group=findViewById(R.id.group_writing);
        final ImageView charimage=findViewById(R.id.characterQleft_writing);
        final Panel charpanel=findViewById(R.id.panel);
        final ImageView phoimage=findViewById(R.id.phoneticleftQ_writing);
        final ImageView phopanel=findViewById(R.id.phoneticQright_writing);
        final ImageView charlast=findViewById(R.id.characterQlast_writing);
        final ImageView pholast=findViewById(R.id.phoneticQlast_writing);
        final  SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final int answer_position=storeinform.getInt("answer_position",0);
        final String rightString=storeinform.getString("right",null);
        final String leftString =storeinform.getString("left",null);
        final String middleString=storeinform.getString("middle",null);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            setDimension(0,answer_position);
            group.invalidate();
            charlast.setVisibility(View.GONE);
            pholast.setVisibility(View.GONE);
            if(answer_position==0){
                charpanel.setBackground(getResources().getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charimage.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
            }
            else{

                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charpanel.setBackground(getResources().getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }
        else {
            setDimension(1,answer_position);
            group.invalidate();
            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){
                charpanel.setBackground(getResources().getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                if (answer_position==10){
                    charimage.setBackground(getResources().getDrawable(R.drawable.white));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==20){
                    charimage.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setBackground(getResources().getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else if(answer_position==210){
                    charimage.setBackground(getResources().getDrawable(R.drawable.white));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setBackground(getResources().getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charimage.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));
                }

            }
            else if(answer_position==1||answer_position==21){
                if (answer_position==1){
                    charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charlast.setImageResource(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                else {
                    charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                    phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                    charlast.setBackground(getResources().getDrawable(R.drawable.white));
                    pholast.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

                }
                charpanel.setBackground(getResources().getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));

            }
            else if(answer_position==2){
                charimage.setImageResource(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()));
                phoimage.setImageResource(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()));
                charlast.setImageResource(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()));
                pholast.setImageResource(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()));
                charpanel.setBackground(getResources().getDrawable(R.drawable.block));
                phopanel.setImageResource(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()));

            }

        }






    }

    private void setDimension(int question_type,int answer_position){                                                   //question_type->0 means 2 words(without middle string),1 means 3 words (contain middle string)
        final WritingPanelGroup group=findViewById(R.id.group_writing);
        final  SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        group.setType(question_type,answer_position);
        group.setDimension(storeinform.getInt("button",0),storeinform.getInt("writing_panel2",0),storeinform.getInt("writing_panel3",0));

    }




    @Override
    public void onClick(View v) {
        final Panel mPanel =findViewById(R.id.panel);
        long newTime = System.currentTimeMillis();                              //set time limit to avoid users hitting the button too many times in a short period
        if(v.getId()==R.id.help_writing){
            /*final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                                                                                                   Intent intent = new  Intent(getBaseContext(), Puzzle.class);
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

                Intent intent = new  Intent(getBaseContext(), Puzzle.class);
                startActivity(intent);



        }
        if(v.getId()==R.id.memo_writing){
            Intent intent = new  Intent(this, MemoEditPic.class);
            startActivity(intent);

        }



            if (v.getId()==R.id.SaveButton){                                    // distinct which the button hit by users
                if (mPanel.points.size()!=0){
                    /*final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                                                                                                           savePicture();
                                                                                                           try {
                                                                                                               uploadFile();
                                                                                                           } catch (FileNotFoundException e) {
                                                                                                               e.printStackTrace();
                                                                                                           }
                                                                                                           //Toast.makeText(WritingPanel.this,"儲存完畢",Toast.LENGTH_LONG).show();
                                                                                                           Intent intent = new  Intent(getBaseContext(), Badge.class);
                                                                                                           String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
                                                                                                           int num;
                                                                                                           num=getSharedPreferences("num",0).getInt(key_name,0);
                                                                                                           getSharedPreferences("num",0).edit().putInt(key_name,num-1).commit();
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
                        savePicture();
                        try {
                            uploadFile();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new  Intent(getBaseContext(), Badge.class);
                        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
                        int num;
                        num=getSharedPreferences("num",0).getInt(key_name,0);
                        getSharedPreferences("num",0).edit().putInt(key_name,num-1).commit();
                        startActivity(intent);



                    }


                }
                else{
                    //Toast.makeText(WritingPanel.this,"停",Toast.LENGTH_LONG).show();
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
                                    if (password.getText().toString().equals("0401")){

                                        //Toast.makeText(WritingPanel.this,"儲存完畢",Toast.LENGTH_LONG).show();
                                        Intent intent = new  Intent(getBaseContext(), ChooseTypePage.class);
                                        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
                                        int num;
                                        num=getSharedPreferences("num",0).getInt(key_name,0);
                                        getSharedPreferences("num",0).edit().putInt(key_name,num-1).commit();
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
            else if (v.getId()==R.id.DeleteButton){
                saveWrongPicture();
                mPanel.resetCanvas();
                int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk< Build.VERSION_CODES.LOLLIPOP){
                    mPanel.setBackground(getResources().getDrawable(R.drawable.space));
                }
                else{
                    mPanel.setBackground(getDrawable(R.drawable.space));;
                }

            }




    }


    public  void savePicture (){
        final Panel mPanel =findViewById(R.id.panel);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="pic"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);
        String user_name=storeinform.getString("userkey",null);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        try {
            FileOutputStream fos = openFileOutput( nowDate+user_name+photo_name+".jpg",Context.MODE_PRIVATE);
            mPanel.vBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void  saveCorrectPicture(){
        final Panel mPanel =findViewById(R.id.panel);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="writing"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2)+"correct"+".jpg";
        String user_name=storeinform.getString("userkey",null);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/writing");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/writing"+"/correct");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/writing"+"/correct/"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2));
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

    public void  saveWrongPicture(){
        final Panel mPanel =findViewById(R.id.panel);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String photo_name="writing"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2)+"correct"+".jpg";
        String user_name=storeinform.getString("userkey",null);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/writing");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/writing"+"/wrong");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        appDir = new File(Environment.getExternalStorageDirectory(), "Writing/"+user_name+"/"+nowDate+"/writing"+"/wrong/"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2));
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

    public void uploadFile() throws FileNotFoundException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String user_name=storeinform.getString("userkey",null);
        String photo_name="pic"+storeinform.getString("right",null).substring(0,storeinform.getString("right",null).length()-2);
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        FileInputStream stream=openFileInput(nowDate+user_name+photo_name+".jpg");
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child(nowDate+user_name+photo_name+".jpg");
        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
        // While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
        UploadTask uploadTask = mountainsRef.putStream(stream);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });

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