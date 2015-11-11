package com.openetizen.cevysays.opennews.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.openetizen.cevysays.opennews.R;
import com.openetizen.cevysays.opennews.adapters.AgendaAdapter;
import com.openetizen.cevysays.opennews.fragments.NavigationDrawerFragment;
import com.openetizen.cevysays.opennews.models.AgendaItem;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class AgendaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Toolbar mToolbar;
    private JazzyListView listView;
    private ArrayList<AgendaItem> agendaArrayList;
    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);

        setSupportActionBar(mToolbar);
        listView = (JazzyListView)
                findViewById(R.id.list);
        listView.setTransitionEffect(JazzyHelper.GROW);
        listView.setOnItemClickListener(this);
        new DownloadData().execute();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    private class DownloadData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            ObjectMapper mapper = new ObjectMapper();
            agendaArrayList = null;
            while (agendaArrayList == null) {
                try {
                    agendaArrayList = mapper.readValue(new URL(AgendaItem.API),
                            mapper.getTypeFactory().constructCollectionType(ArrayList.class, AgendaAdapter.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }
    }


}
