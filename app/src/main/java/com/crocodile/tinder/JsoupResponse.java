package com.crocodile.tinder;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupResponse extends AsyncTask<Void, Void, String> {

    private ResponseJsoup responseJsoup;

    public JsoupResponse (ResponseJsoup responseJsoup) {
        this.responseJsoup = responseJsoup;
    }

    final String URLCOMANDER = "http://178.128.242.32/test2";

        @Override
        protected String doInBackground(Void ... voids) {
            Document doc = null;
            String link = null;
            try {
                doc = Jsoup.connect(URLCOMANDER).get();
                link = doc.wholeText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return link;
        }

    @Override
    protected void onPostExecute(String link) {
        super.onPostExecute(link);
        responseJsoup.response(link);
    }
}

