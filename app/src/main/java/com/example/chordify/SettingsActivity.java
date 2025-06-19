package com.example.chordify;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "app_prefs";
    private static final String FONT_SIZE_KEY = "font_size";

    private SharedPreferences sharedPreferences;
    private SeekBar fontSizeSeekBar;
    private TextView titleTextView, aboutAppTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        fontSizeSeekBar = findViewById(R.id.fontSizeSeekBar);
        titleTextView = findViewById(R.id.title);
        aboutAppTextView = findViewById(R.id.aboutApp);

        // Загрузить сохранённый размер шрифта
        int savedFontSize = sharedPreferences.getInt(FONT_SIZE_KEY, 14); // По умолчанию 14sp
        fontSizeSeekBar.setProgress(savedFontSize);
        updateTextSize(savedFontSize);

        // Обработчик изменения SeekBar
        fontSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Обновить размер шрифта и сохранить в SharedPreferences
                updateTextSize(progress);
                sharedPreferences.edit().putInt(FONT_SIZE_KEY, progress).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Обработчик клика на "О программе"
        aboutAppTextView.setOnClickListener(v -> showAboutDialog());
    }

    // Метод для обновления размера шрифта у всех TextView
    private void updateTextSize(int progress) {
        float fontSize = progress + 10; // Начальный размер 10sp, шаг 1
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        aboutAppTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("Author - Sergei Markidonov\nGroup - 23ISP6")
                .setPositiveButton("OK", null)
                .show();
    }
}