package com.zwhkj.todaynews.todaynews.customview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/**
 * 类描述：自定义EditText
 * 作者：刘士良
 * 时间：2016/2/1 17:09
 * 版本：
 */
public class MyEditText extends EditText {

    //回调函数
    private TextWatcherCallBack mCallback;

    public void setCallBack(TextWatcherCallBack mCallback) {
        this.mCallback = mCallback;
    }

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void init() {

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有在activity中设置回调，则此处可以触发
                if (mCallback != null)
                    mCallback.handleMoreTextChanged();
            }
        };
        this.addTextChangedListener(textWatcher);
    }


    /**
     * 设置晃动动画
     */
    public void shake() {
        //this.setAnimation(shakeAnimation(5));
        this.startAnimation(shakeAnimation(5));
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    private static Animation shakeAnimation(int counts) {

        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        //设置一个循环加速器，使用传入的次数就会出现摆动的效果。
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);

        return translateAnimation;
    }


}
