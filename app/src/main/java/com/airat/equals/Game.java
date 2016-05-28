package com.airat.equals;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class Game extends AppCompatActivity {
    final Random random = new Random();

    ArrayList<Button> foldButtons = new ArrayList<>();
    ArrayList<TextView> resText = new ArrayList<>();

    Button newNumbersBtn;
    TextView timerText;

    TextView winText;

    Animation bigger = null;
    private int res[] = new int[4];
    private int btnVal[] = new int[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        bigger = AnimationUtils.loadAnimation(this, R.anim.bigger);
        
        foldButtons.add((Button) findViewById(R.id.btn_score));
        foldButtons.add((Button) findViewById(R.id.button2));
        foldButtons.add((Button) findViewById(R.id.btn_about));
        foldButtons.add((Button) findViewById(R.id.button4));
        
        newNumbersBtn = (Button) findViewById(R.id.start_butt);
        
        resText.add( (TextView) findViewById(R.id.textView1));
        resText.add( (TextView) findViewById(R.id.textView2));
        resText.add( (TextView) findViewById(R.id.textView3));
        resText.add( (TextView) findViewById(R.id.textView4));
        
        timerText = (TextView) findViewById(R.id.timerText);
        winText = (TextView) findViewById(R.id.textView5);

        timerText.setTextColor(getResources().getColor(R.color.blue));
        roundTimer.start();
        newGame();
    }


    // TIMER
    CountDownTimer roundTimer = new CountDownTimer(GameInfo.nextLevelTime(), 1000) {

        public void onTick(long millisUntilFinished) {
            String timerStr = "" + millisUntilFinished / 1000;
            timerText.setText(timerStr);
            if (millisUntilFinished < 20000)
                timerText.setTextColor(getResources().getColor(R.color.blue));
            if (millisUntilFinished < 10000)
                timerText.setTextColor(getResources().getColor(R.color.red));
            if (millisUntilFinished < 6000)
                timerText.startAnimation(bigger);
        }

        public void onFinish() {
            if (!isEqual()) {
                timerText.setText(null);
                winText.setText(R.string.lose);
                winText.setVisibility(View.VISIBLE);
            }
            newNumbersBtn.setVisibility(View.INVISIBLE);
            for(Button btn: foldButtons){
                btn.setEnabled(false);
            }
            timer.start();
        }
    };

    public void newValues(View view) {
        for(int i = 0; i < 4; i++){
            btnVal[i] = random.nextInt(9) + 1;
        }

        for(Button btn: foldButtons){
            btn.setText(String.valueOf(btnVal[foldButtons.indexOf(btn)])); // если не будет работать заменить через обычный for
        }
    }

    private void addValue(int index){
        res[index] += btnVal[index];
        resText.get(index).setText(String.valueOf(res[index]));
        btnVal[index] = random.nextInt(9) + 1;
        foldButtons.get(index).setText(String.valueOf(btnVal[index]));
        win_1();
    }

    public void btn1Click(View view) {
        addValue(0);
    }

    public void btn2Click(View view) {
        addValue(1);
    }

    public void btn3Click(View view) {
        addValue(2);
    }

    public void btn4Click(View view) {
        addValue(3);
    }

    private boolean isEqual(){
        for(int i = 0; i < 3; i++){
            if((res[i] != res[i + 1]) && res[i] != 0)
                return false;
        }
        return true;
    }

    private void win_1() {
        if (isEqual()) {
            Context context = this;
            GameInfo.setScore(context);
            roundTimer.cancel();
            Intent intent = new Intent(this, LevelInfo.class);
            startActivity(intent);
            this.finish();
            overridePendingTransition(R.anim.right_out, R.anim.left_out);
            timerText.setText(null);
            winText.setText(R.string.win);
            winText.setVisibility(View.VISIBLE);
            newNumbersBtn.setVisibility(View.INVISIBLE);
            for(Button btn: foldButtons){
                btn.setEnabled(false);
            }
        }
    }

    private void newGame() {
        winText.setVisibility(View.INVISIBLE);
        newNumbersBtn.setVisibility(View.VISIBLE);

        for(Button btn: foldButtons){
            btn.setEnabled(true);
        }

        for(int i = 0; i < 4; i++){
            btnVal[i] = random.nextInt(9) + 1;
        }
        for(int i = 0; i < 4; i++){
            foldButtons.get(i).setText(String.valueOf(btnVal[i]));
        }

        for(int i = 0; i < 4; i++){
            res[i] = random.nextInt(9) + 1;
        }

        for(TextView textView: resText){
            textView.setText(String.valueOf(res[resText.indexOf(textView)]));
        }
    }

    CountDownTimer timer = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if (isEqual())
                NextLevel();
            else goMenu();
        }
    };

    private void NextLevel() {
        this.finish();
        Intent level_intent = new Intent(this, LevelInfo.class);
        startActivity(level_intent);
        overridePendingTransition(R.anim.right_out, R.anim.left_out);
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
