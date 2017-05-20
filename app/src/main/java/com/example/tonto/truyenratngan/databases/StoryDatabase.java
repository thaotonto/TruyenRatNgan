package com.example.tonto.truyenratngan.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tonto.truyenratngan.databases.models.Chapter;
import com.example.tonto.truyenratngan.databases.models.Story;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonto on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "story.db";
    private static final int DATABASE_VERSION = 1;
    private static final String STORY_ID = "id";
    private static final String STORY_TITLE = "title";
    private static final String STORY_DESCRIPTION = "description";
    private static final String STORY_AUTHOR = "author";
    private static final String STORY_GENRE = "genre";
    private static final String STORY_IMAGE = "image";
    private static final String STORY_IS_FAVOTITE = "is_favorite";
    private static final String STORY_LAST_CHAPTER_NO = "last_chapter_no";
    private static final String[] STORY_ALL_COLUMNS = new String[]{
            STORY_ID,
            STORY_TITLE,
            STORY_DESCRIPTION,
            STORY_AUTHOR,
            STORY_GENRE,
            STORY_IMAGE,
            STORY_IS_FAVOTITE,
            STORY_LAST_CHAPTER_NO
    };

    private static final String CHAPTER_ID = "id";
    private static final String CHAPTER_TITLE = "title";
    private static final String CHAPTER_CONTENT = "content";
    private static final String[] CHAPTER_ALL_COLUMNS = new String[]{
            CHAPTER_ID,
            CHAPTER_TITLE,
            CHAPTER_CONTENT
    };

    private String TAG = "DATABASES";

    public StoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Story> loadAllStories() {
        List<Story> stories = new ArrayList<>();

        // Get readable database
        SQLiteDatabase db = getReadableDatabase();
        //Query => cursor
        Cursor cursor = db.query("tbl_story", STORY_ALL_COLUMNS, null, null, null, null, null);
        //Go through rows
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(STORY_ID));
            String title = cursor.getString(cursor.getColumnIndex(STORY_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(STORY_DESCRIPTION));
            String author = cursor.getString(cursor.getColumnIndex(STORY_AUTHOR));
            String genre = cursor.getString(cursor.getColumnIndex(STORY_GENRE));
            String image = cursor.getString(cursor.getColumnIndex(STORY_IMAGE));
            boolean isFavorite = cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVOTITE)) != 0;
            int lastChapterNo = cursor.getInt(cursor.getColumnIndex(STORY_LAST_CHAPTER_NO));

            Story story = new Story(id, title, description, author, genre, image, isFavorite, lastChapterNo);
            stories.add(story);
        }
        cursor.close();
        db.close();
        return stories;
    }

    public int getChapterCount(Story story) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM tbl_chapter WHERE novel_id=?", new String[]{
                ((Integer) story.getId()).toString()
        });

        cursor.moveToFirst();
        int chapterCount = cursor.getInt(0);
        cursor.close();
        db.close();
        return chapterCount;
    }

    public List<Integer> getChapterIds(Story story) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM tbl_chapter WHERE novel_id = ?", new String[]{
                ((Integer) story.getId()).toString()
        });

        List<Integer> chapterIds = new ArrayList<>();

        while (cursor.moveToNext()) {
            chapterIds.add(cursor.getInt(0));
        }

        cursor.close();
        db.close();
        return chapterIds;
    }

    public Chapter getChapter(int chapterId) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("tbl_chapter", CHAPTER_ALL_COLUMNS, "id=?", new String[]{
                        ((Integer) chapterId).toString()}
                , null, null, null);
        cursor.moveToFirst();

        String title = cursor.getString(cursor.getColumnIndex(CHAPTER_TITLE));
        String content = cursor.getString(cursor.getColumnIndex(CHAPTER_CONTENT));

        cursor.close();

        return new Chapter(chapterId, title, content);
    }

    public void setFavorite(String id, boolean isFavorite) {
        SQLiteDatabase db = getWritableDatabase();
        int favorite = 0;
        ContentValues values = new ContentValues();
        if (isFavorite) favorite = 1;
        values.put("is_favorite", favorite);
        db.update("tbl_story", values, "id = ?", new String[]{
                id
        });
        db.close();
    }

}
