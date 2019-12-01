package com.um.jobadssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class ViewAdActivity extends AppCompatActivity {

    /** EditText field to enter the pet's name */
    private TextView mTitleText;

    /** EditText field to enter the pet's breed */
    private TextView mDescText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mTitleText = (TextView) findViewById(R.id.view_job_title);
        mDescText = (TextView) findViewById(R.id.view_job_desc);

        Intent intent = getIntent();

        if(intent.hasExtra("viewAd")) {
            Bundle bundle = intent.getExtras();
            Ad ad = bundle.getParcelable("viewAd");
            mTitleText.setText(ad.getTitle());
            mDescText.setText(ad.getDesc());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
