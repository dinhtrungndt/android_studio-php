package com.example.nguyendinhtrung_pk02294_asm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btnRegister, btnLogin;
    Spinner spinner;

    IRetrofitRouter iRetrofitRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        spinner = findViewById(R.id.planets_spinner);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        List<String> planets = new ArrayList<>();
        planets.add("            ---------- Chọn cơ sở ----------");
        planets.add("FPT Polytechnic Hồ Chí Minh");
        planets.add("FPT Polytechnic Tây Nguyên");
        planets.add("FPT Polytechnic Hà Nội");
        planets.add("FPT Polytechnic Đà Nẵng");
        planets.add("FPT Polytechnic Cần Thơ");
        planets.add("FPT Polytechnic Hải Phòng");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtUser.getText().toString();
                String password = edtPass.getText().toString();

                UserLoginRequest request = new UserLoginRequest();
                request.setEmail(email);
                request.setPassword(password);

                iRetrofitRouter.login(request).enqueue(loginCallback);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    Callback<UserLoginResponse> loginCallback = new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            if (response.isSuccessful()){
                UserLoginResponse loginResponseDTO = response.body();
                if (loginResponseDTO.getStatus()) {
                    Toast.makeText(LoginActivity.this,
                                    "Success!!!", Toast.LENGTH_LONG)
                            .show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    // Lưu trạng thái login/user vào shared preferences
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,
                                    "Failed!!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }

        @Override
        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}