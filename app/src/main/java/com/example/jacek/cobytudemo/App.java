package com.example.jacek.cobytudemo;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jacek on 2016-03-29.
 */
public class App extends Application {
    private static App ourInstance = new App();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    public static App getInstance() {
        return ourInstance;
    }

    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ourInstance = this;
        //Volley
        mRequestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(this.mRequestQueue,
                new ImageLoader.ImageCache() {

                    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(
                            10);

                    public void putBitmap(String url, Bitmap bitmap) {
                        mCache.put(url, bitmap);
                    }

                    public Bitmap getBitmap(String url) {
                        return mCache.get(url);
                    }
                });

    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
