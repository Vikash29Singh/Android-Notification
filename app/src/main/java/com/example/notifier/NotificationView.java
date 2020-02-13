package com.example.notifier;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notifier.R;

public class NotificationView extends AppCompatActivity {
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        tv = findViewById(R.id.tv);
        String message = getIntent().getStringExtra("message");
        tv.setText(message);
    }
}