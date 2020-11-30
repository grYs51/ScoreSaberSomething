package be.grys.scoresabersomething.Adapters;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import be.grys.scoresabersomething.Beatsaver.BeatSaverMapInfo.ViewPager.ModesInfo;
import be.grys.scoresabersomething.Models.Beatsaver.beatsavermap.Characteristics;

import java.util.List;

public class InfoModesAdapter extends FragmentPagerAdapter {
    List<Characteristics> characteristics;
    List<String> modelist;
    int duration;
    String key;

    public InfoModesAdapter(@NonNull FragmentManager fm, List<Characteristics> characteristics, List<String> modeList, int duration, String key) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.characteristics = characteristics;
        this.modelist = modeList;
        this.duration = duration;
        this.key = key;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return new ModesInfo(characteristics.get(position).getDifficulties(), duration, key);
    }

    @Override
    public int getCount() {
        return modelist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return characteristics.get(position).getName();
    }
}
