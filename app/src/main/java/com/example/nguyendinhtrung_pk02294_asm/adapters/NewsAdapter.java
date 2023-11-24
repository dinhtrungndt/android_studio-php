package com.example.nguyendinhtrung_pk02294_asm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;

import java.util.List;

public class NewsAdapter extends BaseAdapter{
    List<NewsModelResponse> list;

    public NewsAdapter(List<NewsModelResponse> list){
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
            view = View.inflate(_viewGroup.getContext(), R.layout.item_list_news, null);
            TextView tvNewsTitle = view.findViewById(R.id.titleTextView);
            TextView contentTextView = view.findViewById(R.id.contentTextView);
            TextView timeTextView = view.findViewById(R.id.timeTextView);
            ImageView newsImageView = view.findViewById(R.id.newsImageView);
            ViewHolder holder = new ViewHolder(tvNewsTitle, contentTextView,timeTextView,newsImageView);
            view.setTag(holder);
        }
        NewsModelResponse modelResponse = (NewsModelResponse) getItem(_i);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvNewsTitle.setText(modelResponse.getTitle());
        holder.contentTextView.setText(modelResponse.getContent());
        holder.timeTextView.setText(modelResponse.getCreated_at());

        holder.newsImageView.setImageResource(modelResponse.getImageResource());
        return view;
    }

    public static class ViewHolder{
        final ImageView newsImageView;
        final TextView tvNewsTitle, contentTextView,timeTextView;

        public ViewHolder(TextView tvNewsTitle, TextView contentTextView, TextView timeTextView, ImageView newsImageView){
            this.tvNewsTitle = tvNewsTitle;
            this.contentTextView = contentTextView;
            this.timeTextView = timeTextView;
            this.newsImageView = newsImageView;
        }
    }
}