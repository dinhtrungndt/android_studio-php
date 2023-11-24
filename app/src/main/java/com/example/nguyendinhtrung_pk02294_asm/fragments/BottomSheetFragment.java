package com.example.nguyendinhtrung_pk02294_asm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsData;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsItem;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private NewsData newsData;

    public BottomSheetFragment(NewsData newsData) {
        this.newsData = newsData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView contentTextView = view.findViewById(R.id.contentTextView);
        TextView time = view.findViewById(R.id.time);

        titleTextView.setText(newsData.getTitle());
        contentTextView.setText(newsData.getContent());
        time.setText(newsData.getTime());

        return view;
    }
}
