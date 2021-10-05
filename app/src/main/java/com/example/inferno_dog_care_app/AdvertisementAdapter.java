package com.example.inferno_dog_care_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder> {

    Context context;
    ArrayList<Advertisement> advertisementArrayList;

    public AdvertisementAdapter(Context context, ArrayList<Advertisement> advertisementArrayList) {
        this.context = context;
        this.advertisementArrayList = advertisementArrayList;
    }

    @NonNull
    @Override
    public AdvertisementAdapter.AdvertisementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.advertisement_item,parent,false);
        return new AdvertisementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementAdapter.AdvertisementViewHolder holder, int position) {
        Advertisement advertisement = advertisementArrayList.get(position);

        holder.Breed.setText(advertisement.Breed);
        //holder.Price.setText(String.valueOf(user.Price));
        holder.Price.setText(advertisement.Price);
        String id =advertisement.ID;
        String name =advertisement.Owner_Name;
        String phone =advertisement.Phone_Number;
        String address =advertisement.Address;
        String breed =advertisement.Breed;
        String count =advertisement.No_Of_Puppies;
        String birthday =advertisement.Birthday;
        String price =advertisement.Price;
        //String imageUrl = advertisement.getImageURL();

        /*Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.sell_lab)
                .fit()
                .into(holder.image);*/

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AdvertisementProfile.class);
                intent.putExtra("ID",id);
                intent.putExtra("Owner_Name",name);
                intent.putExtra("Phone_Number",phone);
                intent.putExtra("Address",address);
                intent.putExtra("Breed",breed);
                intent.putExtra("No_Of_Puppies",count);
                intent.putExtra("Birthday",birthday);
                intent.putExtra("Price",price);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return advertisementArrayList.size();
    }

    public static class AdvertisementViewHolder extends RecyclerView.ViewHolder{

        TextView Breed,Price;
        View view;
        //ImageView image;

        public AdvertisementViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            Breed = itemView.findViewById(R.id.tvbreed);
            Price = itemView.findViewById(R.id.tvprice);
            //image = itemView.findViewById(R.id.imageView35);
        }
    }
}
