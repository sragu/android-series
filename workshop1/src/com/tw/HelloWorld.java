package com.tw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWorld extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView content = (TextView) findViewById(R.id.content_view);
        content.setText(" Android Series Chennai ");

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HelloWorld.this, "You clicked on the view", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HelloWorld.this, SecondActivity.class);
                intent.putExtra("content", "show this on the second screen");
                startActivity(intent);
            }
        });

    }
}
