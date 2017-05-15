package com.example.tonto.truyenratngan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tonto.truyenratngan.adapter.StoryAdapter;
import com.example.tonto.truyenratngan.databases.StoryDatabase;
import com.example.tonto.truyenratngan.databases.models.DaoMaster;
import com.example.tonto.truyenratngan.databases.models.DaoSession;
import com.example.tonto.truyenratngan.databases.models.Story;
import com.example.tonto.truyenratngan.databases.models.StoryGreenDAO;
import com.example.tonto.truyenratngan.databases.models.StoryGreenDAODao;

import java.util.List;

public class StoryListActivity extends AppCompatActivity {
    private List<Story> stories;
    private String TAG = "Databases: ";
    private StoryAdapter storyAdapter;
    private ListView lvStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);

        lvStory = (ListView) findViewById(R.id.lv_story_list);
        loadData();
        setupUI();

    }


    private void loadData() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "story.db");
//        SQLiteDatabase db = helper.getReadableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession daoSession = daoMaster.newSession();
//        // do this in your activities/fragments to get hold of a DAO
//        StoryGreenDAODao storyGreenDAODao = daoSession.getStoryGreenDAODao();

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
                loadData();
                Intent intent = new Intent(StoryListActivity.this, StoryDetailActivity.class);
                intent.putExtra("Story", stories.get(position));
                startActivity(intent);
            }
        });
    }
}
