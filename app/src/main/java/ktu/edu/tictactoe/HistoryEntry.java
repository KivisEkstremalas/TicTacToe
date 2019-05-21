package ktu.edu.tictactoe;

public class HistoryEntry {

    private int mID;
    private String mName;
    private String mDate;

    public HistoryEntry()
    {
        mID = 0;
        mName = "";
        mDate = "";
    }

    public HistoryEntry(int id, String name, String date)
    {
        mID = id;
        mName = name;
        mDate = date;
    }

    public void setID(int val)
    {
        mID = val;
    }

    public int getID()
    {
        return mID;
    }

    public void setName(String val)
    {
        mName = val;
    }

    public String getName()
    {
        return mName;
    }

    public void setDate(String date)
    {
        mDate = date;
    }

    public String getDate()
    {
        return mDate;
    }
}
