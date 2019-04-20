package com.example.thelkl321.angrymooseandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class PagerButtonsFragment extends Fragment {

    private static String PAGE_KEY = "number";
    private int pageNumber;

    // Constructs a new fragment for the given page number.
    public static PagerButtonsFragment create(int pageNumber) {
        PagerButtonsFragment fragment = new PagerButtonsFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_KEY, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        assert args != null;
        pageNumber = args.getInt(PAGE_KEY);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageButton btn = (ImageButton) inflater.inflate(R.layout.fragment_pager_buttons, container, false);

        int img;
        switch (pageNumber) {
            case 0:
                img = R.mipmap.easy_button;
                break;

            case 1:
                img = R.mipmap.normal_button;
                break;

            case 2:
                img = R.mipmap.hard_button;
                break;

            case 3:
                img = R.mipmap.impossible_button;
                break;

            default:
                throw new NullPointerException();
        }
        btn.setImageResource(img);

        return btn;
    }
}

