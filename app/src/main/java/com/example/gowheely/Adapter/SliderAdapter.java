package com.example.gowheely.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gowheely.Model.SliderModel;
import com.example.gowheely.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.viewHolder>{
    private SliderModel[] sliderModels;
    private Context ctx;

    public SliderAdapter(SliderModel[] sliderModels, Context ctx) {
        this.sliderModels = sliderModels;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public SliderAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_slider_layout,parent,false);

        return new SliderAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.viewHolder holder, int position) {

        final SliderModel sliderModel = sliderModels[position];
        Glide.with(ctx).load(sliderModel.getSliderImage()).into(holder.sliderImage);

    }

    @Override
    public int getItemCount() {
        return sliderModels.length;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView sliderImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            sliderImage=itemView.findViewById(R.id.iv_sliderImage);
        }
    }
}
