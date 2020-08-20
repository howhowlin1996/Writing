package main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writing.R;

public class EnterScene extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    Boolean check=false;
    int shark_alpha=255;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
        setContentView(R.layout.enterscene);
        CheckBox consent_button=findViewById(R.id.consent_checkBox);
        Button confirm_button=findViewById(R.id.confirmButton_enterScene);
        consent_button.setOnCheckedChangeListener(this);
        confirm_button.setOnClickListener(this);
        EnterScene();




    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態
    }
    private void EnterScene(){
        final ImageView shark=findViewById(R.id.shark_enter);
        TextView consent=findViewById(R.id.consentRule);
        CheckBox consent_button=findViewById(R.id.consent_checkBox);
        Button   confirm=findViewById(R.id.confirmButton_enterScene);
        consent.setVisibility(View.GONE);
        consent_button.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);

       Runnable shark_runnable=new Runnable() {
           @Override
           public void run() {
               shark.getBackground().setAlpha(shark_alpha);
                   shark_alpha-=255/50;
                   shark.getBackground().setAlpha(shark_alpha);
                   if (shark_alpha>0){
                       shark.postDelayed(this,30);
                   }
                   else{

                       consentShow();
                   }

           }
       };


       this.runOnUiThread(shark_runnable);

      /*this.runOnUiThread(runnable);
        */






    }

    public void consentShow(){
        ImageView shark=findViewById(R.id.shark_enter);
        TextView header=findViewById(R.id.header);
        TextView consent=findViewById(R.id.consentRule);
        CheckBox consent_button=findViewById(R.id.consent_checkBox);
        Button   confirm=findViewById(R.id.confirmButton_enterScene);
        header.setVisibility(View.GONE);
        shark.setVisibility(View.GONE);
        consent.setVisibility(View.VISIBLE);
        consent_button.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        String consent_content="本軟體使用者授權合約（以下稱「授權合約」）是一份貴客戶（個人或單一法人團體）與「鯊魚教你寫國字」軟體開發團隊（以下簡稱本團隊）所擁有之軟體産品（包括產品包裝中之電腦軟體、相關媒介物、書面資訊、任何電子文件以及包括的網路線上電腦軟體、電子文檔與服務，以下簡稱「本軟體」）所合法成立之協議。\n" +
                "貴客戶于安裝、拷貝或以其他方式使用本軟體，即表示同意接受本「授權合約」條款之拘束。若貴客戶不同意本「授權合約」之條款，即不得使用本軟體。\n" +
                "本軟體之所有權利均歸本團隊所有﹐受著作權法、國際著作權條約以及其他智慧財産權之法律及國際間著作權相互保障條約之保護。本軟體僅系授權使用，而非販售賣斷。\n" +
                "\n" +
                "1. 授權範圍：\n" +
                "本授權合約授予貴客戶下列權利：\n" +
                "一、貴用戶得在單人使用環境下的平板、手機或其他記憶裝置中儲存或安裝乙份本軟體，其目的僅供貴用戶於單一電子設備上使用本軟體之用。本軟體之授權可由多人共享或安裝儲存於不同電腦終端機設備。\n" +
                "二、使用與複製：本公司授權貴客戶爲備份及保存之需要得將本軟體複製乙份。\n" +
                "2. 禁止事項：\n" +
                "一、禁止就本軟體進行還原工程（Reverse Engineering）、解編譯（Decomplication）、反組譯（Disassemble）或任何更改原始程式設計或系統上的鎖定與解除鎖定之行為。\n" +
                "二、禁止刪除、更改、遮蓋、移開本軟體（包括因資料備份或保存之需要而複製之本軟體）之任何著作權及商標標示。\n" +
                "三、未經本團隊事前書面同意，禁止將本軟體之授權予以出售、再授權、出租、出借、散佈、公開展示，亦不得將本軟體予以任何形式之複製、重製及翻譯，或其他足侵害本團隊權益之行為。\n" +
                "3. 終止：\n" +
                "若貴客戶未能遵守本「授權合約」之任一約定條款，則本公司得隨時終止本授權合約。屆時貴客戶必須銷毀所有本軟體之任何媒介上之儲存及移除已安裝之程式。本團隊得依相關法律請求損害賠償。\n" +
                "4. 著作權：\n" +
                "凡與本軟體及其拷貝有關之所有權與著作權均屬本團隊所有。\n" +
                "凡與因透過本軟體而訪問之資訊內容之所有權以及智慧財産權，均屬各該資訊之所有權人，並受相關著作權或其他智慧財産權法與條約之保護。本「授權合約」並不授權貴客戶就該等資訊之內容享有使用之權利。\n" +
                "5. 不爲瑕疵擔保：\n" +
                "貴客戶因使用本軟體所生成之風險由貴客戶自負。\n" +
                "在相關法律所允許之最大範圍內，本團隊不承擔任何瑕疵擔保責任與條件，不論其爲明示或默示者，其中包括（但不限於）適售性、適合某特定用途以及不侵害他人權益之默示擔保責任。\n" +
                "6. 就衍生性損害不負賠償責任:\n" +
                "在相關法律所允許之最大範圍內，本團隊體對於貴客戶因使用或不能使用本軟體而遭受之特別、附隨、間接或衍生性損害（包括，但不限於營業利益之損失、營業中斷、 營業資訊之損失或其他金錢損失）不負任何損害賠償責任。\n" +
                "此項規定不因貴客戶事先告知本團隊，該損害發生之可能性而有所不同。\n" +
                "7. 責任額之限制：\n" +
                "本團隊依本「授權合約」規定所應負之責任總額，以及貴客戶所享有之唯一救濟權，以不超過産品購買之金額爲限。\n" +
                "8. 資訊紀錄：\n" +
                "一、為了提供使用者良好的使用體驗，本團隊會紀錄使用者於軟體中的使用資訊（如軟體操作與其時間）作為使用分析資料使用。\n" +
                "二、此軟體為保障隱私權，所有個人資料資訊將不會公開揭露於第三者或作為第三方使用；使用者的操作資料會在確保與個人資料去連結化後用於本團隊之學術研究與本軟體之改良中。\n";

        consent.setText(consent_content);







    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        check=isChecked;


    }


    @Override
    public void onClick(View v) {
        if (check==true){

            Intent intent =new Intent(getBaseContext(), FirstScene.class);
            startActivity(intent);

        }
        else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("同意使用聲明才能繼續喔~~");
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            AlertDialog here =builder.create();
            here.show();
        }

    }
}
