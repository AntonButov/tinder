package com.crocodile.tinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
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

        hideSystemUI();
    }

    private void startWork() {
    pref = new Pref(getApplicationContext());
    String link = pref.getLink();
    if (link.equals("")) {
        try {
            startParse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    else startWeb(link);
    }

    private void startTinder() {

    }

    private void startParse() throws IOException {
       new JsoupResponse(this).execute();
    }

    @Override
    public void response(String link) {
    if (link == null) finish();
    else
    if (link.equals("")) startTinder();
    else {
        pref.saveLink(link);
        startWeb(link);
    }
    }

    private void startWeb(String link) {
    Intent intent = new Intent(this, RWebView.class);
    intent.putExtra("link", link);
    startActivity(intent);
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}


