package com.airat.equals;


import android.content.Context;
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

    public static void setScore(Context context) {
        sPref = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        boolean hasVisited = sPref.getBoolean("hasVisited", false);

        if (!hasVisited) {
            SharedPreferences.Editor e = sPref.edit();
            e.putBoolean("hasVisited", true);
            e.putString(SAVED_TEXT, String.valueOf(0));
            e.apply();
        }
        int t = Integer.parseInt(sPref.getString(SAVED_TEXT, ""));
        if (getLevel() > t) {
            saveText(context);
        }
    }

    public static void saveText(Context context) {
        sPref = context.getSharedPreferences("Score",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = GameInfo.sPref.edit();
        int k = GameInfo.getLevel() - 1;
        ed.putString(GameInfo.SAVED_TEXT, String.valueOf(k));
        ed.apply();
    }

}
