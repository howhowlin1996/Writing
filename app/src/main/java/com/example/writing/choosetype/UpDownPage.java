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

public class UpDownPage extends AppCompatActivity implements View.OnClickListener {
    int width_here,height_here;
    Context context;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updownchoose);
        getSupportActionBar().hide(); //隱藏標題
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_updownchoose);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        width_here=dm.widthPixels;
        height_here=dm.heightPixels;
        context=this;
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk< Build.VERSION_CODES.LOLLIPOP){
            prepareViewOld();
            setQuestionOld();
        }
        else{
            prepareView();
            setQuestion();
        }

        for (int i=0;i<4;i++){
            chooseTypeGroup.getChildAt(i).setOnClickListener(this);
        }



    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void prepareView(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_updownchoose);
        chooseTypeGroup.setBackground(getDrawable(R.drawable.bg));
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<4;i++){
                    Button button_here=new Button(context);
                    chooseTypeGroup.addView(button_here);

                }
                for (int i=0;i<4;i++){
                    View button_here=chooseTypeGroup.getChildAt(i);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(button_here.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/3;
                    String name="type"+new String(""+i);
                    button_here.setTag(name);
                    button_here.setId(i);
                    button_here.setLayoutParams(params);


                    if (i==0){
                        button_here.setBackground(getDrawable(R.drawable.updown));
                    }
                    else if (i==1){
                        button_here.setBackground(getDrawable(R.drawable.updown3));
                    }
                    else if (i==2){
                        button_here.setBackground(getDrawable(R.drawable.leftright2));
                    }
                    else{
                        button_here.setBackground(getDrawable(R.drawable.leftright3));
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
                    imageView.setId(4+i);
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
                    imageView.setId(7+i);
                    imageView.setLayoutParams(params);




                }


            }
        };
        this.runOnUiThread(runnable);









    }

    protected void prepareViewOld(){
        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_updownchoose);
        chooseTypeGroup.setBackground(getResources().getDrawable(R.drawable.bg));
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<4;i++){
                    Button button_here=new Button(context);
                    chooseTypeGroup.addView(button_here);

                }
                for (int i=0;i<4;i++){
                    View button_here=chooseTypeGroup.getChildAt(i);
                    ChooseTypeGroup.LayoutParams params=new ChooseTypeGroup.LayoutParams(button_here.getLayoutParams());
                    params.height=width_here/3;
                    params.width=width_here/3;
                    String name="type"+new String(""+i);
                    button_here.setTag(name);
                    button_here.setId(i);
                    button_here.setLayoutParams(params);


                    if (i==0){
                        button_here.setBackground(getResources().getDrawable(R.drawable.updown));
                    }
                    else if (i==1){
                        button_here.setBackground(getResources().getDrawable(R.drawable.updown3));
                    }
                    else if (i==2){
                        button_here.setBackground(getResources().getDrawable(R.drawable.leftright2));
                    }
                    else{
                        button_here.setBackground(getResources().getDrawable(R.drawable.leftright3));
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
                    imageView.setId(4+i);
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
                    imageView.setId(7+i);
                    imageView.setLayoutParams(params);




                }


            }
        };
        this.runOnUiThread(runnable);









    }


    @Override
    public void onClick(View v) {
        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);

        if(v.getTag().equals("type0")){
            num.edit().putInt("split_code",21).commit();
        }
        else if(v.getTag().equals("type1")){
            num.edit().putInt("split_code",22).commit();

        }
        else if(v.getTag().equals("type2")){
            num.edit().putInt("split_code",31).commit();

        }
        else if(v.getTag().equals("type3")){
            num.edit().putInt("split_code",32).commit();

        }



        Intent intent =new Intent(getBaseContext(), ChooseResult.class);
        startActivity(intent);

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setQuestion(){

        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_updownchoose);
        View charLeft=chooseTypeGroup.getChildAt(4);
        View charMiddle=chooseTypeGroup.getChildAt(5);
        View charRight=chooseTypeGroup.getChildAt(6);
        View phoLeft=chooseTypeGroup.getChildAt(7);
        View phoMiddle=chooseTypeGroup.getChildAt(8);
        View phoRight=chooseTypeGroup.getChildAt(9);


        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        int answer_position=storeinform.getInt("answer_position",0);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
           chooseTypeGroup.getNum(4,2,2);
            if(answer_position==0){
                charLeft.setBackground(getDrawable(R.drawable.questionblock));
                phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
            }
            else{

                charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                charRight.setBackground(getDrawable(R.drawable.questionblock));
                phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));

            }

        }
        else {
            chooseTypeGroup.getNum(4,3,3);
            if (answer_position/10==0){
                if (answer_position==0){
                    //charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    charLeft.setBackground(getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));
                }
                else if (answer_position==1){
                    charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    //charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    charMiddle.setBackground(getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }
                else{
                    charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    //charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    charRight.setBackground(getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }

            }
            else  if(answer_position/100==0){
                if(answer_position==21){
                    charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    //charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    //charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    charMiddle.setBackground(getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }
                else  if(answer_position==20){
                    //charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    charLeft.setBackground(getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    //charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    charRight.setBackground(getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }
                else if(answer_position==10){
                    //charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    //charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    charLeft.setBackground(getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }

            }
            else {
                phoLeft.setBackground(getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                phoRight.setBackground(getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                phoMiddle.setBackground(getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

            }


        }







    }


    protected void setQuestionOld(){

        final ChooseTypeGroup chooseTypeGroup=findViewById(R.id.rootGroup_updownchoose);
        View charLeft=chooseTypeGroup.getChildAt(4);
        View charMiddle=chooseTypeGroup.getChildAt(5);
        View charRight=chooseTypeGroup.getChildAt(6);
        View phoLeft=chooseTypeGroup.getChildAt(7);
        View phoMiddle=chooseTypeGroup.getChildAt(8);
        View phoRight=chooseTypeGroup.getChildAt(9);


        SharedPreferences storeinform=getSharedPreferences("num", Context.MODE_PRIVATE);
        String rightString=storeinform.getString("right",null);
        String leftString =storeinform.getString("left",null);
        String middleString=storeinform.getString("middle",null);
        int answer_position=storeinform.getInt("answer_position",0);
        Resources here_r=this.getResources();
        if(middleString.equals("0")){
            chooseTypeGroup.getNum(4,2,2);
            if(answer_position==0){
                charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                charRight.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
            }
            else{

                charLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));

            }

        }
        else {
            chooseTypeGroup.getNum(4,3,3);
            if (answer_position/10==0){
                if (answer_position==0){
                    //charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    charRight.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));
                }
                else if (answer_position==1){
                    charLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    //charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    charMiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }
                else{
                    charLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    charMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    //charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }

            }
            else  if(answer_position/100==0){
                if(answer_position==21){
                    charLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    //charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    //charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    charMiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }
                else  if(answer_position==20){
                    //charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    //charRight.setBackground(getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    charRight.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }
                else if(answer_position==10){
                    //charLeft.setBackground(getDrawable(here_r.getIdentifier("cha"+leftString,"drawable",this.getPackageName())));
                    //charMiddle.setBackground(getDrawable(here_r.getIdentifier("cha"+middleString,"drawable",this.getPackageName())));
                    charLeft.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charMiddle.setBackground(getResources().getDrawable(R.drawable.questionblock));
                    charRight.setBackground(getResources().getDrawable(here_r.getIdentifier("cha"+rightString,"drawable",this.getPackageName())));
                    phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                    phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                    phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

                }

            }
            else {
                phoLeft.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+leftString,"drawable",this.getPackageName())));
                phoRight.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+rightString,"drawable",this.getPackageName())));
                phoMiddle.setBackground(getResources().getDrawable(here_r.getIdentifier("pho"+middleString,"drawable",this.getPackageName())));

            }


        }







    }
}
