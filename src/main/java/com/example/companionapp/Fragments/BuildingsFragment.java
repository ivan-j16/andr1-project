package com.example.companionapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.companionapp.Adapters.BuildingAdapter;
import com.example.companionapp.MainActivity;
import com.example.companionapp.R;
import com.example.companionapp.Resources.Building;

import java.util.List;


public class BuildingsFragment extends Fragment {
    private List<Building> buildings;
    private ListAdapter buildingAdapter;
    private OnFragmentInteractionListener mListener;

    public BuildingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buildings, container, false);

        buildings = ((MainActivity) getActivity()).getBuildings();
        buildingAdapter = new BuildingAdapter(getActivity(), buildings);
        ListView buildingListView = view.findViewById(R.id.buildingsListView);
        buildingListView.setAdapter(buildingAdapter);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
