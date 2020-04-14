package com.example.companionapp.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.companionapp.AsyncTasks.UserJSONTask;
import com.example.companionapp.AsyncTasks.UserPhotoJSONTask;
import com.example.companionapp.MainActivity;
import com.example.companionapp.R;
import com.example.companionapp.Resources.Person;

public class HomeFragment extends Fragment {
    private TextView tv;
    private ImageView iv;
    private String TOKEN;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        tv = view.findViewById(R.id.userTextView);
        iv = view.findViewById(R.id.userImageView);
        tv.setText(" ");
        TOKEN = ((MainActivity)getActivity()).getToken();

        //load user


//        final UserPhotoJSONTask userPhotoJSONTask = new UserPhotoJSONTask();
//                userPhotoJSONTask.execute(user.GetPhotoURL(), TOKEN);
//

        UserJSONTask userJson = (UserJSONTask) new UserJSONTask(tv, iv).execute(TOKEN);
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

//    private void loadUser(){
//        //load user
//        UserJSONTask userJson = new UserJSONTask();
//        userJson.execute(TOKEN);
//        user = userJson.getUser();
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
