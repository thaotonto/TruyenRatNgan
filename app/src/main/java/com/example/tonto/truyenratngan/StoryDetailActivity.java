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
    private ViewPager vpChapter;
    private Story story;
    private ChapterAdapter chapterAdapter;
    private String TAG = "StoryDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        vpChapter = (ViewPager) findViewById(R.id.vp_chapter);
        getStory();
        setupUI();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getStory() {
        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("Story");
    }

    private void setupUI() {
        vpChapter.setAdapter(new ChapterAdapter(getSupportFragmentManager())
                .setStory(this.story));

        vpChapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                story.setLastChapterNo(position);
                SQLiteDatabase db = StoryApplication.getInstance().getStoryDatabase().getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("last_chapter_no", position);
                db.update("tbl_story", values, "id = ?", new String[]{
                        ((Integer) story.getId()).toString()
                });
                db.close();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (story.getLastChapterNo() != -1) {
            vpChapter.setCurrentItem(story.getLastChapterNo());
        }
    }
}
