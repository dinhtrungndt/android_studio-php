package com.example.nguyendinhtrung_pk02294_asm.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsData;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsItem;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import retrofit2.Callback;

public class BottomSheetFragment extends BottomSheetDialogFragment {


    private NewsModelResponse newsModelResponse;

    public BottomSheetFragment(NewsModelResponse newsModelResponse) {
        this.newsModelResponse = newsModelResponse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView contentTextView = view.findViewById(R.id.contentTextView);
        TextView time = view.findViewById(R.id.time);
        ImageView imgDetail = view.findViewById(R.id.imgDetail);

        titleTextView.setText(newsModelResponse.getTitle());
        contentTextView.setText(newsModelResponse.getContent());
        time.setText(newsModelResponse.getCreated_at());

        String imageUrl = newsModelResponse.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(imgDetail);
        } else {
            imgDetail.setImageResource(R.drawable.login);
        }

        return view;
    }
}
