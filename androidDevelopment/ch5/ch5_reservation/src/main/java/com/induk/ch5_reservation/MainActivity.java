package com.induk.ch5_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        time = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
        // 달력의 기준 설정
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // 달력의 시간을 가져옴
        int min = calendar.get(Calendar.MINUTE);
        // 달력의 분을 가져옴
        timePicker1.setHour(hour);
        timePicker1.setMinute(min);
        showTime(hour, min);
    }

    private void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
        //format 맞추기
    }

    public void displayTime(View view) {
        int hour = timePicker1.getHour();
        int min = timePicker1.getMinute();
        showTime(hour, min);
    }


}