package com.example.tonto.truyenratngan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tonto.truyenratngan.StoryApplication;
import com.example.tonto.truyenratngan.databases.StoryDatabase;
import com.example.tonto.truyenratngan.databases.models.Story;
import com.example.tonto.truyenratngan.fragments.ChapterFragment;

import java.util.List;

/**
 * Created by tonto on 5/14/2017.
 */

public class ChapterAdapter extends FragmentStatePagerAdapter {
    private Story story;
    private StoryDatabase storyDatabase;
    private List<Integer> chapterIds;

    public ChapterAdapter setStory(Story story) {
        this.story = story;
        this.chapterIds = storyDatabase.getChapterIds(story);
        return this;
    }

    public ChapterAdapter(FragmentManager fm) {
        super(fm);
        storyDatabase = StoryApplication.getInstance().getStoryDatabase();
    }

    @Override
    public Fragment getItem(int position) {
        int chapterId = this.chapterIds.get(position);
        ChapterFragment chapterFragment = new ChapterFragment();
        chapterFragment.setChapterId(chapterId);
        return chapterFragment;
    }

    @Override
    public int getCount() {
        return storyDatabase.getChapterCount(story);
    }
}
