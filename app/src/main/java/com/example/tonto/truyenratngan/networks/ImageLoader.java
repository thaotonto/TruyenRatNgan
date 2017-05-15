package com.example.tonto.truyenratngan.networks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.tonto.truyenratngan.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tonto on 4/21/2017.
 */

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private Bitmap image;
    private String urlString;
    private String imageTag;

    public ImageLoader() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public void loadImage(String urlString) {
        if (!urlString.equals(imageTag)) {
//            this.imageView.setImageResource(R.drawable.ic_do_not_disturb_black_24px);
            this.imageView.setImageResource(R.drawable.progress_animation);
            execute(urlString);
        }

    }

    public ImageLoader setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.imageTag = (imageView.getTag() == null) ? "" : imageView.getTag().toString();
        return this;
    }

    protected Bitmap doInBackground(String... urls) {
        urlString = urls[0];
        if (urlString.equals(imageTag)) {
            return null;
        }

        try {
            InputStream in = new java.net.URL(urlString).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            image = null;
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            imageView.setImageBitmap(result);
            imageView.setTag(urlString);
        }
    }
}