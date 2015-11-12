package ashwiniyer.thebuttongamereturns;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;

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

        //Update the Listview with the scores
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                mHighScores);
//        setListAdapter(adapter);


        HighScoreAdapter adapter = new HighScoreAdapter(this, scores);
        setListAdapter(adapter);

        mHighScoreDataSource.close();

        //setting up writing to internal storage file
//        String FILENAME = "highscores";


        //Check gameMode to pull appropriate highscores data file

        //Just reading in scores for now and displaying as a toast message

//            Scores readscore= readFromFile(FILENAME);
//
//        if(readscore.getName()!="") {
//            Toast.makeText(HighScores.this, "Previous highscore by " + readscore.getName() + "was " + readscore.getScore(), Toast.LENGTH_LONG).show();
//        }


//
//        writetoFile(score,name, FILENAME);


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
    protected void onPause(){
        super.onPause();
        //Close connection to the database
        mHighScoreDataSource.close();
    }

//    private Scores readFromFile(String FILENAME) {
//        String ret = "";
//        Scores score=new Scores();
//
//        try{
//            InputStream fin = openFileInput(FILENAME);
//
//            if(fin != null) {
//                InputStreamReader reader = new InputStreamReader(fin);
//                BufferedReader bufferedReader = new BufferedReader(reader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ((receiveString = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(receiveString).append("\n");
//                }
//                fin.close();
//                ret = stringBuilder.toString();
//                //Parsing the various parts of the score - name and score
//
//                if (ret != "") {
//                    String[] temp;
//                    String delim = "[$]";
//                    temp = ret.split(delim);
//
//                    score.setName(temp[0]);
//                    score.setScore(temp[1]);
//                }
//                else{
//                    //setting score with flags to indicate empty
//                    score.setName("");
//                    score.setScore("");
//                }
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return score;
//    }
//
//    private void writetoFile(String score, String name, String FILENAME) {
//        try {
//            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//            fos.write((name+"$"+score+"\n").getBytes());
//            //fos.write("".getBytes());
//            fos.close();
//        } catch (FileNotFoundException e) {
//            Log.e("Exception","File not found: "+ e.toString());
//        } catch (IOException e) {
//            Log.e("Exception","Write to file failed: "+e.toString());
//        }
//    }


}
