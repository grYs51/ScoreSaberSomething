package be.grys.scoresabersomething.Models.Friends;

import be.grys.scoresabersomething.Models.PlayerProfile.Player;

public class Testing {
    public FriendsSharedPref getFriendsSharedPref() {
        return friendsSharedPref;
    }

    public void setFriendsSharedPref(FriendsSharedPref friendsSharedPref) {
        this.friendsSharedPref = friendsSharedPref;
    }

    public Player getlPlayer() {
        return lPlayer;
    }

    public void setlPlayer(Player lPlayer) {
        this.lPlayer = lPlayer;
    }

    private FriendsSharedPref friendsSharedPref;
    private Player lPlayer;
}
