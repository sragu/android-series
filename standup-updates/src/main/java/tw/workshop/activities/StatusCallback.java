package tw.workshop.activities;

public interface StatusCallback {
    void onFailure(String reason);

    void onSuccess(String message);
}
