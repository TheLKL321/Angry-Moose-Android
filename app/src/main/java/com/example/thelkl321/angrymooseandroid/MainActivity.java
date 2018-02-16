package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playPressed (View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void optionsPressed (View view){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void creditsPressed (View view){
        // TODO: a popup with some credits
    }

    public void exitPressed (View view){
        finish();
        System.exit(0);
    }
}
