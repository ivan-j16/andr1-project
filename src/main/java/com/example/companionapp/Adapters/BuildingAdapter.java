package com.example.companionapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.companionapp.Resources.Building;
import com.example.companionapp.R;

import java.util.List;

public class BuildingAdapter extends ArrayAdapter<Building>{

    public BuildingAdapter(Context context, List<Building> building) {
        super(context, R.layout.building_row, building);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.building_row, parent, false);
        }
        // Get the data item for this position
        Building b = getItem(position);
        TextView buildingText = convertView.findViewById(R.id.buildingTextView);
        buildingText.setText(b.ToString());
        return convertView;
    }
}
