package com.tw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent intent = getIntent();
        String value = intent.getExtras().getString("content");

        TextView textView = (TextView) findViewById(R.id.contact_content_view);
        textView.setText(value);
        Button launchContactButton = (Button) findViewById(R.id.launch_contacts);

        launchContactButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactIntent = new Intent(Intent.ACTION_VIEW,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivity(contactIntent);
            }
        });

    }
}