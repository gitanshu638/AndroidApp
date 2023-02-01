package com.example.gowheely.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gowheely.Model.CategoryModel;
import com.example.gowheely.Model.OfferModel;
import com.example.gowheely.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    private Context ctx;
    private List<CategoryModel> categoryModels;

    public CategoryAdapter(Context ctx, List<CategoryModel> categoryModels) {
        this.ctx = ctx;
        this.categoryModels = categoryModels;
    }


    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_categories,parent,false);
        return new CategoryAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        final CategoryModel categoryModel=categoryModels.get(position);
        holder.categoryTitle.setText(categoryModel.getCategoryTitle());
        Glide.with(ctx).load(categoryModel.getCategoryImage()).into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView categoryTitle;
        public viewHolder(@NotNull View itemView){
            super(itemView);
            categoryImage = itemView.findViewById(R.id.iv_categoryImage);
            categoryTitle = itemView.findViewById(R.id.txt_categoryTitle);
        }
    }
}
