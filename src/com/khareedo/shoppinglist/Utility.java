package com.khareedo.shoppinglist;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;


/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 8/23/15.
 * Time : 1:52 PM
 * Contact id; vidit.maheshwari@gmail.com
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */
public class Utility {

    //public static ArrayList<String> values = new ArrayList<String>(Arrays.asList(Constants.listArray));

    public static ArrayList<Boolean> getItemsListBoolean(int size){
        ArrayList<Boolean> valuesBool = new ArrayList<Boolean>();
        for (int i =0 ; i<size; i++){
            valuesBool.add(false);
        }
        return valuesBool;
    }
}
