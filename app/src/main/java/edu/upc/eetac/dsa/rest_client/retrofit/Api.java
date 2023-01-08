package edu.upc.eetac.dsa.rest_client.retrofit;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import edu.upc.eetac.dsa.rest_client.domain.Track;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    String URL="http://localhost:8080/myapp/";

    //Show a list of the tracks stored in the service. Use a RecyclerView for the list.
    @GET("tracks")
    Call<List<Track>> getTracks();
//Be able to add new tracks to the system.
    @POST("tracks")
    Call<Track> createTrack(@Body Track track);
//When a specific track in the list is tap, open a new activity when its fields can be edited.
    @PUT("tracks")
    Call<Void> updateTrack(@Body Track track);
//Be able to remove an existing track to the system.
    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") String id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
