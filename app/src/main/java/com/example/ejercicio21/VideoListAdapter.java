package com.example.ejercicio21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.VideoView;

import java.util.ArrayList;

public class VideoListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> videoUrls;

    public VideoListAdapter(Context context, ArrayList<String> videoUrls) {
        this.context = context;
        this.videoUrls = videoUrls;
    }

    @Override
    public int getCount() {
        return videoUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return videoUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.video_list_item, parent, false);

        VideoView videoView = rowView.findViewById(R.id.videoItemView);
        String videoUrl = videoUrls.get(position);

        // Configurar VideoView para reproducir el video
        videoView.setVideoPath(videoUrl);
        videoView.start();

        return rowView;
    }
}
