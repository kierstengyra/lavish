package com.arvention.lavish.activity;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arvention.lavish.R;

public class RateActivity extends AppCompatActivity {

    private ImageButton buttonBack;
    private ImageButton buttonSave;
    private TextView header;
    private TextView textName;
    private RatingBar ratingBar;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        buttonBack = (ImageButton) findViewById(R.id.rate_back);
        buttonSave = (ImageButton) findViewById(R.id.rate_save);

        header = (TextView) findViewById(R.id.rate_toilet_name);
        textName = (TextView) findViewById(R.id.rate_name);

        ratingBar = (RatingBar) findViewById(R.id.rate_ratingBar);
        content = (EditText) findViewById(R.id.rate_contents);

        buttonBack.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
