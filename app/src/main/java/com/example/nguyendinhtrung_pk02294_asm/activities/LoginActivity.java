package com.example.nguyendinhtrung_pk02294_asm.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.ModelResetPass;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegister;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btnRegister, btnLogin;
    TextView forgetPass;
    Spinner spinner;

    IRetrofitRouter iRetrofitRouter;

    private static final String PREFS_NAME = "loginPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        forgetPass = findViewById(R.id.forgetPass);
        spinner = findViewById(R.id.planets_spinner);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        // Kiểm tra trạng thái đăng nhập
        checkLoginStatus();

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

        CheckBox rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        SharedPreferences loginPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if "Remember Me" was checked in the previous session
        boolean rememberMeChecked = loginPreferences.getBoolean(KEY_REMEMBER_ME, false);
        rememberMeCheckbox.setChecked(rememberMeChecked);

        if (rememberMeChecked) {
            // If checked, retrieve the saved username and password
            String savedUsername = loginPreferences.getString(KEY_USERNAME, "");
            String savedPassword = loginPreferences.getString(KEY_PASSWORD, "");

            edtUser.setText(savedUsername);
            edtPass.setText(savedPassword);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtUser.getText().toString();
                String password = edtPass.getText().toString();

                UserLoginRequest request = new UserLoginRequest();
                request.setEmail(email);
                request.setPassword(password);

                iRetrofitRouter.login(request).enqueue(loginCallback);

                // Save the username and "Remember Me" status
                // Save the username, password, and "Remember Me" status
                saveRememberMeStatus(rememberMeCheckbox.isChecked(), email, password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogRP();
            }
        });

    }

    private void saveRememberMeStatus(boolean isChecked, String username, String password) {
        // Save "Remember Me" status, username, and password into SharedPreferences
        SharedPreferences loginPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putBoolean(KEY_REMEMBER_ME, isChecked);

        if (isChecked) {
            editor.putString(KEY_USERNAME, username);
            editor.putString(KEY_PASSWORD, password);
        } else {
            // If "Remember Me" is unchecked, clear the saved username and password
            editor.remove(KEY_USERNAME);
            editor.remove(KEY_PASSWORD);
        }

        editor.apply();
    }


    Callback<UserLoginResponse> loginCallback = new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            if (response.isSuccessful()) {
                UserLoginResponse loginResponseDTO = response.body();
                if (loginResponseDTO.getStatus()) {
                    // Retrieve the user ID
                    int userId = loginResponseDTO.getUser().getId();

                    // Save the user ID for later use
                    saveUserId(userId);

                    // Retrieve user email and name
                    String email = loginResponseDTO.getUser().getEmail();
                    String name = loginResponseDTO.getUser().getName();

                    // Save information to SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("loginStatus", MODE_PRIVATE).edit();
                    editor.putString("email", email);
                    editor.putString("name", name);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Failed!!!", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };


    private void saveUserId(int userId) {
        // Save the user ID for later use (e.g., in updates)
        SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }

    private void saveLoginStatus(boolean isLoggedIn) {
        // Lưu trạng thái đăng nhập vào SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }

    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is already logged in, redirect to MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void showDiaLogRP(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.forgot_password_dialog, null);

        final EditText emailEditText = dialogView.findViewById(R.id.emailEditText);

        builder.setView(dialogView)
                .setTitle("Forgot Password")
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = emailEditText.getText().toString();
                        ModelResetPass request = new ModelResetPass();
                        request.setEmail(email);
                        iRetrofitRouter.forgotPassword(request).enqueue(resetCallback);  // Change from equals to enqueue
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    Callback<UserRegister> resetCallback = new Callback<UserRegister>() {
        @Override
        public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
            if (response.isSuccessful()){
                UserRegister loginResponseDTO = response.body();
                if (loginResponseDTO.getStatus()) {
                    Toast.makeText(LoginActivity.this, "Đã gửi email lấy lại mật khẩu thành công!!!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Failed!!!", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<UserRegister> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };

}