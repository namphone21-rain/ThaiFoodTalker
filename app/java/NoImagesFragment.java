package com.gohgai.thaifoodtalker;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Rattanawalee Surasorn on 5/13/2016.
 *
 * This fragment will have no food images in the list view.
 * The list view contains, English words/sentences, Thai Pronunciations, Thai audios,
 * and tiny images of a headphones.A tiny headphone icon is a symbol to let users know
 * that users can listen to an audio.
 */
public class NoImagesFragment extends Fragment {


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
    public static synchronized NoImagesFragment getFragmentInstance( String header, String[] titles,
                                                        String[] pronunciations,
                                                        int[] audios, String[] thais){

        NoImagesFragment fragment = new NoImagesFragment();

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
    private synchronized ArrayList<EngThaiData> setData_list( String[] titles,
                                                 String[] pronunciations,
                                                 int[] audios, String[] thais) {

    //  FoodEngThaiData(String title, String pronunciation, int audio, String thai, int image,
    //  String web_credit, boolean isChosen)
        ArrayList<EngThaiData>data_list = new ArrayList<EngThaiData>();

        for(int i = 0; i < titles.length; i++){

            data_list.add(new EngThaiData(titles[i], pronunciations[i], audios[i],
                    thais[i])
            );
        }
        return data_list;
    }


    /**
     *  http://www.tutorialspoint.com/android/android_mediaplayer.htm
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ArrayList<EngThaiData> data_list = this.setData_list(

                          this.getArguments().getStringArray("titles"),
                          this.getArguments().getStringArray("pronunciations"),
                          this.getArguments().getIntArray("audios"),
                          this.getArguments().getStringArray("thais")
        );

        final View v = inflater.inflate(R.layout.fragment_no_images, container, false);

        TextView headerTextView = (TextView) v.findViewById(R.id.header_textview);

        String header = (String)getArguments().get("header");

        headerTextView.setText(header);

        final MediaPlayer mediaPlayer = new MediaPlayer();

        final Handler myHandler = new Handler();

        ListView listView = (ListView)v.findViewById(R.id.listView);

        listView.setAdapter(new NoImagesBaseAdapter(v.getContext(),data_list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), "position:" + position,
                        Toast.LENGTH_SHORT).show();

                EngThaiData data = data_list.get(position);

                /* popup window will be displayed if an item is selected.
                * http://android-er.blogspot.com/2012/03/example-of-using-popupwindow.html
                */

                LayoutInflater layoutInflater = (LayoutInflater)getActivity().getBaseContext()
                        .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);

                View popupView = layoutInflater.inflate(R.layout.popup_window_layout, null);

                MyPopUpWindow myPopUpWindow
                        = new MyPopUpWindow(v.getContext(),popupView, data, mediaPlayer, myHandler);

                myPopUpWindow.setFocusable(true);

                myPopUpWindow.showAtLocation(v, Gravity.CENTER,0,0);

            }
        });


        return v;
    }

}
