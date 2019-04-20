package com.example.thelkl321.angrymooseandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OptionsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options, container, false);
    }

    public void vibrationsPressed (View view){
        //TODO
    }

    public void musicPressed (View view){
        //TODO
    }

    public void soundPressed (View view){
        //TODO
    }
}
