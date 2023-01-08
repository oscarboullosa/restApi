package edu.upc.eetac.dsa.rest_client.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.eetac.dsa.rest_client.R;
import edu.upc.eetac.dsa.rest_client.domain.Track;

public class AdapterTrack extends RecyclerView.Adapter<AdapterTrack.ViewHolder>{
    private List<Track> listTracks;

    final AdapterTrack.OnItemClickListener listener;

    public AdapterTrack(List<Track> listTracks,AdapterTrack.OnItemClickListener listener) {
        this.listTracks=listTracks;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(Track track);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }
        void binData(final Track track){
            name.setText(track.getTitle());
            description.setText("Clica para ver más detalles");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(track);
                }
            });
        }
    }

    @NonNull
    @Override //Enlaza el adaptador con la actividad item_list
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTrack.ViewHolder holder, int position) {
        holder.binData(listTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return listTracks.size();
    }
}
