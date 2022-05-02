package com.induk.ch5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    //context menu 사용
    {
//    private TextView textView;
//    private ActionMode actionMode;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textView = (TextView) findViewById(R.id.textContext);
//        textView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                actionMode = startActionMode(actionModeCallBack);
//                return true;
//            }
//        });
//
//    }
//
//    private ActionMode.Callback2 actionModeCallBack = new ActionMode.Callback2() {
//        @Override
//        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//            actionMode.setTitle("Action Menu");
//            actionMode.getMenuInflater().inflate(R.menu.contextmenu, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.contextMenu_plus:
//                    Toast.makeText(MainActivity.this, "Plus", Toast.LENGTH_SHORT).show();
//                    actionMode.finish();
//                    return true;
//                case R.id.contextMenu_minus:
//                    Toast.makeText(MainActivity.this, "Minus", Toast.LENGTH_SHORT).show();
//                    actionMode.finish();
//                    return true;
//                default:
//                    return false;
//            }
//        }
//        @Override
//        public void onDestroyActionMode(ActionMode actionMode) {
//        }
//    };
    }

    //context menu 사용
    {
//private TextView text;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        text = (TextView) findViewById(R.id.textContext);
//        registerForContextMenu(text);
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderTitle("컨텍스트 메뉴");
//        menu.add(0, 1, 0, "배경색: RED");
//        menu.add(0, 2, 0, "배경색: GREEN");
//        menu.add(0, 3, 0, "배경색: BLUE");
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case 1:
//                Toast.makeText(this, "1번" + item.getTitle(), Toast.LENGTH_SHORT).show();
//                text.setBackgroundColor(Color.RED);
//                return true;
//            case 2:
//                Toast.makeText(this, "2번" + item.getTitle(), Toast.LENGTH_SHORT).show();
//                text.setBackgroundColor(Color.GREEN);
//                return true;
//            case 3:
//                Toast.makeText(this, "3번" + item.getTitle(), Toast.LENGTH_SHORT).show();
//                text.setBackgroundColor(Color.BLUE);
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }
    }

    //menu 코드로구현
    {

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //Menu.add (int groupId, int itemId, int order, CharSequence title)
//        //Menu.add (int groupId, int itemId, int order, int titleRes)
//
//        MenuItem item1 = menu.add(0,1,0,"menu_tell");
//        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        item1.setTitle("tell");
//        item1.setIcon(R.drawable.ic_tel);
//
//        MenuItem item2 = menu.add(0,2,0,"menu_plus");
//        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        item2.setTitle("plus");
//        item2.setIcon(R.drawable.ic_plus);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//            case 1:
//                Toast.makeText(this,"1번 tell"+item.getItemId(),Toast.LENGTH_SHORT).show();
//                return true;
//            case 2:
//                Toast.makeText(this,"2번 plus"+item.getItemId(),Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return  super.onOptionsItemSelected(item);
//        }
//    }
    }

    //menu xml로구현
    {
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.mymenu,menu);
//        return true;
//    }

//    onOptionsItemSelected로 이벤트 구현
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_plus:
//                Toast.makeText(this,"clicked plus",Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menu_minus:
//                Toast.makeText(this,"clicked minus",Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//    onClick으로 이벤트 구현
//    public void OnClicked(MenuItem menuItem) {
//       switch (menuItem.getItemId()){
//           case R.id.menu_plus:
//               Toast.makeText(this,"menu_plus clicked",Toast.LENGTH_SHORT).show();
//               return;
//           case R.id.menu_minus:
//               Toast.makeText(this,"menu_plus clicked",Toast.LENGTH_SHORT).show();
//               return;
//           default:
//               return;
//       }
//    }
    }
}