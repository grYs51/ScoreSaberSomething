package be.grys.scoresabersomething.Settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import be.grys.scoresabersomething.R;
import be.grys.scoresabersomething.Shared.GetCache;

import com.google.android.material.navigation.NavigationView;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String TAG = "SettingFragment";

    TextView headerRank, headerName;
    ImageView headerImage;
    Preference player, playerRemove, storage, storageClear;
    String input;
    GetCache getCache;

    SharedPreferences.Editor editor;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.settings_preferences);

        setPreferencesView();

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigationView);
        View header = navigationView.getHeaderView(0);
        headerRank = header.findViewById(R.id.header_rank);
        headerName = header.findViewById(R.id.header_User);
        headerImage = header.findViewById(R.id.imageProfile);

        getSharedPref();

        SetText();

        getCache = new GetCache(getActivity());

        storage.setSummary(getCache.initializeCache());

        RemoveListener();

    }

    private void RemoveListener() {
        playerRemove.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d(TAG, "onPreferenceClick: buttonCLick");
                editor.remove("playerId");
                editor.commit();
                getSharedPref();
                SetText();
                return true;
            }
        });
    }

    private void SetText() {
        if (input != null) {
            player.setSummary(input);
        } else {
            player.setSummary("None");
            headerName.setText("User");
            headerRank.setText("rank");
            headerImage.setImageResource(android.R.color.transparent);

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