package com.example.chordify;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesList;
    private VariantsAdapter adapter;
    private PreferencesHelper preferencesHelper;
    private List<Chord> favoriteChords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        preferencesHelper = new PreferencesHelper(this);

        favoritesList = findViewById(R.id.favoritesList);
        favoritesList.setLayoutManager(new LinearLayoutManager(this));

        List<ChordVariant> favorites = new ArrayList<>();
        Set<String> favoriteNames = preferencesHelper.getFavorites();

        // Загружаем избранные аккорды
        for (String name : favoriteNames) {
            int imageResource = getChordImageByName(name);
            if (imageResource != 0) {
                favorites.add(new ChordVariant(name, imageResource));
            }
        }

        adapter = new VariantsAdapter(this, favorites);
        favoritesList.setAdapter(adapter);
    }

    private int getChordImageByName(String name) {
        // Маппинг между именами аккордов и их изображениями
        Map<String, Integer> chordImageMap = new HashMap<>();
        // C аккорды
        chordImageMap.put("C", R.drawable.c_chord);
        chordImageMap.put("Cmaj", R.drawable.c_maj);
        chordImageMap.put("Cm", R.drawable.c_min);
        chordImageMap.put("C7", R.drawable.c_7);

        // D аккорды
        chordImageMap.put("D", R.drawable.d_chord);
        chordImageMap.put("Dmaj", R.drawable.d_maj);
        chordImageMap.put("Dm", R.drawable.d_min);
        chordImageMap.put("D7", R.drawable.d_7);

        // E аккорды
        chordImageMap.put("E", R.drawable.e_chord);
        chordImageMap.put("Emaj", R.drawable.e_maj);
        chordImageMap.put("Em", R.drawable.e_min);
        chordImageMap.put("E7", R.drawable.e_7);

        // F аккорды
        chordImageMap.put("F", R.drawable.f_chord);
        chordImageMap.put("Fmaj", R.drawable.f_maj);
        chordImageMap.put("Fm", R.drawable.f_min);
        chordImageMap.put("F7", R.drawable.f_7);

        // G аккорды
        chordImageMap.put("G", R.drawable.g_chord);
        chordImageMap.put("Gmaj", R.drawable.g_maj);
        chordImageMap.put("Gm", R.drawable.g_min);
        chordImageMap.put("G7", R.drawable.g_7);

        // A аккорды
        chordImageMap.put("A", R.drawable.a_chord);
        chordImageMap.put("Amaj", R.drawable.a_maj);
        chordImageMap.put("Am", R.drawable.a_min);
        chordImageMap.put("A7", R.drawable.a_7);

        // B аккорды
        chordImageMap.put("B", R.drawable.b_chord);
        chordImageMap.put("Bmaj", R.drawable.b_maj);
        chordImageMap.put("Bm", R.drawable.b_min);
        chordImageMap.put("B7", R.drawable.b_7);

        return chordImageMap.getOrDefault(name, 0); // Возвращаем 0, если изображение не найдено
    }
}