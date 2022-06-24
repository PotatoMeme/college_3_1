package com.induk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class ListAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] value = {
                "Apple", "Apricot", "Avocado", "Banana", "Blackberry",
                "Blueberry", "Cherry", "Coconut", "Cranberry",
                "Grape Raisin", "Honeydew", "Jackfruit", "Lemon", "Lime",
                "Mango", "Watermelon"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,value);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this,R.array.fruits ,android.R.layout.simple_list_item_1);

        // android.R.layout.simple_list_item_1 : 하나의 텍스트 뷰 사용
        // android.R.layout.simple_list_item_2 : 두개의 텍스트 뷰 사용
        // android.R.layout.simple_list_item_checked : 항목의 체크 표시
        // android.R.layout.simple_list_item_single_choice : 한개의 항목만 선택
        // android.R.layout.simple_list_item_multiple_choice : 여러 개의 항목 선택 가능

        setListAdapter(adapter2);
        //setContentView(R.layout.activity_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this,String.format(
                getString(R.string.toast_format),item,position,id),Toast.LENGTH_SHORT).show();
    }
}