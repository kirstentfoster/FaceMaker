/*
  @author Kirsten Foster
 */

package up.edu.facemaker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;

import java.util.*;

public class Face extends SurfaceView {
    int rSkin;
    int gSkin;
    int bSkin;
    int rEye;
    int gEye;
    int bEye;
    int rHair;
    int gHair;
    int bHair;
    int hairStyle;
    int itemChecked; //0=hair, 1=eyes, 2=skin
    int rProg;
    int gProg;
    int bProg;
    boolean isRandom = true;

    /*
          External Citation
               Date: Oct 6, 2020
               Problem: Could not make widgets display correct values upon randomize
               Resource:
                   Emily Hoppe
               Solution: She guided me through this
         */

    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;
    private Spinner hairSelect;
    public Face(Context context, AttributeSet attrs) {
        super(context,attrs);

        setWillNotDraw(false);

        setBackgroundColor(Color.WHITE);
    }

    public void randomize() {
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
        rSkin = rand.nextInt(256);
        gSkin = rand.nextInt(256);
        bSkin = rand.nextInt(256);

        rEye = rand.nextInt(256);
        gEye = rand.nextInt(256);
        bEye = rand.nextInt(256);

        rHair = rand.nextInt(256);
        gHair = rand.nextInt(256);
        bHair = rand.nextInt(256);

        //random value 0-2 selected (0 = long hair, 1 = short hair, 2 = bald)
        hairStyle = rand.nextInt(3);

    }

    public void setRand(SeekBar red, SeekBar green, SeekBar blue, Spinner hair){
        redBar = red;
        greenBar = green;
        blueBar = blue;
        hairSelect = hair;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawSkin(Canvas canvas, int r, int g, int b){
        Paint skin = new Paint();
        skin.setColor(Color.rgb(r,g,b));
        canvas.drawOval(200, 200, 800, 800, skin);
    }

    public void drawHair (Canvas canvas, int r, int g, int b){
        Paint hair = new Paint();
        hair.setColor(Color.rgb(r,g,b));
        hair.setStrokeWidth(100);
        if(hairStyle==0) { // long hair
            canvas.drawLine(500, 200, 900, 550, hair);
            canvas.drawLine(500, 200, 100, 550, hair);
            canvas.drawLine(850, 500, 900, 850, hair);
            canvas.drawLine(150, 500, 100, 850, hair);
        }
        else if(hairStyle==1){ //short hair
            canvas.drawLine(500, 200, 900, 550, hair);
            canvas.drawLine(500, 200, 100, 550, hair);
        }
        //bald = do nothing
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawEyes(Canvas canvas, int r, int g, int b){
        Paint eye = new Paint();
        eye.setColor(Color.WHITE);
        canvas.drawOval(345, 395, 415, 465, eye);
        canvas.drawOval(595, 395, 665, 465, eye);
        eye.setColor(Color.rgb(r,g,b));
        canvas.drawOval(350, 400, 400, 450, eye);
        canvas.drawOval(600, 400, 650, 450, eye);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawMouthandNose(Canvas canvas){
        Paint mouth = new Paint();
        mouth.setColor(Color.RED);
        canvas.drawArc(350, 600, 650, 700, 0, 180, false, mouth);
        Paint nose = new Paint();
        nose.setColor(Color.RED);
        canvas.drawOval(475, 475, 525, 525, nose);
    }

    public void drawRandom(){
        if(itemChecked == 0){
            redBar.setProgress(rHair);
            greenBar.setProgress(gHair);
            blueBar.setProgress(bHair);
        }
        else if(itemChecked == 1){
            redBar.setProgress(rEye);
            greenBar.setProgress(gEye);
            blueBar.setProgress(bEye);
        }
        else if(itemChecked == 2){
            redBar.setProgress(rSkin);
            greenBar.setProgress(gSkin);
            blueBar.setProgress(bSkin);
        }
        hairSelect.setSelection(hairStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onDraw(Canvas canvas) {
        if(this.isRandom){
            this.randomize();
        }
        drawSkin(canvas, rSkin, gSkin, bSkin);
        drawEyes(canvas, rEye, gEye, bEye);
        drawHair(canvas, rHair, gHair, bHair);
        drawMouthandNose(canvas);
        drawRandom();
    }
}
