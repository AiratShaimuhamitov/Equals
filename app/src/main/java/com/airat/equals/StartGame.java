package com.airat.equals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class StartGame extends AppCompatActivity {
    final Random random = new Random();

    Button plus_but_1;
    Button plus_but_2;
    Button plus_but_3;
    Button plus_but_4;
    Button newNumbersBtn;
    TextView timerText;
    TextView res_text_1;
    TextView res_text_2;
    TextView res_text_3;
    TextView res_text_4;
    TextView win_text;
    Animation bigger = null;
    public int res_1 = 0;
    public int res_2 = 0;
    public int res_3 = 0;
    public int res_4 = 0;
    public int butt_1 = 0;
    public int butt_2 = 0;
    public int butt_3 = 0;
    public int butt_4 = 0;
    boolean win;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        bigger = AnimationUtils.loadAnimation(this, R.anim.bigger);
        plus_but_1 = (Button) findViewById(R.id.button);
        plus_but_2 = (Button) findViewById(R.id.button2);
        plus_but_3 = (Button) findViewById(R.id.button3);
        plus_but_4 = (Button) findViewById(R.id.button4);
        newNumbersBtn = (Button) findViewById(R.id.start_butt);
        res_text_1 = (TextView) findViewById(R.id.textView);
        res_text_2 = (TextView) findViewById(R.id.textView2);
        res_text_3 = (TextView) findViewById(R.id.textView3);
        res_text_4 = (TextView) findViewById(R.id.textView4);
        timerText = (TextView) findViewById(R.id.timerText);
        win_text = (TextView) findViewById(R.id.textView5);

        timerText.setTextColor(getResources().getColor(R.color.green));
        roundTimer.start();
        new_game();
    }


    // TIMER
    CountDownTimer roundTimer = new CountDownTimer(GameInfo.nextLevelTime(), 1000) {

        public void onTick(long millisUntilFinished) {
            timerText.setText("" + millisUntilFinished / 1000);
            if (millisUntilFinished < 20000)
                timerText.setTextColor(getResources().getColor(R.color.yellow));
            if (millisUntilFinished < 10000)
                timerText.setTextColor(getResources().getColor(R.color.red));
            if (millisUntilFinished < 6000)
                timerText.startAnimation(bigger);
        }

        public void onFinish() {
            if (!win()) {
                win = false;
                timerText.setText(null);
                win_text.setText(R.string.lose);
                win_text.setVisibility(View.VISIBLE);
            }
            newNumbersBtn.setVisibility(View.INVISIBLE);
            plus_but_1.setEnabled(false);
            plus_but_2.setEnabled(false);
            plus_but_3.setEnabled(false);
            plus_but_4.setEnabled(false);
            waitTaimer.start();
        }
    };

    public void new_values(View view) {
        butt_1 = random.nextInt(9) + 1;
        butt_2 = random.nextInt(9) + 1;
        butt_3 = random.nextInt(9) + 1;
        butt_4 = random.nextInt(9) + 1;
        plus_but_1.setText(String.valueOf(butt_1));
        plus_but_2.setText(String.valueOf(butt_2));
        plus_but_3.setText(String.valueOf(butt_3));
        plus_but_4.setText(String.valueOf(butt_4));
    }

    public void butt1_click(View view) {
        res_1 += butt_1;
        res_text_1.setText(String.valueOf(res_1));
        butt_1 = random.nextInt(9) + 1;
        plus_but_1.setText(String.valueOf(butt_1));
        win_1();
    }

    public void butt2_click(View view) {
        res_2 += butt_2;
        res_text_2.setText(String.valueOf(res_2));
        butt_2 = random.nextInt(9) + 1;
        plus_but_2.setText(String.valueOf(butt_2));
        win_1();

    }

    public void butt3_click(View view) {
        res_3 += butt_3;
        res_text_3.setText(String.valueOf(res_3));
        butt_3 = random.nextInt(9) + 1;
        plus_but_3.setText(String.valueOf(butt_3));
        win_1();
    }

    public void butt4_click(View view) {
        res_4 += butt_4;
        res_text_4.setText(String.valueOf(res_4));
        butt_4 = random.nextInt(9) + 1;
        plus_but_4.setText(String.valueOf(butt_4));
        win_1();
    }

    private void win_1() {
        if ((res_1 == res_2) && (res_3 == res_4) && (res_1 == res_3)) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long milliseconds = 500;
            //v.vibrate(milliseconds);

            setScore();
            roundTimer.cancel();
            Intent level_intent = new Intent(this, LevelInfo.class);
            startActivity(level_intent);
            this.finish();
            overridePendingTransition(R.anim.right_out, R.anim.left_out);
            timerText.setText(null);
            win_text.setText(R.string.win);
            win_text.setVisibility(View.VISIBLE);
            newNumbersBtn.setVisibility(View.INVISIBLE);
            plus_but_1.setEnabled(false);
            plus_but_2.setEnabled(false);
            plus_but_3.setEnabled(false);
            plus_but_4.setEnabled(false);
            plus_but_1.setText(R.string.win_2);
            plus_but_2.setText(R.string.win_2);
            plus_but_3.setText(R.string.win_2);
            plus_but_4.setText(R.string.win_2);
        }
    }

    private void new_game() {
        win_text.setVisibility(View.INVISIBLE);
        newNumbersBtn.setVisibility(View.VISIBLE);
        plus_but_1.setEnabled(true);
        plus_but_2.setEnabled(true);
        plus_but_3.setEnabled(true);
        plus_but_4.setEnabled(true);
        butt_1 = random.nextInt(9) + 1;
        butt_2 = random.nextInt(9) + 1;
        butt_3 = random.nextInt(9) + 1;
        butt_4 = random.nextInt(9) + 1;
        plus_but_1.setText(String.valueOf(butt_1));
        plus_but_2.setText(String.valueOf(butt_2));
        plus_but_3.setText(String.valueOf(butt_3));
        plus_but_4.setText(String.valueOf(butt_4));
        res_1 = random.nextInt(9);
        res_2 = random.nextInt(9);
        res_3 = random.nextInt(9);
        res_4 = random.nextInt(9);
        res_text_1.setText(String.valueOf(res_1));
        res_text_2.setText(String.valueOf(res_2));
        res_text_3.setText(String.valueOf(res_3));
        res_text_4.setText(String.valueOf(res_4));
    }

    private boolean win() {
        if ((res_1 == res_2) && (res_3 == res_4) && (res_1 == res_3) && (res_1 != 0)) {
            return true;
        } else return false;
    }

    CountDownTimer waitTaimer = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (win)
                NextLevel_1();
            else goMenu();
        }
    };

    private void NextLevel_1() {
        this.finish();
        Intent level_intent = new Intent(this, LevelInfo.class);
        startActivity(level_intent);
        overridePendingTransition(R.anim.right_out, R.anim.left_out);
    }
    private void setScore() {
        GameInfo.sPref = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int t = Integer.parseInt(GameInfo.sPref.getString(GameInfo.SAVED_TEXT, ""));
        if (GameInfo.getLevel() > t) {
            saveText();
        }
    }

    private void saveText() {
        GameInfo.sPref = this.getSharedPreferences("Score",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = GameInfo.sPref.edit();
        ed.putString(GameInfo.SAVED_TEXT, String.valueOf(GameInfo.getLevel()));
        ed.apply();
    }

    private void goMenu() {
        this.finish();
        Intent menu_intent = new Intent(this, Menu.class);
        startActivity(menu_intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }

    @Override
    public void onBackPressed() {
        roundTimer.cancel();
        this.finish();
        Intent menu_intent = new Intent(this, Menu.class);
        startActivity(menu_intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }
}
