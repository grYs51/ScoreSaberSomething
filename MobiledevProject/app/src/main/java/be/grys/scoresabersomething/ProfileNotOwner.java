package be.grys.scoresabersomething;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import be.grys.scoresabersomething.profile.ProfileFragment;

public class ProfileNotOwner extends AppCompatActivity {

    private static final String TAG = "ProfileNotOwnerActivity";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_not_owner);

        imageView = findViewById(R.id.returnbutton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String Id = getIntent().getStringExtra("playerNotOwner");
        Log.d(TAG, "onCreate: " + Id);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayoutnotUser, new ProfileFragment(Id)).commit();
    }
}