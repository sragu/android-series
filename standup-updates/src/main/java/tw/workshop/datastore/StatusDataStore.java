package tw.workshop.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import tw.workshop.model.Status;

import static tw.workshop.datastore.StatusUpdatesHelper.*;

public class StatusDataStore {

    private StatusUpdatesHelper statusUpdateHelper;
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "status_updates.db";
    private SQLiteDatabase database;

    public StatusDataStore(Context context) {
        statusUpdateHelper = new StatusUpdatesHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = statusUpdateHelper.getWritableDatabase();
    }

    public void saveStatus(Status status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STORY_NO, status.getStoryNumber());
        contentValues.put(COLUMN_STATUS, status.getStatus());
        contentValues.put(COLUMN_DETAILS, status.getDetails());
        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllStatus() {
        return database.rawQuery(
                "SELECT * FROM " + TABLE_NAME,
                null);
    }
}
