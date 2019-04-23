package com.example.thelkl321.angrymooseandroid.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {
    public static void showFragment(Fragment fragment, FragmentManager fm) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment).commit();
    }

    public static void hideFragment(Fragment fragment, FragmentManager fm) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(fragment).commit();
    }
}
