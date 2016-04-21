package com.airat.equals;


import android.content.SharedPreferences;

public class GameInfo {
    private static int level = 0;
    private static int levelTime = 33000;
    public static SharedPreferences sPref;
    public static String SAVED_TEXT = "saved_text";


    public static int getLevel() {
        return level;
    }

    public static void setLevel(int a) {
        level = a;
    }

    public static void levelAdd() {
        level++;
    }

    public static void setLevelTime(int a) {
        levelTime = a;
    }

    public static int nextLevelTime() {
        if (level > 5)
            levelTime -= 1000;
        else {
            if(level != 1)
               levelTime -= 2000;
        }
        return levelTime;
    }
}
