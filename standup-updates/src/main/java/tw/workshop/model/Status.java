package tw.workshop.model;

import java.io.Serializable;

public class Status implements Serializable {

    private String storyNumber;
    private String status;
    private String details;
    private boolean blocked;

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

    public boolean isBlocked() {
        return blocked;
    }
}
