package com.gohgai.thaifoodtalker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Rattanawalee Surasorn on 4/27/16.
 * This code is reference from http://www.pcsalt.com/android/listview-using-baseadapter-android/
 */
public class NoImagesBaseAdapter extends BaseAdapter {

    ArrayList<EngThaiData> dataList;
    LayoutInflater inflater;
    Context context;

    public NoImagesBaseAdapter(Context context, ArrayList<EngThaiData> dataList){

        this.dataList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    //I won't use this method
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){

            convertView = inflater.inflate(R.layout.row_no_images_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        EngThaiData data = (EngThaiData)getItem(position);
        viewHolder.titleTextView.setText(data.getTitle());
        viewHolder.pronunciationTextView.setText(data.getPronunciation());
        return convertView;
    }

    private class ViewHolder{
        TextView titleTextView, pronunciationTextView;

        public ViewHolder(View v) {
            this.titleTextView = (TextView)v.findViewById(R.id.title_textView);
            this.pronunciationTextView = (TextView)v.findViewById(R.id.pronunciationTextView);
        }
    }
}
