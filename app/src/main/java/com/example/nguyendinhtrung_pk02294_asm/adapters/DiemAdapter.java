package com.example.nguyendinhtrung_pk02294_asm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.DiemItem;

import java.util.List;

public class DiemAdapter extends RecyclerView.Adapter<DiemAdapter.ViewHolder> {

    private List<DiemItem> diemItems;

    public DiemAdapter(List<DiemItem> diemItems) {
        this.diemItems = diemItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiemItem diemItem = diemItems.get(position);
        holder.monHocTextView.setText(diemItem.getMonHoc());
        holder.diemTextView.setText(diemItem.getDiem());
        // Điền thông tin khác nếu cần
    }

    @Override
    public int getItemCount() {
        return diemItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView monHocTextView;
        TextView diemTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            monHocTextView = itemView.findViewById(R.id.monHocTextView);
            diemTextView = itemView.findViewById(R.id.diemTextView);
            // Ánh xạ các TextView khác nếu cần
        }
    }
}
