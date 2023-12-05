package com.example.nguyendinhtrung_pk02294_asm.fragments;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.nguyendinhtrung_pk02294_asm.R;
import com.example.nguyendinhtrung_pk02294_asm.activities.LoginActivity;
import com.example.nguyendinhtrung_pk02294_asm.activities.ThayDoiActivity;
import com.example.nguyendinhtrung_pk02294_asm.helpers.IRetrofitRouter;
import com.example.nguyendinhtrung_pk02294_asm.helpers.RetrofitHelper;
import com.example.nguyendinhtrung_pk02294_asm.models.UserLoginRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class TienIchFragment extends Fragment {
    ImageView avatarImageView;
    LinearLayout lnCapNhap, lnLogout, lnDoiMK, lnQuenMK;
    TextView txtThayDoi;
    TextView ndtText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tien_ich, container, false);

        lnCapNhap = view.findViewById(R.id.lnCapNhap);
        lnLogout = view.findViewById(R.id.lnLogout);
        lnDoiMK = view.findViewById(R.id.lnDoiMK);
        lnQuenMK = view.findViewById(R.id.lnQuenMK);
        txtThayDoi = view.findViewById(R.id.txtThayDoi);
        avatarImageView = view.findViewById(R.id.avatarImageView);
        ndtText = view.findViewById(R.id.ndtText);

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
                // Clear login status
                clearLoginStatus();

                // Redirect to the login activity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        txtThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThayDoiActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

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

    private void clearLoginStatus() {
        // Clear login status in SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginStatus", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
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
                        UserLoginRequest request = new UserLoginRequest();
                        request.setPassword(oldPassword);
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

    private void showUpdateProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.update_profile_dialog, null);

        final EditText passwordEditText = dialogView.findViewById(R.id.passwordEditText);
        final EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        final ImageView avatarImageView = dialogView.findViewById(R.id.avatarImageView);

        builder.setView(dialogView)
                .setTitle("Update Profile")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String password = passwordEditText.getText().toString();
                        String name = nameEditText.getText().toString();
                        updateProfile(password, name /* , otherFields */);
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

    private void updateProfile(String password, String name /* , otherFields */) {
        Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }

}