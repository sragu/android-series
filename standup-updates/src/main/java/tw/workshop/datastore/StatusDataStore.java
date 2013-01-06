package tw.workshop.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import tw.workshop.model.Status;

public class StatusDataStore {

    private StatusUpdatesHelper statusUpdatehelper;
    private static final String DATABASE_NAME = "status_update.db";
    private static final int DATABASE_VERSION = 1;
    private final SQLiteDatabase database;

    public StatusDataStore(Context context) {
        statusUpdatehelper = new StatusUpdatesHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = statusUpdatehelper.getWritableDatabase();
    }

    public void save(Status status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StatusUpdatesHelper.COLUMN_STORY_NO, status.getStoryNumber());
        contentValues.put(StatusUpdatesHelper.COLUMN_DETAILS, status.getDetails());
        contentValues.put(StatusUpdatesHelper.COLUMN_STATUS, status.getStatus());
        database.insert(StatusUpdatesHelper.TABLE_NAME, null, contentValues);
    }

    public void delete(Cursor cursor) {
        Integer columnIndex = cursor.getColumnIndex(StatusUpdatesHelper.COLUMN_ID);
        database.delete(StatusUpdatesHelper.TABLE_NAME, StatusUpdatesHelper.COLUMN_ID+ "=?", new String[]{cursor.getString(columnIndex)});
    }

    public Cursor getStatusCursor() {
        return database.rawQuery("select * from " + StatusUpdatesHelper.TABLE_NAME, null);
    }
}
