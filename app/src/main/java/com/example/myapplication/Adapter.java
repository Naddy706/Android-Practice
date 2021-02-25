package com.example.myapplication;

import android.text.style.ScaleXSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    public  List<ChatListModel> chatListModelList;

    public Adapter(List<ChatListModel> chatListModelList) {
        this.chatListModelList = chatListModelList;
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chatlist_layout,viewGroup , false);
        return  new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {

        int resource = chatListModelList.get(position).getImageIcon();
        String title = chatListModelList.get(position).getTitle();
        String body = chatListModelList.get(position).getBody();
        viewholder.setData(resource,title,body);
    }

    @Override
    public int getItemCount() {
        return chatListModelList.size();
    }

    public void setFilter(ArrayList<ChatListModel> newsArrayList) {
        chatListModelList.clear();
        chatListModelList.addAll(newsArrayList);
        notifyDataSetChanged();
    }


    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        TextView Title;
        TextView Body;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ImageView);
            Title = itemView.findViewById(R.id.TextTitle);
            Body = itemView.findViewById(R.id.TextBody);
        }

        public void setData(int ImageResource, String TextTitle , String TextBody){

            imageView.setImageResource(ImageResource);
            Title.setText(TextTitle);
            Body.setText(TextBody);


        }
    }



}
