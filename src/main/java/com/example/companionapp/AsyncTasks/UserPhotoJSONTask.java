package com.example.companionapp.AsyncTasks;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.companionapp.Resources.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserPhotoJSONTask extends AsyncTask<String, Void, Bitmap> {
    private Bitmap bitmap;
    private ImageView iv;

    public UserPhotoJSONTask(ImageView iv){
        this.iv = iv;
    }

    URL url;
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            url = new URL(strings[0]);
            String token = strings[1];
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.connect();
            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap b) {
        iv.setImageBitmap(bitmap);
    }
}
