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
import com.example.nguyendinhtrung_pk02294_asm.adapters.DiemAdapter;
import com.example.nguyendinhtrung_pk02294_asm.adapters.LichHocAdapter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.DiemItem;
import com.example.nguyendinhtrung_pk02294_asm.models.LichHocModelResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.TranscriptsModelResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiemFragment extends Fragment {
    ImageView avatarImageView;
    ListView lvNews;
    List<TranscriptsModelResponse> list;
    DiemAdapter adapter;
    TextView ndtText;

    IRetrofitRouter iRetrofitRouter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bang_diem, container, false);

        lvNews = view.findViewById(R.id.recyclerViewDiem);
        avatarImageView = view.findViewById(R.id.avatarImageView);
        ndtText = view.findViewById(R.id.ndtText);

        list = new ArrayList<>();
        adapter = new DiemAdapter(list);
        lvNews.setAdapter(adapter);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        iRetrofitRouter.getTranscripts().enqueue(getNewsCallback);

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

    Callback<List<TranscriptsModelResponse>> getNewsCallback = new Callback<List<TranscriptsModelResponse>>() {
        @Override
        public void onResponse(Call<List<TranscriptsModelResponse>> call, Response<List<TranscriptsModelResponse>> response) {
            if (response.isSuccessful()){
                List<TranscriptsModelResponse> model = response.body();
                if (model != null && model.size() > 0) {
                    list.clear();
                    list.addAll(model);
                    adapter.notifyDataSetChanged();
                    for (TranscriptsModelResponse news : list){
                        Log.d(TAG, ">>>>>>>>>>>>>>>>" + news.getName());
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Lấy danh sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<List<TranscriptsModelResponse>> call, Throwable t) {
            Log.d(">>> getNewsCallback", "onFailure: " + t.getMessage());
        }
    };
}
