package com.example.hospital.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.Entity.Status;
import com.example.hospital.R;

import java.util.ArrayList;
import java.util.List;


public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolederStatus> {
    List<Status> Statuss = new ArrayList<>();
    private OnItemCliclkLisener mListener;

    @NonNull
    @Override
    public ViewHolederStatus onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolederStatus(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stutes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolederStatus holder, int position) {
        holder.tv_fullName.setText(Statuss.get(position).getFullName());
        holder.tv_status.setText(Statuss.get(position).getStorys());
        holder.tv_date.setText(Statuss.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return Statuss.size();
    }

    public void setlist(List<Status> Statuss) {
        this.Statuss = Statuss;
        notifyDataSetChanged();
    }

    public void OnItemCliclkLisener(OnItemCliclkLisener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public Status getAllWord(int pos) {
        return Statuss.get(pos);
    }

    public interface OnItemCliclkLisener {
        void onItemClick(Status status);
    }

    public class ViewHolederStatus extends RecyclerView.ViewHolder {
        TextView tv_fullName, tv_date, tv_status;

        public ViewHolederStatus(@NonNull View itemView) {
            super(itemView);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_status = itemView.findViewById(R.id.tv_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if (mListener != null && index != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(Statuss.get(index));
                    }

                }
            });

        }
    }
}








