package be.grys.scoresabersomething.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import be.grys.scoresabersomething.Models.Beatsaver.beatsavermap.Diff;

import java.util.ArrayList;
import java.util.List;


public class InfoDifficultyPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "DifficultyAdapter";
    Diff difficultiesSpecs;
    List<String> strings = new ArrayList<String>();
    String key;

    public InfoDifficultyPagerAdapter(@NonNull FragmentManager fm, Diff difficultiesSpecs, String key) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.difficultiesSpecs = difficultiesSpecs;
        this.key = key;

//        for ( Diff item :
//                difficultiesSpecs) {
//
//            if (!strings.contains(item.getDifficulty())){
//                strings.add(item.getDifficulty());
//            }
//            Log.d(TAG, "InfoDifficultyPagerAdapter: Difficultysize" + strings.toString());
//        }
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
//        return new DifficultyInfoPar(difficultiesSpecs[position], key);



//        switch (strings.get(position)) {
//            case "Easy":
//                return new DifficultyInfoPar(difficultiesSpecs[position], duration, key);
//            case "Normal":
//                return new DifficultyInfoPar(difficultiesSpecs.getNormal(), duration, key);
//            case "Hard":
//                return new DifficultyInfoPar(difficultiesSpecs.getHard(), duration, key);
//            case "Expert":
//                return new DifficultyInfoPar(difficultiesSpecs.getExpert(), duration, key);
//            case "Expert+":
//                return new DifficultyInfoPar(difficultiesSpecs.getExpertPlus(), duration, key);
//            default:
//                return new DifficultyInfoPar(null, duration, key);
//        }
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle: " + difficultiesSpecs);
        return strings.get(position);
    }
}
