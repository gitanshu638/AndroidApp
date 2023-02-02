package com.example.gowheely.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gowheely.Model.CartModel;
import com.example.gowheely.Model.CategoryModel;
import com.example.gowheely.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    private Context ctx;
    private List<CartModel> cartModels;

    public CartAdapter(Context ctx, List<CartModel> cartModels) {
        this.ctx = ctx;
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public CartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_cart,parent,false);
        return new CartAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewHolder holder, int position) {
        final CartModel cartModel=cartModels.get(position);
        holder.cartTitle.setText(cartModel.getTitle());
        holder.cartBrand.setText(cartModel.getBrand());
        holder.cartQty.setText("Qty: "+cartModel.getQuantity());
        holder.cartOriginalPrice.setText("Rs. "+cartModel.getOriginalPrice());
        holder.carDiscountedPrice.setText("Rs. "+cartModel.getDiscountedPrice());
        holder.cartDesc.setText(cartModel.getDescription());
        Glide.with(ctx).load(cartModel.getCartImage()).into(holder.cartImage);

        holder.cartOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView cartImage;
        private TextView cartTitle,cartBrand,cartQty,cartOriginalPrice,carDiscountedPrice,cartDesc;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cartImage = itemView.findViewById(R.id.iv_cartImage);
            cartTitle = itemView.findViewById(R.id.txt_cartTitle);
            cartBrand = itemView.findViewById(R.id.txt_cartBrand);
            cartQty = itemView.findViewById(R.id.txt_cartQuantity);
            cartOriginalPrice = itemView.findViewById(R.id.txt_cartOriginalPrice);
            carDiscountedPrice = itemView.findViewById(R.id.txt_cartDiscountedPrice);
            cartDesc = itemView.findViewById(R.id.txt_cartDesc);

        }
    }
}
