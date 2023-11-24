package com.example.nguyendinhtrung_pk02294_asm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.adapters.LichHocAdapter;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocItem;

import java.util.ArrayList;
import java.util.List;

public class LichHocFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich_hoc, container, false);

        RecyclerView recyclerViewLichHoc = view.findViewById(R.id.recyclerViewLichHoc);
        recyclerViewLichHoc.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Tạo danh sách lịch học mẫu
        List<LichHocItem> lichHocItems = new ArrayList<>();
        lichHocItems.add(new LichHocItem("Lập trình Android", "Thứ 2, 8:00 - 10:00"));
        lichHocItems.add(new LichHocItem("Xử lý ảnh", "Thứ 3, 10:30 - 12:30"));
        lichHocItems.add(new LichHocItem("Machine Learning", "Thứ 4, 14:00 - 16:00"));
        lichHocItems.add(new LichHocItem("Quản trị dự án IT", "Thứ 2, 14:00 - 16:00"));
        lichHocItems.add(new LichHocItem("Phân tích yêu cầu hệ thống", "Thứ 3, 8:00 - 10:00"));
        lichHocItems.add(new LichHocItem("Cơ sở dữ liệu", "Thứ 4, 10:30 - 12:30"));
        lichHocItems.add(new LichHocItem("Kiến trúc phần mềm", "Thứ 5, 14:00 - 16:00"));
        lichHocItems.add(new LichHocItem("Tiếng Anh chuyên ngành CNTT", "Thứ 6, 8:00 - 10:00"));

        // Tạo adapter và gán cho RecyclerView
        LichHocAdapter lichHocAdapter = new LichHocAdapter(lichHocItems);
        recyclerViewLichHoc.setAdapter(lichHocAdapter);

        return view;
    }
}
