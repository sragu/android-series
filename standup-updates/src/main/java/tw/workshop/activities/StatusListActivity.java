package tw.workshop.activities;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import tw.workshop.R;
import tw.workshop.adapter.StatusAdapter;
import tw.workshop.datastore.StatusDataStore;
import tw.workshop.model.Status;

import static tw.workshop.datastore.StatusUpdatesHelper.*;

public class StatusListActivity extends RoboActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static String TAG = "standup-updates-application";
    private static final Integer SAVE_REQUEST_CODE = 3;
    private static final Integer EDIT_REQUEST_CODE = 4;
    private static final int LOADER_ID = 1;

    StatusAdapter statusAdapter;

    @InjectView(R.id.status_list)
    private ListView statusList;
    private StatusDataStore statusDataStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_details);
        statusDataStore = new StatusDataStore(this);
        statusAdapter = new StatusAdapter(this);
        statusList.setAdapter(statusAdapter);
        registerForContextMenu(statusList);
        getLoaderManager().initLoader(LOADER_ID, null, this);

        getContentResolver().registerContentObserver(StatusDataStore.STATUS_TABLE_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                Toast.makeText(StatusListActivity.this, "Observer notified", 2000).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, AddStatusActivity.class);
        startActivityForResult(intent, 3);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Status status = (Status) data.getExtras().get("new_status_item");
        if (SAVE_REQUEST_CODE == requestCode) {
            statusDataStore.save(status);
            Toast.makeText(this, getString(R.string.saved_successfully), 10 * 1000).show();
        } else if (EDIT_REQUEST_CODE == requestCode) {
            statusDataStore.update(status);
            Toast.makeText(this, getString(R.string.modified_successfully), 10 * 1000).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Status Config");
        getMenuInflater().inflate(R.layout.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer position = menuInfo.position;
        Cursor statusCursor = (Cursor) statusAdapter.getItem(position);
        if (item.getTitle().equals(getString(R.string.delete))) {
            deleteStatus(statusCursor);
        } else if (item.getTitle().equals(getString(R.string.edit))) {
            editStatus(statusCursor);
        }
        return true;

    }

    private void editStatus(Cursor statusCursor) {
        Intent intent = new Intent(this, AddStatusActivity.class);
        Bundle extras = new Bundle();
        extras.putString(COLUMN_STORY_NO, statusCursor.getString(statusCursor.getColumnIndex(COLUMN_STORY_NO)));
        extras.putString(COLUMN_DETAILS, statusCursor.getString(statusCursor.getColumnIndex(COLUMN_DETAILS)));
        extras.putString(COLUMN_STATUS, statusCursor.getString(statusCursor.getColumnIndex(COLUMN_STATUS)));
        extras.putString(COLUMN_ID, statusCursor.getString(statusCursor.getColumnIndex(COLUMN_STATUS)));
        intent.putExtras(extras);
        startActivityForResult(intent, 4);
    }

    private void deleteStatus(Cursor statusCursor) {
        statusDataStore.delete(statusCursor);
        Toast.makeText(this, getString(R.string.deleted_successfully), 10 * 1000).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return getStatusCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        statusAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        statusAdapter.swapCursor(null);
    }

    private CursorLoader getStatusCursorLoader() {
        return new CursorLoader(this, StatusDataStore.STATUS_TABLE_URI, new String[]{"_id", "story_number", "story_details", "story_status"}, "", new String[]{}, "");
    }
}
