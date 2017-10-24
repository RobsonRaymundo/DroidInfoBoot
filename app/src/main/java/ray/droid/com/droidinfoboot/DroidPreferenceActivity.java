package ray.droid.com.droidinfoboot;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DroidPreferenceActivity extends PreferenceActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getBaseContext();
        SetPreference();
    }

    private void SetPreference() {
        addPreferencesFromResource(R.xml.preferences);

        Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(DroidCommon.handleTime(context, newValue.toString()));
                return true;
            }
        };
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Preference start = (Preference) findPreference("startTime");
        start.setSummary(DroidCommon.handleTime(context, mPrefs.getString("startTime", "23:00")));
        start.setOnPreferenceChangeListener(listener);

        Preference stop = (Preference) findPreference("stopTime");
        stop.setSummary(DroidCommon.handleTime(context, mPrefs.getString("stopTime", "09:00")));
        stop.setOnPreferenceChangeListener(listener);
    }

}
