package com.example.countdownappversionsecondaprilsurface;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PublicHolidaysFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.holidayapi_main, container, false);

        int numberOfItem = 10;
        ArrayList <String> data = new ArrayList<String>();
        for (int i=0; i<numberOfItem; i++)
            data.add(String.format("List item: %s", i));

        Context context = getContext();
        if (context == null)
            return v;

        ArrayAdapter <String> myArrayAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                data
        );

        ListView listView = v.findViewById(R.id.holidayAPIListView);
        listView.setAdapter(myArrayAdapter);

        return v;
    }
}
