package com.idk.spygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createNormalGame;
    Button createCustomGame;
    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNormalGame = findViewById(R.id.create_normal_game);
        createCustomGame = findViewById(R.id.create_custom_game);
        settings = findViewById(R.id.settings);

        createNormalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent normalGameCreationIntent = new Intent(getApplicationContext(), NormalGameCreate.class);
                startActivity(normalGameCreationIntent);
            }
        });

        createCustomGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsPage.class);
                startActivity(settingsIntent);
            }
        });
    }
}