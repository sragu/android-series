package tw.workshop.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import tw.workshop.R;
import tw.workshop.adapter.StatusAdapter;

public class StatusListActivity extends Activity {

    private static String TAG = "standup-updates-application";

    StatusAdapter statusAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_details);
    }

}

