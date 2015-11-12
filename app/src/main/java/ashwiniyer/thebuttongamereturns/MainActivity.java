package ashwiniyer.thebuttongamereturns;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Colours mColours = new Colours();
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras =getIntent().getExtras();
        final int  time = extras.getInt("Time");
        final int  gameMode = extras.getInt("GameMode");

        //Importing the relative layout into java
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        //counter variable to keep track of score
        final int[] counter = {0};
        //Importing the textview that will show the score as we tap!
        final TextView[] tv = {(TextView) findViewById(R.id.scoreTextView)};
        final Intent intent = new Intent(MainActivity.this, HighScores.class);





        //gameModes 5 through 8 are for the Timed Mode
        if(gameMode>4) {
            //Setting up the countdown timer
            new CountDownTimer(time * 1000, 1000) {
                TextView timer = (TextView) findViewById(R.id.timerTextView);

                public void onTick(long millisUntilFinished) {
                    timer.setText("" + millisUntilFinished / 1000);
                    if ((millisUntilFinished / 1000) < 11) {
                        timer.setTextColor(Color.parseColor("#FF0000"));
                        timer.setTextSize(85);
                    }

                }

                public void onFinish() {
                    timer.setText("Time's Up! :(");
                    tv[0].setText("Game Over! Your final score was " + counter[0] + "!!");
                    tv[0].setTextSize(40);

                    String score = ""+counter[0];
                    alert(score, intent, gameMode);

                }
            }.start();
        }



        rl.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                counter[0] = counter[0] + 1;
                tv[0].setText("" + counter[0]);
                //Change background colour of relative layout every 25 points
                if (counter[0] % 25 == 0) {
                    int Colour = mColours.getColour();
                    rl.setBackgroundColor(Colour);
                }
                //gameMode 1 to 4 are for survival mode


                if (gameMode<5) {

                    //starting the clock
                    Chronometer timee = (Chronometer) findViewById(R.id.chronometer);
                    timee.setVisibility(View.VISIBLE);
                    timee.start();

                    //25 point mode
                    if (Objects.equals(time, 60) && counter[0] == 25) {//try >25 instead of ==25, to see if it fixes the going over issue
                        //Display game over and time that it took
                        timee.stop();
                        //stop the onclick listener
                        alert(timee.getText().toString(), intent, gameMode);
                    }

                    //50 points mode
                    if (Objects.equals(time, 30) && counter[0] == 50) {
                        //Display game over and time that it took
                        timee.stop();
                        alert(timee.getText().toString(), intent,gameMode);
                    }

                    if (Objects.equals(time, 10) && counter[0] == 200) {
                        //Display game over and time that it took
                        timee.stop();
                        alert(timee.getText().toString(), intent, gameMode);
                    }

                    if (Objects.equals(time, 3) && counter[0] == 1000) {
                        //Display game over and time that it took
                        timee.stop();
                        alert(timee.getText().toString(),intent,gameMode);
                    }
                }

            }
        });
    }

    private void alert(final String score, final Intent intent, final int gameMode) {
        //Setting up the alert dialog for end of game
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        final EditText editText = new EditText(this);
        alertDialogBuilder.setMessage("Game over! Your final score was " + score + "! Please enter your name to save your score:");

        alertDialogBuilder.setView(editText);
        alertDialogBuilder.setCancelable(false);


        alertDialogBuilder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Editable name_Get = editText.getText();

                String name = name_Get.toString();
                //Toast.makeText(MainActivity.this, "Starting new Game " + name, Toast.LENGTH_LONG).show();
                intent.putExtra("namee", name);
                intent.putExtra("score", score);
                intent.putExtra("GameMode", gameMode);
                startActivity(intent);

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();//still testing the alert boxes - not fully implemented yet

    }


}
