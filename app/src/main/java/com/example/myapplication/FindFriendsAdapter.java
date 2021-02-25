package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FindFriendsAdapter extends RecyclerView.Adapter<FindFriendsAdapter.ViewHolder> {


    Context context;
    ArrayList<Users> users;

    public FindFriendsAdapter(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public FindFriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_friend_layout,parent , false);
        return  new FindFriendsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindFriendsAdapter.ViewHolder holder, int position) {

        Users user =users.get(position);
        holder.name.setText("Name :"+user.getName());
        holder.email.setText("Email "+user.getEmail());
        holder.btnSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+user.getToken(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,email;
        Button btnSendReq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            name = itemView.findViewById(R.id.textView4);
            email = itemView.findViewById(R.id.textView5);

            btnSendReq = itemView.findViewById(R.id.SendRequest);


        }
    }
}
