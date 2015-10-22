package com.khareedo.shoppinglist;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 9/5/15.
 * Time : 10:58 AM
 * Contact id; vidit.maheshwari@gmail.com
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class ProfileDataHandler {

    String name;

    String email;

    String phone;

    boolean isProfileExist;

    public ProfileDataHandler(String data){
        try {
            JSONObject jobj = new JSONObject(data);
            if(jobj.getString("isProfileSet").equalsIgnoreCase("TRUE")){
                isProfileExist = true;
                setName(jobj.getString("name"));
                setEmail(jobj.getString("email"));
                setPhone(jobj.getString("phone"));
            }else{
                isProfileExist = false;
                setName("");
                setEmail("");
                setPhone("");
            }
        } catch (JSONException e){
            Log.e(Constants.E, "JSON exception ProfileDataHandler " + e.getMessage());
            isProfileExist = false;
            setName("");
            setEmail("");
            setPhone("");
        }
    }

    public void setName(String setName){
        name = setName;
    }

    public void setEmail(String setEmail){
        email = setEmail;
    }

    public void setPhone(String setPhone){
        phone = setPhone;
    }

    public void setProfileStatus(boolean status) {
        isProfileExist = status;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public boolean getProfileStatus(){
        return isProfileExist;
    }

    public JSONObject getDataString(){
        JSONObject jobj = new JSONObject();

        try{
            jobj.put("isProfileSet", String.valueOf(isProfileExist));
            jobj.put("name", name);
            jobj.put("email", email);
            jobj.put("phone",phone);

        }catch (JSONException e){
            Log.e(Constants.E, "Exception occured in ProfileDataHandler :" + e.getMessage());
            jobj = null;
        }

        return jobj;
    }
}
