package com.example.companionapp.Resources;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule {
    private String subject;
    private String room;
    private String teacher;
    private String start;
    private String end;


    public Schedule(String subject, String room, String teacher, String start, String end){
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.start = shortenDateTime(start);
        this.end = shortenDateTime(end);
    }

    public String getStart() {
        return start;
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String shortenDateTime(String stringDate) {

        String result = "";
        String pattern = "yyyy-MM-dd'T'HH:mm";
        try{
            // Transform string to a date with a given pattern
            SimpleDateFormat inFormat = new SimpleDateFormat(pattern);
            Date dtIn = inFormat.parse(stringDate);

            // Transform the Date object back to a string, again using the pattern
            Format formatter = new SimpleDateFormat(pattern);
            result = formatter.format(dtIn);
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
        return result;
    }

    public String scheduleToString() {
        return subject + " - " + room + " - " + teacher + System.lineSeparator() + start + " - " + end;
    }
}

