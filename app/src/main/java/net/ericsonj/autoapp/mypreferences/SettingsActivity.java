package net.ericsonj.autoapp.mypreferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import net.ericsonj.autoapp.R;

/**
 * Created by ejoseph on 1/26/16.
 */
public class SettingsActivity extends MyPreferenceActivity{

    @Override
    public PreferenceFragment getPreferenceFragment() {
        return new PrefsFragment();
    }


    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.pref_settings);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            // bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.key_pref_name)));
            bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.key_pref_id)));
            bindPreferenceSummaryToValue(findPreference(getResources().getString(R.string.key_pref_email)));
        }
    }

}
