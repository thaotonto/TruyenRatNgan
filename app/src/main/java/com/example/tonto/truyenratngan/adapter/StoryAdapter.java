package com.example.tonto.truyenratngan.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tonto.truyenratngan.StoryApplication;
import com.example.tonto.truyenratngan.networks.ImageLoader;
import com.example.tonto.truyenratngan.R;
import com.example.tonto.truyenratngan.databases.models.Story;
import com.example.tonto.truyenratngan.utils.TextViewUtils;

import java.util.List;

/**
 * Created by tonto on 5/7/2017.
 */

public class StoryAdapter extends BaseAdapter {
    private List<Story> storyList;

    public StoryAdapter(List<Story> storyList) {
        this.storyList = storyList;
    }

    @Override
    public int getCount() {
        return storyList.size();
    }

    @Override
    public Object getItem(int position) {
        return storyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1: Load item data
        Story story = storyList.get(position);

        // 2: Create view if neccessary
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.item_story, parent, false);
        }

        // 3: config & return
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
        ImageView ivStory = (ImageView) convertView.findViewById(R.id.iv_story_image);

        tvTitle.setText(story.getTitle());
        TextViewUtils.makeFit(tvDescription);
        tvDescription.setText(story.getDescription());
        // Image
        new ImageLoader().setImageView(ivStory)
                .loadImage(story.getImage());
//        int chapterCount = StoryApplication.getInstance().getStoryDatabase().getChapterCount(story);

        return convertView;
    }
}
