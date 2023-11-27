package com.example.nguyendinhtrung_pk02294_asm.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nguyendinhtrung_pk02294_asm.R;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://chat.socket.io");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSocket.connect();
    }
}