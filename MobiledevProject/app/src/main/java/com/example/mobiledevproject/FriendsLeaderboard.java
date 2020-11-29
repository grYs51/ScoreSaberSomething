package com.example.mobiledevproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.Adapters.RV.LeaderBoardFriendAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Friends.FriendList;
import com.example.mobiledevproject.Models.Friends.FriendsSharedPref;
import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.Shared.Friends;
import com.example.mobiledevproject.profile.DialogScoresaberFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FriendsLeaderboard extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    ImageView imageView;
    RecyclerView recyclerView;
    LeaderBoardFriendAdapter leaderBoardPlayerAdapter;
    String playerId;
    SharedPreferences sharedPref;
    Friends friends;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        leaderBoardPlayerAdapter = new LeaderBoardFriendAdapter(getActivity());

        friends = new Friends(getActivity().getPreferences(getActivity().MODE_PRIVATE));

        getUserId();

        getFriends();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends_leaderboard, container, false);

        showAddButton();

        init_RV(view);

        return view;
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = ((LeaderBoardFriendAdapter) recyclerView.getAdapter()).getPosition();
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {

            case R.id.showProfile:
                Log.d(TAG, "onContextItemSelected: onclick: show profile");
                String id = leaderBoardPlayerAdapter.getData(position);
                Intent intent = new Intent(getContext(), ProfileNotOwner.class);
                intent.putExtra("playerNotOwner", id);
                startActivity(intent);

                break;

            case R.id.removePlayer:

                Log.d(TAG, "onContextItemSelected: onclick: Delete");
                String player = friends.getFriendList().getFriends().get(position).getName();

                friends.getFriendList().getFriends().remove(position);
                friends.SaveFriendList();

                leaderBoardPlayerAdapter.RemovePlayer(position);
                leaderBoardPlayerAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Player: " + player + " removed!", Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onContextItemSelected(item);
    }

    private void getPlayer(String id) {

        Call<Player> playerCall = ApiClient.getPlayerService().getUserInfo(id);

        playerCall.enqueue(new Callback<Player>() {

            FriendsSharedPref fSP;

            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: isSuccessful: " + response.code());
                    return;
                }

                Player player = response.body();

                fSP = new FriendsSharedPref();

                //set fsp
                fSP.setId(player.getPlayer_info().getPlayer_Id());
                fSP.setName(player.getPlayer_info().getPlayer_Name());
                fSP.setAvatar(player.getPlayer_info().getAvatar());

                friends.SaveFriend(fSP);

                Log.d(TAG, "onResponse: Player saved");

                leaderBoardPlayerAdapter.addData(fSP, player);

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });

    }

    private void showDialog() {

        Log.d(TAG, "onClick: Opening Dialog");
        DialogScoresaberFragment dialog = new DialogScoresaberFragment();
        dialog.setTargetFragment(FriendsLeaderboard.this, 1);
        dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");

    }

    private void getUserId() {
        sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);
        playerId = sharedPref.getString("playerId", null);
        if (playerId != null) {
            //TODO: player?
        }
    }

    //TODO: seperate class
    private void getFriends() {

        if (friends.getFriendList() != null) {
            leaderBoardPlayerAdapter.setData(friends.getFriendList());
        } else {
            FriendList tempfriend = new FriendList();
            tempfriend.setFriends(null);
            friends.setFriendList(tempfriend);
        }

    }

    private void init_RV(View view) {
        recyclerView = view.findViewById(R.id.RV_Friend_Leaderboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(leaderBoardPlayerAdapter); // empty
    }

    private void showAddButton() {
        imageView = getActivity().findViewById(R.id.addPerson);
        imageView.setImageResource(R.drawable.ic_person_add);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });
    }

    private void addPerson() {

        if (friends.getFriendList().getFriends() != null) {
            if (friends.getFriendList().getFriends().size() >= 7) {
                Toast.makeText(getActivity(), "Max 7 players!", Toast.LENGTH_SHORT).show();
            } else {
                showDialog();
            }
        } else {
            showDialog();
        }
    }

    public void sendInput(String id) {
        Log.d(TAG, "sendInput: found incoming input: " + id);

        //TODO: friends
        if (friends.checkIfExist(id)) {
            Log.d(TAG, "sendInput: Exist");
            Toast.makeText(getActivity(), "Player already exist", Toast.LENGTH_LONG).show();
        } else {
            Log.d(TAG, "sendInput: No exist");
            getPlayer(id);
        }

    }
}