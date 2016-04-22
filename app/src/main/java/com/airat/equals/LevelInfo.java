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
        Context context = this;
        GameInfo.setScore(context);
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
