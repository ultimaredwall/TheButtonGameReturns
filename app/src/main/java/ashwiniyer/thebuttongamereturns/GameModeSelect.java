package ashwiniyer.thebuttongamereturns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GameModeSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_select);

        //Setting the two textviews to be buttons
        TextView timed = (TextView) findViewById(R.id.timedModeTextView);
        TextView survival = (TextView) findViewById(R.id.survivalModeTextView);

        //String gameMode to pass through to the next activity
        final String[] gameMode = {""};

        timed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMode[0] ="timed";
                Intent intent  = new Intent(GameModeSelect.this,LevelSelect.class);
                intent.putExtra("GameMode",gameMode[0]);
                startActivity(intent);
            }
        });

        survival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMode[0]="survival";
                Intent intent = new Intent(GameModeSelect.this,LevelSelect.class);
                intent.putExtra("GameMode",gameMode[0]);
                startActivity(intent);
            }
        });


    }

}
