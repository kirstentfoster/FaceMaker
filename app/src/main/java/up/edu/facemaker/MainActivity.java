/*
  @author Kirsten Foster
 */

package up.edu.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Face f = findViewById(R.id.FaceDisplay);

        EventListener controller = new EventListener(f);

        /*
          External Citation
               Date: Oct 2, 2020
               Problem: Struggled with Spinner implementation
               Resource:
                   https://developer.android.com/guide/topics/ui/controls/spinner
               Solution: Followed given examples.
         */

        Spinner hairSpin = findViewById(R.id.HairSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.HairSpinnerEntries, android.R.layout.simple_spinner_item);
        hairSpin.setOnItemSelectedListener(controller);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpin.setAdapter(adapter);

        /*
          External Citation
               Date: Oct 4, 2020
               Problem: Struggled with Radio Buttons
               Resource:
                   https://stackoverflow.com/questions/18179124/android-getting-value-from-selected-radiobutton
               Solution: Followed given examples.
         */

        RadioGroup HES = findViewById(R.id.Hair_Eyes_SkinOption);
        HES.setOnCheckedChangeListener(controller);

        SeekBar red = findViewById(R.id.RedSeekBar);
        SeekBar green = findViewById(R.id.GreenSeekBar);
        SeekBar blue = findViewById(R.id.BlueSeekBar);

        red.setOnSeekBarChangeListener(controller);
        blue.setOnSeekBarChangeListener(controller);
        green.setOnSeekBarChangeListener(controller);

        Button rand = findViewById(R.id.RandomFaceButton);
        rand.setOnClickListener(controller);

        f.setRand(red, green, blue, hairSpin);
    }
}


