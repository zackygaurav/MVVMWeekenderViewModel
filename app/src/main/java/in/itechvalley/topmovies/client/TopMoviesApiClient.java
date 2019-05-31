package in.itechvalley.topmovies.client;

import in.itechvalley.topmovies.model.TopMoviesModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TopMoviesApiClient
{
    @GET("bins/1bo7h2")
    Call<TopMoviesModel> getAllMovies();
}
