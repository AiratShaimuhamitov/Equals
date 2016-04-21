package com.airat.equals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void goMenuClick(View view) {
        Intent menu_intent = new Intent(this, Menu.class);
        startActivity(menu_intent);
        this.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }

    @Override
    public void onBackPressed() {
        Intent menu_intent = new Intent(this, Menu.class);
        startActivity(menu_intent);
        this.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }
}
