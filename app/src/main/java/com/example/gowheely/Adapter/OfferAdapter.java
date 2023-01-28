package com.example.gowheely.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gowheely.Model.OfferModel;
import com.example.gowheely.R;

import java.util.ArrayList;
import java.util.List;

// Press Alt+Enter for options
public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.viewHolder> {
    private Context ctx;
    private List<OfferModel> offerModelList = new ArrayList<>();

    public OfferAdapter(Context ctx, List<OfferModel> offerModelList){
        this.ctx = ctx;
        this.offerModelList = offerModelList;
    }
    @NonNull
    @Override
    public OfferAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_offers,parent,false);
        return new OfferAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.viewHolder holder, int position) {
        final OfferModel offerModel=offerModelList.get(position);
        holder.offerPrice.setText(offerModel.getOfferPrice());
        holder.offerConstraint.setText(offerModel.getOfferConstraint());
        Glide.with(ctx).load(offerModel.getOfferImage()).into(holder.offerImage);
    }

    @Override
    public int getItemCount() {
        return offerModelList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView offerImage;
        private TextView offerPrice,offerConstraint;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            offerImage=itemView.findViewById(R.id.iv_offerImage);
            offerPrice=itemView.findViewById(R.id.txt_offerPrice);
            offerConstraint=itemView.findViewById(R.id.txt_offerConstraint);

        }
    }
}
