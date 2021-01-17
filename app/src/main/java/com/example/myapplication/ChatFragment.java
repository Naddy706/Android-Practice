package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {


    //RecyclerView chatList
    private RecyclerView chatList;
    //RecyclerView chatList



    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View view =inflater.inflate(R.layout.fragment_chat, container, false);
        //RecyclerView chatList
        chatList = view.findViewById(R.id.ChatList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(linearLayoutManager);
        //RecyclerView chatList

//List ModelClass
        List<ChatListModel> chatListModels = new ArrayList<>();
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"Admin1","This is admin Descption1"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"Admin2","This is admin Descption2"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"Admin3","This is admin Descption3"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"Admin4","This is admin Descption4"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"Admin5","This is admin Descption5"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"Admin6","This is admin Descption6"));

        Adapter adapter = new Adapter(chatListModels);
        chatList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}