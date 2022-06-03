package com.induk.ch6_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

public class MyView extends View {

    private int count;
    private int what;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW);
        setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("도형선택");
                contextMenu.add(0,1,0,"선");
                contextMenu.add(0,2,0,"면");
                contextMenu.add(0,3,0,"원");
                contextMenu.add(0,4,0,"타원");
                contextMenu.add(0,5,0,"부채꼴");
                contextMenu.add(0,6,0,"텍스트");
            }
        });
    }

    public void setting(int w,int c){
        what = w;
        count = c;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(this.getClass().getName(),"****");
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        paint.setStrokeWidth(20);

        switch (what){
            case 1:
                for (int ii=0; ii < count; ii++) {
                    canvas.drawLine(100, 100 + (50 * ii), 600, 100 + (50 * ii), paint);
                }
                break;
            case 2:
                canvas.drawRect(100, 200, 300, 300, paint);
                break;
            case 3:
                for (int ii=0; ii < count; ii++) {
                    canvas.drawCircle(300 + (150 * ii), 500, 100, paint);
                }
                break;
            case 4:
                canvas.drawOval(new RectF(30, 50, 400, 600), paint);
                break;
            case 5:
                canvas.drawArc(new RectF(30, 50, 400, 600), 0, 90, true, paint);
                break;
            case 6:
                canvas.drawText("This is a test", 100, 350, paint);
                break;
            default:
                break;
        }
    }
}