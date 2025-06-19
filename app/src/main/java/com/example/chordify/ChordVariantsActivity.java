package com.example.chordify;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChordVariantsActivity extends AppCompatActivity {
    private RecyclerView variantsList;
    private VariantsAdapter adapter;
    private List<ChordVariant> variants;
    private PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_variants);
        preferencesHelper = new PreferencesHelper(this);

        variantsList = findViewById(R.id.variantsList);
        variantsList.setLayoutManager(new LinearLayoutManager(this));

        String root = getIntent().getStringExtra("CHORD_ROOT");
        if (root == null || root.isEmpty()) {
            Toast.makeText(this, "Ошибка: данные не переданы", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        variants = loadChordVariants(root);

        if (variants == null || variants.isEmpty()) {
            Toast.makeText(this, "Нет данных для этого аккорда", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        adapter = new VariantsAdapter(this, variants);
        variantsList.setAdapter(adapter);

        Set<String> favorites = preferencesHelper.getFavorites();
        for (ChordVariant variant : variants) {
            if (favorites.contains(variant.getName())) {
                variant.setFavorite(true);
            }
        }
    }

    private List<ChordVariant> loadChordVariants(String root) {
        List<ChordVariant> variants = new ArrayList<>();
        switch (root) {
            case "C":
                variants.add(new ChordVariant("C", getChordImage("c_chord")));
                variants.add(new ChordVariant("Cmaj", getChordImage("c_maj")));
                variants.add(new ChordVariant("Cm", getChordImage("c_min")));
                variants.add(new ChordVariant("C7", getChordImage("c_7")));
                break;
            // Добавлены все остальные ноты
            case "D":
                variants.add(new ChordVariant("D", getChordImage("d_chord")));
                variants.add(new ChordVariant("Dmaj", getChordImage("d_maj")));
                variants.add(new ChordVariant("Dm", getChordImage("d_min")));
                variants.add(new ChordVariant("D7", getChordImage("d_7")));
                break;
            case "E":
                variants.add(new ChordVariant("E", getChordImage("e_chord")));
                variants.add(new ChordVariant("Emaj", getChordImage("e_maj")));
                variants.add(new ChordVariant("Em", getChordImage("e_min")));
                variants.add(new ChordVariant("E7", getChordImage("e_7")));
                break;
            case "F":
                variants.add(new ChordVariant("F", getChordImage("f_chord")));
                variants.add(new ChordVariant("Fmaj", getChordImage("f_maj")));
                variants.add(new ChordVariant("Fm", getChordImage("f_min")));
                variants.add(new ChordVariant("F7", getChordImage("f_7")));
                break;
            case "G":
                variants.add(new ChordVariant("G", getChordImage("g_chord")));
                variants.add(new ChordVariant("Gmaj", getChordImage("g_maj")));
                variants.add(new ChordVariant("Gm", getChordImage("g_min")));
                variants.add(new ChordVariant("G7", getChordImage("g_7")));
                break;
            case "A":
                variants.add(new ChordVariant("A", getChordImage("a_chord")));
                variants.add(new ChordVariant("Amaj", getChordImage("a_maj")));
                variants.add(new ChordVariant("Am", getChordImage("a_min")));
                variants.add(new ChordVariant("A7", getChordImage("a_7")));
                break;
            case "B":
                variants.add(new ChordVariant("B", getChordImage("b_chord")));
                variants.add(new ChordVariant("Bmaj", getChordImage("b_maj")));
                variants.add(new ChordVariant("Bm", getChordImage("b_min")));
                variants.add(new ChordVariant("B7", getChordImage("b_7")));
                break;
            default:
                break;
        }
        return variants;
    }

    private int getChordImage(String imageName) {
        try {
            return getResources().getIdentifier(imageName, "drawable", getPackageName());
        } catch (Exception e) {
            return R.drawable.default_notation; // Placeholder
        }
    }
}