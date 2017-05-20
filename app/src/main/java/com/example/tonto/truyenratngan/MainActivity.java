package com.example.tonto.truyenratngan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tonto.truyenratngan.fragments.StoryListFragment;

public class MainActivity extends AppCompatActivity {
    StoryListFragment storyListFragment = new StoryListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayStartScreen();
    }

    private void displayStartScreen() {
        // 1: Create fragment
        storyListFragment = new StoryListFragment();

        changeScreen(storyListFragment, false);
    }

    public void changeScreen(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, fragment);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);

        // commit
        fragmentTransaction.commit();
    }

    public void setFavorite(View v) {
        storyListFragment.setFavorite(v);
    }
}
