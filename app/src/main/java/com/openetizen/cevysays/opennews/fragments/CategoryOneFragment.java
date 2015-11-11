package com.openetizen.cevysays.opennews.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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

import cz.msebera.android.httpclient.Header;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.Options;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


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
    private View rootView;
    private Toolbar toolbar;
    public static final String MyPREFERENCES = "MyPrefs";
    static SharedPreferences sharedpreferences;
    private PullToRefreshLayout mPullToRefreshLayout;
    private MaterialRefreshLayout materialRefreshLayout;


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

        getData();
        ///new DownloadData().execute();

        materialRefreshLayout = (MaterialRefreshLayout) getActivity().findViewById(R.id.refresh);
        materialRefreshLayout.setWaveColor(0xffffffff);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(true);
        materialRefreshLayout.autoRefresh();//drop-down refresh automatically
        materialRefreshLayout.autoRefreshLoadMore();
//        https://github.com/android-cjj/Android-MaterialRefreshLayout

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                                                             @Override
                                                             public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                                                                 //refreshing...
                                                                 materialRefreshLayout.postDelayed(new Runnable() {
                                                                     @Override
                                                                     public void run() {
                                                                         getData();
                                                                         materialRefreshLayout.finishRefresh();

                                                                     }
                                                                 }, 3000);
                                                                 materialRefreshLayout.finishRefreshLoadMore();
                                                             }

                                                             @Override
                                                             public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                                                                 //load more refreshing...
                                                                 Toast.makeText(getActivity(), "load more", Toast.LENGTH_LONG).show();
                                                             }
        });

// refresh complete
                materialRefreshLayout.finishRefresh();

// load more refresh complete
        materialRefreshLayout.finishRefreshLoadMore();

//        mPullToRefreshLayout = (PullToRefreshLayout) getActivity().findViewById(R.id.ptr_layout);
//
//        // Now setup the PullToRefreshLayout
//        ActionBarPullToRefresh.from(getActivity())
//                // Mark All Children as pullable
//                .allChildrenArePullable()
//                        // Set a OnRefreshListener
//                .listener(new OnRefreshListener() {
//                    @Override
//                    public void onRefreshStarted(View view) {
//                        getData();
//                    }
//                })
//        // Finally commit the setup to our PullToRefreshLayout
//        .setup(mPullToRefreshLayout);


//        ActionBarPullToRefresh.from(getActivity())
//                .options(Options.create()
//                        // Here we make the refresh scroll distance to 75% of the refreshable view's height
//                        .scrollDistance(.75f)
//                                // Here we define a custom header layout which will be inflated and used
//
//                        .build())
//
//                        // Now carry on with the rest of the setup
//                .allChildrenArePullable()
//                .listener(new OnRefreshListener() {
//                    @Override
//                    public void onRefreshStarted(View view) {
//                        getData();
//                    }
//                })
//        .setup(mPullToRefreshLayout);

        return rootView;
//        return inflater.inflate(R.layout.fragment_category_one, container, false);

    }

    private void getData() {

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
                progress.dismiss();
            }

            @Override
            public void onStart() {
                super.onStart();
                progress = ProgressDialog.show(getActivity(), "",
                        "mendownload data", true);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                // Pull out the first event on the public timeline
                JSONObject openObject = null;
                String test = "";
                try {
                    //openObject = jsonArray.getJSONObject()
                    for (int i = 0; i < jsonArray.length(); i++) {
                        dataCatOne.add(new CategoryOneItem(jsonArray.getJSONObject(i).getJSONObject("image").getString("url"), jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("created_at"), jsonArray.getJSONObject(i).getInt("user_id"), jsonArray.getJSONObject(i).getString("content"), jsonArray.getJSONObject(i).getString("category_cd")));
                        image.add(i,jsonArray.getJSONObject(i).getJSONObject("image").getString("url"));
                        title.add(i,jsonArray.getJSONObject(i).getString("title"));
                        created_at.add(i,jsonArray.getJSONObject(i).getString("created_at"));
                        user_id.add(i,jsonArray.getJSONObject(i).getString("user_id"));
                        content.add(i,jsonArray.getJSONObject(i).getString("content"));
                        category_cd.add(i,jsonArray.getJSONObject(i).getString("category_cd"));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(getActivity().getBaseContext(),dataCatOne.get(0).getTitle(),Toast.LENGTH_LONG).show();
                saveArray("image",image);
                saveArray("title",title);
                saveArray("created_at",created_at);
                saveArray("user_id",user_id);
                saveArray("content",content);
                saveArray("category_cd",category_cd);

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
