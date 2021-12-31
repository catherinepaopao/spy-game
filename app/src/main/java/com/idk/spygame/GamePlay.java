package com.idk.spygame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GamePlay extends AppCompatActivity {

    RecyclerView players;
    PlayerAdapter playerAdapter;
    int numPlayersInitiated;
    TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        PlayerNameEntryDialog dialog = new PlayerNameEntryDialog(this);
        EndGameDialog dialog2 = new EndGameDialog(this);
        playerAdapter = new PlayerAdapter(GameData.playerData, dialog, dialog2);
        numPlayersInitiated = 0;
        instructions = findViewById(R.id.instructions);

        dialog.setUpdateRecycler(new PlayerNameEntryDialog.UpdateRecycler() {
            @Override
            public void updateDisplay() {
                numPlayersInitiated++;
                if(numPlayersInitiated >= GameData.playerData.size()){
                    instructions.setText("Tap to vote spy!");
                    GameData.gameMode = 1;
                }
                playerAdapter.notifyDataSetChanged();
            }
        });

        dialog2.setReturnToMainMenu(new EndGameDialog.ReturnToMainMenu() {
            @Override
            public void returnToMainMenu() {
                GameData.gameMode = 0;
                GameData.playerData.clear();
                Intent returnToMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMenu);
            }
        });

        players = findViewById(R.id.players);
        players.setLayoutManager(new GridLayoutManager(this, 3));
        players.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();
    }
}
