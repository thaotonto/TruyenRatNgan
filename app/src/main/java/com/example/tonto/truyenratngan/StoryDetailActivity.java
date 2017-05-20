package com.example.tonto.truyenratngan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tonto.truyenratngan.adapter.ChapterAdapter;
import com.example.tonto.truyenratngan.databases.models.Story;

public class StoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
