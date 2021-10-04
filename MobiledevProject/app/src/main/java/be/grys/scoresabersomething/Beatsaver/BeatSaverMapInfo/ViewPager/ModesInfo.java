package be.grys.scoresabersomething.Beatsaver.BeatSaverMapInfo.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import be.grys.scoresabersomething.Adapters.InfoDifficultyPagerAdapter;
import be.grys.scoresabersomething.Models.Beatsaver.beatsavermap.Diff;
import be.grys.scoresabersomething.R;

import com.google.android.material.tabs.TabLayout;

import static android.content.ContentValues.TAG;

public class ModesInfo extends Fragment {

    Diff difficultiesSpecs[];
    int duration;
    String key;

    public ModesInfo(Diff[] difficultiesSpecs, int duration, String key) {
        Log.d(TAG, "ModesInfo: Difficulties");
        this.difficultiesSpecs = difficultiesSpecs;
        this.duration = duration;

        this.key = key;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_mode_pager, container, false);

        //fill adapter
        ViewPager viewPager = view.findViewById(R.id.infoDifficultyPager);
        PagerAdapter pA = new InfoDifficultyPagerAdapter(getChildFragmentManager(), difficultiesSpecs, duration, key);
        viewPager.setAdapter(pA);

        TabLayout tabLayout = view.findViewById(R.id.infoDifficultyTabLayout);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}
