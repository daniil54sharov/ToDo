package MainPackage;

import java.io.Serializable;

public class ToDo implements Serializable {
    private String topic;
    private String description;

    ToDo(String topic) {
        this.topic = topic;
    }
    @Override
    public String toString() {
        return topic;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        if(description != null) return description;
        else return "";
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
