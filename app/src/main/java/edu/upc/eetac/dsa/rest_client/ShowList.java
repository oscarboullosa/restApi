package edu.upc.eetac.dsa.rest_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import edu.upc.eetac.dsa.rest_client.adapters.AdapterTrack;
import edu.upc.eetac.dsa.rest_client.domain.Track;
import edu.upc.eetac.dsa.rest_client.retrofit.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowList extends AppCompatActivity {
    public List<Track> listTracks;
    private RecyclerView recycler;
    private AdapterTrack adapterTrack;
    private ProgressBar progressBarStore;
    private Track currentTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
    }
    public void showList(View view) {
        Api service = Api.retrofit.create(Api.class);
        Call<List<Track>> call = service.getTracks();
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                listTracks = response.body();
                adapterTrack = new AdapterTrack(listTracks, new AdapterTrack.OnItemClickListener() {
                    @Override
                    public void onItemClick(Track track) {
                        moveToDescription(track);
                    }
                });
                recycler.setAdapter(adapterTrack);

            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
        private void moveToDescription(Track track){
            Intent i=new Intent(getActivity(),TracksDetails.class);
            i.putExtra("Details",track);
            startActivity(i);
    }
}