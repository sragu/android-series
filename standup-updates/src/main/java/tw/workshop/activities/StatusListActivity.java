package tw.workshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import android.widget.Toast;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import tw.workshop.R;
import tw.workshop.adapter.StatusAdapter;
import tw.workshop.datastore.StatusDataStore;
import tw.workshop.model.Status;

public class StatusListActivity extends RoboActivity implements StatusCallback {

    private static String TAG = "standup-updates-application";

    StatusAdapter statusAdapter;
    @Inject
    StatusDataStore statusDataStore;

    @InjectView(R.id.status_list)
    private ListView statusList;

    private SaveStatusTask saveStatusTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_details);
        statusAdapter = new StatusAdapter(this, statusDataStore.getAllStatus());
        statusList.setAdapter(statusAdapter);
        statusList.setLongClickable(true);
        registerForContextMenu(statusList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("some");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.context_menu, menu);
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
        saveStatusTask = new SaveStatusTask(this,this,statusDataStore,status);
        saveStatusTask.execute();
        //statusDataStore.saveStatus(status);

    }

    @Override
    public void onFailure(String reason) {
        Toast.makeText(this, reason, 30);
    }

    @Override
    public void onSuccess(String message) {
        statusAdapter.changeCursor(statusDataStore.getAllStatus());
        Toast.makeText(this, message, 1000*10).show();
    }
}

