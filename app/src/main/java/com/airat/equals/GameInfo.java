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

    /*public static void setScore() {
        sPref = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        boolean hasVisited = sPref.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor e = sPref.edit();
            e.putBoolean("hasVisited", true);
            e.putString(SAVED_TEXT, String.valueOf(0));
            e.apply(); // не забудьте подтвердить изменения
        }
        int t = Integer.parseInt(sPref.getString(SAVED_TEXT, ""));
        if (getLevel() > t) {
            saveText(t);
        }
    }

    public static void saveText(int level) {
        sPref = this.getSharedPreferences("Score",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, String.valueOf(level - 1));
        ed.apply();
    }*/

}
