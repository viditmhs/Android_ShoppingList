package com.khareedo.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 8/22/15.
 * Time : 11:09 AM
 * Contact id; vidit.maheshwari@tmc.nl
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class Profile extends Activity {

    ProfileDataHandler mDataHandler;

    Button mUpdateButton;
    TextView mName;
    TextView mEmail;
    TextView mPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Log.i(Constants.I, "Profile started");

        // reading data frm profile.txt and feeding it to ProfileDataHandler
        mDataHandler = new ProfileDataHandler(readProfile());

        mName = (TextView)findViewById(R.id.profile_name);
        mEmail = (TextView)findViewById(R.id.profile_email);
        mPhone = (TextView)findViewById(R.id.profile_phone);

        mName.setText(mDataHandler.getName());
        mEmail.setText(mDataHandler.getEmail());
        mPhone.setText(mDataHandler.getPhone());

        mUpdateButton = (Button)findViewById(R.id.profile_updateButton);

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constants.I, "Update profile clicked");
                Intent mProfileUpdate = new Intent(getApplicationContext(), ProfileUpdate.class);
                startActivity(mProfileUpdate);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(Constants.I, "Profile.onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        ProfileDataHandler local = new ProfileDataHandler(readProfile());
        mName.setText(local.getName());
        mEmail.setText(local.getEmail());
        mPhone.setText(local.getPhone());
        Log.i(Constants.I, "Profile.onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(Constants.I, "Profile.onPause() called");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(Constants.I, "Profile.onRestart() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(Constants.I, "Profile.onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(Constants.I, "Profile.onDestroy() called");
    }

    private String readProfile(){

        String data = "";
        try {
            InputStreamReader isr = new InputStreamReader(openFileInput(Constants.profileFileName));
            BufferedReader bufferedReader = new BufferedReader(isr);
            data = bufferedReader.readLine();
            bufferedReader.close();
            Log.i(Constants.I, "Information from file: " + data);

        } catch (IOException e) {
            Log.e(Constants.E, e.toString());
        }
       return data;
    }
}