package tw.workshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import tw.workshop.R;
import tw.workshop.model.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusAdapter extends BaseAdapter {

    public static List<Status> statusList = new ArrayList<Status>();

    public StatusAdapter() {
    }

    @Override
    public int getCount() {
        return statusList.size();
    }

    @Override
    public Object getItem(int i) {
        return statusList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.status_row, null);
        }

        TextView storyNumberView = (TextView) view.findViewById(R.id.story_number);
        Status status = statusList.get(i);
        storyNumberView.setText(status.getStoryNumber());
        return view;

    }

    public void add(Status status) {
        statusList.add(status);
        notifyDataSetChanged();
    }
}
