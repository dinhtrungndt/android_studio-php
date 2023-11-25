package com.example.nguyendinhtrung_pk02294_asm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUser, edtName, edtPass, edtPassConfirm;
    Button btnLogin, btnRegister;

    IRetrofitRouter iRetrofitRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = findViewById(R.id.edtUser);
        edtName = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        edtPassConfirm = findViewById(R.id.edtPassConfirm);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtUser.getText().toString();
                String name = edtName.getText().toString();
                String password = edtPass.getText().toString();
                String PassConfirm = edtPassConfirm.getText().toString();

                UserRegisterRequest request = new UserRegisterRequest();
                request.setEmail(email);
                request.setName(name);
                request.setPassword(password);
                request.setPassword_confirm(PassConfirm);

                iRetrofitRouter.register(request).enqueue(regisCallback);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    Callback<UserLoginResponse> regisCallback = new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            if (response.isSuccessful()){
                UserLoginResponse regisResponseDTO = response.body();
                if (regisResponseDTO != null && regisResponseDTO.getStatus()) {
                    Toast.makeText(RegisterActivity.this, "Registration Success!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Failed!!!", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
            Log.d(">>> register", "onFailure: " + t.getMessage());
        }
    };

}