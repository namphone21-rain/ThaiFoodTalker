package com.gohgai.thaifoodtalker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * This Fragment display a quick or short menu of food to users.
 * First, a server will choose available dishes in his/her menu by checking the checkboxes.
 * Then, a foriegner user in Thaliand, will decide what to order and click the more detail button to
 * learn how to pronunce the name of that dish.
 *
 * Future add on. The goal of this app is to solve the problems of how to order and what to eat?
 * 1. users can learn about ingredients
 * 2. users can learn how to cook their favorite dishes
 * 3. users can rate dishes that they have tried
 * 4. users can share their thoughts of food that they have tried
 * 5. users can share cooking videos that they have done themselves
 * 6. users can share their favorite dishes on social medias.
 * 7. users can recommend the must go restaurants for their favorite dishes
 * 8. It will be available in many languages
 *
 * A simple {@link Fragment} subclass.
 *
 *  Created by Rattanawlaee(Rain) Surasorn on 5/16/2016.
 */
public class HomeFragment extends Fragment {

    // ListView of English words/sentences, Thai Pronunciations,
    // Thai audios,and tiny images of a headphones.
    private ListView listView;

    // ItemData object contains an English word/sentence, a Thai Pronunciation, a Thai word/sentence
    // and a Thai audio.
    // ArrayList is a container that contains ItemData. I want to access data according to indexes.
    private ArrayList<FoodEngThaiData> data_list;

    private String[] food_titles; // English worldss

    private String[] food_thais; // Thai words

    private String[] food_pronunciations; //Thai pronunciations

    //calling getIntegerArray gives errors
    //There are no errors by doing this.
    private int[] audios = AudiosArrays.home_audios;

    private String[] web_credits; // websites of images

    //calling getIntegerArray gives errors
    //There are no errors by doing this.
    //I will have all images arrays in ImagesArrays class
    private int[] images = ImagesArrays.HOME_IMAGES;

    private final int LIST_SIZE = 36; // size of the quick menu

    public HomeFragment() {

        // Required empty public constructor
        food_thais = null;
        food_pronunciations = null;
        web_credits = null;
        listView = null;
        data_list = new ArrayList<FoodEngThaiData>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get string array of Thai words
        food_thais = getResources().getStringArray(R.array.recommended_thaifood_thai);

        // get string array of English words
        food_titles = getResources().getStringArray(R.array.recommended_thaifood_title);

        // get string array of images' websites
        web_credits = getResources().getStringArray(R.array.homepage_web_credits);

        //
        food_pronunciations =
                getResources().getStringArray(R.array.recommended_thaifood_pronunciations);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //get the ListView
        listView = (ListView)v.findViewById(R.id.homeListView);

        //generate information to store in FoodEngThaiData objects and add these objects into
        //ArrayList
        for(int i = 0; i < LIST_SIZE; i++){
            data_list.add(new FoodEngThaiData(food_titles[i], food_pronunciations[i], audios[i],
                    food_thais[i], images[i], web_credits[i], false ));
        }

        //the ListView object needs to call setAdapter to generate the view of each row in the list
        listView.setAdapter(new FoodListBaseAdapter(getActivity(), data_list));

        return v;
    }

}
