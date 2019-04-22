package com.example.thelkl321.angrymooseandroid.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
