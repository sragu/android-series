package tw.workshop.model;

import java.io.Serializable;

public class Status implements Serializable {

    private String storyNumber;
    private String Status;

    public Status(String storyNumber, String status) {
        this.storyNumber = storyNumber;
        Status = status;
    }

    public String getStoryNumber() {
        return storyNumber;
    }

    public void setStoryNumber(String storyNumber) {
        this.storyNumber = storyNumber;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
