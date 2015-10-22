package com.khareedo.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 9/5/15.
 * Time : 3:42 PM
 * Contact id; vidit.maheshwari@tmc.nl
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class ProfileUpdate extends Activity {

    ProfileDataHandler mdatahandler;

    EditText mName;
    EditText mEmail;
    EditText mPhone;
    Button mDoneButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);

        Log.i(Constants.I, "Profile Update started");

        mdatahandler = new ProfileDataHandler(readProfile());

        // initializing all field
        mName = (EditText)findViewById(R.id.profileupdate_name);
        mEmail = (EditText)findViewById(R.id.profileupdate_email);
        mPhone = (EditText)findViewById(R.id.profileupdate_phone);

        mDoneButton = (Button)findViewById(R.id.profileupdate_done_button);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(Constants.I, "button is clicked");

                //Setting name
                mdatahandler.setProfileStatus(true);
                mdatahandler.setName(mName.getText().toString());

                if(checkEmail()){
                    mdatahandler.setEmail(mEmail.getText().toString());
                }else{
                    Log.i(Constants.I, "Email id failed verification " + mEmail.getText().toString());
                }

                if(checkPhone()){
                    mdatahandler.setPhone(mPhone.getText().toString());
                }else{
                    Log.i(Constants.I, "Phone number failed verification " + mPhone.getText().toString());
                }

                JSONObject jobj = mdatahandler.getDataString();

                if(jobj!=null) {
                    writeFile(jobj.toString());
                    Log.i(Constants.I, "Profile file data is: " + jobj.toString());
                }else{
                    Log.i(Constants.I, "JSON object returned from ProfileDatahander is null");
                }
                finish();
            }
        });
    }

    private boolean checkEmail(){

        return true;
    }

    private boolean checkPhone(){

        return true;
    }

    private String readProfile(){

        String data = "";

        try {
            InputStreamReader isr = new InputStreamReader(openFileInput(Constants.profileFileName));
            BufferedReader bufferedReader = new BufferedReader(isr);
            data = bufferedReader.readLine();
            bufferedReader.close();

        } catch (IOException e) {
            Log.e(Constants.E, e.toString());
        }

        return data;
    }

    private void writeFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput
                    (Constants.profileFileName, getApplicationContext().MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(Constants.E, "Failed to write on profile: " + e.toString());
        }
    }
}