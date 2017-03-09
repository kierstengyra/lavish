package com.lavishinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayAllToiletsActivity extends AppCompatActivity {

    LinearLayout linear1, linear2, linear3;
    LinearLayout linear4, linear5, linear6;
    TextView txtTitle;
    ImageButton imageButton;

    String feature;
    Intent intent;

    Intent nextIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_toilets);

        intent = getIntent();
        feature = intent.getStringExtra("FEATURE");

        linear1 = (LinearLayout) findViewById(R.id.layout1);
        linear2 = (LinearLayout) findViewById(R.id.layout2);
        linear3 = (LinearLayout) findViewById(R.id.layout3);
        linear4 = (LinearLayout) findViewById(R.id.layout4);
        linear5 = (LinearLayout) findViewById(R.id.layout5);
        linear6 = (LinearLayout) findViewById(R.id.layout6);

        switch(feature) {
            case "With Bidet": linear4.setVisibility(View.GONE);
                               linear5.setVisibility(View.GONE);
                               linear6.setVisibility(View.GONE);
                               break;
            case "With Soap": linear6.setVisibility(View.GONE);
                              break;
            default: linear1.setVisibility(View.VISIBLE);
                     linear2.setVisibility(View.VISIBLE);
                     linear3.setVisibility(View.VISIBLE);
                     linear4.setVisibility(View.VISIBLE);
                     linear5.setVisibility(View.VISIBLE);
                     linear6.setVisibility(View.VISIBLE);
                     break;
        }

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(feature);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
    }

    public void backToList(View view) {
        finish();
    }

    public void viewInfo(View view) {
        nextIntent = new Intent(this, InfoActivity.class);
        int id = view.getId();

        if(id == R.id.relLayout1)
            nextIntent.putExtra("TOILET", "DLSU Science and Technology Complex");
        else if(id == R.id.relLayout2)
            nextIntent.putExtra("TOILET", "Jollibee");
        else if(id == R.id.relLayout3)
            nextIntent.putExtra("TOILET", "Solenad 3");
        else if(id == R.id.relLayout4)
            nextIntent.putExtra("TOILET", "McDonald's");
        else if(id == R.id.relLayout5)
            nextIntent.putExtra("TOILET", "KFC");
        else if(id == R.id.relLayout6)
            nextIntent.putExtra("TOILET", "7-11/Caltex");

        startActivity(nextIntent);
    }
}
