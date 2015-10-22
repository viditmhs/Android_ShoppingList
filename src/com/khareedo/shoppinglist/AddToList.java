package com.khareedo.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDE
 * User : vidi
 * Date : 8/22/15.
 * Time : 11:05 AM
 * Contact id; vidit.maheshwari@tmc.nl
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class AddToList extends Activity {

    ListView mlistView;
    Button addItem;
    EditText newItem;
    ArrayList<String> values;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtolist);
        Log.i(Constants.I, "Add to list started");

        // Get List view from XML
        mlistView = (ListView)findViewById(R.id.itemslist);

        // Initializing addItem button
        addItem = (Button)findViewById(R.id.buttonAddItem);

        // Initializing newItem EditText
        newItem = (EditText)findViewById(R.id.newItem);


        // Obtain list from the database
        // Expand it using listAdapter
        values = readFile();


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_expandable_list_item_1, android.R.id.text1, values);
        mlistView.setAdapter(adapter);

        // Add new item name to the adapter list when add item is clicked
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if EditText is null
                String newItemName = newItem.getText().toString();

                if(newItemName != null && !newItemName.isEmpty()){
                    Log.i(Constants.I, "New Item is not null and is not empty");

                    // Add this new Item to adapter
                    if (!values.contains(newItemName)) {
                        newItemName = newItemName.replace("\n", " ");
                        newItemName = newItemName.replace("\r", " ");
                        Log.i(Constants.I, "Value added " + newItemName);
                        values.add(0, newItemName);
                        newItem.setText("");
                        adapter.notifyDataSetChanged();
                    } else {
                     Toast.makeText(getApplicationContext(), Constants.ITEM_EXISTS, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Item name is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onDestroy(){
        super.onDestroy();
        Log.i(Constants.I, "Calling AddToList onDestroy " + values.size());
        writeFile(values);
    }

    private void writeFile(ArrayList<String> stringList) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput
                    (Constants.fileName, getApplicationContext().MODE_PRIVATE));
            for(int i = 0; i < stringList.size(); i++){
                outputStreamWriter.write(stringList.get(i) + "\n");
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
                Log.i(Constants.I, "Valued added " + line);
                value.add(line);
            }
            bufferedReader.close();
        }catch (IOException e){
            Log.e(Constants.E, "Fail to read file" + Constants.fileName);
        }

        return value;
    }
}
