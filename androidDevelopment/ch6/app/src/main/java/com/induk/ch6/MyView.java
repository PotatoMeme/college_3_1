package com.induk.ch6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW);
    }

    /*public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.BLUE);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

         /*paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
        paint.setTextSize(50);

        //canvas.drawLine(100,100,300,100,paint);
        //canvas.drawRect(100,200,300,300,paint);
        //canvas.drawCircle(300,500,100,paint);
        //canvas.drawText("this is a test",100,350,paint);
        //canvas.drawRoundRect(new RectF(30,50,600,600),45,45,paint);
        //canvas.drawOval(new RectF(30,50,400,600),paint);
        //canvas.drawArc(new RectF(30,50,400,600),0,90,true,paint);
        //canvas.drawArc(new RectF(30,50,400,600),0,90,false,paint);
        float[] point = {30,120,120,200
                ,120,200,180,50
                ,180,50,400,400};
        canvas.drawLines(point,paint);*/

        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        canvas.drawArc(new RectF(30,50,600,600),120,270,true,paint);
    }
}
