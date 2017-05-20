package com.example.tonto.truyenratngan.fragments;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tonto.truyenratngan.R;
import com.example.tonto.truyenratngan.StoryApplication;
import com.example.tonto.truyenratngan.adapter.ChapterAdapter;
import com.example.tonto.truyenratngan.databases.models.Story;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment extends Fragment {
    private ViewPager vpChapter;
    private Story story;
    private ChapterAdapter chapterAdapter;

    public StoryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);
        vpChapter = (ViewPager) view.findViewById(R.id.vp_chapter);

        return view;
    }

    public StoryDetailFragment setStory(Story story) {
        this.story = story;
        return this;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupUI();
    }


    private void setupUI() {
        vpChapter.setAdapter(new ChapterAdapter(getFragmentManager())
                .setStory(this.story));
        if (story.getLastChapterNo() != -1) {
            vpChapter.setCurrentItem(story.getLastChapterNo());
        }
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

    }

}
