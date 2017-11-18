package com.threek.roomie.MemoryManager;

import android.content.Context;
import android.content.SharedPreferences;

import src.Gender;

/**
 * Created by USER on 4.11.2017.
 */

public class MemoryManager {

    // constants
    private static final String FILE_NAME = "SaveFile";
    private static final int FILE_MODE = Context.MODE_PRIVATE;

    private static final String DEFAULT_NAME = "John Doe";
    private static final boolean DEFAULT_GENDER = true;
    private static final boolean DEFAULT_GAME_STARTED = true;

    private static final String NAME_KEY = "Name";
    private static final String GENDER_KEY = "Gender";
    private static final String GAME_START_KEY = "GameStarted";

    // methods
    public static void saveName(Context context, String name)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.commit();
    }

    public static String loadName(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);
        return preferences.getString(NAME_KEY, DEFAULT_NAME);
    }

    public static void saveGender(Context context, Gender gender)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);
        SharedPreferences.Editor editor = preferences.edit();

        // enum to boolean conversion
        boolean genderToSave;
        if (gender.equals(Gender.MALE))
        {
            genderToSave = true;
        }
        else
        {
            genderToSave = false;
        }

        editor.putBoolean(GENDER_KEY, genderToSave);
        editor.commit();
    }

    public static Gender loadGender(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);

        boolean genderToReturn = preferences.getBoolean(GENDER_KEY, DEFAULT_GENDER);

        // boolean to enum conversion
        if (genderToReturn)
        {
            return Gender.MALE;
        }
        return  Gender.FEMALE;
    }

    public static boolean loadGameStarted(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);

        return preferences.getBoolean(GAME_START_KEY, DEFAULT_GAME_STARTED);
    }

    public static void saveGameStarted(Context context, boolean gameStarted)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(GAME_START_KEY, gameStarted);
        editor.commit();
    }
}

