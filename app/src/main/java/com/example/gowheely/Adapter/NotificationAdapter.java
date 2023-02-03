package com.example.gowheely.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowheely.Model.NotificationModel;
import com.example.gowheely.Model.OfferModel;
import com.example.gowheely.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHlder> {
    private Context ctx;
    private List<NotificationModel> notificationModels;

    public NotificationAdapter(Context ctx, List<NotificationModel> notificationModels) {
        this.ctx = ctx;
        this.notificationModels = notificationModels;
    }

    @NonNull
    @Override
    public NotificationAdapter.viewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_notification,parent,false);
        return new NotificationAdapter.viewHlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.viewHlder holder, int position) {

        final NotificationModel notificationModel=notificationModels.get(position);
        holder.notiType.setText(notificationModel.getNotiType());
        holder.notiDate.setText(notificationModel.getNotiDate());
        holder.notiDesc.setText(notificationModel.getNotiDesc());
    }

    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    public class viewHlder extends RecyclerView.ViewHolder {
        private TextView notiType, notiDate, notiDesc;
        public viewHlder(@NonNull View itemView) {
            super(itemView);
            notiType=itemView.findViewById(R.id.txt_notificationType);
            notiDate=itemView.findViewById(R.id.txt_notificationDate);
            notiDesc=itemView.findViewById(R.id.txt_notificationDesc);
        }
    }
}
