package com.example.a108810039_hw10_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.loader.app.LoaderManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<String> {
    private String mSpinnerValue;
    private EditText mURLEditText;
    private TextView mSourceCodeTextView;
    private static final String QUERY = "queryString";
    private static final String PROTOCOL = "transferProtocol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mURLEditText = findViewById(R.id.editTextTextPersonName);
        mSourceCodeTextView = findViewById(R.id.page_source_code);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.http_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.http_spinner);
        if (spinner != null){
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            spinner.setAdapter(adapter);
        }

        Log.d("loader", "loader");
        LoaderManager.getInstance(this).initLoader(0, null, this);
        if (LoaderManager.getInstance(this).getLoader(0) != null){
            Log.d("loader", "loader not null");
            LoaderManager.getInstance(this).initLoader(0, null, this);
        } else {
            Log.d("loader", "loader null");
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerValue = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        String[] values = getResources().getStringArray(R.array.http_array);
        mSpinnerValue = values[0];
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle bundle) {
        Log.d("run loader", "loader running");
        Log.d("bundle", String.valueOf(bundle));
        String queryString = "";
        String transferProtocol = "";
        if (bundle != null){
            queryString = bundle.getString(QUERY);
            transferProtocol = bundle.getString(PROTOCOL);
        }
        return new SourceCodeLoader(this, queryString, transferProtocol);
    }

    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try{
            mSourceCodeTextView.setText(s);
        }
        catch (Exception e){
            e.printStackTrace();
            mSourceCodeTextView.setText(R.string.no_response);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void getSourceCode(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String queryString = mURLEditText.getText().toString();

        // check connectivity before executing query
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null){
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && (queryString.length() != 0)){
            Bundle queryBundle = new Bundle();
            queryBundle.putString(QUERY, queryString);
            queryBundle.putString(PROTOCOL, mSpinnerValue);
            LoaderManager.getInstance(this).restartLoader(0, queryBundle, this);
            mSourceCodeTextView.setText(R.string.loading);
        }
        else{
            if (queryString.length() == 0){
                Toast.makeText(this, R.string.no_url, Toast.LENGTH_LONG).show();
            }
            else if(!URLUtil.isValidUrl(queryString)){
                Toast.makeText(this, R.string.invalid_url, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onClick(View view) {

    }
}