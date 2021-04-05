package com.example.countdownappversionsecondaprilsurface;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PublicHolidaysFragment extends Fragment {

    private RequestQueue requestQueue;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> events;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.holidayapi_main, container, false);

        Context context = getContext();
        if (context == null) return v;

        listView = v.findViewById(R.id.holidayAPIListView);


        requestQueue = Volley.newRequestQueue(getContext());


        // Public Gov.UK api for displaying bank holidays
        // DOCUMENTATION https://www.api.gov.uk/gds/bank-holidays/#bank-holidays
        String url = "https://www.gov.uk/bank-holidays.json";

        // THIS WORKS



//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//
//                Request.Method.GET, // Type of request, using GET to get info
//                url,
//                null,// url from the gov.uk page
//                new Response.Listener<JSONObject>() { // When no error this method runs
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        String holidaysPrinted;
//
//                        try {
//                            holidaysPrinted = response.getJSONObject("england-and-wales").getJSONObject("division").getJSONObject("events").getString("title");
//                            Log.d("HOLIDAYS", response.toString(2));
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            holidaysPrinted = "Unknown";
//                        }
//                        listView.(holidaysPrinted);
//                    }
//
//                },
//
//
//
//                new Response.ErrorListener() { // When there is an error
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (error.getMessage() != null) {
//                            Log.d("ERRORGOVUK", error.getMessage());
//                        }
//                    }
//                }
//        );
//
//        requestQueue.add(jsonObjectRequest);

        events = new ArrayList<>();

        fetchDataAndUpdateList(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TEST","onResponse Started");
                        try {
                            JSONObject englandAndWales = response.getJSONObject("england-and-wales");
                            JSONArray eventsJSON = englandAndWales.getJSONArray("events");
                            Log.d("jsonnn derulo", String.valueOf(eventsJSON));
                            for (int i = 0; i < eventsJSON.length(); i++) {
                                Log.d("viruss", String.valueOf(i));
                                JSONObject event = eventsJSON.getJSONObject(i);
                                Log.d("Alex is a sstupid",event.toString());
                                Log.d("Alex is a ",event.getString("title"));
                                events.add(event.getString("title") + " " +  event.getString("date"));
                                String[] eventsArray = new String[events.size()];
                                eventsArray = events.toArray(eventsArray);
                                arrayAdapter = new ArrayAdapter<String>(
                                        getContext(),
                                        android.R.layout.simple_list_item_1,
                                        eventsArray
                                );



                                Log.d("ArrayAdapter", arrayAdapter.toString());
                                Log.d("ArrayAdapter", listView.toString());
                                listView.setAdapter(arrayAdapter);

                            }

//                            System.out.println(events);
//                            int l = events.length();
//                            for (int i=0; i < l; i++) {
//                                JSONObject obj = events.getJSONObject(i);
//                                arrayAdapter.add(obj.getString("title"));
//                            }
                        } catch (JSONException e) {
                            Log.d("ERROR","JSON Error");
                        }
                    }
                });


        return v;
    }

    private void fetchDataAndUpdateList(String url, Response.Listener<JSONObject> onResponse){
        JsonObjectRequest exampleRequest = new JsonObjectRequest(Request.Method.GET, url, null, onResponse,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR HOLIDAYS", error.getMessage());
                    }
                });
        requestQueue.add(exampleRequest);
    }

    @Override
    public void onResume() {
        requestQueue.start();
        super.onResume();
    }

    @Override
    public void onPause() {
        requestQueue.stop();
        super.onPause();
    }

}
