package com.example.dogs;

import android.annotation.SuppressLint;
import android.icu.lang.UCharacter;

import java.util.Locale;

public class Dog {
    private String breed;
    private String image;

    public Dog(String breed, String image) {
        this.breed = breed;
        this.image = image;
    }

    public String getBreed() {
        return breed;
    }

    @SuppressLint("NewApi")
    public String getCapitalizedBreed() {
        return UCharacter.toTitleCase(Locale.getDefault(), this.getBreed(), null, 0);
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return String.format("It's a %s dog!", this.getCapitalizedBreed());
    }
}
