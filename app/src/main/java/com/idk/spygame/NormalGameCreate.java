package com.idk.spygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.ArrayList;

public class NormalGameCreate extends AppCompatActivity {

    Button playerAdd;
    Button playerSubtract;
    Button spyAdd;
    Button spySubtract;
    Button startGame;
    TextView playerNumber;
    TextView spyNumber;
    SharedPreferences prefs;
    ArrayList<Pair<String, String>> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* try {
            InputStream file = getResources().getAssets().open("<my file name from assets folder");
            // read the file!
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        setContentView(R.layout.normal_game_create);
        prefs = this.getSharedPreferences("spy_game_prefs", Context.MODE_PRIVATE);

        playerAdd = findViewById(R.id.player_add);
        playerSubtract = findViewById(R.id.player_subtract);
        spyAdd = findViewById(R.id.spy_add);
        spySubtract = findViewById(R.id.spy_subtract);
        startGame = findViewById(R.id.start_game);
        playerNumber = findViewById(R.id.player_number);
        spyNumber = findViewById(R.id.spy_number);
        playerNumber.setText(Integer.toString(prefs.getInt("playerCount", 3)));
        spyNumber.setText(Integer.toString(prefs.getInt("spyCount", 1)));
        createList();


        playerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int playerNum = Integer.parseInt((String) playerNumber.getText());
                if(playerNum != 10){
                    playerNum++;
                    playerNumber.setText(Integer.toString(playerNum));
                }
            }
        });

        playerSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int playerNum = Integer.parseInt((String) playerNumber.getText());
                if(playerNum != 0){
                    playerNum--;
                    playerNumber.setText(Integer.toString(playerNum));
                }
            }
        });

        spyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int playerNum = Integer.parseInt((String) playerNumber.getText());
                int spyNum = Integer.parseInt((String) spyNumber.getText());
                if(spyNum < playerNum/3){
                    spyNum ++;
                    spyNumber.setText(Integer.toString(spyNum));
                }
            }
        });

        spySubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spyNum = Integer.parseInt((String) spyNumber.getText());
                if(spyNum != 1){
                    spyNum --;
                    spyNumber.setText(Integer.toString(spyNum));
                }
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int playerCount = Integer.parseInt((String) playerNumber.getText());
                int spyCount = Integer.parseInt((String) spyNumber.getText());
                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt("playerCount", playerCount);
                edit.putInt("spyCount", spyCount);
                edit.apply();

                GameData.spiesLeft = spyCount;
                GameData.civiliansLeft = playerCount-spyCount;

                for(int i = 0 ; i < playerCount; i ++) {
                    GameData.playerData.add(new PlayerData());
                }

                ArrayList<Integer> alreadySpy = new ArrayList<Integer>();

                for(int i = 0; i<spyCount; i++){
                    while(true){
                        int index = (int) (Math.random()*playerCount);
                        if(!alreadySpy.contains(index)){
                            alreadySpy.add(index);
                            GameData.playerData.get(index).isSpy = true;
                            break;
                        }
                    }
                }

                int pairNumber = (int) (Math.random()*words.size());
                int random = (int) (Math.random()*2);

                if(random == 0){
                    GameData.spyWord = words.get(pairNumber).first;
                    GameData.civilianWord = words.get(pairNumber).second;
                } else {
                    GameData.spyWord = words.get(pairNumber).second;
                    GameData.civilianWord = words.get(pairNumber).first;
                }

                Intent playGameIntent = new Intent(getApplicationContext(), GamePlay.class);
                startActivity(playGameIntent);
            }
        });
    }

    public void createList(){
        words = new ArrayList<Pair<String, String>>();
        words.add(new Pair<String, String>("Fly", "Mosquito"));
        words.add(new Pair<String, String>("Apple", "Orange"));
        words.add(new Pair<String, String>("Bed", "Sofa"));
        words.add(new Pair<String, String>("Juice", "Soda"));
        words.add(new Pair<String, String>("Oven", "Microwave"));
        words.add(new Pair<String, String>("Socks", "Gloves"));
        words.add(new Pair<String, String>("Noodles", "Rice"));
        words.add(new Pair<String, String>("Chinese New Year", "Mid-Autumn Festival"));
        words.add(new Pair<String, String>("Fire", "Stove"));
        words.add(new Pair<String, String>("iPhone", "Google Pixel"));
        words.add(new Pair<String, String>("Bowl", "Plate"));
    }
}
