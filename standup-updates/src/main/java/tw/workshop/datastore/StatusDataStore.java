package tw.workshop.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import tw.workshop.model.Status;

import static tw.workshop.datastore.StatusContentProvider.STATUS_TABLE;

public class StatusDataStore {

    private StatusUpdatesHelper statusUpdateHelper;
    private static final String DATABASE_NAME = "status_update.db";
    private static final int DATABASE_VERSION = 1;
    private final SQLiteDatabase database;
    private Context context;

    public StatusDataStore(Context context) {
        this.context = context;
        statusUpdateHelper = new StatusUpdatesHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = statusUpdateHelper.getWritableDatabase();
    }

    public void save(Status status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StatusUpdatesHelper.COLUMN_STORY_NO, status.getStoryNumber());
        contentValues.put(StatusUpdatesHelper.COLUMN_DETAILS, status.getDetails());
        contentValues.put(StatusUpdatesHelper.COLUMN_STATUS, status.getStatus());
        context.getContentResolver().insert(statusTableUri(), contentValues);
    }

    public void delete(Cursor cursor) {
        Integer columnIndex = cursor.getColumnIndex(StatusUpdatesHelper.COLUMN_ID);
        context.getContentResolver().delete(statusTableUri(), StatusUpdatesHelper.COLUMN_ID+"=?", new String[]{cursor.getString(columnIndex)});
    }

    public Cursor getStatusCursor() {
        return database.rawQuery("select * from " + STATUS_TABLE, null);
    }

    private Uri statusTableUri() {
        return Uri.parse("content://tw.workshop/status");
    }
}
