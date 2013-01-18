package tw.workshop.datastore;

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

    @Override
    public boolean onCreate() {
        super.onCreate();
        context = getContext();
        return true;
    }

    public StatusContentProvider() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


        uriMatcher.addURI(AUTHORITY, STATUS_TABLE, 1);
        tables.put(1, "status");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        throw new RuntimeException("Not yet supported");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        database().insert(getTableName(uri), null, values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database().delete(getTableName(uri) , selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new RuntimeException("Not yet supported");
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir.Status";
    }

    public String getTableName(Uri uri) {
        return tables.get(uriMatcher.match(uri));
    }

    public SQLiteDatabase database() {
        StatusUpdatesHelper statusUpdatesHelper = new StatusUpdatesHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        return statusUpdatesHelper.getWritableDatabase();
    }
}
