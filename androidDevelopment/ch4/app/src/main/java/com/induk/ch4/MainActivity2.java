package com.induk.ch4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void next_act(View view) {
        Intent i  = new Intent(MainActivity2.this,MainActivity3.class);
        startActivity(i);
        finish();
    }

    public void onClicked(View view) {
        Toast.makeText(this,"test toast",Toast.LENGTH_LONG).show();
    }

    public void onClicked_ver1(View view) {
        Snackbar.make(view,"snackbar Test_ver1",Snackbar.LENGTH_INDEFINITE).show();
    }

    public void onClicked_ver2(View view) {
        Snackbar s = Snackbar.make(view,"snackbar Test_ver2",Snackbar.LENGTH_INDEFINITE);
        s.setAction("확인", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });
        s.show();
    }


}