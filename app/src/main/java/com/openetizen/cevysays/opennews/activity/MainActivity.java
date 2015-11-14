package com.openetizen.cevysays.opennews.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.openetizen.cevysays.opennews.fragments.AgendaFragment;
import com.openetizen.cevysays.opennews.fragments.CategoryOneFragment;
import com.openetizen.cevysays.opennews.fragments.DocumentFragment;
import com.openetizen.cevysays.opennews.fragments.GalleryFragment;
import com.openetizen.cevysays.opennews.util.NavigationDrawerCallbacks;
import com.openetizen.cevysays.opennews.R;
import com.openetizen.cevysays.opennews.fragments.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);


//        ImageView login = (ImageView) findViewById(R.id.login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Starting a new Intent for login
//                Intent i = new Intent(MainActivity.this, LoginActivity.class);
//
//                startActivity(i);
//            }
//        });

        LinearLayout login = (LinearLayout) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(i);
            }
        });

        LinearLayout about = (LinearLayout) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Starting a new Intent for about
                Intent i = new Intent(MainActivity.this, AboutActivity.class);

                startActivity(i);
            }
        });

        LinearLayout help = (LinearLayout) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Starting a new Intent for about
                Intent i = new Intent(MainActivity.this, HelpActivity.class);

                startActivity(i);
            }
        });




        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData(BitmapFactory.decodeResource(getResources(), R.drawable.login), BitmapFactory.decodeResource(getResources(), R.drawable.googleplus), BitmapFactory.decodeResource(getResources(), R.drawable.facebook));

//         display the first navigation drawer view on app launch
        onNavigationDrawerItemSelected(0);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction
                = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.container, new CategoryOneFragment());
                transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
                //getSupportActionBar().setTitle(R.string.title_news);
                break;
            case 1:
                transaction.replace(R.id.container, new AgendaFragment());
                transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
                getSupportActionBar().setTitle(R.string.title_agenda);

                break;
            case 2:
                transaction.replace(R.id.container,new GalleryFragment());
                transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
                getSupportActionBar().setTitle(R.string.title_gallery);
                break;
            case 3:
                transaction.replace(R.id.container, new DocumentFragment());
                transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
                getSupportActionBar().setTitle(R.string.title_document);
                break;

            default:
                break;
        }

//        coba
//        Fragment fragment = null;
//        String title = getString(R.string.app_name);
//        switch (position) {
//            case 0:
//                fragment = new MainFragment();
//                title = getString(R.string.title_news);
//                break;
//            case 1:
//                fragment = new AgendaFragment();
//                title = getString(R.string.title_agenda);
//                break;
//            case 2:
//                fragment = new GalleryFragment();
//                title = getString(R.string.title_gallery);
//                break;
//            case 3:
//                fragment = new DocumentFragment();
//                title = getString(R.string.title_document);
//            default:
//                break;
//        }
//
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            android.support.v4.app.FragmentTransaction transaction
//                    = fragmentManager.beginTransaction();
//            transaction.replace(R.id.container, fragment);
//            transaction.commit();
//
//            // set the toolbar title
//            getSupportActionBar().setTitle(title);
//        }

    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
}

