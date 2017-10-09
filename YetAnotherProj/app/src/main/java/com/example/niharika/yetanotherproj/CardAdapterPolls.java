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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by NIHARIKA on 01-04-2016.
 */
public class CardAdapterPolls extends RecyclerView.Adapter<CardAdapterPolls.ViewHolder>{

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<Polls> polls;

    public CardAdapterPolls(List<Polls> polls, Context context){
        super();
//Getting all the superheroes
        this.polls = polls;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.polling, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Polls poll = polls.get(position);

        // imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(contact.getImageUrl(), ImageLoader.getImageListener(holder.imageViewContact, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        //Picasso.with(context).load(polls.getImageUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageViewContact);
//        holder.imageViewContact.setImageUrl(contact.getImageUrl(), imageLoader);
        holder.question.setText(poll.getQuestion());
        holder.op1.setText(poll.getOp1());
        holder.op2.setText(poll.getOp2());
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // RadioGroup R1 = (RadioGroup) v;
                //RadioButton R2 ;
               // Polls p1=(Polls) R1.getTag();

              int a =   holder.options.getCheckedRadioButtonId();
                String x;

                if(a==2131493006)
                    x="YES";
                else
                    x="NO";

                //R2 = (RadioButton)  v.findViewById(a);

                Submitpoll sp = new Submitpoll();
                sp.getRes(x);

                    Toast.makeText(context, "Selected Radio Button is: " +x , Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return polls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //public ImageView imageViewContact;
        public TextView question;
        public RadioButton op1;
        public RadioButton op2;
        public Button submit;
        public RadioGroup options;
        public RadioButton rb;

        ///public Button callbutton;



        public ViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.ques);
            op1= (RadioButton) itemView.findViewById(R.id.OP1);
            op2= (RadioButton) itemView.findViewById(R.id.OP2);
            submit = (Button) itemView.findViewById(R.id.submit);
            options = (RadioGroup) itemView.findViewById(R.id.options);



        }




    }


}


