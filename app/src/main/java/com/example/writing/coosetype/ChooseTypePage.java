package com.example.writing.coosetype;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.writing.R;

import java.util.Set;
import java.util.TreeSet;


public class ChooseTypePage extends AppCompatActivity implements View.OnClickListener {
    Set<String> defaultSet=new TreeSet<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepage);
        final Button singleButton = findViewById(R.id.SingleButton);                 //get character type id from choosepage.xml
        final Button updownButton = findViewById(R.id.UpDownButton);
        final Button inoutButton = findViewById(R.id.InOutButton);
        String key_name=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet).iterator().next();
        int num;
        num=getSharedPreferences("num",0).getInt(key_name,0);

        if(num==0){
            Set<String> setkeyname=new TreeSet<String>();
            setkeyname=getSharedPreferences("num",0).getStringSet("chartypenum",defaultSet);
            setkeyname.remove(key_name);
            if (setkeyname.iterator().hasNext()){
                key_name=setkeyname.iterator().next();
            }
            if (setkeyname.size()==0){
                //Toast.makeText(this,"end",Toast.LENGTH_SHORT).show();
                getSharedPreferences("num",0).edit().putStringSet("chartypenum",defaultSet).clear().commit();
            }

        }
        else{
            setQuestion(key_name);



        }
        //Toast.makeText(this,new String(" "+key_name+" "+getSharedPreferences("num",0).getInt(key_name,0)),Toast.LENGTH_SHORT).show();




        singleButton.setOnClickListener(this);
        updownButton.setOnClickListener(this);
        inoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Integer idnum=v.getId();
        if (idnum==R.id.SingleButton){
            Intent intent = new  Intent(ChooseTypePage.this, SinglePage.class);             //change activity to the activity that every button matches
            startActivity(intent);
        }
        else if (idnum==R.id.UpDownButton){
            Intent intent = new  Intent(ChooseTypePage.this, UpDownPage.class);
            startActivity(intent);
        }
        else  if (idnum==R.id.InOutButton){
            Intent intent = new  Intent(ChooseTypePage.this, InOutPage.class);
            startActivity(intent);
        }
        this.finish();



    }

    void setQuestion(String type){
        final ImageView charquestionright=findViewById(R.id.characterQright_choose);
        final ImageView charquestionleft=findViewById(R.id.characterQleft_choose);
        final ImageView phoquestionright=findViewById(R.id.phoneticQright_choose);
        final ImageView phoquestionleft=findViewById(R.id.phoneticQleft_choose);
        int chartype=0;
        int question_num=0;
        int qadecision=0;
        if(type=="Single"){
            chartype=11;
        }
        else if(type=="ThreeEle"){
            chartype=41;
        }





        Resources here_r=this.getResources();
        int charleft_id=here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName());
        int charright_id=here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName());
        int pholeft_id=here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"1"),"drawable",this.getPackageName());
        int phoright_id=here_r.getIdentifier(new String("pho" +chartype+question_num+qadecision+"0"),"drawable",this.getPackageName());
        charquestionright.setImageResource(charright_id);
        charquestionleft.setImageResource(charleft_id);
        phoquestionright.setImageResource(phoright_id);
        phoquestionleft.setImageResource(pholeft_id);



    }
}
