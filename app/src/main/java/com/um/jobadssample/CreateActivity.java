package com.um.jobadssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    private final static String className = CreateActivity.class.getName();

    /** EditText field to enter the ad's title */
    private EditText mTitleEditText;

    /** EditText field to enter the ad's desc */
    private EditText mDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);
        setTitle(getString(R.string.editor_activity_title_new_ad));
        invalidateOptionsMenu();

        mTitleEditText = (EditText) findViewById(R.id.edit_job_title);
        mDescEditText = (EditText) findViewById(R.id.edit_job_desc);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                // when tick button is pressed
                Intent replyIntent = new Intent();
                // validation
                if(TextUtils.isEmpty(mTitleEditText.getText()) || TextUtils.isEmpty(mDescEditText.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                //create new pet
                Ad a = setAd(mTitleEditText.getText().toString(), mDescEditText.getText().toString());
                replyIntent.putExtra("adClass", a);
                setResult(RESULT_OK, replyIntent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Ad setAd(String title, String desc) {
        Ad ad = new Ad();
        ad.setTitle(title);
        ad.setDesc(desc);

        return ad;
    }
}
