package com.induk.ch5_reactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.reactivex.rxjava3.core.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edtLog);

        for(int ii = 0; ii<8;ii++)startRX(ii);
        findViewById(R.id.btn_next_act).setOnClickListener(this);

    }

    private void startRX(int num) {
        Observable<Integer> source = Observable.create(
                subscriber -> {
                    try {
                        for (int ii = 0; ii < 5; ii++) {
                            subscriber.onNext(ii);
                        }
                        subscriber.onComplete();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
        );
        source.subscribe(
                ii -> editText.append(num+"Next:"+ii+"\n"),
                e -> editText.append(num+"Error:"+e),
                () -> editText.append(num+"Completed\n")
        );

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,MainActivity2.class));
    }
}