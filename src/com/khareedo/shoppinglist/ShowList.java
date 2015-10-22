package com.khareedo.shoppinglist;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 8/22/15.
 * Time : 10:52 AM
 * Contact id; vidit.maheshwari@gmail.com
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class ShowList extends Activity {

    ListView mShowList;
    ArrayList<String> values;
    ArrayList<Boolean> valuesBoolean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist);
        Log.i(Constants.I, "Show list started.");

        // Getting item from database
        values = readFile();
        valuesBoolean = Utility.getItemsListBoolean(values.size());

        // Initializing ListView

        mShowList = (ListView)findViewById(R.id.itemsshowlist);

        // Creating the adaptor
        mShowList.setAdapter(new CustomAdapterShowList(this, values, valuesBoolean));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.layout.main,menu);
        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(Constants.I, "ShowList.onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(Constants.I, "ShowList.onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(Constants.I, "ShowList.onPause() called");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(Constants.I, "ShowList.onRestart() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(Constants.I, "ShowList.onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        writeFile(values, valuesBoolean);
        Log.i(Constants.I, "ShowList.onDestroy() called");
    }

    private void writeFile(ArrayList<String> stringList, ArrayList<Boolean> booleanList) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput
                                            (Constants.fileName, getApplicationContext().MODE_PRIVATE));
            for(int i = 0; i < stringList.size(); i++){
                if(!booleanList.get(i)){
                    outputStreamWriter.write(stringList.get(i)+"\n");
                }
            }
           outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(Constants.E, "File write failed: " + e.toString());
        }
    }

    private ArrayList<String> readFile(){

        ArrayList<String> value = new ArrayList<String>();
        try {
            InputStreamReader isr = new InputStreamReader(openFileInput(Constants.fileName));
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Log.i(Constants.I, "Value read " + line);
                value.add(line);
            }
        }catch (IOException e){
            Log.e(Constants.E, "Fail to read file" + Constants.fileName);
        }

        return value;
    }

}