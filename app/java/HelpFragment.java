package com.gohgai.thaifoodtalker;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * written by Rattanawalee(Rain) Surasorn 5/30/2016
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends Fragment {


    /**
     * I will be creating many of NoImagesFragment objects.
     * Each fragment has different information.
     * I have to synchronized this method to protect data of the Fragment's Bundle.
     * @param header string of the header's name of this fragment
     * @param titles string array of English word/words/sentence
     * @param pronunciations string that explains Thai pronunciation
     * @param audios integer value of the audio ID
     * @param thais string array of Thai word/words/sentence
     * @return NoImagesFragment object
     */
    public static synchronized FoodListFragment getFragmentInstance( String header,
                                                                     String[] titles,
                                                                     String[] pronunciations,
                                                                     int[] audios,
                                                                     String[] thais,
                                                                     int [] images,
                                                                     String[] web_credits){

        FoodListFragment fragment = new FoodListFragment();

           /*
            Android studio does not allow to have a non-default constructor.
            We have to create a method that return an instance of a Fragment, if we want to pass
            information into the Fragment class. Bundle is used to store data info.
            http://stackoverflow.com/questions/9245408/
            best-practice-for-instantiating-a-new-android-fragment
              */

        Bundle bundles = new Bundle();
        bundles.putString("header",header);
        bundles.putStringArray("titles",titles);
        bundles.putStringArray("pronunciations", pronunciations);
        bundles.putStringArray("thais", thais);
        bundles.putIntArray("audios", audios);
        bundles.putIntArray("images", images);
        bundles.putStringArray("web_credits", web_credits);
        fragment.setArguments(bundles);

        return fragment;

    }
    /**
     * store data into EngThaiData objects. These objects are stored in the ArrayList<EngThaiData>
     * @param titles string array of English word/words/sentence
     * @param pronunciations string that explains Thai pronunciation
     * @param audios integer value of the audio ID
     * @param thais string array of Thai word/words/sentence
     * @return ArrayList<EngThaiData> object
     */
    private synchronized ArrayList<FoodEngThaiData> setData_list(String[] titles,
                                                                 String[] pronunciations,
                                                                 int[] audios,
                                                                 String[] thais,
                                                                 int[] images,
                                                                 String[] web_credits) {

        ArrayList<FoodEngThaiData> data_list = new ArrayList<FoodEngThaiData>();

        for(int i = 0; i < titles.length; i++){

            data_list.add(new FoodEngThaiData(titles[i], pronunciations[i], audios[i], thais[i],
                    images[i], web_credits[i], false));
        }
        return data_list;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ArrayList<FoodEngThaiData> data_list = this.setData_list(

                this.getArguments().getStringArray("titles"),
                this.getArguments().getStringArray("pronunciations"),
                this.getArguments().getIntArray("audios"),
                this.getArguments().getStringArray("thais"),
                this.getArguments().getIntArray("images"),
                this.getArguments().getStringArray("web_credits")
        );

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food_list, container, false);
        TextView headerTextView = (TextView) v.findViewById(R.id.food_header);

        String header = (String)getArguments().get("header");

        headerTextView.setText(header);

        final MediaPlayer mediaPlayer = new MediaPlayer();

        final Handler myHandler = new Handler();

        ListView listView = (ListView)v.findViewById(R.id.foodListView);

        listView.setAdapter(new FoodListBaseAdapter(getActivity(),data_list));
        return v;

    }

}
