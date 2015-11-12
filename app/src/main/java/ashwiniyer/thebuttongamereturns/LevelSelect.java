package ashwiniyer.thebuttongamereturns;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class LevelSelect extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        //Defining all 4 buttons
        TextView easy = (TextView) findViewById(R.id.easyMode);

        TextView medium = (TextView) findViewById(R.id.mediumMode);

        TextView hard = (TextView) findViewById(R.id.hardMode);

        TextView extreme = (TextView) findViewById(R.id.extremeMode);

        //Receiving the game mode information from the previous activity
        Bundle extras =getIntent().getExtras();
        final String gameMode = extras.getString("GameMode");

        //The final integer gameMode to be added to the DB
        final int[] gameModeInt = {0};

        //Setting up the instruction textview to display the appropriate message based on the gamemode
        TextView description = (TextView) findViewById(R.id.descriptionTextView);
        //if it is the timed mode, we want to provide the correct description
        if(Objects.equals(gameMode,"timed"))
            description.setText("Score as many points in the time provided!");
        else{
            description.setText("See how quickly you can score a set number of point!");
            easy.setText("25 points!");
            medium.setText("50 points!");
            hard.setText("200 points!");
            extreme.setText("1000 points!");
        }



        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = 60;
                Intent intent = new Intent(LevelSelect.this,MainActivity.class);
                intent.putExtra("Time", time);
                if(Objects.equals(gameMode,"timed")){
                    gameModeInt[0] = 5;
                }
                else{
                    gameModeInt[0]=1;
                }
                intent.putExtra("GameMode",gameModeInt[0]);
                startActivity(intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = 30;
                Intent intent = new Intent(LevelSelect.this,MainActivity.class);
                intent.putExtra("Time", time);
                if(Objects.equals(gameMode,"timed")){
                    gameModeInt[0] = 6;
                }
                else{
                    gameModeInt[0]=2;
                }
                intent.putExtra("GameMode",gameModeInt[0]);
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = 10;
                Intent intent = new Intent(LevelSelect.this, MainActivity.class);
                intent.putExtra("Time", time);
                if(Objects.equals(gameMode,"timed")){
                    gameModeInt[0] = 7;
                }
                else{
                    gameModeInt[0]=3;
                }
                intent.putExtra("GameMode",gameModeInt[0]);
                startActivity(intent);
            }
        });

        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = 3;
                Intent intent = new Intent(LevelSelect.this, MainActivity.class);
                intent.putExtra("Time", time);
                if(Objects.equals(gameMode,"timed")){
                    gameModeInt[0] = 8;
                }
                else{
                    gameModeInt[0]=4;
                }
                intent.putExtra("GameMode",gameModeInt[0]);
                startActivity(intent);
            }
        });
    }

}
