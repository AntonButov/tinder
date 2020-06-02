package com.crocodile.tinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ResponseJsoup{

    final String URLPOLITIC = "https://www.freeprivacypolicy.com/";

    private TextView policyTextView;
    private Button buttonStart;

    private Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        policyTextView = findViewById(R.id.textViewPolic);
        policyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlPolic = URLPOLITIC;
                Uri uri = Uri.parse(urlPolic);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        buttonStart = findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
            startWork();
            }
        }.start();
    }

    private void startWork() {
    pref = new Pref(getApplicationContext());
    if (pref.getModer()) startTinder();
    else {
        try {
            startWebView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    private void startTinder() {

    }

    private void startWebView() throws IOException {
       new JsoupResponse(this).execute();
    }

    @Override
    public void response(String link) {
    if (link == null) finish();
    else
    if (link.equals("")) startTinder();
    else startWeb(link);
    }

    private void startWeb(String link) {
    Intent intent = new Intent(this, RWebView.class);
    intent.putExtra("link", link);
    startActivity(intent);
    }
}


