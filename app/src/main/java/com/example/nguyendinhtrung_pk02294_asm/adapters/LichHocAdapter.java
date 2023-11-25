package com.example.nguyendinhtrung_pk02294_asm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocItem;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocModelResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LichHocAdapter extends BaseAdapter{
    List<LichHocModelResponse> list;

    public LichHocAdapter(List<LichHocModelResponse> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int _i, View _view, ViewGroup _viewGroup) {
        View view = _view;
        if(view == null){
            view = View.inflate(_viewGroup.getContext(), R.layout.item_lich_hoc, null);
            TextView monHocIdTextView = view.findViewById(R.id.monHocIdTextView);
            TextView diaDiemTextView = view.findViewById(R.id.diaDiemTextView);
            TextView caTextView = view.findViewById(R.id.caTextView);
            TextView ngayHocTextView = view.findViewById(R.id.ngayHocTextView);
            TextView userIdTextView = view.findViewById(R.id.userIdTextView);

            LichHocAdapter.ViewHolder holder = new LichHocAdapter.ViewHolder(monHocIdTextView, diaDiemTextView, caTextView,ngayHocTextView,userIdTextView);
            view.setTag(holder);
        }
        LichHocModelResponse modelResponse = (LichHocModelResponse) getItem(_i);
        LichHocAdapter.ViewHolder holder = (LichHocAdapter.ViewHolder) view.getTag();
        holder.monHocIdTextView.setText(String.valueOf(modelResponse.getMonhoc_id()));
        holder.diaDiemTextView.setText(modelResponse.getDiadiem());
        holder.caTextView.setText(modelResponse.getCa());
        holder.ngayHocTextView.setText(modelResponse.getNgayhoc());
        holder.userIdTextView.setText(String.valueOf(modelResponse.getUser_id()));

        return view;
    }

    public static class ViewHolder{
        final TextView monHocIdTextView, diaDiemTextView,caTextView,ngayHocTextView,userIdTextView;

        public ViewHolder(TextView monHocIdTextView, TextView diaDiemTextView, TextView caTextView, TextView ngayHocTextView, TextView userIdTextView){
            this.monHocIdTextView = monHocIdTextView;
            this.diaDiemTextView = diaDiemTextView;
            this.caTextView = caTextView;
            this.ngayHocTextView = ngayHocTextView;
            this.userIdTextView = userIdTextView;
        }
    }
}
