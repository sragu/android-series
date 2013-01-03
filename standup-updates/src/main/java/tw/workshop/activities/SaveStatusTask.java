package tw.workshop.activities;

import android.content.Context;
import roboguice.util.RoboAsyncTask;
import tw.workshop.datastore.StatusDataStore;
import tw.workshop.model.Status;

public class SaveStatusTask extends RoboAsyncTask<Status> {

    private StatusCallback statusCallback;
    private StatusDataStore statusDataStore;
    private Status status;

    protected SaveStatusTask(Context context, StatusCallback statusCallback, StatusDataStore statusDataStore, Status status) {
        super(context);
        this.statusCallback = statusCallback;
        this.statusDataStore = statusDataStore;
        this.status = status;
    }

    @Override
    public Status call() throws Exception {
        statusDataStore.saveStatus(status);
        return status;
    }

    @Override
    protected void onSuccess(Status status) throws Exception {
        statusCallback.onSuccess("Status - " + status.getStoryNumber() + " - saved");
    }

    @Override
    protected void onException(Exception e) throws RuntimeException {
        statusCallback.onFailure("Error occured");
    }
}
