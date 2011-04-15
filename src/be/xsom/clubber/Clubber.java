package be.xsom.clubber;

import be.xsom.clubber.R;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Clubber extends TabActivity {

	private SharedPreferences mSharedPrefs;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        TabHost mTabHost = getTabHost();
        
        TabSpec tspecOrders = mTabHost.newTabSpec("orders");
        tspecOrders.setIndicator(getString(R.string.tab_orders_text),
        		res.getDrawable(R.drawable.ic_tab_orders));
        tspecOrders.setContent(new Intent(this, OrdersActivity.class));
        mTabHost.addTab(tspecOrders);
        
        TabSpec tspecStats = mTabHost.newTabSpec("stats");
        tspecStats.setIndicator(getString(R.string.tab_stats_text),
        		res.getDrawable(R.drawable.ic_tab_orders));
        tspecStats.setContent(new Intent(this, StatsActivity.class));
        mTabHost.addTab(tspecStats);
        
        TabSpec tspecClubs = mTabHost.newTabSpec("clubs");
        tspecClubs.setIndicator(getString(R.string.tab_clubs_text),
        		res.getDrawable(R.drawable.ic_tab_orders));
        tspecClubs.setContent(new Intent(this, ClubsActivity.class));
        mTabHost.addTab(tspecClubs);

        // Set Tabs background colors.
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab_bg_selector);
        }
        
        // XXX: Dirty, but works as expected.
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String StartupTab = mSharedPrefs.getString("StartupTabPref", getResources().
        		getString(R.string.pref_startup_tab_default_value));
  
        if (StartupTab.equals(getResources().
        		getString(R.string.pref_startup_tab_default_value)) || StartupTab.equals(null)) {
            mTabHost.setCurrentTab(0);	
        } else {
            mTabHost.setCurrentTab(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.foobar_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.settings:
        	Intent settingsActivity = new Intent(getBaseContext(), Preferences.class);
            startActivity(settingsActivity);
            return true;
        case R.id.about:
        	showAbout();
        	return true;
        }
        return false;
    }
    
    protected void showAbout() {
    	View messageView = getLayoutInflater().inflate(R.layout.about, null, false);
    	
    	TextView textView = (TextView) messageView.findViewById(R.id.about_credits);
        int defaultColor = textView.getTextColors().getDefaultColor();
        textView.setTextColor(defaultColor);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_tab_orders_grey);  // XXX: get app_icon here instead
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }
}