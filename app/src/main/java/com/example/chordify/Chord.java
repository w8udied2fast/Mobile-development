package com.example.chordify;

public class Chord {
    private String name;
    private int notationImage;

    public Chord(String name, int notationImage) {
        this.name = name;
        this.notationImage = notationImage;
    }

    public String getName() {
        return name;
    }

    public int getNotationImage() {
        return notationImage;
    }
}
