package tw.workshop.model;

import android.content.ContentValues;
import tw.workshop.datastore.StatusUpdatesHelper;

import java.io.Serializable;

public class Status implements Serializable {

    private String storyNumber;
    private String status;
    private String details;

    public Status(String storyNumber, String status, String details) {
        this.storyNumber = storyNumber;
        this.status = status;
        this.details = details;
    }

    public String getStoryNumber() {
        return storyNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(StatusUpdatesHelper.COLUMN_STORY_NO, this.getStoryNumber());
        contentValues.put(StatusUpdatesHelper.COLUMN_DETAILS, this.getDetails());
        contentValues.put(StatusUpdatesHelper.COLUMN_STATUS, this.getStatus());
        return contentValues;
    }
}
