package tw.workshop.datastore;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import roboguice.content.RoboContentProvider;

import java.util.HashMap;
import java.util.Map;

public class StatusContentProvider extends RoboContentProvider {
    public static final String STATUS_TABLE = "status";
    private final UriMatcher uriMatcher;
    public static final String AUTHORITY = "tw.workshop";

    private static final String DATABASE_NAME = "status_update.db";
    private static final int DATABASE_VERSION = 1;

    private Map<Integer, String> tables = new HashMap<Integer, String>();
    private Context context;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        super.onCreate();
        context = getContext();

        StatusUpdatesHelper statusUpdatesHelper = new StatusUpdatesHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = statusUpdatesHelper.getWritableDatabase();

        return true;
    }

    public StatusContentProvider() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


        uriMatcher.addURI(AUTHORITY, STATUS_TABLE, 1);
        tables.put(1, "status");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final ContentResolver resolver = getContext().getContentResolver();
        Cursor cursor = database.query(getTableName(uri), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(resolver, uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long insertedId = database.insert(getTableName(uri), null, values);
        Uri uriToNotify = Uri.withAppendedPath(uri, String.valueOf(insertedId));
        getContext().getContentResolver().notifyChange(uriToNotify, null);
        return uriToNotify;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletedRecords = database.delete(getTableName(uri), selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedRecords;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
       return  database.update(getTableName(uri),values, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir.Status";
    }

    public String getTableName(Uri uri) {
        return tables.get(uriMatcher.match(uri));
    }
}
