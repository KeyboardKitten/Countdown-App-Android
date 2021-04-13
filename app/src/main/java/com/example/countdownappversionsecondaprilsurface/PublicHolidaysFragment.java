package com.example.countdownappversionsecondaprilsurface;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.ContentLoadingProgressBar;
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
import java.util.HashSet;

public class PublicHolidaysFragment extends Fragment {

    private RequestQueue requestQueue;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> events;
    private HashSet hs;


    private LinearLayoutCompat holidayAPILoadingContainer;
    private ContentLoadingProgressBar holidayLoadingProgress;

    private LinearLayoutCompat holidayFailedContainer;

    private RelativeLayout holidayAPIContainer;

    private ClipData myClip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Context context = getContext();
        if (context == null) return v;

        listView = v.findViewById(R.id.holidayAPIListView);
        this.holidayLoadingProgress = v.findViewById(R.id.progress);
        this.holidayAPILoadingContainer = v.findViewById(R.id.holidayAPI_loading_container);
        this.holidayFailedContainer = v.findViewById(R.id.holidayAPI_fetch_failed_container);
        this.holidayAPIContainer = v.findViewById(R.id.holidayAPIFragment);

        setLoadingUI();


        requestQueue = Volley.newRequestQueue(getContext());


        // Public Gov.UK api for displaying bank holidays
        // DOCUMENTATION https://www.api.gov.uk/gds/bank-holidays/#bank-holidays
        String url = "https://www.gov.uk/bank-holidays.json";

        events = new ArrayList<>();
        hs = new HashSet();

        fetchDataAndUpdateList(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TEST", "onResponse Started");
                        try {
                            JSONObject englandAndWales = response.getJSONObject("england-and-wales");
                            JSONArray eventsJSON = englandAndWales.getJSONArray("events");
                            Log.d("First Holiday Call ", String.valueOf(eventsJSON));
                            for (int i = 0; i < eventsJSON.length(); i++) {
                                Log.d("Second Holiday Call ", String.valueOf(i));
                                JSONObject event = eventsJSON.getJSONObject(i);
                                Log.d("Third Holiday Call ", event.toString());
                                Log.d("Fourth Holiday Call ", event.getString("title"));
                                events.add(event.getString("title"));
//                                REFERENCE https://stackoverflow.com/questions/7633742/removing-duplicates-from-listview-android
//                                Removing dupes from list
                                hs.add(events);
                                events.clear();
                                events.addAll(hs);
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
                                setFetchedUI();
                            }
                        } catch (JSONException e) {
                            Log.d("ERROR", "JSON Error");
                            setFailedUI();
                        }
                    }
                });

        // REFERENCE https://stackoverflow.com/a/39763474
        final ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "hello world";
                myClip = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(myClip);
                return true;
            }

        });

        return v;
    }

    private void fetchDataAndUpdateList(String url, Response.Listener<JSONObject> onResponse) {
        JsonObjectRequest onErrorRequest = new JsonObjectRequest(Request.Method.GET, url, null, onResponse,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR HOLIDAYS", error.getMessage());
                        setFailedUI();
                    }
                });
        requestQueue.add(onErrorRequest);
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

//    Progress bar UI replacements

    private void setLoadingUI() {
        this.holidayAPILoadingContainer.setVisibility(View.VISIBLE);
        this.holidayFailedContainer.setVisibility(View.GONE);
        this.holidayAPIContainer.setVisibility(View.GONE);
    }

    private void setFetchedUI() {
        this.holidayAPIContainer.setVisibility(View.VISIBLE);
        this.holidayFailedContainer.setVisibility(View.GONE);
        this.holidayAPILoadingContainer.setVisibility(View.GONE);
    }

    private void setFailedUI() {
        this.holidayAPIContainer.setVisibility(View.GONE);
        this.holidayAPILoadingContainer.setVisibility(View.GONE);
        this.holidayFailedContainer.setVisibility(View.VISIBLE);
    }

}
