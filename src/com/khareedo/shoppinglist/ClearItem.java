package com.khareedo.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 8/22/15.
 * Time : 11:07 AM
 * Contact id; vidit.maheshwari@gmail.com
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class ClearItem extends Activity {

    Button mClearItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.clearitems);
        Log.i(Constants.I, "Clear items started");

        // Initializing button
        mClearItem = (Button)findViewById(R.id.clearItem_button_1);

        // on clicking clear item clean the file
        mClearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constants.I, "Clear all item has been clicked");

                clearAll();
            }
        });

    }

    private void clearAll(){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput
                    (Constants.fileName, getApplicationContext().MODE_PRIVATE));
            outputStreamWriter.write("");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(Constants.E, "File write failed: " + e.toString());
        }
    }
}