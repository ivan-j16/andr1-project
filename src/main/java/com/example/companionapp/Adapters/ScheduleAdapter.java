package com.example.companionapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.companionapp.R;
import com.example.companionapp.Resources.Schedule;
import java.util.List;

public class ScheduleAdapter extends ArrayAdapter<Schedule> {

    public ScheduleAdapter(Context context, List<Schedule> schedule) {
        super(context, R.layout.schedule_row, schedule);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.schedule_row, parent, false);
        }
        // Get the data item for this position
        Schedule s = getItem(position);
        TextView scheduleText = convertView.findViewById(R.id.scheduleTextView);
        scheduleText.setText(s.scheduleToString());
        return convertView;
    }
}
