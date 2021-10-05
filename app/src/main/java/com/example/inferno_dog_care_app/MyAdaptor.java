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

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public MyAdaptor(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.adopt_item,parent, false);

       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptor.MyViewHolder holder, int position) {
        User user = userArrayList.get(position);

        holder.name.setText(user.name);
        holder.age.setText(user.age);

        String id =user.id;
        String name =user.name;
        String age =user.age;
        String color =user.color;
        String contact_no =user.contact_no;
        String gender =user.gender;
        String weight =user.weight;


        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AdoptDogDetails.class);
                intent.putExtra("id",id);
                intent.putExtra("age",age);
                intent.putExtra("name",name);
                intent.putExtra("color",color);
                intent.putExtra("contact_no",contact_no);
                intent.putExtra("gender",gender);
                intent.putExtra("weight",weight);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, age;
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.tvbreed);
            age = itemView.findViewById(R.id.tvage);
        }
    }
}
