package be.grys.scoresabersomething.Shared;

import android.content.SharedPreferences;
import android.util.Log;

import be.grys.scoresabersomething.Models.Friends.FriendList;
import be.grys.scoresabersomething.Models.Friends.FriendsSharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Friends {

    FriendList friendList;

    public FriendList getFriendList() {
        return getFriends();
    }

    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }

    Gson gson = new Gson();
    SharedPreferences sharedPref;

    public Friends( SharedPreferences sharedPref){
    this.sharedPref = sharedPref;
    getFriends();
    }

    public Boolean checkIfExist(String id) {
        if (friendList.getFriends() != null) {
            for (FriendsSharedPref fr : friendList.getFriends()) {
                if (fr.getId().equals(id))
                    return true;
            }
        }
        return false;
    }

    public void SaveFriendList() {
        String json = this.gson.toJson(friendList);
        Log.d(TAG, "onResponse: json: " + json);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("playerFriends", json);
        editor.apply();
    }

    public FriendList getFriends() {

        String json = sharedPref.getString("playerFriends", null);
        Log.d(TAG, "getFriends: " + json);
        friendList = gson.fromJson(json, FriendList.class);
        if( friendList == null){
            friendList = new FriendList();
            friendList.setFriends(null);
            SaveFriendList();
        }

        return friendList;


    }

    public void SaveFriend(FriendsSharedPref fSP){
        if (friendList.getFriends() == null) {
            List<FriendsSharedPref> friends = new ArrayList<>();
            friends.add(fSP);
            friendList.setFriends(friends);
        } else {
            friendList.getFriends().add(fSP);
        }
        SaveFriendList();
    }

}
