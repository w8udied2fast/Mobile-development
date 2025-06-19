package com.example.chordify;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class PreferencesHelper {
    private static final String FAVORITES_KEY = "favorites";
    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public void saveFavorite(String chordName, boolean isFavorite) {
        Set<String> favorites = new HashSet<>(getFavorites());
        if (isFavorite) {
            favorites.add(chordName);
        } else {
            favorites.remove(chordName);
        }
        sharedPreferences.edit().putStringSet(FAVORITES_KEY, favorites).apply();
    }

    public Set<String> getFavorites() {
        return sharedPreferences.getStringSet(FAVORITES_KEY, new HashSet<>());
    }
}