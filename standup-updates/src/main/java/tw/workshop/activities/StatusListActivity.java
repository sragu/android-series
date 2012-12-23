package tw.workshop.activities;

import android.os.Bundle;
import android.widget.ListView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import tw.workshop.R;
import tw.workshop.adapter.StatusAdapter;

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

}

