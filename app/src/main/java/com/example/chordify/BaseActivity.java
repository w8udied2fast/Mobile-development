package com.example.chordify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    protected int fontSize = 14;
    private SharedPreferences sharedPreferences;
    private BroadcastReceiver fontReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        fontSize = sharedPreferences.getInt("font_size", 14);

        // Регистрация BroadcastReceiver для обновления шрифта
        fontReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("FONT_SIZE_CHANGED".equals(intent.getAction())) {
                    fontSize = sharedPreferences.getInt("font_size", 14);
                    updateTextSize(findViewById(android.R.id.content));
                }
            }
        };
        registerReceiver(fontReceiver, new IntentFilter("FONT_SIZE_CHANGED"), Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(fontReceiver);
    }

    // Рекурсивное обновление размера текста для всех TextView
    private void updateTextSize(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(fontSize);
        } else if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                updateTextSize(group.getChildAt(i));
            }
        }
    }
}