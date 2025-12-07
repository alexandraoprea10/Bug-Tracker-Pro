package main;

public final class Notifications {
    private String notification;
    private boolean isSeen;
    public Notifications(final String notification) {
        this.notification = notification;
        this.isSeen = false;
    }

    /**
     * Returneaza mesajul din notificare
     * @return
     */
    public String getNotification() {
        return notification;
    }

    /**
     * Returneaza daca s-a mai printat notificarea.
     * @return
     */
    public boolean isSeen() {
        return isSeen;
    }

    /**
     * Setez notificarea.
     * @param notification
     */
    public void setNotification(final String notification) {
        this.notification = notification;
    }

    /**
     * Verific daca mai trebuie sa printez notificarea.
     * @param seen
     */
    public void setSeen(final boolean seen) {
        isSeen = seen;
    }
}
