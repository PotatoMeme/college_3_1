package com.induk.ch5_reactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.reactivex.rxjava3.core.Observable;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = findViewById(R.id.edtLog);
        startRX();
        findViewById(R.id.btn_next_act).setOnClickListener(this);

    }

    private void startRX() {
        Observable<String> source = Observable.fromCallable(
                () -> "welcome to induk"
        );

        source.subscribe(
                ii -> editText.append("Next:"+ii+"\n"),
                e -> editText.append("Error:"+e),
                () -> editText.append("Completed")
        );
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity3.class));
    }
}