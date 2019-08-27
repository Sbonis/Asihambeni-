package com.example.asihambeni;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private EditText edit_location,edit_Destination;
    private Button schedule_;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        edit_location = (EditText)V.findViewById(R.id.editlocation);
        edit_Destination = (EditText)V.findViewById(R.id.editDestination);
        schedule_ = (Button)V.findViewById(R.id.schedule);

        schedule_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ScheduleActivity.class);
                i.putExtra("pickUp", edit_location.getText().toString().trim());
                i.putExtra("destination", edit_location.getText().toString().trim());
                startActivity(i);
            }
        });

        return V;
    }




}
