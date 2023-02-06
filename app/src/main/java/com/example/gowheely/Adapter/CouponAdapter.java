package com.example.gowheely.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowheely.Coupon;
import com.example.gowheely.Model.CouponModel;
import com.example.gowheely.R;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.viewHolder> {
    private Context context;
    private List<CouponModel> promocodeList;

    public CouponAdapter(Context context, List<CouponModel> promocodeList) {
        this.context = context;
        this.promocodeList = promocodeList;
    }

    @NonNull
    @Override
    public CouponAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_coupon_layout,parent,false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.viewHolder holder, int position) {
        final CouponModel couponModel = promocodeList.get(position);
        holder.couponCode.setText(couponModel.getCoupon_code());
        holder.couponDesc.setText(couponModel.getDescription());
        holder.maxDiscount.setText(couponModel.getDiscount_value());
    }

    @Override
    public int getItemCount() {
        return promocodeList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView couponCode,couponDesc,maxDiscount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            couponCode = itemView.findViewById(R.id.txt_couponCode);
            couponDesc = itemView.findViewById(R.id.txt_couponDesc);
            maxDiscount = itemView.findViewById(R.id.txt_totalDiscount);

        }
    }
}
