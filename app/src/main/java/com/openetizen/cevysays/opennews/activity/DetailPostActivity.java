package com.openetizen.cevysays.opennews.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.openetizen.cevysays.opennews.R;
import com.openetizen.cevysays.opennews.models.CategoryOneItem;
import com.squareup.picasso.Picasso;

public class DetailPostActivity extends ActionBarActivity {
    private ImageView gambar;
    private TextView judul;
    private TextView tanggal;
    private TextView deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);


        gambar = (ImageView) findViewById(R.id.gambar);
        judul = (TextView) findViewById(R.id.judul);
        tanggal = (TextView) findViewById(R.id.tanggal);
        deskripsi = (TextView) findViewById(R.id.deskripsi);

        CategoryOneItem post =
                getIntent().getParcelableExtra("post");
        Picasso.with(this)
                .load("http://openetizen.com"+post.getImage())
                .into(gambar);
        judul.setText(post.getTitle());
        Log.e("TEST", post.getTitle() + " " + post.getImage() + " " + post.getCreated_at() + " " + post.getContent());
        tanggal.setText(post.getCreated_at());

        deskripsi.setText(Html.fromHtml(post.getContent()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_post, menu);
//        MenuItemCompat shareItem = (MenuItemCompat) menu.findItem(R.id.action_share);
//
//
//
//        ShareActionProvider mShare = (ShareActionProvider)shareItem.getActionProvider();
//
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, "text to share");
//
//        mShare.setShareIntent(shareIntent);
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
        } else if (id == R.id.action_share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "link");
            startActivity(Intent.createChooser(intent, "Share with"));
        }

        return super.onOptionsItemSelected(item);
    }
}
