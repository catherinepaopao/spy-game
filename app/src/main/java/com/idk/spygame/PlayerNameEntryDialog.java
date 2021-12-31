package com.idk.spygame;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class PlayerNameEntryDialog extends Dialog {

    UpdateRecycler updateRecycler = null;

    public PlayerNameEntryDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.name_entry_dialog);
    }

    public void setUpdateRecycler(UpdateRecycler updateRecycler) {
        this.updateRecycler = updateRecycler;
    }

    public void showDialog(int position) {
        TextView playerWord = findViewById(R.id.player_word);
        if (GameData.playerData.get(position).isSpy) {
            playerWord.setText(GameData.spyWord);
        } else {
            playerWord.setText(GameData.civilianWord);
        }
        EditText editText = findViewById(R.id.player_name);
        editText.setText("");
        Button button = findViewById(R.id.submit_name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameData.playerData.get(position).playerName = editText.getText().toString();
                GameData.playerData.get(position).isWordViewed = true;
                updateRecycler.updateDisplay();
                dismiss();
            }
        });

        show();
    }

    interface UpdateRecycler{
        void updateDisplay();
    }

}
