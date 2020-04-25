package com.technocrats.tictaetoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    boolean gameisActive = true;
    int activePlayer = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int WinnerChoice[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void choiceSelection(View view) {
        ImageView imageview = (ImageView) view;

        System.out.println(imageview.getTag().toString());
        int tappedchoice = Integer.parseInt(imageview.getTag().toString());

        if (gameState[tappedchoice] == 2 && gameisActive) {

            gameState[tappedchoice] = activePlayer;
            //System.out.println("Value of gamestate :"+gameState[tappedchoice]);

            imageview.setTranslationY(-1000f);
            if (activePlayer == 0) {
                imageview.setImageResource(R.drawable.oimage);
                activePlayer = 1;
            } else {
                imageview.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }

            imageview.animate().translationYBy(1000f).rotationXBy(360).alpha(1);

            for (int winpos[] : WinnerChoice) {

                String winnermsg = "";
                if (gameState[winpos[0]] == gameState[winpos[1]] &&
                        gameState[winpos[1]] == gameState[winpos[2]] &&
                        gameState[winpos[0]] != 2) {

                    gameisActive = false;

                    if (gameState[winpos[0]] == 1) {
                        winnermsg = "Red";
                    }
                    if (gameState[winpos[0]] == 0) {
                        winnermsg = "Yellow";
                    }

                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
                    layout.setVisibility(view.VISIBLE);

                    TextView win = (TextView) findViewById(R.id.textView2);
                    win.setText("You Win " + winnermsg);

                } else {

                    boolean gameIsOver = true;
                    for (int i : gameState) {
                        if (i == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {

                        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
                        layout.setVisibility(view.VISIBLE);

                        TextView win = (TextView) findViewById(R.id.textView2);
                        win.setText("Match is Draw");
                    }


                }

            }

        }

    }// end of if == 2
    public void playAgain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
        layout.setVisibility(view.INVISIBLE);

        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout1 = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout1.getChildCount(); i++) {
            ((ImageView) gridLayout1.getChildAt(i)).setImageResource(0);
        }

    }


}