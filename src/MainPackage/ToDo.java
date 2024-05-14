package MainPackage;

public class ToDo {
    private String topic;
    private String description;

    ToDo(String topic) {
        this.topic = topic;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
