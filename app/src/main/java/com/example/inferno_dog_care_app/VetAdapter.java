package com.example.inferno_dog_care_app;

import android.content.Context;
import android.content.Intent;
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

        holder.Name.setText(veterinarian.Name);
        holder.Specialization.setText(veterinarian.Specialization);
        holder.Address.setText(veterinarian.Address);

        String id=veterinarian.ID;
        String name=veterinarian.Name;
        String specialization=veterinarian.Specialization;
        String address=veterinarian.Address;
        String registrationNo = veterinarian.RegistrationNo;
        String phone = veterinarian.Phone;
        String email = veterinarian.Email;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),VetChannel.class);
                intent.putExtra("ID",id);
                intent.putExtra("Name",name);
                intent.putExtra("Specialization",specialization);
                intent.putExtra("Address",address);
                intent.putExtra("RegistrationNo",registrationNo);
                intent.putExtra("Phone",phone);
                intent.putExtra("Email",email);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return veterinarianArrayList.size();
    }


    public static class VetViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Specialization, Address;
        View view;


        public VetViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            Name = itemView.findViewById(R.id.tvfirstname);
            Specialization = itemView.findViewById(R.id.tvspecial);
            Address = itemView.findViewById(R.id.tvaddress);


        }
    }
}