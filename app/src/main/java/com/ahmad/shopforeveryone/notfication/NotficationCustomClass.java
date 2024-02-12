package com.ahmad.shopforeveryone.notfication;

public class NotficationCustomClass {
    private String title;
    private String body;
    private long timestamp;

    public NotficationCustomClass() {
    }

    public NotficationCustomClass(String title, String body) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
