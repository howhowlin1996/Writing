package main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;
import com.example.writing.choosetype.ChooseTypePage;

import java.util.Set;
import java.util.TreeSet;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {

     Set<String>chartypenum=new TreeSet<String>() ;
     int random_position=0,part_num=1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.setting);
        getSupportActionBar().hide(); //隱藏標題
        Spinner single = findViewById(R.id.SingleSpin);
        Spinner updown =findViewById(R.id.UpDownSpin);
        Spinner leftright2 =findViewById(R.id.LeftRight2Spin);
        Spinner leftright3 =findViewById(R.id.LeftRight3Spin);
        Spinner rightup =findViewById(R.id.RightUpSpin);
        Spinner rightmiddle =findViewById(R.id.RightMiddleSpin);
        Spinner rightdown =findViewById(R.id.RightDownSpin);
        Spinner middlemiddle =findViewById(R.id.MiddleMiddleSpin);
        Spinner middledown =findViewById(R.id.MiddleDownSpin);
        Spinner leftdown =findViewById(R.id.LeftDownSpin);
        Spinner updown3=findViewById(R.id.UpDown3Spin);
        Spinner randomspin=findViewById(R.id.RandomSpin);
        Button confirm=findViewById(R.id.ConfirmButton_setting);
        Spinner numspin=findViewById(R.id.numberSpin);

         String[] target={"0","1~5","6~10","11~15","16~20","21~25","26~30","31~35","36~40","41~45","46~50"};
         String[] randomchoose={"照順序","隨機出題"};
         String[] num={"選項數量","1","2","3","4"};

        ArrayAdapter<String> targetList= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, target);
        targetList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<String> randomList= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, randomchoose);
        randomList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<String> numList= new ArrayAdapter< String>(this, R.layout.support_simple_spinner_dropdown_item, num);
        numspin.setAdapter(numList);
        single.setAdapter(targetList);
        updown.setAdapter(targetList);
        leftright2.setAdapter(targetList);
        leftright3.setAdapter(targetList);
        rightup.setAdapter(targetList);
        rightmiddle.setAdapter(targetList);
        rightdown.setAdapter(targetList);
        middlemiddle.setAdapter(targetList);
        middledown.setAdapter(targetList);
        leftdown.setAdapter(targetList);
        updown3.setAdapter(targetList);
        randomspin.setAdapter(randomList);
        single.setOnItemSelectedListener(this);
        updown.setOnItemSelectedListener(this);
        updown3.setOnItemSelectedListener(this);
        leftright2.setOnItemSelectedListener(this);
        leftright3.setOnItemSelectedListener(this);
        rightup.setOnItemSelectedListener(this);
        rightmiddle.setOnItemSelectedListener(this);
        rightdown.setOnItemSelectedListener(this);
        middlemiddle.setOnItemSelectedListener(this);
        middledown.setOnItemSelectedListener(this);
        leftdown.setOnItemSelectedListener(this);
        randomspin.setOnItemSelectedListener(this);
        numspin.setOnItemSelectedListener(this);
        confirm.setOnClickListener(this);






    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
        if(parent.getId()==R.id.SingleSpin){

            if (position!=0){
                num.edit().putInt("Single",5).commit();
                num.edit().putInt("Single_position",position).commit();
                chartypenum.add("Single");


            }
            else{
                num.edit().putInt("Single",0).commit();
                chartypenum.remove("Single");

            }


        }
        else if (parent.getId()==R.id.UpDownSpin){


            if (position!=0){
                num.edit().putInt("UpDown",5).commit();
                num.edit().putInt("UpDown_position",position).commit();
                chartypenum.add("UpDown");


            }
            else{
                num.edit().putInt("UpDown",0).commit();
                chartypenum.remove("UpDown");

            }
        }
        else if (parent.getId()==R.id.UpDown3Spin){

            if (position!=0){
                num.edit().putInt("UpDown3",5).commit();
                num.edit().putInt("UpDown3_position",position).commit();
                chartypenum.add("UpDown3");

            }
            else{
                num.edit().putInt("UpDown3",0).commit();
                chartypenum.remove("UpDown3");

            }
        }
        else if(parent.getId()==R.id.LeftRight2Spin){

            if (position!=0){
                num.edit().putInt("LeftRight2",5).commit();
                num.edit().putInt("LeftRight2_position",position).commit();
                chartypenum.add("LeftRight2");
            }
            else{
                num.edit().putInt("LeftRight2",0).commit();
                chartypenum.remove("LeftRight2");
            }

        }
        else if(parent.getId()==R.id.LeftRight3Spin){

            if (position!=0){
                num.edit().putInt("LeftRight3",5).commit();
                num.edit().putInt("LeftRight3_position",position).commit();
                chartypenum.add("LeftRight3");
            }
            else{
                num.edit().putInt("LeftRight3",0).commit();
                chartypenum.remove("LeftRight3");
            }
        }

        else  if (parent.getId()==R.id.RightUpSpin){

            if (position!=0){
                num.edit().putInt("RightUp",5).commit();
                num.edit().putInt("RightUp_position",position).commit();
                chartypenum.add("RightUp");
            }
            else{
                num.edit().putInt("RightUp",0).commit();
                chartypenum.remove("RightUp");
            }
        }
        else if(parent.getId()==R.id.RightMiddleSpin){

            if (position!=0){
                num.edit().putInt("RightMiddle",5).commit();
                num.edit().putInt("RightMiddle_position",position).commit();
                chartypenum.add("RightMiddle");
            }
            else{
                num.edit().putInt("RightMiddle",0).commit();
                chartypenum.remove("RightMiddle");
            }
        }
        else if(parent.getId()==R.id.RightDownSpin){

            if (position!=0){
                num.edit().putInt("RightDown",5).commit();
                num.edit().putInt("RightDown_position",position).commit();
                chartypenum.add("RightDown");
            }
            else{
                num.edit().putInt("RightDown",0).commit();
                chartypenum.remove("RightDown");
            }
        }
        else if(parent.getId()==R.id.MiddleMiddleSpin){

            if (position!=0){
                num.edit().putInt("MiddleMiddle",5).commit();
                num.edit().putInt("MiddleMiddle_position",position).commit();
                chartypenum.add("MiddleMiddle");
            }
            else{
                num.edit().putInt("MiddleMiddle",0).commit();
                chartypenum.remove("MiddleMiddle");
            }
        }
        else if(parent.getId()==R.id.MiddleDownSpin){

            if (position!=0){
                num.edit().putInt("MiddleDown",5).commit();
                num.edit().putInt("MiddleDown_position",position).commit();
                chartypenum.add("MiddleDown");
            }
            else{
                num.edit().putInt("MiddleDown",0).commit();
                chartypenum.remove("MiddleDown");
            }
        }
        else if(parent.getId()==R.id.LeftDownSpin){

            if (position!=0){
                num.edit().putInt("LeftDown",5).commit();
                num.edit().putInt("LeftDown_position",position).commit();
                chartypenum.add("LeftDown");
            }
            else{
                num.edit().putInt("LeftDown",0).commit();
                chartypenum.remove("LeftDown");
            }
        }
        else if(parent.getId()==R.id.RandomSpin){
               random_position=position;
        }
        else if (parent.getId()==R.id.numberSpin){
            if (position>1){
                part_num=position;
            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (chartypenum.size()==0){
            onBackPressed();
        }
        else {
            SharedPreferences num=getSharedPreferences("num", Context.MODE_PRIVATE);
            num.edit().putStringSet("chartypenum",chartypenum).commit();
            num.edit().putInt("random",random_position).commit();
            num.edit().putInt("part_num",part_num).commit();
            Intent intent =new Intent(getBaseContext(), ChooseTypePage.class);
            startActivity(intent);
        }


    }
}
