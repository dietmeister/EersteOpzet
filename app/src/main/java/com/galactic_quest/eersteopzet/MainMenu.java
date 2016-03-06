package com.galactic_quest.eersteopzet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button MainLogInButton          =       (Button)findViewById(R.id.MainLoginButton);
        Button MainLeaderBoardButton    =       (Button)findViewById(R.id.MainLeaderBoardButton);
        Button MainQuitButton           =       (Button)findViewById(R.id.MainQuitButton);
        MainLogInButton.setOnClickListener(this);
        MainLeaderBoardButton.setOnClickListener(this);
        MainQuitButton.setOnClickListener(this);

        Intent startTick; // new intent (hier kan acitivty of service mee gestart worden)
        startTick = new Intent(this, CoreFunctionality.class); // naamgeving en locatie
        startService(startTick);

        CentralDataStorage newDB = new CentralDataStorage(this);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.MainLoginButton:
                Intent logIn;
                logIn = new Intent(this,BaseScreen.class);
                startActivity(logIn);
                break;
            case R.id.MainLeaderBoardButton:
                Intent leaderBoard;
                leaderBoard = new Intent(this,LeaderBoard.class);
                startActivity(leaderBoard);
                break;
            case R.id.MainQuitButton:
                finish();
                Intent stop = new Intent(this, CoreFunctionality.class);
                stopService(stop);
                System.exit(0);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
    protected void onResume() {
        super.onResume();
    }

}

