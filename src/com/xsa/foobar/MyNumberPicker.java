package com.xsa.foobar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyNumberPicker extends LinearLayout  {

	public TextView count;
	public ImageView plus;
	public ImageView minus;
	
	private OnClickListener plusListener = new OnClickListener() {
	    public void onClick(View v) {
	    	String s = (String) count.getText();
	    	int i = Integer.parseInt(s)+1;
	    	count.setText(""+i);
	    }
	};
	
	private OnClickListener minusListener = new OnClickListener() {
	    public void onClick(View v) {
	    	String s = (String) count.getText();
	    	int i=0;
	    	if(Integer.parseInt(s)>0) {
	    		i = Integer.parseInt(s)-1;
	    	}
	    	count.setText(""+i);
	    }
	};
	
	public MyNumberPicker(Context context, AttributeSet attributes) {
		super(context,attributes);
		LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.my_number_picker, this,true);
		count = (TextView)findViewById(R.id.count);
		plus = (ImageView)findViewById(R.id.plus_icon);
		minus = (ImageView)findViewById(R.id.minus_icon);
		plus.setOnClickListener(plusListener);
		minus.setOnClickListener(minusListener);
	}
}
