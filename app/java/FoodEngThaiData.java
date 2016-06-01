package com.gohgai.thaifoodtalker;

import android.graphics.Color;

/**
 * Store information of an audio of a Thai pronunciation, written Thai pronunciation,
 * Thai word or sentence, English word or sentence that will be a title.
 * Created by Rattanawalee(Rain) Surasorn on 5/21/2016.
 */
public class FoodEngThaiData extends EngThaiData {

    private int image;
    private String web_credit;
    private boolean isChosen;

    /**
     *
     * Constructor that set all data
     * @param title an English word or sentence
     * @param pronunciation Thai pronunciation
     * @param audio an integer of an audio from a raw folder
     * @param thai a Thai word or sentence
     * @param image food image
     * @param web_credit text of the website of the food image that I have found
     * @param isChosen store the checkbox value. The checkboxes are in the ListView of food menus
     */
    FoodEngThaiData(String title, String pronunciation, int audio, String thai, int image,
                    String web_credit, boolean isChosen){

        super(title,pronunciation,audio,thai);
        this.image = image;
        this.web_credit = web_credit;
        this.isChosen = isChosen;
    }

    public void setChosen(boolean isChosen){
        this.isChosen = isChosen;
    }

    public int getImage(){return image;}

    public String getWeb_credit(){return web_credit;}

    public boolean getIsChosen(){return isChosen;}

    @Override
    public String toString(){
        return super.toString() + " image:" + getImage() + " web_credit:" +
                getWeb_credit() + " isChosen:" + getIsChosen();
    }
}
