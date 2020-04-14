package com.example.companionapp.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import com.example.companionapp.NotificationHandler;
import com.example.companionapp.Resources.Schedule;

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

public class ScheduleJSONTask extends AsyncTask<String, Void, List<Schedule>> {
    private List<Schedule> scheduleList = new ArrayList<>();
    private Context mContext;

    public ScheduleJSONTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected List<Schedule> doInBackground(String... strings) {
        URL url = null;
        String token = strings[0];
        try {
            url = new URL("https://api.fhict.nl/schedule/me");
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.connect();
            InputStream is;
            try{
                is = connection.getInputStream();
            }
            catch (IOException e) {
                // For testing purposes to notify on the dummy data
                NotificationHandler nh = new NotificationHandler(mContext, scheduleList);
                nh.checkStartTimesInRelationToCurrentTime();
                return scheduleList;
            }
            Scanner scn = new Scanner(is);

            String jsonString = scn.useDelimiter("\\Z").next();

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject scheduleObject = jsonArray.getJSONObject(i);

                // from each object, get field "subject", which is a string
                String subject = scheduleObject.getString("subject");
                String room = scheduleObject.getString("room");
                String teacher = scheduleObject.getString("teacherAbbreviation");
                String start = scheduleObject.getString("start");
                String end = scheduleObject.getString("end");
                Schedule schedule = new Schedule(subject, room, teacher, start, end);
                scheduleList.add(schedule);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Check if there are any schedules that are starting in less than 60 minutes. If there are, notify user
        NotificationHandler nh = new NotificationHandler(mContext, scheduleList);
        nh.checkStartTimesInRelationToCurrentTime();

        return scheduleList;
    }

    @Override
    protected void onPostExecute(List<Schedule> scheduleList) {
        super.onPostExecute(scheduleList);
    }

    public List<Schedule> getSchedule() {
        return scheduleList;
    }
}
