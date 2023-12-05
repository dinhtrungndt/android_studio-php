package com.example.nguyendinhtrung_pk02294_asm.fragments;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.activities.LoginActivity;
import com.example.nguyendinhtrung_pk02294_asm.activities.MainActivity;
import com.example.nguyendinhtrung_pk02294_asm.activities.ThayDoiActivity;
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
    ImageView avatarImageView;
    ListView lvNews;
    List<NewsModelResponse> list;
    NewsAdapter adapter;
    TextView ndtText;

    IRetrofitRouter iRetrofitRouter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tin_tuc, container, false);

        lvNews = view.findViewById(R.id.newsListView);
        avatarImageView = view.findViewById(R.id.avatarImageView);
        ndtText = view.findViewById(R.id.ndtText);

        list = new ArrayList<>();
        adapter = new NewsAdapter(list);
        lvNews.setAdapter(adapter);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsModelResponse selectedNewsModel = (NewsModelResponse) adapter.getItem(position);
                fetchNewsDetail(selectedNewsModel.getId());
            }
        });

        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThayDoiActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void fetchNewsDetail(int newsId) {
        // Call the API to fetch news detail by ID
        iRetrofitRouter.getNewsDetail(newsId).enqueue(new Callback<NewsModelResponse>() {
            @Override
            public void onResponse(Call<NewsModelResponse> call, Response<NewsModelResponse> response) {
                if (response.isSuccessful()) {
                    NewsModelResponse newsDetail = response.body();
                    if (newsDetail != null) {
                        showBottomSheet(newsDetail);
                    } else {
                        Toast.makeText(getActivity(), "Failed to get news detail", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsModelResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to get news detail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBottomSheet(NewsModelResponse newsModelResponse) {
        // Tạo một instance của BottomSheetFragment và truyền dữ liệu
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(newsModelResponse);

        // Hiển thị BottomSheet
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onResume() {
        super.onResume();
        iRetrofitRouter.getNews().enqueue(getNewsCallback);

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


    Callback<List<NewsModelResponse>> getNewsCallback = new Callback<List<NewsModelResponse>>() {
        @Override
        public void onResponse(Call<List<NewsModelResponse>> call, Response<List<NewsModelResponse>> response) {
            if (response.isSuccessful()){
                List<NewsModelResponse> model = response.body();
                if (model != null && model.size() > 0) {
                    list.clear();
                    list.addAll(model);
                    adapter.notifyDataSetChanged();
                    for (NewsModelResponse news : list){
                        Log.d(TAG, ">>>>>>>>>>>>>>>>" + news.getImage());
                    }
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



}