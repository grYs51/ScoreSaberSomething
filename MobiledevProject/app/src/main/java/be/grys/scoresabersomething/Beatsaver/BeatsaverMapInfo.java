package be.grys.scoresabersomething.Beatsaver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import be.grys.scoresabersomething.Adapters.InfoPagerAdapter;
import be.grys.scoresabersomething.Models.Beatsaver.beatsavermap.BeatsaverMap;
import be.grys.scoresabersomething.R;
import be.grys.scoresabersomething.Shared.GetSpecificStringLength;

import com.google.android.material.tabs.TabLayout;

public class BeatsaverMapInfo extends AppCompatActivity {

    TextView songName;

    private static final String TAG = "MapInfoActivity";
    BeatsaverMap beatsaverMap;
    ImageButton returnButton;
    ImageView songImage;
    GetSpecificStringLength getSpecificStringLength = new GetSpecificStringLength();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatsaver_map_info);

        transparentStatusAndNavigation();

        beatsaverMap = (BeatsaverMap) getIntent().getSerializableExtra("ree");
        Log.d(TAG, "onCreate: dataget: Title: " + beatsaverMap.getMetadata().getSongName());

        songName = findViewById(R.id.InfoTitle);
        songName.setText(getSpecificStringLength.getShorterString(beatsaverMap.getMetadata().getSongName(), 50));


        //return
        returnButton = findViewById(R.id.returnbutton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        songImage = findViewById(R.id.infoImage);
        Glide.with(getApplicationContext())
                .load(beatsaverMap.getVersion()[0].getCoverURL())
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorPrimary)
                .into(songImage);

        //setviewpager
        ViewPager viewPager = findViewById(R.id.infoPager);
        PagerAdapter pA = new InfoPagerAdapter(getSupportFragmentManager(), beatsaverMap);
        viewPager.setAdapter(pA);

        //tablayout
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        return super.onCreateView(parent, name, context, attrs);
    }


    private void transparentStatusAndNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}