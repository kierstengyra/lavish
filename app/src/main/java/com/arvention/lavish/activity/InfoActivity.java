package com.lavishinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView txtTitle;

    Intent intent;
    String toilet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        intent = getIntent();
        toilet = intent.getStringExtra("TOILET");
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(toilet);
    }

    public void backToList(View view) {
        finish();
    }
}
