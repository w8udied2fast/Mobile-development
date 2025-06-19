package com.example.chordify;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;
import android.text.Editable;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity {
    private EditText searchField;
    private RecyclerView resultsList;
    private List<String> allChords;
    private ChordsAdapter searchAdapter;

    private Map<String, Integer> chordImageMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = findViewById(R.id.searchField);
        resultsList = findViewById(R.id.resultsList);
        resultsList.setLayoutManager(new LinearLayoutManager(this));

        // Инициализируем список всех возможных аккордов
        allChords = Arrays.asList(
                "C", "Cmaj", "Cm", "C7",
                "D", "Dmaj", "Dm", "D7",
                "E", "Emaj", "Em", "E7",
                "F", "Fmaj", "Fm", "F7",
                "G", "Gmaj", "Gm", "G7",
                "A", "Amaj", "Am", "A7",
                "B", "Bmaj", "Bm", "B7"
        );

        // Инициализируем маппинг между аккордами и их изображениями
        initializeChordImageMap();

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                List<Chord> filteredResults = filterChords(query);
                updateResults(filteredResults);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            private List<Chord> filterChords(String query) {
                return allChords.stream()
                        .filter(chord -> chord.toLowerCase().startsWith(query.toLowerCase()))
                        .map(chord -> {
                            int imageResource = chordImageMap.getOrDefault(chord, R.drawable.default_notation); // Default fallback
                            return new Chord(chord, imageResource);
                        })
                        .collect(Collectors.toList());
            }

            private void updateResults(List<Chord> results) {
                searchAdapter = new ChordsAdapter(SearchActivity.this, results);
                resultsList.setAdapter(searchAdapter);
            }
        });
    }

    private void initializeChordImageMap() {
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
    }
}
