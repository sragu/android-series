package tw.workshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import tw.workshop.R;
import tw.workshop.adapter.StatusAdapter;
import tw.workshop.model.Status;

public class StatusListActivity extends RoboActivity {

    private static String TAG = "standup-updates-application";

    StatusAdapter statusAdapter;

    @InjectView(R.id.status_list)
    private ListView statusList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_details);
        statusAdapter = new StatusAdapter();
        statusList.setAdapter(statusAdapter);
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
        statusAdapter.add(status);
    }
}

