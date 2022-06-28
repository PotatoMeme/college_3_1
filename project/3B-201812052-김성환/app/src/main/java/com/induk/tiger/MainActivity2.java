package com.induk.tiger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private MyView myView;
    private EditText edtCount;
    private Integer saveContextItemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initializeView();
    }

    private void initializeView() {
        edtCount = (EditText) findViewById(R.id.edtCount);
        myView = (MyView) findViewById(R.id.vCanvas);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.colormenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        myView.setPaint(item.getItemId());
        drawingTask();
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                saveContextItemId = item.getItemId();
                drawingTask();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void drawingTask() {
        try {
            myView.setting(saveContextItemId,
                    Integer.parseInt(edtCount.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this, R.string.err_msg, Toast.LENGTH_SHORT).show();
        }
        myView.invalidate();//화면을 갱신 View.invalidate
        // 뷰를 무효화 한 후에는 다시 화면을 그리기 위해 onDraw를 호출
    }
}