package com.eproject.vien.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author VienWang
 * @time 2018/7/29/029 13:02
 * @desc K歌传输图片的loading控件
 */

public class KaraokeReceiveLoadingView extends View {

    private int mTotal = 20;
    private int mProgress = 0;
    private int circleRadius = 20;

    private int widthSize;
    private int heightSize;

    private int mCircleInterval;
    private int yPosition;

    private int mTextSize = 50;
    private boolean isStart = true;
    private int mCurrentLightPosition;
    private int animTriggerTime = 400;

    public KaraokeReceiveLoadingView(Context context) {
        super(context);
        init();
    }

    public KaraokeReceiveLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KaraokeReceiveLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mTextSize = 50;
        circleRadius = 6;
        animTriggerTime = 400;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize =  MeasureSpec.getSize(widthMeasureSpec);
        heightSize =  MeasureSpec.getSize(heightMeasureSpec);

        mCircleInterval = (widthSize - mCircleInterval)/6;
        yPosition = heightSize/2;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //新建画园的画笔
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //第一个圆点
        canvas.drawCircle(circleRadius/2, yPosition, circleRadius,paint);
        //第二个圆点
        canvas.drawCircle(circleRadius/2 + mCircleInterval, yPosition, circleRadius,paint);
        //第三个圆点
        canvas.drawCircle(circleRadius/2 + mCircleInterval * 2, yPosition, circleRadius,paint);
        //第四个圆点
        canvas.drawCircle(widthSize - circleRadius/2 - mCircleInterval * 2, yPosition, circleRadius,paint);
        //第五个圆点
        canvas.drawCircle(widthSize - circleRadius/2 - mCircleInterval, yPosition, circleRadius,paint);
        //第六个圆点
        canvas.drawCircle(widthSize - circleRadius/2, yPosition, circleRadius,paint);

        paint.setColor(Color.RED);
        switch(getLightPosition()){
            case 0:
                //第一个圆点
                canvas.drawCircle(circleRadius/2, yPosition, circleRadius,paint);
                break;
            case 1:
                //第二个圆点
                canvas.drawCircle(circleRadius/2 + mCircleInterval, yPosition, circleRadius,paint);
                break;
            case 2:
                //第三个圆点
                canvas.drawCircle(circleRadius/2 + mCircleInterval * 2, yPosition, circleRadius,paint);
                break;
            case 3:
                //第四个圆点
                canvas.drawCircle(widthSize - circleRadius/2 - mCircleInterval * 2, yPosition, circleRadius,paint);
                break;
            case 4:
                //第五个圆点
                canvas.drawCircle(widthSize - circleRadius/2 - mCircleInterval, yPosition, circleRadius,paint);
                break;
            case 5:
                //第六个圆点
                canvas.drawCircle(widthSize - circleRadius/2, yPosition, circleRadius,paint);
                break;

        }

        //画数字
        paint.setColor(Color.BLACK);
        paint.setTextSize(mTextSize);
        canvas.drawText(mProgress + "/" + mTotal,widthSize/2,yPosition,paint);

        //如果当前正在显示，则每500ms显示一次
        if(isStart){
            postInvalidateDelayed(animTriggerTime);
        }

    }

    public void startLoading(){
        isStart = true;
        postInvalidate();
    }

    public void stopLoading(){
        isStart = false;
    }

    public void setProgress(int progress,int total){
        this.mProgress = progress;
        this.mTotal = total;
    }

    private int getLightPosition(){
        return mCurrentLightPosition++ % 6;
    }
}
