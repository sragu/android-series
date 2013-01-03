package tw.workshop.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import tw.workshop.model.Status;

public class StatusDatastore {

    private final StatusUpdatesHelper statusUpdatesHelper;
    private static final String DATABASE_NAME = "status_update.db";
    private static final int DATABASE_VERSION = 1;
    private final SQLiteDatabase database;

    public StatusDatastore(Context context) {
        statusUpdatesHelper = new StatusUpdatesHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = statusUpdatesHelper.getWritableDatabase();
    }


    public void save(Status status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StatusUpdatesHelper.COLUMN_STATUS, status.getStatus());
        contentValues.put(StatusUpdatesHelper.COLUMN_DETAILS, status.getDetails());
        contentValues.put(StatusUpdatesHelper.COLUMN_STORY_NO, status.getStoryNumber());
        database.insert(StatusUpdatesHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor getStatusCursor() {
        return database.rawQuery("select * from "+ StatusUpdatesHelper.TABLE_NAME , null);
    }
}
