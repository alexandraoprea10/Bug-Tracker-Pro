package main.Ticket;

import main.Milestone;

public class History {
    private String action;
    private String from;
    private String to;
    private String by;
    private String milestone;
    private String timestamp;
    public static class Builder {
        private String action;
        private String from;
        private String to;
        private String by;
        private String milestone;
        private String timestamp;
        public Builder(String action, String by, String timestamp) {
            this.action = action;
            this.by = by;
            this.timestamp = timestamp;
        }
        public Builder from(String from) {
            this.from = from;
            return this;
        }
        public Builder to(String to) {
            this.to = to;
            return this;
        }
        public Builder milestone(String milestone) {
            this.milestone = milestone;
            return this;
        }
        public History build() {
            return new History(this);
        }
    }
    private History(Builder builder) {
        this.action = builder.action;
        this.from = builder.from;
        this.to = builder.to;
        this.by = builder.by;
        this.timestamp = builder.timestamp;
        this.milestone = builder.milestone;
    }
    // getteri
    public String getAction() {
        return action;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getBy() {
        return by;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getMilestone() {
        return milestone;
    }
    // setteri
    public void setAction(String action) {
        this.action = action;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setBy(String by) {
        this.by = by;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }
}
