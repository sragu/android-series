package tw.workshop.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import tw.workshop.R;

public class StatusAdapter extends CursorAdapter {

    public StatusAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.status_row, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView storyNumberView = (TextView) view.findViewById(R.id.story_number);
        TextView storyStatusView = (TextView) view.findViewById(R.id.story_status);
        TextView detailsView = (TextView) view.findViewById(R.id.details);
        storyNumberView.setText(cursor.getString(1));
        detailsView.setText(cursor.getString(2));
        storyStatusView.setText(cursor.getString(3));
    }


}
