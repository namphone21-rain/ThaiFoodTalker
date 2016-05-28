package com.gohgai.thaifoodtalker;

/**
 * Store information of an audio of a Thai pronunciation, written Thai pronunciation,
 * Thai word or sentence, English word or sentence that will be a title.
 * Created by Rattanawlaee(Rain) Surasorn on 5/21/2016.
 */
public class EngThaiData {

   private String title; // a word or a sentence in English
   private String pronunciation; // Thai pronunciation
   private String thai; // a word or a sentence in Thai
   private int audio; // an audio of Thai pronunciation
    
    /**
     * Constructor that set all data
     * @param title an English word or sentence
     * @param pronunciation Thai pronunciation
     * @param audio an integer of an audio from a raw folder
     * @param thai a Thai word or sentence
     */
    public EngThaiData(String title, String pronunciation, int audio, String thai) {
        this.title = title;
        this.pronunciation = pronunciation;
        this.audio = audio;
        this.thai = thai;
    }

    public String getTitle() { return title; }

    public String getPronunciation() {return pronunciation;}

    public String getThai(){ return thai; }

    public int getAudio(){return audio;}

    @Override
    public String toString(){
        return "title:" + getTitle() + " Thai:" + getThai() +
                " pronunciation:" +getPronunciation()+ " audio:"+getAudio();
    }
    
    
}
