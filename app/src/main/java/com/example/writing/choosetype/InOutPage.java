package com.example.writing.choosetype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.util.ArrayList;
import java.util.Random;

public class InOutPage extends AppCompatActivity implements View.OnClickListener {
    Context context;
    int width_here,height_here;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inoutchoose);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_inoutchoose);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        context=this;
        width_here=dm.widthPixels;
        height_here=dm.heightPixels;
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
            setQuestionOld();
        }
        else{
            prepareView();
            setQuestion();
        }

        for (int i=0;i<6;i++){
            chooseTypeGroup.getChildAt(i).setOnClickListener(this);
        }





    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void prepareView(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_inoutchoose);
        chooseTypeGroup.setBackground(getDrawable(R.drawable.bg));
        Runnable runnable=new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                for (int i=0;i<6;i++){
                    Button button_here=new Button(context);
                    chooseTypeGroup.addView(button_here);

                }
                ArrayList<Integer> order=randomChoose();
                for (int i=0;i<6;i++){
                    int button_order=order.get(i);
                    View button_here=chooseTypeGroup.getChildAt(i);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(button_here.getLayoutParams());
                    if (width_here*6/3>height_here){
                        params.height=height_here/6;
                        params.width=height_here/6;
                    }
                    else {
                        params.height=width_here/3;
                        params.width=width_here/3;
                    }

                    String name="type"+new String(""+button_order);
                    button_here.setTag(name);
                    button_here.setId(button_order);
                    button_here.setLayoutParams(params);


                    if (button_order==0){
                        button_here.setBackground(getDrawable(R.drawable.rightup));
                    }
                    else if (button_order==1){
                        button_here.setBackground(getDrawable(R.drawable.rightdown));

                    }
                    else if(button_order==2){
                        button_here.setBackground(getDrawable(R.drawable.rightmiddle));
                    }
                    else if(button_order==3){
                        button_here.setBackground(getDrawable(R.drawable.middlemiddle));
                    }
                    else if(button_order==4){
                        button_here.setBackground(getDrawable(R.drawable.middledown));
                    }
                    else{
                        button_here.setBackground(getDrawable(R.drawable.leftdown));
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
                    imageView.setId(6+i);
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
                    imageView.setId(9+i);
                    imageView.setLayoutParams(params);




                }


            }
        };
        this.runOnUiThread(runnable);







    }

    protected void prepareViewOld(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_inoutchoose);
        chooseTypeGroup.setBackground(getResources().getDrawable(R.drawable.bg));
        Runnable runnable=new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                for (int i=0;i<6;i++){
                    Button button_here=new Button(context);
                    chooseTypeGroup.addView(button_here);

                }
                ArrayList<Integer> order=randomChoose();
                for (int i=0;i<6;i++){
                    int button_order=order.get(i);
                    View button_here=chooseTypeGroup.getChildAt(i);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(button_here.getLayoutParams());
                    if (width_here*6/3>height_here){
                        params.height=height_here/6;
                        params.width=height_here/6;
                    }
                    else {
                        params.height=width_here/3;
                        params.width=width_here/3;
                    }

                    String name="type"+new String(""+button_order);
                    button_here.setTag(name);
                    button_here.setId(button_order);
                    button_here.setLayoutParams(params);


                    if (button_order==0){
                        button_here.setBackground(getResources().getDrawable(R.drawable.rightup));
                    }
                    else if (button_order==1){
                        button_here.setBackground(getResources().getDrawable(R.drawable.rightdown));

                    }
                    else if(button_order==2){
                        button_here.setBackground(getResources().getDrawable(R.drawable.rightmiddle));
                    }
                    else if(button_order==3){
                        button_here.setBackground(getResources().getDrawable(R.drawable.middlemiddle));
                    }
                    else if(button_order==4){
                        button_here.setBackground(getResources().getDrawable(R.drawable.middledown));
                    }
                    else{
                        button_here.setBackground(getResources().getDrawable(R.drawable.leftdown));
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
                    imageView.setId(6+i);
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
                    imageView.setId(9+i);
                    imageView.setLayoutParams(params);




                }


            }
        };
        this.runOnUiThread(runnable);







    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(getBaseContext(),ChooseResult .class);
        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
        String ID =v.getTag().toString();
        if(ID.equals("type0")){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("rightup",0);
            time++;
            pref.edit().putInt("rightup",time).commit();

            num.edit().putInt("split_code",51).commit();
        }
        else if(ID.equals("type1")){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("rightdown",0);
            time++;
            pref.edit().putInt("rightdown",time).commit();

            num.edit().putInt("split_code",52).commit();
        }
        else if(ID.equals("type2")){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("rightmiddle",0);
            time++;
            pref.edit().putInt("rightmiddle",time).commit();

            num.edit().putInt("split_code",53).commit();
        }
        else if(ID.equals("type3")){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("middlemiddle",0);
            time++;
            pref.edit().putInt("middlemiddle",time).commit();
            num.edit().putInt("split_code",54).commit();

        }
        else if(ID.equals("type4")){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("middledown",0);
            time++;
            pref.edit().putInt("middledown",time).commit();
            num.edit().putInt("split_code",55).commit();
        }
        else if(ID.equals("type5")){
            SharedPreferences pref = getSharedPreferences("record", MODE_PRIVATE);
            int time =pref.getInt("leftdown",0);
            time++;
            pref.edit().putInt("leftdown",time).commit();
            num.edit().putInt("split_code",56).commit();
        }
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setQuestion(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_inoutchoose);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        View charLeft=chooseTypeGroup.getChildAt(6);
        View charMiddle=chooseTypeGroup.getChildAt(7);
        View charRight=chooseTypeGroup.getChildAt(8);
        View phoLeft=chooseTypeGroup.getChildAt(9);
        View phoMiddle=chooseTypeGroup.getChildAt(10);
        View phoRight=chooseTypeGroup.getChildAt(11);


        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        int answer_position=storeinform.getInt("answer_position",0);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            chooseTypeGroup.getNum(6,2,2);
            if(answer_position==0){
                charLeft.setBackground(getDrawable(R.drawable.questionblock));
                phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
            }
            else{

                charLeft.setBackground(getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                charRight.setBackground(getDrawable(R.drawable.questionblock));
                phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));

            }

        }
        else {
            chooseTypeGroup.getNum(6,3,3);
            if (answer_position/10==0){
                if (answer_position==0){
                    charLeft.setBackground(getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getDrawable((here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()))));
                    charRight.setBackground(getDrawable((here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()))));
                    phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));
                }
                else if (answer_position==1){
                    charLeft.setBackground(getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                    charMiddle.setBackground(getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getDrawable((here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()))));
                    phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }
                else{
                    charLeft.setBackground(getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                    charMiddle.setBackground(getDrawable((here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()))));
                    charRight.setBackground(getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }

            }
            else  if(answer_position/100==0){
                if(answer_position==21){
                    charLeft.setBackground(getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                    charMiddle.setBackground(getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }
                else  if(answer_position==20){
                    charLeft.setBackground(getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getDrawable((here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()))));
                    charRight.setBackground(getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }
                else if(answer_position==10){
                    charLeft.setBackground(getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getDrawable((here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()))));
                    phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }

            }
            else {
                phoLeft.setBackground(getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                phoRight.setBackground(getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                phoMiddle.setBackground(getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

            }


        }












    }



    protected void setQuestionOld(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_inoutchoose);
        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        View charLeft=chooseTypeGroup.getChildAt(6);
        View charMiddle=chooseTypeGroup.getChildAt(7);
        View charRight=chooseTypeGroup.getChildAt(8);
        View phoLeft=chooseTypeGroup.getChildAt(9);
        View phoMiddle=chooseTypeGroup.getChildAt(10);
        View phoRight=chooseTypeGroup.getChildAt(11);


        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        int answer_position=storeinform.getInt("answer_position",0);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            chooseTypeGroup.getNum(6,2,2);
            if(answer_position==0){
                charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                charRight.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
            }
            else{

                charLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));

            }

        }
        else {
            chooseTypeGroup.getNum(6,3,3);
            if (answer_position/10==0){
                if (answer_position==0){
                    charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()))));
                    charRight.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()))));
                    phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));
                }
                else if (answer_position==1){
                    charLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                    charMiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()))));
                    phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }
                else{
                    charLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                    charMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()))));
                    charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }

            }
            else  if(answer_position/100==0){
                if(answer_position==21){
                    charLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName()))));
                    charMiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }
                else  if(answer_position==20){
                    charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName()))));
                    charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }
                else if(answer_position==10){
                    charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getResources().getDrawable((here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName()))));
                    phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                    phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                    phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

                }

            }
            else {
                phoLeft.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName()))));
                phoRight.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName()))));
                phoMiddle.setBackground(getResources().getDrawable((here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName()))));

            }


        }












    }


    private ArrayList<Integer> randomChoose(){
        ArrayList<Integer> chooseOrder =new ArrayList<>();
        ArrayList<Integer>numlist=new ArrayList<Integer>();
        for (int i=0;i<6;i++){
            numlist.add(i);
        }
        int bound=6;
        Random rd=new Random();
        for (int i=0;i<6;i++){
            int here= rd.nextInt(bound);
            chooseOrder.add(numlist.get(here));
            numlist.remove(here);
            bound--;
        }
        return  chooseOrder;
    }
}
