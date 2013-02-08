package tw.workshop.datastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import tw.workshop.model.Status;

public class StatusDataStore {

    public static final Uri STATUS_TABLE_URI = Uri.parse("content://tw.workshop/status");
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
        context.getContentResolver().insert(STATUS_TABLE_URI, status.getContentValues());
    }

    public void delete(Cursor cursor) {
        Integer columnIndex = cursor.getColumnIndex(StatusUpdatesHelper.COLUMN_ID);
        context.getContentResolver().delete(STATUS_TABLE_URI, StatusUpdatesHelper.COLUMN_ID+"=?", new String[]{cursor.getString(columnIndex)});
    }

    public void update(Status status) {
        context.getContentResolver().update(STATUS_TABLE_URI, status.getContentValues(), StatusUpdatesHelper.COLUMN_STORY_NO+"=?", new String[]{status.getStoryNumber()});
    }
}
