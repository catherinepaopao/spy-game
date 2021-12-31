package com.idk.spygame;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class EndGameDialog extends Dialog {

    ReturnToMainMenu returnToMainMenu;

    public EndGameDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.end_game_dialog);
    }

    public void setReturnToMainMenu(ReturnToMainMenu returnToMainMenu){
        this.returnToMainMenu = returnToMainMenu;
    }

    public void showDialog(String winnerMessage){
        TextView winnerText = findViewById(R.id.winner_text);
        TextView civilianWordDisplay = findViewById(R.id.civilian_word_display);
        TextView spyWordDisplay = findViewById(R.id.spy_word_display);
        Button returnToMenu = findViewById(R.id.return_to_menu);

        winnerText.setText(winnerMessage);
        civilianWordDisplay.setText("Civilian word: " + GameData.civilianWord);
        spyWordDisplay.setText("Spy word: " + GameData.spyWord);

        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainMenu.returnToMainMenu();
                dismiss();
            }
        });

        show();
    }

    interface ReturnToMainMenu{
        void returnToMainMenu();
    }
}
