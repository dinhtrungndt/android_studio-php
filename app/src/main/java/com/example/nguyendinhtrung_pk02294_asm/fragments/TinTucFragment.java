package com.example.nguyendinhtrung_pk02294_asm.fragments;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.activities.LoginActivity;
import com.example.nguyendinhtrung_pk02294_asm.activities.MainActivity;
import com.example.nguyendinhtrung_pk02294_asm.adapters.NewsAdapter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsData;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsItem;
import com.example.nguyendinhtrung_pk02294_asm.models.NewsModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinTucFragment extends Fragment {
    ListView lvNews;
    List<NewsModelResponse> list;
    NewsAdapter adapter;

    IRetrofitRouter iRetrofitRouter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tin_tuc, container, false);

        lvNews = view.findViewById(R.id.newsListView);

        list = new ArrayList<>();
        adapter = new NewsAdapter(list);
        lvNews.setAdapter(adapter);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
//
//        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NewsModelResponse selectedNewsModel = (NewsModelResponse) adapter.getItem(position);
//                showBottomSheet(selectedNewsModel);
//            }
//        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        iRetrofitRouter.getNews().enqueue(getNewsCallback);
    }

    Callback<List<NewsModelResponse>> getNewsCallback = new Callback<List<NewsModelResponse>>() {
        @Override
        public void onResponse(Call<List<NewsModelResponse>> call, Response<List<NewsModelResponse>> response) {
            if (response.isSuccessful()){
                List<NewsModelResponse> model = response.body();
                if (model != null && model.size()>0) {
                    list.clear();
                    list.addAll(model);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(), "Lấy danh sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<List<NewsModelResponse>> call, Throwable t) {
            Log.d(">>> getNewsCallback", "onFailure: " + t.getMessage());
        }
    };

//    private void showBottomSheet(NewsModelResponse newsModelResponse) {
//        // Tạo một instance của BottomSheetFragment và truyền dữ liệu
//        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment((NewsData) newsModelResponse);
//
//        // Hiển thị BottomSheet
//        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
//    }
}