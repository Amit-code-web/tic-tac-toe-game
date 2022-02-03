package com.example.amit2.tictactoe;

import android.graphics.ImageFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 - yellow, 1 - red, 2 - empty
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    int winningPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0,gameActive = 1,counter=0;
    public void dropImage(View view)
    {
        ImageView image = (ImageView) view;
        int tappedTag = Integer.parseInt(image.getTag().toString());
        if(gamestate[tappedTag]==2&&gameActive==1) {

            gamestate[tappedTag] = activePlayer;
            image.setTranslationY(-1500);
            if (activePlayer == 0) {
                image.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            image.animate().rotationBy(3600).translationYBy(1500).setDuration(500);
            counter++;
            Log.i("counter",Integer.toString(counter));
            if(counter==9)
            {
                gameActive=0;
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Game Draw!!");
                textView.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < 8; i++) {
                if (gamestate[winningPositions[i][0]] == gamestate[winningPositions[i][1]] && gamestate[winningPositions[i][0]] == gamestate[winningPositions[i][2]] && gamestate[winningPositions[i][0]] != 2) {
                    //someone has won
                    String message = "";
                    if (activePlayer == 0)
                        message = "Red";
                    else
                        message = "Yellow";
                    Toast.makeText(this, message + " has won!!", Toast.LENGTH_LONG).show();
                    gameActive=0;
                    Button button = (Button) findViewById(R.id.button);
                    button.setVisibility(View.VISIBLE);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(message+" has won!!");
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view)
    {
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
        for(int i=0;i<9;i++) {
            gamestate[i] = 2;
        }
        activePlayer = 0;gameActive = 1;counter=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
