package main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
     int  charactertype  [] =new int [12];
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.setting);
        Spinner single = findViewById(R.id.SingleSpin);
        Spinner updown =findViewById(R.id.UpDownSpin);
        Spinner leftright2 =findViewById(R.id.LeftRight2Spin);
        Spinner leftright3 =findViewById(R.id.LeftRight3Spin);
        Spinner threeele =findViewById(R.id.ThreeEleSpin);
        Spinner rightup =findViewById(R.id.RightUpSpin);
        Spinner rightmiddle =findViewById(R.id.RightMiddleSpin);
        Spinner rightdown =findViewById(R.id.RightDownSpin);
        Spinner middlemiddle =findViewById(R.id.MiddleMiddleSpin);
        Spinner middledown =findViewById(R.id.MiddleDownSpin);
        Spinner leftdown =findViewById(R.id.LeftDownSpin);
        Spinner randomspin=findViewById(R.id.RandomSpin);
        Button confirm=findViewById(R.id.ConfirmButton_setting);
        for(int i=0;i<12;i++){
            charactertype[i]=0;
        }
         String[] target={"0","1~5","6~10","11~15","16~20","21~25","26~30","31~35","36~40","41~45","46~50"};
         String[] randomchoose={"照順序","隨機出題"};


        ArrayAdapter<String> targetList= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, target);
        targetList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<String> randomList= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, randomchoose);
        randomList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        single.setAdapter(targetList);
        updown.setAdapter(targetList);
        leftright2.setAdapter(targetList);
        leftright3.setAdapter(targetList);
        threeele.setAdapter(targetList);
        rightup.setAdapter(targetList);
        rightmiddle.setAdapter(targetList);
        rightdown.setAdapter(targetList);
        middlemiddle.setAdapter(targetList);
        middledown.setAdapter(targetList);
        leftdown.setAdapter(targetList);
        randomspin.setAdapter(randomList);
        single.setOnItemSelectedListener(this);
        updown.setOnItemSelectedListener(this);
        leftright2.setOnItemSelectedListener(this);
        leftright3.setOnItemSelectedListener(this);
        threeele.setOnItemSelectedListener(this);
        rightup.setOnItemSelectedListener(this);
        rightmiddle.setOnItemSelectedListener(this);
        rightdown.setOnItemSelectedListener(this);
        middlemiddle.setOnItemSelectedListener(this);
        middledown.setOnItemSelectedListener(this);
        leftdown.setOnItemSelectedListener(this);
        randomspin.setOnItemSelectedListener(this);
        confirm.setOnClickListener(this);





    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.SingleSpin){
            charactertype[0]=position;

        }
        else if (parent.getId()==R.id.UpDownSpin){
            charactertype[1]=position;
        }
        else if(parent.getId()==R.id.LeftRight2Spin){
            charactertype[2]=position;
        }
        else if(parent.getId()==R.id.LeftRight3Spin){
            charactertype[3]=position;
        }
        else if(parent.getId()==R.id.ThreeEleSpin){
            charactertype[4]=position;
        }
        else  if (parent.getId()==R.id.RightUpSpin){
            charactertype[5]=position;
        }
        else if(parent.getId()==R.id.RightMiddleSpin){
            charactertype[6]=position;
        }
        else if(parent.getId()==R.id.RightDownSpin){
            charactertype[7]=position;
        }
        else if(parent.getId()==R.id.MiddleMiddleSpin){
            charactertype[8]=position;
        }
        else if(parent.getId()==R.id.MiddleDownSpin){
            charactertype[9]=position;
        }
        else if(parent.getId()==R.id.LeftDownSpin){
            charactertype[10]=position;
        }
        else if(parent.getId()==R.id.RandomSpin){
            charactertype[11]=position;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }
}
