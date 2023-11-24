package com.example.nguyendinhtrung_pk02294_asm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.adapters.DiemAdapter;
import com.example.nguyendinhtrung_pk02294_asm.models.DiemItem;

import java.util.ArrayList;
import java.util.List;

public class DiemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bang_diem, container, false);

        RecyclerView recyclerViewDiem = view.findViewById(R.id.recyclerViewDiem);
        recyclerViewDiem.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Tạo danh sách điểm mẫu
        List<DiemItem> diemItems = new ArrayList<>();
        diemItems.add   (new DiemItem("Lập trình Android", "9.0"));
        diemItems.add(new DiemItem("Xử lý ảnh", "8.5"));
        diemItems.add(new DiemItem("Machine Learning", "10.0"));
        diemItems.add(new DiemItem("Quản trị dự án IT", "8.0"));
        diemItems.add(new DiemItem("Phân tích yêu cầu hệ thống", "9.5"));
        diemItems.add(new DiemItem("Cơ sở dữ liệu", "7.5"));
        diemItems.add(new DiemItem("Kiến trúc phần mềm", "8.8"));
        diemItems.add(new DiemItem("Tiếng Anh chuyên ngành CNTT", "9.2"));

        // Tạo adapter và gán cho RecyclerView
        DiemAdapter diemAdapter = new DiemAdapter(diemItems);
        recyclerViewDiem.setAdapter(diemAdapter);

        return view;
    }
}
