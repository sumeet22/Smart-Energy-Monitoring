package com.example.smartenergymonitoring.tips;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.smartenergymonitoring.R;
import com.example.smartenergymonitoring.models.Tips;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TipsAdapter implements ListAdapter {
    ArrayList<Tips> arrayList;
    Context context;

    public TipsAdapter(Context context, ArrayList<Tips> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tips tips = arrayList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.listitem, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(tips.Link + context.getPackageName()));
//                    i.setData(Uri.parse(tips.Link));
                    context.startActivity(i);
                    //Toast.makeText(context,subjectData.Link,Toast.LENGTH_LONG).show();
                }
            });
            TextView tittle = convertView.findViewById(R.id.title);
            TextView consumtion = convertView.findViewById(R.id.consumtion);
            consumtion.setText(tips.description);
            ImageView imag = convertView.findViewById(R.id.list_image);
            tittle.setText(tips.title);
            Picasso.with(context)
                    .load(tips.Image)
                    .into(imag);
//            imag.setImageResource(subjectData.Image);

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }


    @Override
    public boolean isEmpty() {
        return false;
    }
}
