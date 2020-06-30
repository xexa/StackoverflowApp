package com.example.stackoverflowapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.stackoverflowapp.R;
import com.example.stackoverflowapp.adapter.ItemAdapter;
import com.example.stackoverflowapp.model.Item;
import com.example.stackoverflowapp.model.StackApiResponse;
import com.example.stackoverflowapp.service.RetrofitClient;
import com.example.stackoverflowapp.viewmodel.ItemViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemViewModel itemViewModel;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        itemAdapter = new ItemAdapter(this);

        itemViewModel.itemPageList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                itemAdapter.submitList(items);
            }
        });

        recyclerView.setAdapter(itemAdapter);




    }
}