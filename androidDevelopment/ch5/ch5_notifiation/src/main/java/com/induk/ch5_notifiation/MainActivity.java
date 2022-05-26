package com.induk.ch5_notifiation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        findViewById(R.id.btn_notification).setOnClickListener(this);
    }

    private void createNotificationChannel() {// Notification 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// 빌드버전이 오레오 이상만 동작

            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID // 아이디
                            , "My Notifications" // 이름
                            , NotificationManager.IMPORTANCE_DEFAULT); // 중요도

            notificationChannel.setDescription("Channel description");

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);// 서비스를 가져옴

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }


    @Override
    public void onClick(View view) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        // NOTIFICATION_CHANNEL_ID를 이용하여 notificationBuilder와 채널을 연결

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        notificationBuilder.setSmallIcon(R.drawable.ic_alarm_action)
                .setContentTitle("메일 알림")
                .setContentText("세로운 메일이 도착하였습니다.")// 속성을 설정
                .setContentIntent(pendingIntent);//pending intent(액션을 첨부) 지정

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //알림 객체 생성하여 보내기
        for(int i=1;i<5;i++){
            notificationManager.notify(/*nofication id*/i,notificationBuilder.build());
        }
    }
}