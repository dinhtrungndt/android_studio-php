package com.example.nguyendinhtrung_pk02294_asm.fragments;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.adapters.LichHocAdapter;
import com.example.nguyendinhtrung_pk02294_asm.adapters.NewsAdapter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocItem;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichHocFragment extends Fragment {
    ImageView avatarImageView;
    TextView ndtText;
//    ListView lvNews;
//    List<LichHocModelResponse> list;
//    LichHocAdapter adapter;
//
//    IRetrofitRouter iRetrofitRouter;

    ListView listView;
    List<LichHocItem> list;
    LichHocAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich_hoc, container, false);

        avatarImageView = view.findViewById(R.id.avatarImageView);
        ndtText = view.findViewById(R.id.ndtText);

//        lvNews = view.findViewById(R.id.recyclerViewLichHoc);
//
//        list = new ArrayList<>();
//        adapter = new LichHocAdapter(list);
//        lvNews.setAdapter(adapter);
//
//        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        listView = view.findViewById(R.id.recyclerViewLichHoc);

        // Create sample data
        list = new ArrayList<>();
        list.add(new LichHocItem("Lập trình Android", "Thứ 2, 8:00 - 10:00"));
        list.add(new LichHocItem("Xử lý ảnh", "Thứ 3, 10:30 - 12:30"));
        list.add(new LichHocItem("Machine Learning", "Thứ 4, 14:00 - 16:00"));
        list.add(new LichHocItem("Quản trị dự án IT", "Thứ 2, 14:00 - 16:00"));
        list.add(new LichHocItem("Phân tích yêu cầu hệ thống", "Thứ 3, 8:00 - 10:00"));
        list.add(new LichHocItem("Cơ sở dữ liệu", "Thứ 4, 10:30 - 12:30"));
        list.add(new LichHocItem("Kiến trúc phần mềm", "Thứ 5, 14:00 - 16:00"));
        list.add(new LichHocItem("Tiếng Anh chuyên ngành CNTT", "Thứ 6, 8:00 - 10:00"));
        list.add(new LichHocItem("Lập trình Android", "Thứ 2, 8:00 - 10:00"));
        list.add(new LichHocItem("Xử lý ảnh", "Thứ 3, 10:30 - 12:30"));
        list.add(new LichHocItem("Machine Learning", "Thứ 4, 14:00 - 16:00"));
        list.add(new LichHocItem("Quản trị dự án IT", "Thứ 2, 14:00 - 16:00"));
        list.add(new LichHocItem("Phân tích yêu cầu hệ thống", "Thứ 3, 8:00 - 10:00"));
        list.add(new LichHocItem("Cơ sở dữ liệu", "Thứ 4, 10:30 - 12:30"));
        list.add(new LichHocItem("Kiến trúc phần mềm", "Thứ 5, 14:00 - 16:00"));
        list.add(new LichHocItem("Tiếng Anh chuyên ngành CNTT", "Thứ 6, 8:00 - 10:00"));

        // Create and set the adapter
        adapter = new LichHocAdapter(list);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Get SharedPreferences from the parent activity
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginStatus", MODE_PRIVATE);
        String avatar = sharedPreferences.getString("avatar", "");
        String name = sharedPreferences.getString("name", "");

        // Set EditText values
        Glide.with(this)
                .load(avatar)
                .into(avatarImageView);
        ndtText.setText(name);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        iRetrofitRouter.getLichHoc().enqueue(getNewsCallback);
//    }
//
//    Callback<List<LichHocModelResponse>> getNewsCallback = new Callback<List<LichHocModelResponse>>() {
//        @Override
//        public void onResponse(Call<List<LichHocModelResponse>> call, Response<List<LichHocModelResponse>> response) {
//            if (response.isSuccessful()){
//                List<LichHocModelResponse> model = response.body();
//                if (model != null && model.size() > 0) {
//                    list.clear();
//                    list.addAll(model);
//                    adapter.notifyDataSetChanged();
//                    for (LichHocModelResponse news : list){
//                        Log.d(TAG, ">>>>>>>>>>>>>>>>" + news.getCa());
//                    }
//                }
//                else {
//                    Toast.makeText(getActivity(), "Lấy danh sách thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<LichHocModelResponse>> call, Throwable t) {
//            Log.d(">>> getNewsCallback", "onFailure: " + t.getMessage());
//        }
//    };
}
