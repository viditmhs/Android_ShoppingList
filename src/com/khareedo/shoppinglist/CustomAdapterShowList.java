package com.khareedo.shoppinglist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDE
 * User : vidit
 * Date : 8/28/15.
 * Time : 9:06 PM
 * Contact id; vidit.maheshwari@gmail.com
 * Copy Right: All copy rights are hold by TMC Embedded BV
 * To modify this template follow File->Settings->File and Code Templates->Includes
 */

public class CustomAdapterShowList extends BaseAdapter{
    ArrayList<String> values;
    ArrayList<Boolean> checkBoxValue;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapterShowList(ShowList showList, ArrayList<String> shoppingListValue,
                                 ArrayList<Boolean> shoppingListBoolean) {
        // TODO Auto-generated constructor stub
        Log.i(Constants.I, "Customized adapter called");
        values=shoppingListValue;
        context=showList;
        checkBoxValue=shoppingListBoolean;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.customized_showlist_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.showlistitem_customized);
        holder.tv.setText(values.get(position));
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();

                Log.i(Constants.I, "Item in the showlist has been clicked");

                TextView _localTV = (TextView)v.findViewById(R.id.showlistitem_customized);

                if(checkBoxValue.get(position)){
                    _localTV.setBackgroundColor(Color.BLACK);
                    _localTV.setTextColor(Color.WHITE);
                    checkBoxValue.set(position,false);
                }else{
                    _localTV.setBackgroundColor(Color.GRAY);
                    _localTV.setTextColor(Color.MAGENTA);
                    checkBoxValue.set(position,true);
                }

            }
        });
        return rowView;
    }

}
