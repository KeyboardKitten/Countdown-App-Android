package com.example.countdownappversionsecondaprilsurface;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class PublicHolidaysFragment extends Fragment {

    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.holidayapi_main, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        String url = "https://www.gov.uk/bank-holidays.json";

        // THIS WORKS

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, // Type of request, using GET to get info
                url, // url from the gov.uk page
                new Response.Listener<String>() { // When no error this method runs
                    @Override
                    public void onResponse(String response) {
                        Log.d("GOVUK", response);
                    }
                },
                new Response.ErrorListener() { // When there is an error
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERRORGOVUK", error.getMessage());
                    }
                }


        );

        requestQueue.add(stringRequest);

//        int numberOfItem = 10;
//        ArrayList <String> data = new ArrayList<String>();
//        for (int i=0; i<numberOfItem; i++)
//            data.add(String.format("List item: %s", i));
//
//        Context context = getContext();
//        if (context == null)
//            return v;
//
//        ArrayAdapter <String> myArrayAdapter = new ArrayAdapter<String>(
//                context,
//                android.R.layout.simple_list_item_1,
//                data
//        );
//
//        ListView listView = v.findViewById(R.id.holidayAPIListView);
//        listView.setAdapter(myArrayAdapter);

        return v;
    }
}
