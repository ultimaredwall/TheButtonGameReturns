package ashwiniyer.thebuttongamereturns;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.sql.SQLException;
import java.util.ArrayList;

import ashwiniyer.thebuttongamereturns.Adapters.HighScoreAdapter;
import ashwiniyer.thebuttongamereturns.db.HighScoreDataSource;
import ashwiniyer.thebuttongamereturns.db.HighScoreHelper;

public class HighScores extends ListActivity {

    //Setting up the DB
    protected HighScoreDataSource mHighScoreDataSource;
    protected ArrayList<String> mHighScoresName;
    protected ArrayList<String> mHighScoresScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);


        //Start by pulling the extras passed in from the previous activity
        Bundle extras = getIntent().getExtras();

        String name = extras.getString("namee");
        String score = extras.getString("score");
        int gameMode = extras.getInt("GameMode");


        //Creating a new Scores object  for all the data to be stored in the DB
        Scores score_db = new Scores();
        score_db.setName(name);
        score_db.setScore(score);
        score_db.setMode(gameMode);

        //Creating the data source object
        mHighScoreDataSource = new HighScoreDataSource(HighScores.this);

        //Open connection to the database
        try {
            mHighScoreDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Inserting the new highscore into the DB
        mHighScoreDataSource.insertHighscore(score_db);

        //Pulling all the highscores for the current mode from the DB
        Cursor cursor = mHighScoreDataSource.selectAllHighScores(gameMode);

        //iterate through the table with the cursor
        mHighScoresName = new ArrayList<String>();
        mHighScoresScore = new ArrayList<String>();
        cursor.moveToFirst();

        int k = 0;
        int i;
        while (!cursor.isAfterLast()) {
            //do stuff
            //index variables

            i = cursor.getColumnIndex(HighScoreHelper.COLUMN_NAME);
            int j = cursor.getColumnIndex(HighScoreHelper.COLUMN_SCORE);
            String highscore_name;
            if(cursor.getString(i).length()<3){
                highscore_name = cursor.getString(i).toUpperCase();
            }
            else {
                highscore_name = cursor.getString(i).substring(0, 2).toUpperCase();
            }
            String highscore_score = cursor.getString(j);
            mHighScoresName.add(highscore_name);
            mHighScoresScore.add(highscore_score);
            k++;
            cursor.moveToNext();
        }
        int length = mHighScoresName.size();

        //creating and populating an array of Scores to be passed into out HighScoreAdapter
        Scores[] scores = new Scores[length];

        for (i = 0; i < length; i++) {
            scores[i] = new Scores();
            scores[i].setPlace(""+(i+1)+".");
            scores[i].setScore(mHighScoresScore.get(i));
            scores[i].setName(mHighScoresName.get(i));
        }


        HighScoreAdapter adapter = new HighScoreAdapter(this, scores);
        setListAdapter(adapter);

        mHighScoreDataSource.close();

        //Finally allow user to go back to the mode select screen

        //On tap, want the activity to close and launch the mode select activity
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScores.this,GameModeSelect.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onResume(){
        super.onResume();
        //Open connection to the database
        try {
            mHighScoreDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close connection to the database
        mHighScoreDataSource.close();
    }


}
