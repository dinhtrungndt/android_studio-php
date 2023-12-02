package com.example.nguyendinhtrung_pk02294_asm.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.fragments.TienIchFragment;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.ModelPutUser;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginResponse;
import com.example.nguyendinhtrung_pk02294_asm.models.UserRegisterRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThayDoiActivity extends AppCompatActivity {
    ImageView cloneTab, imgAvatar,imgSave;
    EditText edtEmail,edtName,edtPassword;
    private Uri selectedImageUri;
    private String imageUrl;
    private int id;

    IRetrofitRouter iRetrofitRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi);

        imgAvatar = findViewById(R.id.imgAvatar);
        imgSave = findViewById(R.id.imgSave);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        cloneTab = findViewById(R.id.cloneTab);

        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);

        // Khởi tạo Cloudinary
        MediaManager.init(this);

        cloneTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở Intent để chọn ảnh từ thư viện
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();

                // Retrieve the user ID from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
                int userId = sharedPreferences.getInt("userId", -1); // -1 is a default value if the key is not found

                if (userId != -1) {
                    // User ID is valid, proceed with the request
                    ModelPutUser request = new ModelPutUser();
                    request.setId(userId); // Set the retrieved user ID
                    request.setEmail(email);
                    request.setName(name);
                    request.setPassword(password);
                    request.setAvatar(imageUrl);

                    Log.d(TAG, "Email: " + email);
                    Log.d(TAG, "Name: " + name);
                    Log.d(TAG, "Password: " + password);
                    Log.d(TAG, "ImageUrl: " + imageUrl);

                    iRetrofitRouter.putUser(request).enqueue(putCallback);
                    Intent intent = new Intent(ThayDoiActivity.this, MainActivity.class);
                    Toast.makeText(ThayDoiActivity.this, "Cập nhập thành công !", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                } else {
                    // Handle the case where the user ID is not available
                    Toast.makeText(ThayDoiActivity.this, "User ID not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called");

        // Retrieve information from SharedPreferences and set EditText values
        SharedPreferences sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String name = sharedPreferences.getString("name", "");

        // Set EditText values
        edtEmail.setText(email);
        edtName.setText(name);
        edtPassword.setText("*");

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
                            Glide.with(ThayDoiActivity.this)
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

    Callback<UserLoginResponse> putCallback = new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            if (response.isSuccessful()){
                UserLoginResponse regisResponseDTO = response.body();
                if (regisResponseDTO != null && regisResponseDTO.getStatus() != null && regisResponseDTO.getStatus()) {
                    Toast.makeText(ThayDoiActivity.this, "Registration Success!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ThayDoiActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        }

        @Override
        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
            Log.d(">>> register", "onFailure: " + t.getMessage());
        }
    };
}