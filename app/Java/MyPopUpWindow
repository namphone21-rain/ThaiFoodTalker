package com.gohgai.thaifoodtalker;

import android.app.ActionBar;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Customize popup window
 * This popup window displays
 * 1. English word or sentence
 * 2. Thai pronunciation
 * 3. Thai word or sentence
 * 4. an audio with SeekBar, play button and views of audio's times
 *
 * Created by Rattanawalee Surasorn on 5/6/16.
 */
public class MyPopUpWindow extends PopupWindow {

    View view; // view from a fragment

    EngThaiData data; // an object that contains information that will be used in the popup window

    MediaPlayer mediaPlayer; // manage and audio

    Context context; // context from a fragment

    double finalTime, startTime; //display times of an audio

    Handler myHandler; // helps move the seekbar

    TextView timeMovingTextView; //display changes of time when an audio is displayed

    SeekBar audioProgressSeekBar; //seekbar the shows the progress of an audio

    final long DELAY_MILLI_SECONDS = 100; // the time delay in seekbar

    ImageButton playPauseButton; //press play and pause an audio


    /**
     *
     * @param context Context object from a fragment
     * @param view View object from a fragment
     * @param data EngThaiData object that stores information of the dish
     * @param mediaPlayer Mediaplayer object helps display an audio of Thai pronunciation
     * @param myHandler Handler object helps updating the seekbar
     */
    MyPopUpWindow(Context context, View view, EngThaiData data, MediaPlayer mediaPlayer,
                  Handler myHandler){

        super(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        this.view = view;
        this.data = data;
        this.mediaPlayer = mediaPlayer;
        this.context = context;
        finalTime = 0.0;
        startTime = 0.0;
        this.myHandler = myHandler;
        playPauseButton = (ImageButton)view.findViewById(R.id.playPauseButton);

        prePareAudio(); // initialize MediaPlayer Object

        setUpCloseButton(); // the user can close this popup window

        setUpDisplayText(); // display all text of food's information

        setUpAudioDurationText(); // text shows how long is an audio

        setUpPlayPauseButton(); // play and pause an audio

        playAudioAndSeekBar(); // seekbar shows the progress an audio

    }

    /**
     * add action the a close button. It closes the popup window.
     */
    private void setUpCloseButton(){

        ImageButton closeButton = (ImageButton)view.findViewById(R.id.close_popupWindow_button);

        // when this window is closed, MediaPlayer object will be reset.
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                dismiss();
            }
        });
    }

    /**
     *Displays texts of information of the food
     */
    private void setUpDisplayText(){

        // Displays an English Description of the food
        TextView titleTextView = (TextView)view.findViewById(R.id.popup_title_textView);
        titleTextView.setText(" "+data.getTitle());

        //Displays the text about how to pronounce the food
        TextView pronunciation = (TextView)view.findViewById(R.id.popup_pronounce_textview);
        pronunciation.setText(" "+data.getPronunciation());

        //Displays the thai text
        TextView thaiView = (TextView)view.findViewById(R.id.popup_thai_textview);
        thaiView.setText(" "+data.getThai());

    }


    /**
     * Displays the length of this audio
     * 
     * reference from http://www.tutorialspoint.com/android/android_mediaplayer.htm
     */
    private void setUpAudioDurationText(){
        finalTime = mediaPlayer.getDuration();
        TextView finalDurationTextView = (TextView)view.findViewById(R.id.final_duration_textview);
        finalDurationTextView.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
        );
    }

    /**
     * initialize MediaPlayer object
     */
    private void prePareAudio(){

        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer.create(context, data.getAudio());

        } else {

            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(context, data.getAudio());

        }

    }

    /**
     * initialize seekbar
     * set the maximum time of the seekbar
     * set the start time of the seekbar
     * run the seekbar
     * display the text of moving time
     */
    private void playAudioAndSeekBar(){

        //initialize seekbar
        audioProgressSeekBar = (SeekBar)view.findViewById(R.id.audio_progressSeekBar);

        //set the maximum time of the seekbar
        audioProgressSeekBar.setMax((int)finalTime);

        //initialize the time textview
        timeMovingTextView = (TextView)view.findViewById(R.id.current_duration_textview);

        //start the audio
        mediaPlayer.start();

        //get the starting time
        startTime = mediaPlayer.getCurrentPosition();

        //display time that is moving forward
        //reference from http://www.tutorialspoint.com/android/android_mediaplayer.htm
        timeMovingTextView.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
        );

        //start the seekbar
        runSeekBar();
    }

    /**
     * set the action of a button that it does play or pause an audio
     */
    private void setUpPlayPauseButton(){

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reference from http://www.tutorialspoint.com/android/android_mediaplayer.htm
                if(!mediaPlayer.isPlaying()) {

                    mediaPlayer.start();

                    startTime = mediaPlayer.getCurrentPosition();
                    
                    timeMovingTextView.setText(String.format("%d:%d",
                                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                            TimeUnit.MINUTES.toSeconds(
                                                    TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
                    );
                    runSeekBar();

                }else{
                    mediaPlayer.pause();
                }

            }
        });


    }

    //make the seekbar moves
    private void runSeekBar(){

        audioProgressSeekBar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateAudioTime, DELAY_MILLI_SECONDS); //delay 100 millisecond
    }


    //it helps make the seekbar move and the textview of time is updated as well
    //reference from http://www.tutorialspoint.com/android/android_mediaplayer.htm
    private Runnable UpdateAudioTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            timeMovingTextView.setText(String.format("%d:%d",

                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                            toMinutes((long) startTime)))
            );
            audioProgressSeekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, DELAY_MILLI_SECONDS); //delay 100 milliseconds
        }
    };
}
