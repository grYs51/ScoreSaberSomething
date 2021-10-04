package be.grys.scoresabersomething.ApiCall.Interfaces;

import be.grys.scoresabersomething.Models.Beatsaver.MapsBeatsaver;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AllmapsBeatsaverApi {

    @Headers({
            "User-Agent: MobileDevApp\\1.0.0"
    })

    @GET("api/search/text/{page}")
    Call<MapsBeatsaver> getMaps(@Path(value = "page", encoded = true) String page,
                                @Query(value = "sortOrder") String sortOrder,
                                @Query(value = "automapper") Boolean autoMapper,
                                @Query(value = "chroma") Boolean chroma,
                                @Query(value = "noodle") Boolean noodle,
                                @Query(value = "me") Boolean mappingExtensions,
                                @Query(value = "cinema") Boolean cinema,
                                @Query(value = "ranked") Boolean ranked,
                                @Query(value = "fullSpread") Boolean fullSpread,
                                @Query(value = "q") String q,
                                @Query(value = "maxNps") String maxNps,
                                @Query(value = "minNps") String minNps,
                                @Query(value = "from") String from,
                                @Query(value = "to") String to);
}
