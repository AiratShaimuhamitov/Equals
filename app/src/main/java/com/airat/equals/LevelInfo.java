package com.airat.equals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import java.lang.String;
import java.lang.Integer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LevelInfo extends AppCompatActivity {

    TextView textLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_info);
        textLevel = (TextView) findViewById(R.id.textLevel);
        String levelInf = "Level " + String.valueOf(GameInfo.getLevel());
        textLevel.setText(levelInf);
        levelTimer.start();
        setScore();
    }

    void setScore() {
        GameInfo.sPref = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        boolean hasVisited = GameInfo.sPref.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // выводим нужную активность
            SharedPreferences.Editor e = GameInfo.sPref.edit();
            e.putBoolean("hasVisited", true);
            e.putString(GameInfo.SAVED_TEXT, String.valueOf(0));
            e.apply(); // не забудьте подтвердить изменения
        }
        int t = Integer.parseInt(GameInfo.sPref.getString(GameInfo.SAVED_TEXT, ""));
        if (GameInfo.getLevel() > t) {
            saveText(t);
        }
    }

    public void saveText(int level) {
        GameInfo.sPref = this.getSharedPreferences("Score",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = GameInfo.sPref.edit();
        ed.putString(GameInfo.SAVED_TEXT, String.valueOf(level - 1));
        ed.apply();
    }

    CountDownTimer levelTimer = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            NextLevel();
        }
    };


    private void NextLevel() {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_out, R.anim.left_out);
        GameInfo.levelAdd();
        this.finish();
    }
}
