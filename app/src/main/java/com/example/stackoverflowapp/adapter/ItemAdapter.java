package com.example.stackoverflowapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stackoverflowapp.R;
import com.example.stackoverflowapp.model.Item;

import java.util.Objects;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewholder> {

    private Context context;

    public ItemAdapter(Context ctx) {
        super(DIFF_CALLBACK);

        this.context = ctx;
    }

    @NonNull
    @Override
    public ItemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false);

        return new ItemViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewholder holder, int position) {

        Item item = getItem(position);

        if (item != null){
            Glide.with(context)
                    .load(item.getOwner().getProfileImage())
                    .into(holder.imageView);

            holder.nameTextView.setText(item.getOwner().getDisplayName());
        }else {
            Toast.makeText(context, "Item is null", Toast.LENGTH_SHORT).show();
        }
    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                    return oldItem.getAnswerId().equals(newItem.getAnswerId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };

    class ItemViewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTextView;

        public ItemViewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.textViewName);
        }
    }
}
