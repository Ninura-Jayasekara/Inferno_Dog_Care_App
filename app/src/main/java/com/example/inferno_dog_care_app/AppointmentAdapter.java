package com.example.inferno_dog_care_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointentViewHolder> {

    Context context;
    ArrayList<Appointments> apointmentArrayList;

    public AppointmentAdapter(Context context, ArrayList<Appointments> apointmentArrayList) {
        this.context = context;
        this.apointmentArrayList = apointmentArrayList;
    }

    @NonNull
    @Override
    public AppointmentAdapter.AppointentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.appointment_item,parent, false);

        return new AppointentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.AppointentViewHolder holder, int position) {

        Appointments appointments = apointmentArrayList.get(position);

        holder.DoctorName.setText(appointments.DoctorName);
        holder.Owner.setText(appointments.Owner);
        holder.Date.setText(appointments.Date);
    }

    @Override
    public int getItemCount() {

        return apointmentArrayList.size();
    }



    public class AppointentViewHolder extends RecyclerView.ViewHolder{

        TextView DoctorName, Owner, Date;

        public AppointentViewHolder(@NonNull View itemView) {
            super(itemView);

            DoctorName = itemView.findViewById(R.id.tvdoctorname);
            Owner = itemView.findViewById(R.id.tvownername);
            Date = itemView.findViewById(R.id.tvdate);
        }
    }
}
