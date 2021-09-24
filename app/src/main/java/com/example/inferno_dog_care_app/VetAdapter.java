package com.example.inferno_dog_care_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VetAdapter extends RecyclerView.Adapter<VetAdapter.VetViewHolder> {

    Context context;
    ArrayList<Veterinarian> veterinarianArrayList;

    public VetAdapter(Context context, ArrayList<Veterinarian> veterinarianArrayList) {
        this.context = context;
        this.veterinarianArrayList = veterinarianArrayList;
    }

    @NonNull
    @Override
    public VetAdapter.VetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new VetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VetAdapter.VetViewHolder holder, int position) {

        Veterinarian veterinarian = veterinarianArrayList.get(position);

        holder.FirstName.setText(veterinarian.FirstName);
        holder.Specialization.setText(veterinarian.Specialization);
        holder.Address.setText(veterinarian.Address);


    }

    @Override
    public int getItemCount() {
        return veterinarianArrayList.size();
    }


    public static class VetViewHolder extends RecyclerView.ViewHolder{

        TextView FirstName, Specialization, Address;
        View mView;


        public VetViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            FirstName = itemView.findViewById(R.id.tvfirstname);
            Specialization = itemView.findViewById(R.id.tvspecial);
            Address = itemView.findViewById(R.id.tvaddress);


        }
    }
    }

