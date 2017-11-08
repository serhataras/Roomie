package com.threek.roomie.MemoryManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.threek.roomie.Game.Gender;

/**
 * Created by USER on 4.11.2017.
 */

public class MemoryManager {

    // constants
    private static final String FILE_NAME = "SaveFile";
    private static final int FILE_MODE = Context.MODE_PRIVATE;

    private static final String NAME_KEY = "Name";
    private static final String GENDER_KEY = "Gender";

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
        return preferences.getString(NAME_KEY, "asd");
    }

    public static void saveGender(Context context, Gender gender)
    {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, FILE_MODE);
        SharedPreferences.Editor editor = preferences.edit();

        // enum to boolean conversion
        Boolean genderToSave;
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

        Boolean genderToReturn = preferences.getBoolean(GENDER_KEY, true);

        // boolean to enum conversion
        if (genderToReturn)
        {
            return Gender.MALE;
        }
        return  Gender.FEMALE;
    }

}

