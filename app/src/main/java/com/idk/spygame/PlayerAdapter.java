package com.idk.spygame;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    List<PlayerData> players;
    PlayerNameEntryDialog dialogBox;
    EndGameDialog dialogBox2;

    public PlayerAdapter(List<PlayerData> players, PlayerNameEntryDialog dialog, EndGameDialog dialog2) {
        this.players = players;
        dialogBox = dialog;
        dialogBox2 = dialog2;
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final Button buttonView;

        public PlayerViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            buttonView = (Button) view.findViewById(R.id.player_icon);
        }

        public Button getButtonView() {
            return buttonView;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.game_cell, viewGroup, false);
        return new PlayerViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PlayerViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getButtonView().setText(players.get(position).playerName);
        viewHolder.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameData.gameMode == 0){
                    if (GameData.playerData.get(position).isWordViewed == false) {
                        dialogBox.showDialog(position);
                    }
                } else if(GameData.gameMode == 1){
                    if(!GameData.playerData.get(position).isSpy){
                        viewHolder.getButtonView().setText("CIVILIAN");
                        viewHolder.getButtonView().setTextColor(Color.parseColor("#fcba03"));
                        GameData.civiliansLeft--;
                    } else {
                        viewHolder.getButtonView().setText("SPY");
                        viewHolder.getButtonView().setTextColor(Color.parseColor("#ff0000"));
                        GameData.spiesLeft--;
                    }

                    if(GameData.spiesLeft == 0){
                        dialogBox2.showDialog("Civilians have won!");
                        GameData.gameMode = 2;
                    } else if(GameData.spiesLeft >= GameData.civiliansLeft){
                        dialogBox2.showDialog("Spies have won!");
                        GameData.gameMode = 2;
                    }
                }

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return players.size();
    }

}
