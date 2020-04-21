package com.wd.tech.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wd.tech.R;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 11:15
 * @classname :
 */
public class Mysearchview  extends LinearLayout {
    private ImageView btn_sou;
    private EditText sou_content;
    private setContext setContext;
    public Mysearchview(Context context) {
        super(context);addview(context);
    }
    public Mysearchview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);addview(context);
    }
    public  void addview(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.searchview_item,this,true);
        btn_sou= view.findViewById(R.id.btn_sou);
        sou_content=view.findViewById(R.id.sou_content);
        btn_sou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setContext != null) {
                    setContext.onContent(sou_content.getText().toString());
                }
            }
        });
    }

    public void setSetContext(Mysearchview.setContext setContext) {
        this.setContext = setContext;
    }

    public  interface  setContext{
        void  onContent(String text);
    }
}
