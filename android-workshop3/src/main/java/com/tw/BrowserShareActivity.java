package com.tw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BrowserShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        setContentView(R.layout.browser_share);
        TextView textTwo = (TextView) findViewById(R.id.textTwo);
        textTwo.setText(intent.getStringExtra(Intent.EXTRA_TEXT));

    }
}
