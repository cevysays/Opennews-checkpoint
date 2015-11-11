package com.openetizen.cevysays.opennews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.openetizen.cevysays.opennews.R;

/**
 * Created by cevyyufindra on 11/8/15.
 */
public class MenuShareAdapter extends BaseAdapter
{
    private LayoutInflater layoutInflater;

    public MenuShareAdapter(Context context)
    {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return 8;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        View view = convertView;
        if (view == null)
        {
            view = layoutInflater.inflate(R.layout.menu_item_share, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.text_view);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image_view);
            view.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        switch (position)
        {
            case 0:
                viewHolder.textView.setText("Google Plus");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 1:
                viewHolder.textView.setText("Email");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 2:
                viewHolder.textView.setText("Messenger");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 3:
                viewHolder.textView.setText("WhatsApp");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 4:
                viewHolder.textView.setText("Messaging");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 5:
                viewHolder.textView.setText("Facebook");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 6:
                viewHolder.textView.setText("Twitter");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
            case 7:
                viewHolder.textView.setText("More");
                viewHolder.imageView.setImageResource(R.drawable.ic_about);
                break;
        }

        return view;
    }

    private static class ViewHolder
    {
        TextView textView;
        ImageView imageView;
    }

}
