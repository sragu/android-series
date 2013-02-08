package tw.workshop.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import tw.workshop.R;

public class StatusAdapter extends ResourceCursorAdapter {

    public StatusAdapter(Context context) {
        super(context, R.layout.status_row, null);
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
