package com.example.tonto.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tonto.truyenratngan.MainActivity;
import com.example.tonto.truyenratngan.R;
import com.example.tonto.truyenratngan.StoryApplication;
import com.example.tonto.truyenratngan.adapter.StoryAdapter;
import com.example.tonto.truyenratngan.databases.StoryDatabase;
import com.example.tonto.truyenratngan.databases.models.Story;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryListFragment extends Fragment {
    private List<Story> stories;
    private StoryAdapter storyAdapter;
    private ListView lvStory;

    public StoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        lvStory = (ListView) view.findViewById(R.id.lv_story_list);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
        setupUI();
    }

    private void loadData() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "story1.db");
//        SQLiteDatabase db = helper.getReadableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession daoSession = daoMaster.newSession();
//        // do this in your activities/fragments to get hold of a DAO
//        StoryGreenDAODao storyGreenDAODao = daoSession.getStoryGreenDAODao();
//
//        List<StoryGreenDAO> storyGreenDAOs = storyGreenDAODao.loadAll();
//        for (int i = 0; i < storyGreenDAOs.size(); i++) {
//            System.out.println(storyGreenDAOs.get(i).getTitle());
//        }

        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        stories = storyDatabase.loadAllStories();
    }

    private void setupUI() {
        // 1: create adapter
        storyAdapter = new StoryAdapter(stories);

        // 2: connect adapter to list view
        lvStory.setAdapter(storyAdapter);

        // 3: Add event
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
//                intent.putExtra("Story", stories.get(position));
//                startActivity(intent);
                // TODO: change screen
                ((MainActivity) getActivity())
                        .changeScreen(new StoryDetailFragment().setStory(stories.get(position)), true);

            }
        });
    }

    public void setFavorite(View v) {
        if (v.isSelected()) {
            v.setSelected(false);
            StoryApplication.getInstance().getStoryDatabase().setFavorite(v.getTag().toString(), false);
        } else {
            v.setSelected(true);
            StoryApplication.getInstance().getStoryDatabase().setFavorite(v.getTag().toString(), true);
        }
    }
}
