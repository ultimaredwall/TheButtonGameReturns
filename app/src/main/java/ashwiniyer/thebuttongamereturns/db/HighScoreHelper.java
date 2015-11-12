package ashwiniyer.thebuttongamereturns.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ashwin on 29/10/2015.
 */
public class HighScoreHelper extends SQLiteOpenHelper {

    public static final String TABLE_HIGHSCORES= "HIGHSCORES";
    public static final String COLUMN_ID= "_ID";
    public static final String COLUMN_NAME= "NAME";
    public static final String COLUMN_SCORE= "SCORE";
    public static final String COLUMN_MODE= "MODE";
    public static final String COLUMN_PLACE= "PLACE";

    private static final String DB_NAME = "highscores.db";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE =
            "CREATE TABLE " +TABLE_HIGHSCORES + " (" +COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME +"  VARCHAR(25)," + COLUMN_SCORE + "  VARCHAR(10)," + COLUMN_MODE + " INT)";
    private static final String DB_ALTER =
            "ALTER TABLE " + TABLE_HIGHSCORES +"  ADD COLUMN " +COLUMN_PLACE+ " INTEGER";



    public HighScoreHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_ALTER);
    }
}
