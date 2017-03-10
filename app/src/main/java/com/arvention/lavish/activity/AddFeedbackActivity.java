package com.arvention.lavish.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arvention.lavish.R;

public class AddFeedbackActivity extends AppCompatActivity {

    TextView txtTitle;
    EditText feedback;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        feedback = (EditText) findViewById(R.id.editTextFeedback);
    }

    public void submitFeedback(View view) {
        //TODO: Rating

        String userFeedback = feedback.getText().toString();
        intent = new Intent();
        intent.putExtra("FEEDBACK", userFeedback);
        setResult(1, intent);

        finish();
    }
}
