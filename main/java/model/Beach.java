package model;

import java.io.Serializable;

public class Beach implements Serializable {
    private String slug;
    private String title;
    private String description;

    public Beach(String slug, String title, String description) {
        super();
        this.slug = slug;
        this.title = title;
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}