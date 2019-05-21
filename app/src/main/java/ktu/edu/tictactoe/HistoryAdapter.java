package ktu.edu.tictactoe;

import android.widget.BaseAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {

    ArrayList<HistoryEntry> dataList;
    Activity activity;

    HistoryAdapter(ArrayList<HistoryEntry> d, Activity a)
    {
        dataList = d;
        activity = a;
    }

    public int getCount()
    {
        if (dataList != null)
        {
            return dataList.size();
        }
        return 0;
    }

    public long getItemId(int position) { return position; }

    public Object getItem(int position)
    {
        if(dataList != null) {
            return dataList.get(position);
        }
        return null;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        View vi = convertView;
        if(vi == null)
        {
            LayoutInflater li = LayoutInflater.from(activity);
            vi = li.inflate(R.layout.history_list_layout, null);
        }

        TextView outcomeText = (TextView)vi.findViewById(R.id.listview_outcome);
        TextView dateText = (TextView)vi.findViewById(R.id.listview_date);


        HistoryEntry le = dataList.get(position);

        outcomeText.setText(le.getName());
        dateText.setText(le.getDate());

        return vi;
    }
}
