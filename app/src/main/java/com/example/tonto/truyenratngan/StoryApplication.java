package com.example.tonto.truyenratngan;

import android.app.Application;
import android.util.Log;

import com.example.tonto.truyenratngan.databases.StoryDatabase;


/**
 * Created by tonto on 4/18/2017.
 */



public class StoryApplication extends Application {
    private static StoryApplication instance;
    private String TAG = "StoryApplication";
    private StoryDatabase storyDatabase;

    @Override
    public void onCreate() {
        storyDatabase = new StoryDatabase(this);
        instance = this;
        super.onCreate();
        Log.d(TAG, "onCreate:");
    }

    public StoryDatabase getStoryDatabase() {
        return storyDatabase;
    }

    public static StoryApplication getInstance() {
        return instance;
    }
}
