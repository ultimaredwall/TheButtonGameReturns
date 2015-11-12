package ashwiniyer.thebuttongamereturns;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Ashwin on 06/10/2015.
 */
public class Colours {
    public String[] mColours = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    //Method (abilities: things the object can do)
    public int getColour() {

        String colour = "";

        //Randomly select a fact
        Random randomGenerator = new Random(); //Telling the app to construct a new random number generator named randomGenerator
        int randomNumber = randomGenerator.nextInt(mColours.length);

        colour = mColours[randomNumber];

        int colourAsInt = Color.parseColor(colour);

        return colourAsInt;


    }
}