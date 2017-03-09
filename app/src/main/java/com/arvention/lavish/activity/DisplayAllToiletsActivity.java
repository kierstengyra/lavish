package com.lavishinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DisplayAllToiletsActivity extends AppCompatActivity {

    TextView txtTitle;

    String feature;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_toilets);

        intent = getIntent();
        feature = intent.getStringExtra("FEATURE");

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(feature);
    }

    public void backToList(View view) {
        finish();
    }
}
