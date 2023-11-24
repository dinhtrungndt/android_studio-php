package com.example.nguyendinhtrung_pk02294_asm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocItem;

import java.util.List;

public class LichHocAdapter extends RecyclerView.Adapter<LichHocAdapter.ViewHolder> {

    private List<LichHocItem> lichHocItems;

    public LichHocAdapter(List<LichHocItem> lichHocItems) {
        this.lichHocItems = lichHocItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lich_hoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LichHocItem lichHocItem = lichHocItems.get(position);
        holder.monHocTextView.setText(lichHocItem.getMonHoc());
        holder.thoiGianTextView.setText(lichHocItem.getThoiGian());
        // Điền thông tin khác nếu cần
    }

    @Override
    public int getItemCount() {
        return lichHocItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView monHocTextView;
        TextView thoiGianTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            monHocTextView = itemView.findViewById(R.id.monHocTextView);
            thoiGianTextView = itemView.findViewById(R.id.thoiGianTextView);
            // Ánh xạ các TextView khác nếu cần
        }
    }
}
