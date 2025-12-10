package main;

public class PerformanceReport {
    private String username;
    private int closedTickets;
    private double averageResolutionTime;
    private double performanceScore;
    private String seniority;
    public PerformanceReport(final String username,
                             final int closedTickets,
                             final double averageResolutionTime,
                             final double performanceScore,
                             final String seniority) {
        this.username = username;
        this.closedTickets = closedTickets;
        this.averageResolutionTime = averageResolutionTime;
        this.performanceScore = performanceScore;
        this.seniority = seniority;
    }

    /**
     * Returneaza username.
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returneaza nr de tichete closed.
     * @return
     */
    public int getClosedTickets() {
        return closedTickets;
    }

    /**
     * Returneaza avg res time.
     * @return
     */
    public double getAverageResolutionTime() {
        return averageResolutionTime;
    }

    /**
     * Returneaza scorul de performanta.
     * @return
     */
    public double getPerformanceScore() {
        return performanceScore;
    }

    /**
     * Returneaza senioritatea.
     * @return
     */
    public String getSeniority() {
        return seniority;
    }
    // setteri

    /**
     * Seteaza username-ul.
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Seteaza nr de tichete close.
     * @param closedTickets
     */
    public void setClosedTickets(final int closedTickets) {
        this.closedTickets = closedTickets;
    }

    /**
     * Seteaza avg res.
     * @param avg
     */
    public void setAverageResolutionTime(final double avg) {
        this.averageResolutionTime = avg;
    }

    /**
     * Seteaza scorul de performanta
     * @param performanceScore
     */
    public void setPerformanceScore(final double performanceScore) {
        this.performanceScore = performanceScore;
    }

    /**
     * Seteaza senioritatea
     * @param seniority
     */
    public void setSeniority(final String seniority) {
        this.seniority = seniority;
    }
}
