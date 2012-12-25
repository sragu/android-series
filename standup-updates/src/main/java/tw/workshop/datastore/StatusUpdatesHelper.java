package tw.workshop.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatusUpdatesHelper extends SQLiteOpenHelper{

    private SQLiteDatabase sqLiteDatabase;
    public static final String TABLE_NAME = "status";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STORY_NO = "story_number";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_STATUS = "status";

    public StatusUpdatesHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_STORY_NO + " TEXT, "
                + COLUMN_DETAILS + " TEXT, "
                + COLUMN_STATUS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + "");
        onCreate(database);

    }



}
