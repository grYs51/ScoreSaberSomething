package com.example.mobiledevproject.Models.Friends;

import com.example.mobiledevproject.Models.LeaderboardPlayer.LPlayer;

public class Testing {
    public FriendsSharedPref getFriendsSharedPref() {
        return friendsSharedPref;
    }

    public void setFriendsSharedPref(FriendsSharedPref friendsSharedPref) {
        this.friendsSharedPref = friendsSharedPref;
    }

    public LPlayer getlPlayer() {
        return lPlayer;
    }

    public void setlPlayer(LPlayer lPlayer) {
        this.lPlayer = lPlayer;
    }

    private FriendsSharedPref friendsSharedPref;
    private LPlayer lPlayer;
}
