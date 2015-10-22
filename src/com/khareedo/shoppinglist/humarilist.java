package com.khareedo.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class humarilist extends Activity {
    /**
     * Called when the activity is first created.
     */

    Button showList;
    Button addToList;
    Button clearItem;
    Button profile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.i(Constants.I, "Setting up startup screen");

        // Initializing Buttons
        showList = (Button)findViewById(R.id.showList);
        addToList = (Button)findViewById(R.id.addToList);
        clearItem = (Button)findViewById(R.id.clearItems);
        profile = (Button)findViewById(R.id.profile);

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constants.I, "showList button is clicked" );

                Intent intentShowList = new Intent(getApplicationContext(), ShowList.class);
                startActivity(intentShowList);

            }
        });

        addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Log.i(Constants.I, "addToList button is clicked");
                Intent intentAddToList = new Intent(getApplicationContext(), AddToList.class);
                startActivity(intentAddToList);
            }
        });

        clearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Log.i(Constants.I, "clearItem button is clicked");
                Intent intentClearItem = new Intent(getApplicationContext(), ClearItem.class);
                startActivity(intentClearItem);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Log.i(Constants.I, "profile button is clicked");
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(Constants.I, "Closing ShowList application");
    }

}
