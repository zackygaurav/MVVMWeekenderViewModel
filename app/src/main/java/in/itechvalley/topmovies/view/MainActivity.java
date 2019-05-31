package in.itechvalley.topmovies.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.itechvalley.topmovies.R;
import in.itechvalley.topmovies.client.TopMoviesApiClient;
import in.itechvalley.topmovies.model.SingleMovieModel;
import in.itechvalley.topmovies.model.TopMoviesModel;
import in.itechvalley.topmovies.viewmodel.TopMoviesViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    /*
    * Global Instance of ViewModel associated with this Lifecycle Owner
    * */
    private TopMoviesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Bind ButterKnife to this Activity
        * */
        ButterKnife.bind(this);

        /*
        * How to create Instance of a ViewModel class
        * */
        viewModel = ViewModelProviders.of(this).get(TopMoviesViewModel.class);
    }

    @OnClick(R.id.btnFetch)
    void onFetchClick()
    {
        viewModel.requestMovieData();
    }
}
