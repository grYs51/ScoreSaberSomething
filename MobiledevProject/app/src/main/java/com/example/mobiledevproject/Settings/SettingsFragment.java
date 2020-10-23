package com.example.mobiledevproject.Settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Shared.GetCache;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String TAG = "SettingFragment";
    Preference player, playerRemove, storage, storageClear;
    String input;
    GetCache getCache;

    SharedPreferences.Editor editor;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.settings_preferences);

        setPreferencesView();

        getSharedPref();

        SetText();

        getCache = new GetCache(getActivity());

        storage.setSummary( getCache.initializeCache());

        RemoveListener();

    }

    private void RemoveListener() {
        playerRemove.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d(TAG, "onPreferenceClick: buttonCLick" );

                editor.remove("playerId");
                editor.commit();
                getSharedPref();
                SetText();
                return true;
            }
        });
    }

    private void SetText() {
        if(input != null){
            player.setSummary(input);
        } else {
            player.setSummary("None");
        }

    }

    private void getSharedPref() {
        SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);
        input = sharedPref.getString("playerId", null);

         editor = sharedPref.edit();
    }

    private void setPreferencesView() {
        player = (Preference) findPreference("user_Id");
        playerRemove = (Preference) findPreference("remove_Player");
        storage = (Preference) findPreference("used_Storage");
        storageClear = (Preference) findPreference("clear_Storage");
    }


}