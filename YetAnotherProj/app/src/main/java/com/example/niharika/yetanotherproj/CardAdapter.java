package com.example.niharika.yetanotherproj;


import android.content.Context;
import android.media.Image;
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
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<Offers> offers;

    public CardAdapter(List<Offers> offers, Context context){
        super();
//Getting all the superheroes
        this.offers = offers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Offers offer = offers.get(position);

       // imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(offer.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        Picasso.with(context).load(offer.getImageUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageView);
       // holder.imageView.setImageUrl(offer.getImageUrl(), imageLoader);

    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewOffer);

        }
    }
}