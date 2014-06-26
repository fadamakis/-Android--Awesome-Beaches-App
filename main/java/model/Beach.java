package model;

import java.io.Serializable;

public class Beach implements Serializable {
    private String id;
    private String title;
    private String description;

    public Beach(String id, String title, String description) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}