package com.example.writing.memo;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.writing.R;

import static java.lang.StrictMath.abs;

public class EditText_memo extends AppCompatEditText {
   private int decision=0;

    public EditText_memo(Context context) {
        super(context);
    }

    public EditText_memo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public EditText_memo(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.setInputType(InputType.TYPE_NULL);
        return super.onTouchEvent(event);
    }


}
