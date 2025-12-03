package main;

public class Notifications {
    private String notification;
    private boolean isSeen;
    public Notifications(String notification) {
        this.notification = notification;
        this.isSeen = false;
    }
    public String getNotification() {
        return notification;
    }
    public boolean isSeen() {
        return isSeen;
    }
    public void setNotification(String notification) {
        this.notification = notification;
    }
    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
