package com.example.qmr.pureweatherremark.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by qmr on 2016/9/8.
 * 风车自定义View
 */
public class WindmillView extends View {

    public static final String TAG = "WindmillView";

    int widthSize,heightSize;

    float degree = 0;

    Rect baseRect, fansRect;

    private boolean mStart = true;//是否在旋转

    private Paint mPaint;

    public WindmillView(Context context) {
        super(context);
        initPaint();
    }

    public WindmillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public WindmillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public WindmillView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int wsize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int hmode = MeasureSpec.getMode(heightMeasureSpec);
        int hsize = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

        if(MeasureSpec.EXACTLY == wmode)
            widthSize = wsize;
        else if(MeasureSpec.AT_MOST == wmode){
            widthSize = Math.min(wsize,500);
        }

        if(MeasureSpec.EXACTLY == hmode)
            heightSize = hsize;
        else if(MeasureSpec.AT_MOST == hmode){
            heightSize = Math.min(hsize,500);
        }
        initRect();
        setMeasuredDimension(widthSize,heightSize);
    }

    void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(android.R.color.white));
        mPaint.setStyle(Paint.Style.FILL);
    }

    void initRect(){
        baseRect = new Rect(widthSize/2-10,heightSize/2-10,widthSize/2+10,heightSize);
        fansRect = new Rect(widthSize/2-5,(heightSize/2),widthSize/2+5,heightSize/6*5);
        Log.i(TAG, "initRect: " + (widthSize/2-10)+"  " +heightSize/2+"  " +(widthSize/2+10)+"  " +getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mStart) {
            canvas.drawRect(baseRect,mPaint);
            canvas.rotate(degree,widthSize/2,heightSize/2);
            canvas.drawRect(fansRect,mPaint);
            canvas.rotate(120,widthSize/2,heightSize/2);
            canvas.drawRect(fansRect,mPaint);
            canvas.rotate(120,widthSize/2,heightSize/2);
            canvas.drawRect(fansRect,mPaint);
            degree -= 3;
            postInvalidateDelayed(16);
        }
    }
}
