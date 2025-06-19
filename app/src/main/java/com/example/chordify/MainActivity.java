package com.example.chordify;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView chordsList;
    private ChordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chordsList = findViewById(R.id.chordsList);
        chordsList.setLayoutManager(new LinearLayoutManager(this));

        // Создаем список всех аккордов
        List<Chord> chords = new ArrayList<>();
        chords.add(new Chord("C chords", R.drawable.c_notation));
        chords.add(new Chord("D chords", R.drawable.d_notation));
        chords.add(new Chord("E chords", R.drawable.e_notation));
        chords.add(new Chord("F chords", R.drawable.f_notation));
        chords.add(new Chord("G chords", R.drawable.g_notation));
        chords.add(new Chord("A chords", R.drawable.a_notation));
        chords.add(new Chord("B chords", R.drawable.b_notation));

        // Устанавливаем адаптер
        adapter = new ChordsAdapter(this, chords);
        chordsList.setAdapter(adapter);

        // Обработчики иконок
        findViewById(R.id.searchIcon).setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
        findViewById(R.id.favoritesIcon).setOnClickListener(v -> startActivity(new Intent(this, FavoritesActivity.class)));
        findViewById(R.id.settingsIcon).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
}
