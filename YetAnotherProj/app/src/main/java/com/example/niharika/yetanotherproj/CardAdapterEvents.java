package com.example.niharika.yetanotherproj;

/**
 * Created by NIHARIKA on 27-03-2016.
 */
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by Belal on 11/9/2015.
 */
public class CardAdapterEvents extends RecyclerView.Adapter<CardAdapterEvents.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<Events> events;

    public CardAdapterEvents(List<Events> events, Context context){
        super();
//Getting all the superheroes
        this.events = events;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Events event = events.get(position);
       // Offers offer = offers.get(position);
        Picasso.with(context).load(event.getImageUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageView);
        //imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(event.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //holder.imageView.setImageUrl(event.getImageUrl(), imageLoader);
        holder.textViewTitle.setText(event.getTitle());
        holder.textViewDesc.setText(event.getDesc());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textViewTitle;
        public TextView textViewDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewEvents);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDesc= (TextView) itemView.findViewById(R.id.textViewDesc);

        }
    }
}
