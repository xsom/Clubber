package com.xsa.foobar;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrdersActivity extends ListActivity {
	
	static String[] DRINKS;		// *burp*
	
    private static class DrinkListAdapter extends BaseAdapter {
    	private LayoutInflater mInflater;
    	private Bitmap mIcon;
    	
    	public DrinkListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            
            mIcon = BitmapFactory.decodeResource(context.getResources(),
            		R.drawable.ic_tab_orders_white);
        }
    	
        static class ViewHolder {
        	ImageView iconDrink;
            TextView textDrink;
        }
    	
        public View getView(int position, View convertView, ViewGroup parent) {
    	    ViewHolder holder;
    	
    	    if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_orders, parent, false);

                holder = new ViewHolder();
                holder.iconDrink = (ImageView) convertView.findViewById(R.id.icon);
                holder.textDrink = (TextView) convertView.findViewById(R.id.drink);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

    	    holder.iconDrink.setImageBitmap(mIcon);
            holder.textDrink.setText(DRINKS[position]);

            return convertView;
        }
        
       	public int getCount() {
            return DRINKS.length;
        }
    	
	    public Object getItem(int position) {
            return position;
        }
	
	    public long getItemId(int position) {
            return position;
        }
    }
       
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_orders);
        
        DRINKS = getResources().getStringArray(R.array.drinks_array);
        
        setListAdapter(new DrinkListAdapter(this));
    }
    
   public void onOrderButtonClick(View v) {
    	// XXX: temp
    	Toast.makeText(this, "You clicked to finalize your order", Toast.LENGTH_SHORT).show();
    }
}
