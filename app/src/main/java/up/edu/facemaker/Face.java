package up.edu.facemaker;

import android.graphics.Color;

import java.util.*;

public class Face {
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    public Face() {
        randomize();
    }

    private void randomize() {
        Random rand = new Random();

        /*
          External Citation
               Date: Sept 9, 2020
               Problem: Could not figure out how to create random color integer
               Resource:
                   https://stackoverflow.com/questions/5280367/android-generate-random-color-on
                   -click#:~:text=Random%20rnd%20%3D%20new%20Random()
                   %3B%20int%20color%20%3D%20Color.,nextInt(256)%2C%20rnd.
               Solution: Used sample code from this post.
         */

        //for each color, random RGB values are selected
        skinColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        eyeColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));
        hairColor = Color.argb(255, rand.nextInt(256), rand.nextInt(256),
                rand.nextInt(256));

        //random value 0-2 selected (0 = long hair, 1 = short hair, 2 = bald)
        hairStyle = rand.nextInt(3);
    }
}
