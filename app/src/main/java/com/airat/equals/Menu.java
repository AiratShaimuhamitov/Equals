package com.airat.equals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameInfo.setLevel(1);
        GameInfo.setLevelTime(33000);
        setContentView(R.layout.activity_menu);
    }


    public void Start(View view) {
        Intent intent = new Intent(this, LevelInfo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_out, R.anim.left_out);
        this.finish();
    }

    public void aboutClick(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.right_out, R.anim.left_out);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }

    public void scoreClick(View view) {
        this.finish();
        Intent intent = new Intent(this, Score.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_out, R.anim.left_out);
    }
}

