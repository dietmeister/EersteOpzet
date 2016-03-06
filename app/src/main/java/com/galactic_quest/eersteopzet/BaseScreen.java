package com.galactic_quest.eersteopzet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseScreen extends Activity implements View.OnClickListener {

    private Object lock = new Object();

    private TextView baseTickVar;
    private Button baseResourcesButton;
    private Button baseFleetButton;
    private Button baseNewsButton;
    private Button baseFactoryButton;
    private Button baseTickButton;
    private int tickNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_screen);

        baseResourcesButton          =       (Button)findViewById(R.id.baseButtonResources);
        baseFleetButton              =       (Button)findViewById(R.id.baseButtonFleet);
        baseNewsButton               =       (Button)findViewById(R.id.baseButtonNews);
        baseFactoryButton            =       (Button)findViewById(R.id.baseButtonFactory);
        baseTickButton               =       (Button)findViewById(R.id.basetickbutton);

        baseTickVar                  =       (TextView)findViewById(R.id.baseTickVar);

        baseResourcesButton.setOnClickListener(this);
        baseFleetButton.setOnClickListener(this);
        baseNewsButton.setOnClickListener(this);
        baseFactoryButton.setOnClickListener(this);
        baseTickButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base_screen, menu);
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

    public void onResume() {
        baseTickVar.setText("8");
        Log.i("a","b");
        super.onResume();
        this.onCreate(null);
    }

    @Override
    public void onClick(View baseview) {

        switch(baseview.getId()){

            case(R.id.baseButtonResources):
                Intent Resources;
                Resources = new Intent(this,ResourcesScreen.class);
                startActivity(Resources);
                break;

            case(R.id.baseButtonFleet):
                Intent Fleet;
                Fleet = new Intent(this,FleetScreen.class);
                startActivity(Fleet);
                break;

            case(R.id.baseButtonNews):
                Intent News;
                News = new Intent(this,NewsScreen.class);
                startActivity(News);
                break;

            case(R.id.baseButtonFactory):
                Intent Factory;
                Factory = new Intent(this,FactoryScreen.class);
                startActivity(Factory);
                break;

            case(R.id.basetickbutton):
                tickNumber++;
                baseTickVar.setText("" + tickNumber);
        }
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            baseTickVar.setText("" + msg);
        }
    }
}
