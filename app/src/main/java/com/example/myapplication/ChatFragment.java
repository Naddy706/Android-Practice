package com.example.myapplication;

import android.app.Application;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {


    //RecyclerView chatList
    private RecyclerView chatList;
    //RecyclerView chatList

    Adapter adapter;

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
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"bbb","This is admin Descption2"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"cc","This is admin Descption3"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"drd","This is admin Descption4"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"yvyv","This is admin Descption5"));
        chatListModels.add(new ChatListModel(R.drawable.ic_launcher_background,"zew","This is admin Descption6"));

         adapter = new Adapter(chatListModels);
        chatList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        setHasOptionsMenu(true);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu, menu);
        // Associate searchable configuration with the SearchView
        final MenuItem searchItem = menu.findItem(R.id.search_bar);
        MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
     //   adapter.setFilter(chatList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
    //    if (newText == null || newText.trim().isEmpty()) {
           // adapter.setFilter(chatList);
    //        return false;
    //    }
    //    newText = newText.toLowerCase();
    //    final ArrayList<ChatListModel> filteredNewsList = new ArrayList<>();
    //    for (News model : newsList) {
     //       final String title = model.getTitle().toLowerCase();

       //     if ((title.contains(newText))) {
       //         filteredNewsList.add(model);
       //     }
     //   }
       // adapter.setFilter(chatList);
        return true;
    }



}