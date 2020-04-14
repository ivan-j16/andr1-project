package com.example.companionapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.companionapp.Resources.Person;
import com.example.companionapp.R;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, List<Person> people) {
        super(context, R.layout.person_row, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.person_row, parent, false);
        }
        // Get the data item for this position
        Person p = getItem(position);
        TextView personText = convertView.findViewById(R.id.scheduleTextView);
        personText.setText(p.personToString());
        return convertView;
    }

}
