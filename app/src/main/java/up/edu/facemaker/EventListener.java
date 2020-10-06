/*
    @author Kirsten Foster
 */

package up.edu.facemaker;

import android.util.EventLog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class EventListener implements Spinner.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,
        Button.OnClickListener{
    private Face f;

    EventListener(Face faceView) {
        f = faceView;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        f.hairStyle = i;
        f.isRandom = false;
        f.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        f.itemChecked = i;
        if(radioGroup.getCheckedRadioButtonId()==R.id.HairSelector){
            f.rProg = f.rHair;
            f.gProg = f.gHair;
            f.bProg = f.bHair;
            f.itemChecked = 0;
        }
        else if(radioGroup.getCheckedRadioButtonId()==R.id.EyesSelector){
            f.rProg = f.rEye;
            f.gProg = f.gEye;
            f.bProg = f.bEye;
            f.itemChecked = 1;
        }
        else if(radioGroup.getCheckedRadioButtonId()==R.id.SkinSelector){
            f.rProg = f.rSkin;
            f.gProg = f.gSkin;
            f.bProg = f.bSkin;
            f.itemChecked = 2;
        }
        f.isRandom = false;
        f.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(seekBar.getId() == R.id.RedSeekBar){
            f.rProg = i;
        }
        else if(seekBar.getId() == R.id.GreenSeekBar){
            f.gProg = i;
        }
        else if (seekBar.getId() == R.id.BlueSeekBar){
            f.bProg = i;
        }
        if(f.itemChecked == 0){
            f.rHair = f.rProg;
            f.bHair = f.bProg;
            f.gHair = f.gProg;
        }
        else if(f.itemChecked == 1){
            f.rEye = f.rProg;
            f.bEye = f.bProg;
            f.gEye = f.gProg;
        }
        else if(f.itemChecked == 2){
            f.rSkin = f.rProg;
            f.bSkin = f.bProg;
            f.gSkin = f.gProg;
        }
        f.isRandom = false;
        f.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //do nothing
    }

    @Override
    public void onClick(View view) {
        f.isRandom = true;
        f.randomize();
        f.invalidate();
    }
}
