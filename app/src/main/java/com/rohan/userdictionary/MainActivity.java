package com.rohan.userdictionary;

import android.database.Cursor;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    ListView dictionaryListView;
    SimpleCursorAdapter cursorAdapter;

    private static final String[] COLUMNS_OF_CURSOR = new String[]{
            UserDictionary.Words.WORD,
            UserDictionary.Words.FREQUENCY
    };

    private static final int[] TEXT_VIEWS_OF_LAYOUT = new int[]{
            android.R.id.text1,
            android.R.id.text2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionaryListView = (ListView) findViewById(R.id.dictionary_list_view);

        Cursor cursor = getContentResolver().query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, COLUMNS_OF_CURSOR, TEXT_VIEWS_OF_LAYOUT, 0);
        dictionaryListView.setAdapter(cursorAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        cursorAdapter.notifyDataSetChanged();
    }
}
