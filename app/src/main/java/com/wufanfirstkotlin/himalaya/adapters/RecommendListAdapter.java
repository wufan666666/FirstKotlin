package com.wufanfirstkotlin.himalaya.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wufanfirstkotlin.R;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wf
 * @date : 2021年10月11日 10:17
 */
public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.InnerHolder> {

    private List<Album> albumList = new ArrayList<>();
    private OnItemClickListener listener =null;

    @NonNull
    @NotNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InnerHolder holder, int position) {

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Integer tag = (Integer) v.getTag();
                    listener.ItemClick(tag,albumList.get(tag));
                }
            }
        });
        holder.setData(albumList.get(position));
    }

    @Override
    public int getItemCount() {
        if (albumList != null) {
            return albumList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<Album> albumList) {
        if (albumList != null) {
            this.albumList = albumList;
            notifyDataSetChanged();
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView albumCover;
        private TextView albumTitleTv;
        private TextView albumDescription;
        private TextView albumPlayCount;
        private TextView albumContentSize;

        public InnerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

        public void setData(Album album) {
            albumCover = itemView.findViewById(R.id.album_cover);
            albumTitleTv = itemView.findViewById(R.id.album_title_tv);
            albumDescription = itemView.findViewById(R.id.album_description);
            albumPlayCount = itemView.findViewById(R.id.album_play_count);
            albumContentSize = itemView.findViewById(R.id.album_content_size);
            albumTitleTv.setText(album.getAlbumTitle());
            albumDescription.setText(album.getAlbumIntro());
            albumPlayCount.setText(album.getPlayCount() / 10000 + "万");
            albumContentSize.setText(album.getIncludeTrackCount() + "集");
            Glide.with(itemView.getContext()).load(album.getCoverUrlLarge()).into(albumCover);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public interface OnItemClickListener{
        void ItemClick(int position, Album album);
    }
}