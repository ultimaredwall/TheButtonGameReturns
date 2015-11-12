package ashwiniyer.thebuttongamereturns.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import ashwiniyer.thebuttongamereturns.Scores;

/**
 * Created by Ashwin on 29/10/2015.
 */
public class HighScoreDataSource {

    private SQLiteDatabase mDatabase;
    private HighScoreHelper mHighScoreHelper;
    private Context mContext;


    public HighScoreDataSource(Context context) {
        mContext = context;
        mHighScoreHelper = new HighScoreHelper(mContext);
    }

    //Open a database
    public void open() throws SQLException {
        mDatabase = mHighScoreHelper.getWritableDatabase();
    }

    //Close
    public void close() {
        mDatabase.close();
    }

    //Insert
    public void insertHighscore(Scores scores) {
        ContentValues values = new ContentValues();
        values.put(HighScoreHelper.COLUMN_NAME, scores.getName());
        values.put(HighScoreHelper.COLUMN_SCORE, scores.getScore());
        values.put(HighScoreHelper.COLUMN_MODE, scores.getMode());

        mDatabase.insert(HighScoreHelper.TABLE_HIGHSCORES, null, values);
    }

    //Select
    public Cursor selectAllHighScores(int gameMode) {
        String whereClause = HighScoreHelper.COLUMN_MODE + " = ?";
        String gameModeString = "" + gameMode;

        String order;
        if(gameMode <5)
            order =" ASC";
        else{
            order = "DESC";
        }

        Cursor cursor = mDatabase.query(
                HighScoreHelper.TABLE_HIGHSCORES,//table
                new String[]{HighScoreHelper.COLUMN_NAME, HighScoreHelper.COLUMN_SCORE}, //columns
                whereClause, //where clause
                new String[]{gameModeString}, //where parameters
                null, //group by
                null, //having
                HighScoreHelper.COLUMN_SCORE + order //order by
        );

        return cursor;

    }

    //Update - not necessary for our purposes since we will never need to update a highscore
    // public int updateScore(double newScore){
        //ContentValues values = new ContentValues();
        //values.put(HighScoreHelper.COLUMN_SCORE, newScore);
        //int rowsUpdated = mDatabase.update(
                //HighScoreHelper.TABLE_HIGHSCORES, //table
                //values, //values
                //null, //where clause
                //null  //where parameters
    //);
        //return rowsUpdated;
    //}


    //Delete - we will call this whenever there are more than 5 scores in a gameMode - we will only be displaying the top 5 scores in our listview for each gameMode
    public void delete(){
        mDatabase.delete(
          HighScoreHelper.TABLE_HIGHSCORES,
          null, //where clause
          null //where params

        );
    }
}
