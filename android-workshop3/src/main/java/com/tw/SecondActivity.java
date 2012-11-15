package com.tw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = getIntent();
        String value = intent.getExtras().getString("content");

        TextView textView = (TextView) findViewById(R.id.content_view);
        textView.setText(value);
    }
}