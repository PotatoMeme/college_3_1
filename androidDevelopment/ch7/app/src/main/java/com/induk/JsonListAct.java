package com.induk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class JsonListAct extends ListActivity {

    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<String[]> source = Observable.fromCallable(
                () -> {
                    String[] values = new HttpHelper().getList();

                    // NULL value not allowed.
                    if (values == null) {
                        values = new String[] {"No data"};
                    }
                    return values;
                }
        );
        backgroundTask = source.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String[]>() {
                    @Override
                    public void accept(String[] result) {

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(JsonListAct.this,
                                android.R.layout.simple_list_item_1, result);
                        setListAdapter(adapter);

                        backgroundTask.dispose();
                    }
                });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
    }

}