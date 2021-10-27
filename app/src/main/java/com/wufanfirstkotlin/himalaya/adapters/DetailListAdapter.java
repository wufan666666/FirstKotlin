package com.wufanfirstkotlin.himalaya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wufanfirstkotlin.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月20日 15:11
 */
public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.ViewHolder> {
    private final List<Track> tracks = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat durationFormat = new SimpleDateFormat("mm:ss");
    private OnDetailItemClickListener onItemClickListener = null;


    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.orderText.setText((position + 1) + "");
        holder.detailTitle.setText(tracks.get(position).getTrackTitle());
        int playCount = tracks.get(position).getPlayCount() / 10000;
        if (playCount > 0) {
            holder.detailCount.setText(tracks.get(position).getPlayCount() / 10000 + "万");
        } else {
            holder.detailCount.setText(tracks.get(position).getPlayCount() + "");
        }
        holder.detailPlayTime.setText(durationFormat.format(tracks.get(position).getDuration() * 1000) + "");
        holder.detailTime.setText(dateFormat.format(tracks.get(position).getUpdatedAt()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(tracks,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void setData(List<Track> track) {
        this.tracks.clear();
        this.tracks.addAll(track);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderText;
        private TextView detailTitle;
        private TextView detailCount;
        private TextView detailPlayTime;
        private TextView detailTime;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            orderText = itemView.findViewById(R.id.order_text);
            detailTitle = itemView.findViewById(R.id.detail_title);
            detailCount = itemView.findViewById(R.id.detail_count);
            detailPlayTime = itemView.findViewById(R.id.detail_play_time);
            detailTime = itemView.findViewById(R.id.detail_time);
        }
    }

    public void setOnItemClickListener(OnDetailItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnDetailItemClickListener {
        void onItemClick(List<Track> tracks, int position);
    }

}