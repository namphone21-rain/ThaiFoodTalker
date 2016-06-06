package com.gohgai.thaifoodtalker;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import android.os.Handler;


/**
 * This BaseAdater is for the ListView that contains food images, food name in Thai,
 * buttons for more info and TextViews that show resources of images
 *
 * Created by Rattanawalee Surasorn on 5/21/2016.
 */
public class FoodListBaseAdapter extends BaseAdapter{

    private ViewHolder viewHolder; // views recycling purpose

    private ArrayList<FoodEngThaiData> data_list; // information that will be display to users

    private Activity activity; // is needed to create a popup window

    private LayoutInflater inflater; //have to inflate the view of each row in the ListView

    private Handler myHandler; // is needed to be part of the popup window's activity

    private MediaPlayer mediaPlayer; //Manage audios. it is needed in the popup window

    /**
     *
     * @param activity an activity that was attached in the Fragment class is passed through this
     * @param data_list FoodEngThaiData objects are stored in the ArrayList. We use the information
     *                  from FoodEngThaiData objects to create views in the ListView
     */
    FoodListBaseAdapter(Activity activity, ArrayList<FoodEngThaiData> data_list){

        this.viewHolder = null;
        this.data_list = data_list;
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        mediaPlayer = new MediaPlayer();
        myHandler = new Handler();

    }

    @Override
    public int getCount() {
        return data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return data_list.get(position);
    }

    //I won't use this method
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

       viewHolder = null;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.one_row_foodlayout,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        FoodEngThaiData data = (FoodEngThaiData)getItem(position);

        viewHolder.thaiDish.setText(data.getThai() + "\n(" + data.getTitle() + ")");

        viewHolder.web_credit_textView.setText(data.getWeb_credit());

        viewHolder.foodImage.setBackgroundResource(data.getImage());

        viewHolder.thaiDish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //The link bellow explains how we protect the checkbox's value not to jump around
                //http://lalit3686.blogspot.in/2012/06/today-i-am-going-to-show-how-to-deal.html

                int pos = (Integer)(buttonView.getTag());
                FoodEngThaiData tempData = data_list.get(pos);
                tempData.setChosen(buttonView.isChecked());
            }
        });

        //set this tag. so we can retrieve information to update the views
        viewHolder.thaiDish.setTag(position);

        //data has been updated in the setOnCheckedChangeListener.
        // Set the checkbox's value accordingly.
        viewHolder.thaiDish.setChecked(data.getIsChosen());

        //create a press/click button's action. The action is, a popup window is displayed.
        viewHolder.moreDetailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos = (Integer)v.getTag();
                FoodEngThaiData data = data_list.get(pos);

                /* popup window will be displayed when button is click
                *  It displays an English and a Thai word or sentence,
                *  Thai pronunciation and an audio.
                *  Link bellow shows how to create a popup window
                * http://android-er.blogspot.com/2012/03/example-of-using-popupwindow.html
                */

                LayoutInflater layoutInflater = (LayoutInflater)activity.getBaseContext()
                        .getSystemService(activity.LAYOUT_INFLATER_SERVICE);

                View popupView = layoutInflater.inflate(R.layout.popup_window_layout, null);

                MyPopUpWindow myPopUpWindow
                        = new MyPopUpWindow(v.getContext(),popupView, data, mediaPlayer, myHandler);

                myPopUpWindow.setFocusable(true);

                myPopUpWindow.showAtLocation(v, Gravity.CENTER,0,0);
            }
        });
        viewHolder.moreDetailButton.setTag(position);
        return convertView;
    }

    /**
     * View Holder is needed for recycling views in the ListView
     */
    private class ViewHolder{

        CheckBox thaiDish;
        ImageView foodImage;
        TextView web_credit_textView;
        ImageButton moreDetailButton;

        public ViewHolder(View v) {

            this.moreDetailButton = (ImageButton)v.findViewById(R.id.more_detail_button);
            this.thaiDish = (CheckBox) v.findViewById(R.id.checkbox);
            this.foodImage = (ImageView)v.findViewById(R.id.food_image);
            this.web_credit_textView = (TextView)v.findViewById(R.id.web_credit_text);
        }
    }
}
