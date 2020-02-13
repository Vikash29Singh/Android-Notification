package com.example.notifier;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button toastnotofy ,statusnotofy;
private final String CHANNEL_ID ="Personal Notification";
private final int NOTIFICATION_ID =001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toastnotofy = findViewById(R.id.toastnotofy);
        statusnotofy = findViewById(R.id.statusnotofy);
        toastnotofy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Notification Generated", Toast.LENGTH_SHORT).show();
            }
        });

        statusnotofy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                createnotificationchannel(); //Create method if android version is 8 or above


                //this method if android version is below 8
                String message = "This is a new message";

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
                              builder.setSmallIcon(R.drawable.bell)
                                      .setContentTitle("Notifications Example")
                                .setContentText("This is a test notification")
                                      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true);

                Intent notificationIntent = new Intent(getApplicationContext(), NotificationView.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                notificationIntent.putExtra("message",message);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
                manager.notify(NOTIFICATION_ID, builder.build());


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createnotificationchannel()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O);
        {
            CharSequence name = "Personal Notification";
            String description = "Include all the personal Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
    }
}
