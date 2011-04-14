package be.xsom.clubber;

import com.xsa.foobar.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    public static final String STARTUP_TAB_LIST_PREFERENCE = "StartupTabPref";

    private ListPreference mListPreference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
        mListPreference = (ListPreference)getPreferenceScreen().findPreference(STARTUP_TAB_LIST_PREFERENCE);
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        mListPreference.setSummary(mListPreference.getEntry()); 

        // Set up a listener whenever a key changes            
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);    
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(STARTUP_TAB_LIST_PREFERENCE)) {
          mListPreference.setSummary(mListPreference.getEntry()); 
        }
    }
}
