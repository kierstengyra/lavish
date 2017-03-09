package com.arvention.lavish.activity;

import com.arvention.lavish.database.Database;

import com.arvention.lavish.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChooseFeatureActivity extends AppCompatActivity {

    String[] features = {"With Bidet", "With Flush", "With Soap", "Free", "PWD Friendly", "Cubicle Count"};
    ArrayAdapter<String> adapter;

    RelativeLayout relLayout;
    TextView txtBrowse;
    ListView listToilets;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        txtBrowse = (TextView) findViewById(R.id.txtBrowse);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, features);

        listToilets = (ListView) findViewById(R.id.listToilets);
        listToilets.setAdapter(adapter);

        database = Database.getInstance(this);
    }
}