package com.galactic_quest.eersteopzet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class ResourcesScreen extends Activity implements View.OnClickListener {

    private int tickNumber;
    private Button resourcesExploreCrystalMines;
    private Button resourcesExploreMetalMines;
    private Button resourcesTickButton;

    private TextView resourcesNumberOfCrystalMines;
    private TextView resourcesNumberOfMetalMines;
    private TextView resourcesTickVar;

    private EditText resourcesEditTextnumberCrystal;
    private EditText resourcesEditTextnumberMetal;

    private int numberCrystalMines;
    private int numberMetalMines;

    private int crystalExplorePartiesTotal;
    private int metalExplorePartiesTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_screen);

        resourcesExploreCrystalMines =      (Button)findViewById(R.id.resourcesExploreCrystalMines);
        resourcesExploreMetalMines =        (Button)findViewById(R.id.resourcesExploreMetalMines);
        resourcesTickButton =               (Button)findViewById(R.id.resourcestickbutton);

        resourcesTickVar =                  (TextView)findViewById(R.id.ResourcesTickVar);
        resourcesNumberOfCrystalMines =     (TextView)findViewById(R.id.resourcesNumberofCrystalMines);
        resourcesNumberOfMetalMines =       (TextView)findViewById(R.id.resourcesNumberofMetalMines);

        resourcesEditTextnumberCrystal =    (EditText)findViewById(R.id.resourcesTextViewExploreCrystalMines);
        resourcesEditTextnumberMetal =      (EditText)findViewById(R.id.resourcesTextViewExploreMetalMines);

        resourcesTickButton.setOnClickListener(this);
        resourcesExploreCrystalMines.setOnClickListener(this);
        resourcesExploreMetalMines.setOnClickListener(this);

        numberCrystalMines = 0;
        numberMetalMines = 0;

        crystalExplorePartiesTotal = 0;
        metalExplorePartiesTotal = 0;

        resourcesNumberOfCrystalMines.setText("" + numberCrystalMines);
        resourcesNumberOfMetalMines.setText("" + numberMetalMines);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resources_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View buttons) {
        switch(buttons.getId()){

            case(R.id.resourcesExploreCrystalMines):
                if(Integer.parseInt(resourcesEditTextnumberCrystal.getText().toString()) > 0) {
                    int numberCrystaltoExplore = Integer.parseInt(resourcesEditTextnumberCrystal.getText().toString());

                    CentralDataStorage db = new CentralDataStorage(this);

                    int crystalexplored = 1 * numberCrystaltoExplore;
                    long returnAdd = db.add("crystal", crystalexplored);
                    resourcesNumberOfCrystalMines.setText("" + db.get(returnAdd));

                    break;

                }
                else {
                    Toast.makeText(getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
                }

            case(R.id.resourcesExploreMetalMines):
                if(Integer.parseInt(resourcesEditTextnumberMetal.getText().toString()) > 0) {
                    int numberMetaltoExplore = Integer.parseInt(resourcesEditTextnumberMetal.getText().toString());

                    CentralDataStorage db = new CentralDataStorage(this);

                    int metalexplored = 1 * numberMetaltoExplore;
                    long returnAdd = db.add("metal", metalexplored);
                    resourcesNumberOfMetalMines.setText("" + db.get(returnAdd));

                    break;

                }
                else {
                    Toast.makeText(getApplicationContext(), "Not a valid number", Toast.LENGTH_SHORT).show();
                }

            case(R.id.resourcestickbutton):
                tickNumber++;
                resourcesTickVar.setText("" + tickNumber);
        }
    }
}
