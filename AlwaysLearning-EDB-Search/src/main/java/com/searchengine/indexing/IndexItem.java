package com.searchengine.indexing;

public class IndexItem {

    private String id;
    private String title;
    private String content;
    private String type;
    private String description;

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";

    public IndexItem(String id, String title, String description) {
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

    public String getContent() {
        return content;
    }
    public String getType() {
        return type;
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String toString() {
        return "IndexItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
