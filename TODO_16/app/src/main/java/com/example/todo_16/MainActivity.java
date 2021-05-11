package com.example.todo_16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private wordListAdapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 25; i++){
            mWordList.addLast("word" + i);
        }
        mRecycleView = findViewById(R.id.recyclerView);
        mAdapter = new wordListAdapter(this, mWordList);
        mRecycleView.setAdapter(mAdapter);
        LinearLayoutManager obj = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(obj);

        FloatingActionButton fab = findViewById(R.id.button_plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wordListSize = mWordList.size();
                //Add a new word to the word list
                mWordList.addLast("+ Word" + wordListSize);
                //Notify the adapter that the data has changed
                mRecycleView.getAdapter().notifyItemInserted(wordListSize);
                //Scroll to the bottom
                mRecycleView.smoothScrollToPosition(wordListSize);
            }
        });
    }
}