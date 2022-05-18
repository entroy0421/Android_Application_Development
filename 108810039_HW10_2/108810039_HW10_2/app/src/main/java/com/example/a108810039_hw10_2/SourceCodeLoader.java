package com.example.a108810039_hw10_2;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SourceCodeLoader extends AsyncTaskLoader<String> {

    private String mQueryString;
    private String mTransferProtocol;
    Context mContext;

    public SourceCodeLoader(Context context, String queryString, String transferProtocol) {
        super(context);
        mContext = context;
        mQueryString = queryString;
        mTransferProtocol = transferProtocol;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Log.d("asdfasdfasdf", "asdfasdfasdf");

        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String htmlSourceCode = null;

        try{
            Uri builder;
            if (mTransferProtocol.equals("http")){
                // http
                builder = Uri.parse(mQueryString).buildUpon()
                        .scheme("http")
                        .build();
            }
            else{
                // https
                builder = Uri.parse(mQueryString).buildUpon()
                        .scheme("https")
                        .build();
            }

            URL requestURL = new URL(builder.toString());
            Log.d("request url", builder.toString());
            // httpURLConnection automatically goes for http or https based on the URI scheme
            httpURLConnection = (HttpURLConnection) requestURL.openConnection();
            httpURLConnection.getRequestMethod();
            httpURLConnection.connect();
            Log.d("connection", String.valueOf(httpURLConnection));
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            if (stringBuilder.length() == 0){
                return null;
            }
            htmlSourceCode = stringBuilder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        Log.d("source code: ", htmlSourceCode);
        return htmlSourceCode;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}