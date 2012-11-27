package com.tw;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.first_layout)
public class FirstActivity extends Activity {

    @ViewById(R.id.content_view)
    TextView content;

    @ViewById(R.id.launch_activity)
    Button launchButton;

    @AfterViews
    void updateScreenContent() {
        content.setText("Android Series Chennai - First Activity");
    }

    @Click(R.id.launch_activity)
    void goToSecondActivity() {
        Toast.makeText(this, "You clicked on the view", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("content", "Second Activity started - click to view contact");
        startActivity(intent);
    }
}
