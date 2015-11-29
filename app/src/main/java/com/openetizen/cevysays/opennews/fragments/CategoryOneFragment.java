package com.openetizen.cevysays.opennews.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.openetizen.cevysays.opennews.R;;
import com.openetizen.cevysays.opennews.activity.DetailPostActivity;
import com.openetizen.cevysays.opennews.activity.MainActivity;
import com.openetizen.cevysays.opennews.adapters.CategoryOneAdapter;
import com.openetizen.cevysays.opennews.models.CategoryOneItem;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;


public class CategoryOneFragment extends Fragment implements AdapterView.OnItemClickListener {

    //    private ArrayList<CategoryOneItem> categoryOneItemArrayList;
    private JazzyListView listView;
    private ArrayList<CategoryOneItem> dataCatOne = new ArrayList<>();
    private ArrayList<String> image = new ArrayList<String>();
    private ArrayList<String> url = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> created_at = new ArrayList<String>();
    private ArrayList<String> user_id = new ArrayList<String>();
    private ArrayList<String> content = new ArrayList<String>();
    private ArrayList<String> category_cd = new ArrayList<String>();
    private ArrayList<String> article_id = new ArrayList<String>();
    private View rootView;
    private Toolbar toolbar;
    public static final String MyPREFERENCES = "MyPrefs";
    static SharedPreferences sharedpreferences;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;


    public CategoryOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_category_one, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar_actionbar);
        toolbar.setTitle("Berita");
        getActivity().setTitle("Berita");
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        getData(false);
        ///new DownloadData().execute();
        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) rootView.findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                getData(true);
            }
        });


        return rootView;


    }

    private void getData(final boolean isRefresh) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://openetizen.com/opennews.json", null, new JsonHttpResponseHandler() {

            ProgressDialog progress;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (!isRefresh) {
                    progress.dismiss();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                if (!isRefresh) {
                    progress = ProgressDialog.show(getActivity(), "",
                            "Memuat data...", true);
                }

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                // Pull out the first event on the public timeline
                mWaveSwipeRefreshLayout.setRefreshing(false);
//                JSONArray openArray null;
//                JSONObject openObject = null;
                String test = "";
                try {
//                    openArray = jsonArray.getJSONArray(openArray);
//                    openObject = jsonArray.getJSONObject(openObject);
                    dataCatOne = new ArrayList<>();
                    image = new ArrayList<String>();
                    url = new ArrayList<String>();
                    title = new ArrayList<String>();
                    created_at = new ArrayList<String>();
                    user_id = new ArrayList<String>();
                    content = new ArrayList<String>();
                    category_cd = new ArrayList<String>();
                    article_id = new ArrayList<String>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String date = jsonArray.getJSONObject(i).getString("created_at").split("T")[0].split("-")[2] + "-" + jsonArray.getJSONObject(i).getString("created_at").split("T")[0].split("-")[1] + "-" + jsonArray.getJSONObject(i).getString("created_at").split("T")[0].split("-")[0];
                        dataCatOne.add(new CategoryOneItem(jsonArray.getJSONObject(i).getJSONObject("image").getString("url"), jsonArray.getJSONObject(i).getString("title"), date, jsonArray.getJSONObject(i).getInt("user_id"), jsonArray.getJSONObject(i).getString("content"), jsonArray.getJSONObject(i).getString("category_cd"), jsonArray.getJSONObject(i).getString("article_id")));
                        image.add(i, jsonArray.getJSONObject(i).getJSONObject("image").getString("url"));
                        title.add(i, jsonArray.getJSONObject(i).getString("title"));
                        created_at.add(i, date);
                        user_id.add(i, jsonArray.getJSONObject(i).getString("user_id"));
                        content.add(i, jsonArray.getJSONObject(i).getString("content"));
                        category_cd.add(i, jsonArray.getJSONObject(i).getString("category_cd"));
                        article_id.add(i, jsonArray.getJSONObject(i).getString("article_id"));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("image");
                editor.remove("title");
                editor.remove("created_at");
                editor.remove("user_id");
                editor.remove("content");
                editor.remove("category_cd");
                editor.remove("article_id");
                editor.commit();
                saveArray("image", image);
                saveArray("title", title);
                saveArray("created_at", created_at);
                saveArray("user_id", user_id);
                saveArray("content", content);
                saveArray("category_cd", category_cd);
                saveArray("article_id", article_id);


                listView = (JazzyListView) rootView.findViewById(R.id.list);
                listView.setTransitionEffect(JazzyHelper.GROW);
                listView.setOnItemClickListener(CategoryOneFragment.this);
                listView.setAdapter(new CategoryOneAdapter(dataCatOne, getActivity()));

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(CategoryOneFragment.this.getActivity(), DetailPostActivity.class);
        intent.putExtra("post", dataCatOne.get(i));
        startActivity(intent);

    }

    public static boolean saveArray(String key, ArrayList<String> sKey) {
        SharedPreferences.Editor mEdit1 = sharedpreferences.edit();
        mEdit1.putInt(key + "_size", sKey.size()); /* sKey is an array */

        for (int i = 0; i < sKey.size(); i++) {
            mEdit1.remove(key + "_" + i);
            mEdit1.putString(key + "_" + i, sKey.get(i));
        }

        return mEdit1.commit();
    }


}
//    }
//}
