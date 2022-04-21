package com.induk.ch4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    static boolean o;
    Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        o = true;

        btn = findViewById(R.id.btn_id);
        btn1 = findViewById(R.id.btn_id1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this,"*************",Toast.LENGTH_LONG).show();
            }
        });

        Listener lis = new Listener();
        btn.setOnClickListener(lis);


    }

    class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Toast.makeText(MainActivity3.this,"*************",Toast.LENGTH_LONG).show();
        }
    }

    public void onClicked(View view) {
        o = !o;
        view.setSelected(o);
    }
}