package com.induk.tiger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    private int count;
    private int what;
    private Paint paint;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.setHeaderTitle("도형선택");
                contextMenu.add(0, 1, 0, "선");
                contextMenu.add(0, 2, 0, "면");
                contextMenu.add(0, 3, 0, "원");
                contextMenu.add(0, 4, 0, "타원");
                contextMenu.add(0, 5, 0, "텍스트");
            }
        });
    }

    public void setPaint(int color) {
        switch (color) {
            case R.id.red:
                paint.setColor(Color.RED);
                break;
            case R.id.blue:
                paint.setColor(Color.BLUE);
                break;
            case R.id.green:
                paint.setColor(Color.GREEN);
                break;
            case R.id.white:
                paint.setColor(Color.WHITE);
                break;
        }
    }

    public void setting(int w, int c) {
        what = w;
        count = c;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        paint.setTextSize(100);
        paint.setStrokeWidth(20);
        int j = 0;
        int k = 0;
        int heigt;
        switch (what) {
            case 1:
                heigt = (canvas.getHeight() - 150) / 50;
                for (int ii = 0; ii < count; ii++) {
                    canvas.drawLine(100 + (j * 350), 100 + (50 * k), 300 + (j * 350), 100 + (50 * k), paint);
                    k++;
                    if (k >= heigt) {
                        k = 0;
                        j++;
                    }
                }
                break;
            case 2:
                heigt = (canvas.getHeight() - 400) / 150;
                for (int ii = 0; ii < count; ii++) {
                    canvas.drawRect(100 + (150 * j), 100 + (150 * k), 200 + (150 * j), 200 + (150 * k), paint);
                    k++;
                    if (k >= heigt) {
                        k = 0;
                        j++;
                    }
                }
                break;
            case 3:
                heigt = (canvas.getHeight() - 400) / 150;
                for (int ii = 0; ii < count; ii++) {
                    canvas.drawCircle(100 + (150 * j), 100 + (150 * k), 50, paint);
                    k++;
                    if (k >= heigt) {
                        k = 0;
                        j++;
                    }
                }
                break;
            case 4:
                heigt = (canvas.getHeight() - 400) / 150;
                for (int ii = 0; ii < count; ii++) {
                    canvas.drawOval(new RectF(100 + (350 * j), 100 + (150 * k), 400 + (350 * j), 200 + (150 * k)), paint);
                    k++;
                    if (k >= heigt) {
                        k = 0;
                        j++;
                    }
                }

                break;
            case 5:
                for (int ii = 0; ii < count; ii++) {
                    canvas.drawText("This is a test", 100, 350 + 200 * ii, paint);
                }
                break;
            default:
                break;
        }
    }
}
