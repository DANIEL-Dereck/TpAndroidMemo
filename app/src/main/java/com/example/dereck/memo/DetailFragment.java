package com.example.dereck.memo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int position = getArguments().getInt(MainActivity.ITEM_POSITION, -1);
        String text = getArguments().getString(MainActivity.ITEM_TEXT);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView textView = view.findViewById(R.id.fragment_detail);
        textView.setText(String.valueOf(position) + " | " + text);

        return view;
    }

}
