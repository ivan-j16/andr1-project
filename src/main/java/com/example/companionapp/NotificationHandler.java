package com.example.companionapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.companionapp.Resources.Schedule;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationHandler {

    private List<Schedule> schedules;
    private Context mContext;

    public NotificationHandler(Context context, List<Schedule> schedules) {
        this.schedules = schedules;
        this.mContext = context;
    }

    public void checkStartTimesInRelationToCurrentTime() {
        String text = "";
        String pattern = "yyyy-MM-dd'T'HH:mm";
        Date date_now = new Date();
        long diff;
        for (Schedule schedule:schedules) {
            // Check if the start time of the subject is near the current time
            String start_time = schedule.getStart();
            Date start_date = stringToDate(start_time, pattern);
            diff = start_date.getTime() - date_now.getTime();
            int minutes = (int) (diff / (1000*60));

            if (minutes > 0 && minutes < 60) {
                text += schedule.getSubject() + " in " + minutes + " minutes" + ", room: " + schedule.getRoom() + System.lineSeparator();
            }
        }
        if (text != "") {
            startNotification(text);
        }
    }

    public void startNotification(String body) {
        NotificationManager nm = (NotificationManager) mContext.getSystemService(Service.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Register channel if android version >= 8.0 - https://developer.android.com/training/notify-user/build-notification#Priority
            NotificationChannel nc = new NotificationChannel("abc", "Class", NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(nc);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "abc");
        builder.setSmallIcon(R.drawable.ic_schedule_black_24dp);
        builder.setContentTitle("You have classes approaching!");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(body));

        // Show the notification
        Notification notif = builder.build();
        nm.notify(1, notif);
    }

    public Date stringToDate(String stringDate, String pattern) {
        Date result = null;
        try{
            SimpleDateFormat inFormat = new SimpleDateFormat(pattern);
            result = inFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
