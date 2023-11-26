package com.example.nguyendinhtrung_pk02294_asm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.DiemItem;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.TranscriptsModelResponse;

import java.util.List;

public class DiemAdapter extends BaseAdapter {
    List<TranscriptsModelResponse> list;

    public DiemAdapter(List<TranscriptsModelResponse> list){
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_diem, null);
            TextView monHocTextView = view.findViewById(R.id.monHocTextView);
            TextView diemTextView = view.findViewById(R.id.diemTextView);

            DiemAdapter.ViewHolder holder = new DiemAdapter.ViewHolder( monHocTextView, diemTextView);
            view.setTag(holder);
        }
        TranscriptsModelResponse modelResponse = (TranscriptsModelResponse) getItem(_i);
        DiemAdapter.ViewHolder holder = (DiemAdapter.ViewHolder) view.getTag();
        holder.monHocTextView.setText(modelResponse.getName());
        holder.diemTextView.setText(String.valueOf(modelResponse.getPoint()));

        return view;
    }
    public static class ViewHolder{
        final TextView monHocTextView, diemTextView;

        public ViewHolder(TextView monHocTextView, TextView diemTextView){
            this.monHocTextView = monHocTextView;
            this.diemTextView = diemTextView;
        }
    }

}
