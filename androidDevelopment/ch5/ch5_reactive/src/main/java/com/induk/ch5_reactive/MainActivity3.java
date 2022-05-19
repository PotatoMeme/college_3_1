package com.induk.ch5_reactive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity3 extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editText = findViewById(R.id.edtLog);
        startRX();
    }

    private void startRX() {
        Observable<String> source = Observable.just("event1","event2")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        source.subscribe(
                new Observer<String>() {
                         @Override
                         public void onSubscribe(@NonNull Disposable d) {
                             editText.append("subscribing\n");
                         }

                         @Override
                         public void onNext(@NonNull String s) {
                            editText.append("Next:"+s+"\n");
                         }

                         @Override
                         public void onError(@NonNull Throwable e) {
                             editText.append("ERROR:"+e.toString()+"\n");
                         }

                         @Override
                         public void onComplete() {
                             editText.append("Complete");
                         }
                     }
        );

    }
}