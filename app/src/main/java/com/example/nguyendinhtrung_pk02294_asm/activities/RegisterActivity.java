package com.example.nguyendinhtrung_pk02294_asm.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtUser, edtName, edtPass, edtPassConfirm;
    private TextView tvlogin;
    private Button btnRegister;
    private Uri selectedImageUri;
    private ImageView imgAvatar;
    private String imageUrl;

    IRetrofitRouter iRetrofitRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser = findViewById(R.id.edtUser);
        edtName = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        edtPassConfirm = findViewById(R.id.edtPassConfirm);
        imgAvatar = findViewById(R.id.imgAvatar);

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở Intent để chọn ảnh từ thư viện
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        tvlogin = findViewById(R.id.tvlogin);
        btnRegister = findViewById(R.id.btnRegister);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        // Khởi tạo Cloudinary
        MediaManager.init(this);

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
                request.setAvatar(imageUrl);

                iRetrofitRouter.register(request).enqueue(regisCallback);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Lấy đường dẫn của ảnh được chọn
            selectedImageUri = data.getData();

            // Đọc giá trị cloud_name từ resources
            String cloudName = getResources().getString(R.string.cloud_name);

            // Tải ảnh lên Cloudinary
            MediaManager.get().upload(selectedImageUri)
                    .unsigned("myfpt_ki6_androidstudio") // Thay "your_unsigned_preset" bằng unsigned preset của bạn
                    .option("public_id111", "public_id111") // Thay "your_public_id" bằng public_id của bạn (tùy chọn)
                    .option("cloud_name", cloudName) // Sử dụng cloud_name đã đọc từ resources
                    .callback(new UploadCallback() {
                        @Override
                        public void onStart(String requestId) {
                            // TODO: Xử lý khi bắt đầu tải ảnh
                        }

                        @Override
                        public void onProgress(String requestId, long bytes, long totalBytes) {
                            // TODO: Cập nhật tiến trình tải ảnh (nếu cần)
                        }

                        @Override
                        public void onSuccess(String requestId, Map resultData) {
                            // Lấy URL của ảnh sau khi tải lên thành công
                             imageUrl = (String) resultData.get("url");

                            // Load selected image into ImageView using Glide
                            Glide.with(RegisterActivity.this)
                                    .load(imageUrl)
                                    .into(imgAvatar);

                            // Set the background of the ImageView to null (remove the current background drawable)
                            imgAvatar.setBackground(null);

                            Log.d(TAG, ">>>>>>>>>>>>>>>> 141 " + imageUrl);
                        }

                        @Override
                        public void onError(String requestId, ErrorInfo error) {
                            Log.e("Cloudinary", "Error during image upload: " + error.getDescription());
                        }

                        @Override
                        public void onReschedule(String requestId, ErrorInfo error) {
                            // TODO: Xử lý khi có lỗi và cần reschedule lại quá trình tải ảnh
                        }
                    })
                    .dispatch();
        }
    }

    Callback<UserLoginResponse> regisCallback = new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            if (response.isSuccessful()){
                UserLoginResponse regisResponseDTO = response.body();
                if (regisResponseDTO != null && regisResponseDTO.getStatus()) {
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