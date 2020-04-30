package com.wd.tech.view.information;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.bean.informationentity.InformationInfosEntity;

import java.util.List;

/**
 * @ClassName: FlowView
 * @Description: Java类的作用
 * @Author: LazyRui
 * @CreateDate: 2020/4/6 14:40
 */
public class FlowView extends ViewGroup {
    //右侧展示区域使用自定义流式布局来完成，使用第三方不给分

    public FlowView(Context context) {
        super(context);
    }

    public FlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int left = 0;
        int top = 0;
        int right = 0;
        int botton = 0;

        int childCount = getChildCount();

        if (childCount > 0) {

            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);

                childAt.measure(0, 0);

                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();

                right = left + measuredWidth;

                if (right > getWidth()) {

                    left = 0;
                    right = left + measuredWidth;
                    top = botton + 15;
                }
                botton = top + measuredHeight;

                childAt.layout(left, top, right, botton);

                left += measuredWidth + 15;

            }
        }

    }

    public void addTextView(List<InformationInfosEntity.ResultBean.PlateBean> plate) {
        if (plate != null) {
            for (int i = 0; i < plate.size(); i++) {
                TextView textView = new TextView(getContext());
                textView.setText(plate.get(i).getName());
                textView.setPadding(10,10,10,10);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(16);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.flowlayout_shape);
                addView(textView);
            }
        }
    }
}
