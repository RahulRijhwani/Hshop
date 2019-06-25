package com.hshop.shopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hshop.R;

/**
 * Created by Nilay on 12-Apr-17.
 */

public class Category extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_category, container, false);

        return view;
    }
    public static Fragment newInstance() {
        Category fragment = new Category();
        return fragment;
    }
}