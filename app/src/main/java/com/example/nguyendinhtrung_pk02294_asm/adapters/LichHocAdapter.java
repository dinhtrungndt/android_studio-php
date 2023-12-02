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
    List<LichHocItem> list;

    public LichHocAdapter(List<LichHocItem> list){
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

            LichHocAdapter.ViewHolder holder = new LichHocAdapter.ViewHolder(monHocIdTextView, diaDiemTextView, caTextView);
            view.setTag(holder);
        }
        LichHocItem modelResponse = (LichHocItem) getItem(_i);
        LichHocAdapter.ViewHolder holder = (LichHocAdapter.ViewHolder) view.getTag();
        holder.monHocIdTextView.setText(String.valueOf(modelResponse.getMonHoc()));
        holder.diaDiemTextView.setText(modelResponse.getThoiGian());

        return view;
    }

    public static class ViewHolder{
        final TextView monHocIdTextView, diaDiemTextView,caTextView;

        public ViewHolder(TextView monHocIdTextView, TextView diaDiemTextView, TextView caTextView){
            this.monHocIdTextView = monHocIdTextView;
            this.diaDiemTextView = diaDiemTextView;
            this.caTextView = caTextView;
        }
    }
}
