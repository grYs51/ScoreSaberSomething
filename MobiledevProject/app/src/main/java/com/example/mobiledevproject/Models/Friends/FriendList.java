package com.example.mobiledevproject.Models.Friends;

import java.util.List;

public class FriendList {
   private List<FriendsSharedPref> friends;

    public List<FriendsSharedPref> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendsSharedPref> friends) {
        this.friends = friends;
    }
}
