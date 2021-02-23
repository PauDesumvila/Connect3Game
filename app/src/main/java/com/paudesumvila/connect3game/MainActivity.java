package com.paudesumvila.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 for blue, 1 for red

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,};  // 2 for empty

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // horizontals
                                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // verticals
                                {0, 4, 8}, {2, 4, 6}};            // diagonals

    boolean gameActive = true;


    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue_checker);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_checker);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(1200);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(View.VISIBLE);

                    String winner = "";

                    if (activePlayer == 1) winner = "Blue";
                    else winner = "Red";

                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_LONG).show();
                }
            }

            if(gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 &&
               gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 &&
               gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2){

                gameActive = false;

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);

                Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void playAgain(View view){

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout myGridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < myGridLayout.getChildCount(); i++){
            ImageView counter = (ImageView)myGridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}