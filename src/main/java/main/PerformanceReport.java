package main;

public class PerformanceReport {
    private String username;
    private int closedTickets;
    private double averageResolutionTime;
    private double performanceScore;
    private String seniority;
    public PerformanceReport(String username, int closedTickets, double averageResolutionTime, double performanceScore, String seniority) {
        this.username = username;
        this.closedTickets = closedTickets;
        this.averageResolutionTime = averageResolutionTime;
        this.performanceScore = performanceScore;
        this.seniority = seniority;
    }
    public String getUsername() {
        return username;
    }
    public int getClosedTickets() {
        return closedTickets;
    }
    public double getAverageResolutionTime() {
        return averageResolutionTime;
    }
    public double getPerformanceScore() {
        return performanceScore;
    }
    public String getSeniority() {
        return seniority;
    }
    // setteri
    public void setUsername(final String username) {
        this.username = username;
    }
    public void setClosedTickets(final int closedTickets) {
        this.closedTickets = closedTickets;
    }
    public void setAverageResolutionTime(final double avg) {
        this.averageResolutionTime = avg;
    }
    public void setPerformanceScore(double performanceScore) {
        this.performanceScore = performanceScore;
    }
    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }
}
