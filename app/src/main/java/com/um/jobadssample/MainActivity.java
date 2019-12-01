package com.um.jobadssample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_JOB_ACTIVITY_REQUEST_CODE = 1;
    public static final int VIEW_AD_REQUEST_CODE = 2;

    private AdViewModel mAdViewModel;

    private List<Ad> mAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AdListAdapter adapter = new AdListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdViewModel = ViewModelProviders.of(this).get(AdViewModel.class);

        mAdViewModel.getAllAds().observe(this, new Observer<List<Ad>>() {
            @Override
            public void onChanged(@Nullable List<Ad> ads) {
                mAds = ads;
                adapter.setAds(ads);
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, ViewAdActivity.class);
                        intent.putExtra("viewAd", mAds.get(position));
                        startActivityForResult(intent, VIEW_AD_REQUEST_CODE);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, NEW_JOB_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_JOB_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if(data.hasExtra("adClass")) {
                Bundle bundle = data.getExtras();
                Ad ad = (Ad) bundle.getParcelable("adClass");
                mAdViewModel.insert(ad);
                Toast.makeText(MainActivity.this, ad.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }
}
