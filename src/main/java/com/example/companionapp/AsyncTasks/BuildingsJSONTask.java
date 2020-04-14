package com.example.companionapp.AsyncTasks;

import android.os.AsyncTask;

import com.example.companionapp.Resources.Building;

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

public class BuildingsJSONTask extends AsyncTask<String, Void, List<Building>> {

    private List<Building> buildingList = new ArrayList<>();
    @Override
    protected List<Building> doInBackground(String... strings) {
        URL url = null;
        String token = strings[0];
        try {
            url = new URL("https://api.fhict.nl/buildings");
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
                JSONObject buildingObject = jsonArray.getJSONObject(i);

                // from each object, get field "subject", which is a string
                String name = buildingObject.getString("name");
                String description = buildingObject.getString("description");
                String address = buildingObject.getString("address");
                String postalCode = buildingObject.getString("postalCode");
                String city = buildingObject.getString("city");
                Building b = new Building(name, description, address, postalCode, city);
                // add each "subject" string to list
                buildingList.add(b);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return buildingList;
    }

    @Override
    protected void onPostExecute(List<Building> buildingList) {
        super.onPostExecute(buildingList);
    }

    public List<Building> getBuildings() {
        return buildingList;
    }
}
