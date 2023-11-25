package com.example.nguyendinhtrung_pk02294_asm.fragments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    ListView lvNews;
    List<LichHocModelResponse> list;
    LichHocAdapter adapter;

    IRetrofitRouter iRetrofitRouter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich_hoc, container, false);

        lvNews = view.findViewById(R.id.recyclerViewLichHoc);

        list = new ArrayList<>();
        adapter = new LichHocAdapter(list);
        lvNews.setAdapter(adapter);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        iRetrofitRouter.getLichHoc().enqueue(getNewsCallback);
    }

    Callback<List<LichHocModelResponse>> getNewsCallback = new Callback<List<LichHocModelResponse>>() {
        @Override
        public void onResponse(Call<List<LichHocModelResponse>> call, Response<List<LichHocModelResponse>> response) {
            if (response.isSuccessful()){
                List<LichHocModelResponse> model = response.body();
                if (model != null && model.size() > 0) {
                    list.clear();
                    list.addAll(model);
                    adapter.notifyDataSetChanged();
                    for (LichHocModelResponse news : list){
                        Log.d(TAG, ">>>>>>>>>>>>>>>>" + news.getCa());
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Lấy danh sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<List<LichHocModelResponse>> call, Throwable t) {
            Log.d(">>> getNewsCallback", "onFailure: " + t.getMessage());
        }
    };
}
