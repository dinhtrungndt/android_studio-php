package com.example.nguyendinhtrung_pk02294_asm.fragments;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.activities.LoginActivity;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;

public class TienIchFragment extends Fragment {
    LinearLayout lnCapNhap,lnLogout,lnDoiMK, lnQuenMK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tien_ich, container, false);

        lnCapNhap = view.findViewById(R.id.lnCapNhap);
        lnLogout = view.findViewById(R.id.lnLogout);
        lnDoiMK = view.findViewById(R.id.lnDoiMK);
        lnQuenMK = view.findViewById(R.id.lnQuenMK);

        lnCapNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateProfileDialog();
            }
        });

        lnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangePasswordDialog();
            }
        });

        lnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForgotPasswordDialog();
            }
        });

        lnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.change_password_dialog, null);

        final EditText oldPasswordEditText = dialogView.findViewById(R.id.oldPasswordEditText);
        final EditText newPasswordEditText = dialogView.findViewById(R.id.newPasswordEditText);
        final EditText confirmPasswordEditText = dialogView.findViewById(R.id.confirmPasswordEditText);

        builder.setView(dialogView)
                .setTitle("Change Password")
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String oldPassword = oldPasswordEditText.getText().toString();
                        String newPassword = newPasswordEditText.getText().toString();
                        String confirmPassword = confirmPasswordEditText.getText().toString();

                        // Kiểm tra mật khẩu cũ và xác nhận mật khẩu mới
                        if (newPassword.equals(confirmPassword)) {
                            // Gọi phương thức để xử lý việc đổi mật khẩu với oldPassword và newPassword
                        } else {
                            Toast.makeText(getActivity(), "New Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
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

    // Trong TienIchFragment.java
    private void showForgotPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.forgot_password_dialog, null);

        final EditText emailEditText = dialogView.findViewById(R.id.emailEditText);
        final ProgressBar progress = dialogView.findViewById(R.id.progress);

        builder.setView(dialogView)
                .setTitle("Forgot Password")
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progress.setVisibility(View.VISIBLE);
                        String email = emailEditText.getText().toString();
//                        if(email.equals("")){
//                            Toast.makeText(getActivity(), "Vui lòng nhập email !", Toast.LENGTH_SHORT).show();
//                            progress.setVisibility(View.GONE);
//                        }else {
//                            RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//                            String url = "http://192.168.11.116:8686/forgot-password.php";
//
//                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    progress.setVisibility(View.GONE);
//                                    if (response.equals("status")) {
//                                        Toast.makeText(getActivity(), "Gửi email thành công !", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(getActivity(), "Gửi thất bại !", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    progress.setVisibility(View.GONE);
//                                    error.printStackTrace();
//                                }
//                            }){
//                                protected Map<String, String> getParams(){
//                                    Map<String, String> paramV = new HashMap<>();
//                                    paramV.put("To email", email);
//                                    return paramV;
//                                }
//                            };
//
//                            stringRequest.setRetryPolicy(new RetryPolicy() {
//                                @Override
//                                public int getCurrentTimeout() {
//                                    return 50000;
//                                }
//
//                                @Override
//                                public int getCurrentRetryCount() {
//                                    return 50000;
//                                }
//
//                                @Override
//                                public void retry(VolleyError error) throws VolleyError {
//
//                                }
//                            });
//                            queue.add(stringRequest);
//
//                        }
                        sendPasswordResetRequest(email);
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

    private void sendPasswordResetRequest(String email) {
        // Sử dụng Retrofit để gửi yêu cầu đến máy chủ với email
        IRetrofitRouter retrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
        Call<Void> call = retrofitRouter.forgotPassword(email);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                    // Gửi email thành công, hiển thị thông báo cho người dùng
                    Toast.makeText(getActivity(), "Password recovery email sent to " + email, Toast.LENGTH_SHORT).show();
                } else {
                    // Gửi email thất bại, hiển thị thông báo cho người dùng
                    Toast.makeText(getActivity(), "Failed to send recovery email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý lỗi khi kết nối không thành công
                Toast.makeText(getActivity(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showUpdateProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.update_profile_dialog, null);

        final EditText passwordEditText = dialogView.findViewById(R.id.passwordEditText);
        final EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        final ImageView avatarImageView = dialogView.findViewById(R.id.avatarImageView);

        // Thêm logic để tải và hiển thị hình ảnh avatar từ nguồn dữ liệu nếu cần thiết
        // Ví dụ: avatarImageView.setImageResource(R.drawable.default_avatar);

        builder.setView(dialogView)
                .setTitle("Update Profile")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String password = passwordEditText.getText().toString();
                        String name = nameEditText.getText().toString();
                        // Lấy các giá trị của các trường khác tùy theo yêu cầu

                        // Gọi phương thức để xử lý việc cập nhật hồ sơ với các giá trị mới
                        updateProfile(password, name /*, otherFields */);
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

    private void updateProfile(String password, String name /*, otherFields */) {
        // Thực hiện các thay đổi cần thiết để cập nhật hồ sơ cá nhân
        // Ví dụ:
        // userService.updatePassword(password);
        // userService.updateName(name);
        // userService.updateOtherFields(otherFields);

        // Hiển thị thông báo cập nhật thành công hoặc xử lý lỗi nếu cần thiết
        Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }


}