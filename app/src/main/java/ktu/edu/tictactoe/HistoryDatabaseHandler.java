package ktu.edu.tictactoe;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.util.ArrayList;

public class HistoryDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "history.db";

    private static String SCORES_TABLE_NAME = "scores";

    private final static String KEY_ID = "id";
    private final static String KEY_OUTCOME = "outcome";
    private final static String KEY_DATE = "date";

    public HistoryDatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + SCORES_TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_OUTCOME + " TEXT," +
                KEY_DATE + " TEXT " +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS " + SCORES_TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long addEntry(HistoryEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_OUTCOME, entry.getName());
        cv.put(KEY_DATE, entry.getDate());

        long id = db.insert(SCORES_TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    public HistoryEntry getEntry(int id)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        cursor = db.query(SCORES_TABLE_NAME, new String[] { KEY_ID, KEY_OUTCOME, KEY_DATE}, KEY_ID + "=?", new String[] { Integer.toString(id) }, null, null, null);

        HistoryEntry entry = new HistoryEntry();
        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                entry.setID(cursor.getInt(0));
                entry.setName(cursor.getString(1));
                entry.setDate(cursor.getString(2));
            }
        }

        cursor.close();
        db.close();

        return entry;
    }

    public ArrayList<HistoryEntry> getAllEntries()
    {
        ArrayList<HistoryEntry> entries = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + SCORES_TABLE_NAME + " ORDER BY " + KEY_DATE + " DESC ";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                do {
                    HistoryEntry entry = new HistoryEntry();
                    entry.setID(cursor.getInt(0));
                    entry.setName(cursor.getString(1));
                    entry.setDate(cursor.getString(2));
                    entries.add(entry);
                } while(cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();

        return entries;
    }

    public void deleteEntry(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SCORES_TABLE_NAME, KEY_ID + "=?", new String[]{Integer.toString(id)});
        db.close();
    }

    public void deleteEntries() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SCORES_TABLE_NAME, null, null);
        db.close();
    }

    public void updateEntry(HistoryEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_OUTCOME, entry.getName());
        cv.put(KEY_DATE, entry.getDate());

        db.update(SCORES_TABLE_NAME, cv, KEY_ID + "=?", new String[] { Integer.toString(entry.getID()) });

        db.close();
    }



}
