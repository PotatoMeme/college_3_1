package com.induk.ch6_make_hoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class MyView extends View {

    private Paint mPaint,mFramePaint;
    private RectF mBigOval;
    private float mStart,mSweep;
    private static final float SWEEP_INC = 2;
    private static final float START_INC = 15;


    public MyView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0x88FF0000);

        mFramePaint = new Paint();
        mFramePaint.setAntiAlias(true);
        mFramePaint.setStyle(Paint.Style.STROKE);
        mFramePaint.setStrokeWidth(3);
        mFramePaint.setColor(0x8800FF00);

        mBigOval = new RectF(100, 40, 400, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        canvas.drawRect(mBigOval,mFramePaint);
        canvas.drawArc(mBigOval,mStart,mSweep,false,mPaint);
        mSweep += SWEEP_INC;
        if (mSweep >360) {
            mSweep -= 360;
            mStart += START_INC;
            if (mStart >= 360) mStart -= 360;
        }
        invalidate();
    }
}
