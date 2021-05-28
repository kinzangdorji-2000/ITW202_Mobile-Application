package com.example.todo_22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private TextView mTitleText;
    private TextView mAuthorText;
    private EditText mBookInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleText = (TextView) findViewById(R.id.TitleText);
        mAuthorText = (TextView) findViewById(R.id.AuthorText);
        mBookInput = (EditText) findViewById(R.id.Book_Input);

        if (getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        new FetchBook(mTitleText, mAuthorText).execute(queryString);

        ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivity != null){
            networkInfo = connectivity.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected() && queryString.length() !=0){
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        }
        else {
            if (queryString.length() == 0){
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            }
            else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if (args !=null){
            queryString = args.getString("queryString");
        }
        return new BookLoader(this,queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Move to the next item.
                i++;
            }
            if (title != null && authors != null) {
                mTitleText.setText(title);
                mAuthorText.setText(authors);
            }
            else {
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText(R.string.no_results);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}