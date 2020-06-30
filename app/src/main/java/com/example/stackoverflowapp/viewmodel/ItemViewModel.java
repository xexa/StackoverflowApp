package com.example.stackoverflowapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.stackoverflowapp.datasource.ItemDataSource;
import com.example.stackoverflowapp.datasource.ItemDataSourceFactory;
import com.example.stackoverflowapp.model.Item;

public class ItemViewModel extends ViewModel {

    private LiveData<PagedList<Item>> itemPageList;
    private LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE)
                .build();

        itemPageList = (new LivePagedListBuilder(itemDataSourceFactory, config))
                .build();
    }
}
