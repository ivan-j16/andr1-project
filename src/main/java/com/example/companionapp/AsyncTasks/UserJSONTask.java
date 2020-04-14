package com.example.companionapp.AsyncTasks;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

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
public class UserJSONTask extends AsyncTask<String, Void, Person>  {
    private Person person;
    private String token;
    private TextView tv;
    private ImageView iv;

    public UserJSONTask(TextView tv, ImageView iv){
        this.tv = tv;
        this.iv = iv;
    }

    @Override
    protected Person doInBackground(String... strings) {
        URL url = null;
        token = strings[0];
        try {
            url = new URL("https://api.fhict.nl/people/me");
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.connect();
            InputStream is = connection.getInputStream();
            Scanner scn = new Scanner(is);

            String jsonString = scn.useDelimiter("\\Z").next();

            JSONObject jsonObject = new JSONObject(jsonString);

            String id = jsonObject.getString("id");
            String displayName = jsonObject.getString("displayName");
            String email = jsonObject.getString("mail");
            String title = jsonObject.getString("title");
            String personalTitle = jsonObject.getString("personalTitle");
            String photo = jsonObject.getString("photo");

            person = new Person(id, displayName, email, title, personalTitle, photo);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    protected void onPostExecute(Person p) {
        tv.setText("WELCOME" + System.lineSeparator() + System.lineSeparator() + person.profileToString());
        UserPhotoJSONTask photoJson = (UserPhotoJSONTask) new UserPhotoJSONTask(iv).execute(person.GetPhotoURL(), token);
    }
}
