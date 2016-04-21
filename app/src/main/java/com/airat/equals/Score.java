package com.airat.equals;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    String savedText;
    TextView scoreText;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreText = (TextView) findViewById(R.id.YouScore);
        loadText();
    }
    public void loadText() {
        GameInfo.sPref = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        savedText = GameInfo.sPref.getString(GameInfo.SAVED_TEXT, "");
        str = getString(R.string.uRec);
        scoreText.setText(str + " " + savedText);
    }
    public void goMenuClick(View view) {
        Intent menu_intent = new Intent(this, Menu.class);
        startActivity(menu_intent);
        this.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_in);
    }
}
