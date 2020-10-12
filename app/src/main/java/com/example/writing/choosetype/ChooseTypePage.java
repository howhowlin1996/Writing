package com.example.writing.choosetype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;
import java.util.Set;
import java.util.TreeSet;

import main.FirstScene;


public class ChooseTypePage extends AppCompatActivity implements View.OnClickListener {
    Set<String> defaultSet=new TreeSet<String>();
    Context context;
    int width_here,height_here;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosetype);
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_choosetype);
        getSupportActionBar().hide(); //隱藏標題
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        width_here=dm.widthPixels;
        height_here=dm.heightPixels;
        context=this;
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        int num;
        num=getSharedPreferences("num",0).getInt(key_name,0);
        int position;
        position=getSharedPreferences("num",0).getInt(key_name+new String("_position"),0);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
        }
        else{
            prepareView();
        }

        if(num==0){
            Set<String> setkeyname=new TreeSet<String>();
            setkeyname=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet);
            setkeyname.remove(key_name);
            if (setkeyname.iterator().hasNext()){
                key_name=setkeyname.iterator().next();
                num=getSharedPreferences("num",0).getInt(key_name,0);
                position=getSharedPreferences("num",0).getInt(key_name+new String("_position"),0);

                if (sdk< Build.VERSION_CODES.LOLLIPOP){
                    setQuestionOld(key_name,(position-1)*4+4-num);
                }
                else{
                    setQuestion(key_name,(position-1)*4+4-num);
                }

            }

            if (setkeyname.size()==0){
                getSharedPreferences("num",0).edit().remove("chartypenum").commit();
                Intent intent = new  Intent(this, FirstScene.class);
                startActivity(intent);
            }

        }
        else{
            if (sdk< Build.VERSION_CODES.LOLLIPOP){
                setQuestionOld(key_name,(position-1)*4+4-num);
            }
            else{
                setQuestion(key_name,(position-1)*4+4-num);
            }

        }


        for (int i=0;i<3;i++){
            chooseTypeGroup.getChildAt(i).setOnClickListener(this);
        }


    }

    protected  void prepareViewOld(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_choosetype);
        chooseTypeGroup.setBackground(getResources().getDrawable(R.drawable.bg));
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<3;i++){
                    Button button_here=new Button(context);
                    chooseTypeGroup.addView(button_here);

                }
                for (int i=0;i<3;i++){
                    View button_here=chooseTypeGroup.getChildAt(i);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(button_here.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/3;
                    String name="type"+new String(""+i);
                    button_here.setTag(name);
                    button_here.setId(i);
                    button_here.setLayoutParams(params);


                    if (i==0){
                        button_here.setBackground(getResources().getDrawable(R.drawable.singlesum));
                    }
                    else if (i==1){
                        button_here.setBackground(getResources().getDrawable(R.drawable.leftrightsum));
                    }
                    else{
                        button_here.setBackground(getResources().getDrawable(R.drawable.rightupsum));
                    }


                }
                for (int i=0;i<3;i++){
                    ImageView imageView=new ImageView(context);
                    chooseTypeGroup.addView(imageView);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(imageView.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/3;
                    String name="question"+new String(""+i);;
                    imageView.setTag(name);
                    imageView.setId(3+i);
                    imageView.setLayoutParams(params);


                }

                for (int i=0;i<3;i++){
                    ImageView imageView=new ImageView(context);
                    chooseTypeGroup.addView(imageView);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(imageView.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/12;
                    String name="phonetic"+new String(""+i);;
                    imageView.setTag(name);
                    imageView.setId(6+i);
                    imageView.setLayoutParams(params);




                }


            }
        };
        this.runOnUiThread(runnable);










    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void prepareView(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_choosetype);
        chooseTypeGroup.setBackground(getDrawable(R.drawable.bg));
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<3;i++){
                    Button button_here=new Button(context);
                    chooseTypeGroup.addView(button_here);

                }
                for (int i=0;i<3;i++){
                    View button_here=chooseTypeGroup.getChildAt(i);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(button_here.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/3;
                    String name="type"+new String(""+i);
                    button_here.setTag(name);
                    button_here.setId(i);
                    button_here.setLayoutParams(params);


                    if (i==0){
                        button_here.setBackground(getDrawable(R.drawable.singlesum));
                    }
                    else if (i==1){
                        button_here.setBackground(getDrawable(R.drawable.leftrightsum));
                    }
                    else{
                        button_here.setBackground(getDrawable(R.drawable.rightupsum));
                    }


                }
                for (int i=0;i<3;i++){
                    ImageView imageView=new ImageView(context);
                    chooseTypeGroup.addView(imageView);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(imageView.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/3;
                    String name="question"+new String(""+i);;
                    imageView.setTag(name);
                    imageView.setId(3+i);
                    imageView.setLayoutParams(params);


                }

                for (int i=0;i<3;i++){
                    ImageView imageView=new ImageView(context);
                    chooseTypeGroup.addView(imageView);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(imageView.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/12;
                    String name="phonetic"+new String(""+i);;
                    imageView.setTag(name);
                    imageView.setId(6+i);
                    imageView.setLayoutParams(params);




                }


            }
        };
        this.runOnUiThread(runnable);





    }


    @Override
    public void onClick(View v) {

        int idnum=v.getId();

        if (idnum==0){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("singlesum",0);
            time++;
            pref.edit().putInt("singlesum",time).commit();
            Intent intent = new  Intent(ChooseTypePage.this, SinglePage.class);             //change activity to the activity that every button matches
            startActivity(intent);
        }
        else if (idnum==1){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("leftrightsum",0);
            time++;
            pref.edit().putInt("leftrightsum",time).commit();
            Intent intent = new  Intent(ChooseTypePage.this, UpDownPage.class);
            startActivity(intent);
        }
        else  if (idnum==2){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("inoutsum",0);
            time++;
            pref.edit().putInt("inoutsum",time).commit();
            Intent intent = new  Intent(ChooseTypePage.this, InOutPage.class);
            startActivity(intent);
        }
        this.finish();



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setQuestion(String type, int num){
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_choosetype);
        final View charquestionleft=chooseTypeGroup.getChildAt(3);
        final View charquestionmiddle=chooseTypeGroup.getChildAt(4);
        final View charquestionright=chooseTypeGroup.getChildAt(5);
        final View phoquestionleft=chooseTypeGroup.getChildAt(6);
        final View phoquestionmiddle=chooseTypeGroup.getChildAt(7);
        final View phoquestionright=chooseTypeGroup.getChildAt(8);

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
        Log.d("ERROR_HERE",question_num);

        String identift_code=chartype+question_num+qadecision;

        Resources here_r=this.getResources();
        if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"2"),"drawable",this.getPackageName())!=0){
            chooseTypeGroup.getNum(3,3,3);
            chooseTypeGroup.invalidate();
            int answer_num=1;
            int answer_position=2;
            phoquestionright.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"2"),"drawable",this.getPackageName())));
            charquestionright.setBackground(getDrawable(R.drawable.questionblock));
            storeinform.edit().putString("right",chartype+question_num+qadecision+"2").commit();

            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())!=0){
                phoquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                charquestionleft.setBackground(getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                answer_num++;
                answer_position=20;
            }
            else{
                charquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                phoquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+"0"+"0").commit();


            }
            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                phoquestionmiddle.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
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
                charquestionmiddle.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                phoquestionmiddle.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+"0"+"1").commit();

            }


            storeinform.edit().putInt("answer_position",answer_position).commit();



        }
        else if(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())!=0){

            chooseTypeGroup.getNum(3,3,3);
            chooseTypeGroup.invalidate();

            charquestionright.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())));
            phoquestionright.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())));
            storeinform.edit().putString("right",chartype+question_num+"0"+"2").commit();
            int answer_num=0;
            int answer_position=-1;

            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())!=0){
                phoquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                charquestionleft.setBackground(getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                answer_num++;
                answer_position=0;
            }
            else{
                charquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                phoquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("left",chartype+question_num+"0"+"0").commit();


            }
            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                phoquestionmiddle.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
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
                charquestionmiddle.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                phoquestionmiddle.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("middle",chartype+question_num+"0"+"1").commit();

            }
            storeinform.edit().putInt("answer_position",answer_position).commit();

        }
        else{
            Log.d("ERROR_HERE","cha" +chartype+question_num+qadecision+"0");
            chooseTypeGroup.getNum(3,2,2);
            chooseTypeGroup.invalidate();
            if(here_r.getIdentifier("cha" +chartype+question_num+qadecision+"0","drawable",this.getPackageName())!=0){
                charquestionmiddle.setVisibility(View.GONE);
                phoquestionmiddle.setVisibility(View.GONE);
                //charquestionleft.setBackground(R.drawable.white);
                charquestionleft.setBackground(getDrawable(R.drawable.questionblock));
                phoquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                qadecision="0";
                charquestionright.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                phoquestionright.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+qadecision+"1").commit();
                storeinform.edit().putString("middle","0").commit();
                storeinform.edit().putInt("answer_position",0).commit();

            }
            else if(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                Log.d("ERROR_HERE","cha" +chartype+question_num+qadecision+"1");
                charquestionmiddle.setVisibility(View.GONE);
                phoquestionmiddle.setVisibility(View.GONE);
                //charquestionright.setBackground(R.drawable.white);
                charquestionright.setBackground(getDrawable(R.drawable.questionblock));
                phoquestionright.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+qadecision+"1").commit();
                qadecision="0";
                charquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                phoquestionleft.setBackground(getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                storeinform.edit().putString("middle","0").commit();
                storeinform.edit().putInt("answer_position",1).commit();
            }

        }






    }



    void setQuestionOld(String type, int num){
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_choosetype);
        final View charquestionleft=chooseTypeGroup.getChildAt(3);
        final View charquestionmiddle=chooseTypeGroup.getChildAt(4);
        final View charquestionright=chooseTypeGroup.getChildAt(5);
        final View phoquestionleft=chooseTypeGroup.getChildAt(6);
        final View phoquestionmiddle=chooseTypeGroup.getChildAt(7);
        final View phoquestionright=chooseTypeGroup.getChildAt(8);

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

        Resources here_r=this.getResources();
        if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"2"),"drawable",this.getPackageName())!=0){
            chooseTypeGroup.getNum(3,3,3);
            chooseTypeGroup.invalidate();
            int answer_num=1;
            int answer_position=2;
            phoquestionright.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"2"),"drawable",this.getPackageName())));
            charquestionright.setBackground(getResources().getDrawable(R.drawable.questionblock));
            storeinform.edit().putString("right",chartype+question_num+qadecision+"2").commit();

            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())!=0){
                phoquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                charquestionleft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                answer_num++;
                answer_position=20;
            }
            else{
                charquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                phoquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+"0"+"0").commit();


            }
            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                phoquestionmiddle.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                charquestionmiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("middle",chartype+question_num+qadecision+"1").commit();

                if (answer_num==2){
                    answer_position=210;
                }
                else {
                    answer_position=21;
                }
            }
            else{
                charquestionmiddle.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                phoquestionmiddle.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+"0"+"1").commit();

            }


            storeinform.edit().putInt("answer_position",answer_position).commit();



        }
        else if(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())!=0){

            chooseTypeGroup.getNum(3,3,3);
            chooseTypeGroup.invalidate();

            charquestionright.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())));
            phoquestionright.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"2"),"drawable",this.getPackageName())));
            storeinform.edit().putString("right",chartype+question_num+"0"+"2").commit();
            int answer_num=0;
            int answer_position=-1;

            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())!=0){
                phoquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                charquestionleft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                answer_num++;
                answer_position=0;
            }
            else{
                charquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                phoquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("left",chartype+question_num+"0"+"0").commit();


            }
            if (here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){
                phoquestionmiddle.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                charquestionmiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                storeinform.edit().putString("middle",chartype+question_num+qadecision+"1").commit();
                if (answer_num==1){
                    answer_position=10;
                }
                else {
                    answer_position=1;
                }
            }
            else{
                charquestionmiddle.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                phoquestionmiddle.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+"0"+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("middle",chartype+question_num+"0"+"1").commit();

            }
            storeinform.edit().putInt("answer_position",answer_position).commit();

        }
        else{
            chooseTypeGroup.getNum(3,2,2);
            chooseTypeGroup.invalidate();
            if(here_r.getIdentifier("cha" +chartype+question_num+qadecision+"0","drawable",this.getPackageName())!=0){
                charquestionmiddle.setVisibility(View.GONE);
                phoquestionmiddle.setVisibility(View.GONE);
                //charquestionleft.setBackground(R.drawable.white);
                charquestionleft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                phoquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                qadecision="0";
                charquestionright.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                phoquestionright.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+qadecision+"1").commit();
                storeinform.edit().putString("middle","0").commit();
                storeinform.edit().putInt("answer_position",0).commit();

            }
            else if(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())!=0){

                charquestionmiddle.setVisibility(View.GONE);
                phoquestionmiddle.setVisibility(View.GONE);
                //charquestionright.setBackground(R.drawable.white);
                charquestionright.setBackground(getResources().getDrawable(R.drawable.questionblock));
                phoquestionright.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName())));
                storeinform.edit().putString("right",chartype+question_num+qadecision+"1").commit();
                qadecision="0";
                charquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("cha" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                phoquestionleft.setBackground(getResources().getDrawable(here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName())));
                storeinform.edit().putString("left",chartype+question_num+qadecision+"0").commit();
                storeinform.edit().putString("middle","0").commit();
                storeinform.edit().putInt("answer_position",1).commit();
            }

        }






    }
}
