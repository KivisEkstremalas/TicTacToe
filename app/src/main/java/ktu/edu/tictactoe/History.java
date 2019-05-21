package ktu.edu.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    HistoryDatabaseHandler db;
    ArrayList<HistoryEntry> mContents;

    HistoryAdapter mAdapter;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        db = new HistoryDatabaseHandler(this);

        mContents = new ArrayList<>();
        mContents = db.getAllEntries();

        mAdapter = new HistoryAdapter(mContents, this);

        mListView = (ListView)findViewById(R.id.historyList);
        mListView.setAdapter(mAdapter);
    }
}
