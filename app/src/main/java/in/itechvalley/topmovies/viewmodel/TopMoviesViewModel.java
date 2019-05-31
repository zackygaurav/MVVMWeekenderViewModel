package in.itechvalley.topmovies.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.itechvalley.topmovies.client.TopMoviesApiClient;
import in.itechvalley.topmovies.model.SingleMovieModel;
import in.itechvalley.topmovies.model.TopMoviesModel;
import in.itechvalley.topmovies.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopMoviesViewModel extends AndroidViewModel
{
    /*
    * LOG TAG
    * */
    private static final String TAG = "TopMoviesViewModel";

    private Context context;

    /*
    * Constructor
    * */
    public TopMoviesViewModel(@NonNull Application application)
    {
        super(application);

        /*
        * Injecting Context from Application
        * */
        context = application.getBaseContext();
    }

    public void requestMovieData()
    {
        /*
         * Retrofit's Builder with Base URL, Converter
         *
         * Converters - Gson, Jackson, Moshi
         * */
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl("https://api.myjson.com/");
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());

        /*
         * Create Instance of Retrofit from it's builder
         * */
        Retrofit retrofit = retrofitBuilder.build();

        /*
         * Create the Object of TopMoviesApiClient
         * with the help of Retrofit
         * */
        TopMoviesApiClient client = retrofit.create(TopMoviesApiClient.class);

        Call<TopMoviesModel> request = client.getAllMovies();

        /*
         * Make a Call
         * */
        request.enqueue(new Callback<TopMoviesModel>()
        {
            @Override
            public void onResponse(@NonNull Call<TopMoviesModel> call, @NonNull Response<TopMoviesModel> response)
            {
                TopMoviesModel responseBody = response.body();
                if (responseBody == null)
                {
                    Toast.makeText(context, "Response Body is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (responseBody.isSuccess())
                {
                    /*
                     * Get the List from ResponseBody
                     * */
                    List<SingleMovieModel> moviesList = responseBody.getMoviesList();

                    Toast.makeText(context, String.format("Total %d movies loaded", moviesList.size()), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context, "Failed to get movies", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopMoviesModel> call, @NonNull Throwable throwable)
            {
                Log.e(TAG, "Failed to get movies from API", throwable);
            }
        });
    }
}
