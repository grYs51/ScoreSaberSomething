package be.grys.scoresabersomething.ApiCall.Interfaces;

import be.grys.scoresabersomething.Models.Scores;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecentSongApi {

    @GET("api/player/{player_id}/scores/recent/{page}")
    Call<Scores> getRecentSong(@Path(value = "player_id", encoded = true) String playerId, @Path(value = "page", encoded = true) String page);
}
