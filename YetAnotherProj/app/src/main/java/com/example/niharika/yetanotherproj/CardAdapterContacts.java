package com.example.niharika.yetanotherproj;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by NIHARIKA on 31-03-2016.
 */
public class CardAdapterContacts extends RecyclerView.Adapter<CardAdapterContacts.ViewHolder>{

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<Contacts> contacts;

    public CardAdapterContacts(List<Contacts> contacts, Context context){
        super();
//Getting all the superheroes
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Contacts contact = contacts.get(position);

       // imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(contact.getImageUrl(), ImageLoader.getImageListener(holder.imageViewContact, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        Picasso.with(context).load(contact.getImageUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageViewContact);
//        holder.imageViewContact.setImageUrl(contact.getImageUrl(), imageLoader);
        holder.textViewName.setText(contact.getName());
        holder.textViewDesg.setText(contact.getDesignation());
        holder.textViewPNO.setText(contact.getPhonenumber());
        holder.callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getPhonenumber() + ""));
                context.startActivity(callIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewContact;
        public TextView textViewName;
        public TextView textViewPNO;
        public TextView textViewDesg;
        public Button callbutton;



        public ViewHolder(View itemView) {
            super(itemView);
            imageViewContact = (ImageView) itemView.findViewById(R.id.imageViewContact);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPNO= (TextView) itemView.findViewById(R.id.textViewPNO);
            textViewDesg= (TextView) itemView.findViewById(R.id.textViewDesg);
            callbutton=(Button) itemView.findViewById(R.id.callbutton);


        }
    }

}
