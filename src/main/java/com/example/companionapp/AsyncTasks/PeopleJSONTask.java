package com.example.companionapp.AsyncTasks;
import android.os.AsyncTask;

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

public class PeopleJSONTask extends AsyncTask<String, Void, List<Person>> {
    private List<Person> personList = new ArrayList<>();
    @Override
    protected List<Person> doInBackground(String... strings) {
        URL url = null;
        String token = strings[0];
        try {
            url = new URL("https://api.fhict.nl/people");
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.connect();
            InputStream is = connection.getInputStream();
            Scanner scn = new Scanner(is);

            String jsonString = scn.useDelimiter("\\Z").next();
            JSONArray jsonArray = new JSONArray(jsonString);

            for(int i=0; i < jsonArray.length(); i++) {
                JSONObject scheduleObject = jsonArray.getJSONObject(i);

                // from each object, get field "subject", which is a string
                String id = scheduleObject.getString("id");
                String firstName = scheduleObject.getString("givenName");
                String lastName = scheduleObject.getString("surName");
                String email = scheduleObject.getString("mail");
                String office;
                try{
                    office = scheduleObject.getString("office");
                }
                catch (JSONException е) {
                    office = "";
                }
                Person p = new Person(id, firstName, lastName, email, office);
                personList.add(p);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    protected void onPostExecute(List<Person> personList) {
        super.onPostExecute(personList);
    }

    public List<Person> getPersons() {
        return personList;
    }
}

