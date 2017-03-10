package com.arvention.lavish.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arvention.lavish.R;

public class InfoActivity extends AppCompatActivity {

    TextView txtTitle;
    TextView txtRating;
    TextView txtHours;

    Intent intent;
    Intent nextIntent;
    String toilet;

    CheckBox checkBidet;
    CheckBox checkFlush;
    CheckBox checkSoap;
    CheckBox checkFree;
    CheckBox checkPWD;

    RelativeLayout rl;
    TextView txtFeedback3_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        intent = getIntent();
        toilet = intent.getStringExtra("TOILET");
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(toilet);

        rl = (RelativeLayout) findViewById(R.id.relativeLayout3);
        rl.setVisibility(View.GONE);
        txtFeedback3_1 = (TextView) findViewById(R.id.txtFeedback3_1);

        txtRating = (TextView) findViewById(R.id.txtRating);
        txtHours = (TextView) findViewById(R.id.txtHours);

        checkBidet = (CheckBox) findViewById(R.id.checkBidet);
        checkFlush = (CheckBox) findViewById(R.id.checkFlush);
        checkSoap = (CheckBox) findViewById(R.id.checkSoap);
        checkFree = (CheckBox) findViewById(R.id.checkFree);
        checkPWD = (CheckBox) findViewById(R.id.checkPWD);

        setFeature();
    }

    private void setFeature() {
        if(toilet.equals("DLSU Science and Technology Complex")) {
            txtRating.setText("Rating: 4.5");
            txtHours.setText("6:00 am - 9:00 pm");
        }
        else if(toilet.equals("Jollibee")) {
            txtRating.setText("Rating: 3.2");
            txtHours.setText("24 hours");
        }
        else if(toilet.equals("Solenad 3")) {
            txtRating.setText("Rating: 4.9");
            txtHours.setText("10:00 am - 11:00 pm");
        }
        else if(toilet.equals("McDonald's")) {
            txtRating.setText("Rating: 3.0");
            txtHours.setText("24 hours");
        }
        else if(toilet.equals("KFC")) {
            txtRating.setText("Rating: 3.6");
            txtHours.setText("24 hours");
        }
        else if(toilet.equals("7-11/Caltex")) {
            txtRating.setText("Rating: 2.9");
            txtHours.setText("24 hours");
        }

        checkBidet.setChecked(true);
        checkFlush.setChecked(true);
        checkSoap.setChecked(true);
        checkFree.setChecked(true);
        checkPWD.setChecked(true);

        if(toilet.equals("McDonald's") || txtTitle.equals("KFC") || txtTitle.equals("7-11/Caltex")) {
            checkBidet.setChecked(false);
        }
        if(toilet.equals("7-11/Caltex"))
            checkSoap.setChecked(false);
    }

    public void addFeedback(View view) {
        nextIntent = new Intent(this, AddFeedbackActivity.class);
        nextIntent.putExtra("TOILET", toilet);

        startActivityForResult(nextIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(data != null) {
                //TODO: Display Rating
                String userFeedback = data.getStringExtra("FEEDBACK");
                displayData(userFeedback); //Add Rating to parameter
            }
        }
    }

    private void displayData(String userFeedback) {
        txtFeedback3_1.setText(userFeedback);
        rl.setVisibility(View.VISIBLE);
    }

    public void backToList(View view) {
        finish();
    }
}
