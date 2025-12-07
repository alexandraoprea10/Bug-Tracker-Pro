package main.Ticket;

import main.Milestone;

import java.util.ArrayList;
import java.util.List;


public abstract class Ticket {
    private int id;
    private String type;
    private String title;
    private String businessPriority;
    private int businesspriorityCode;
    private String status;
    private String createdAt;
    private String assignedAt;
    private String solvedAt;
    private String assignedTo;
    private List<String> comments;
    private List<String> authors;
    private List<String> date;
    private String expertiseArea;
    private String reportedBy;
    private String description;
    private ArrayList<History> histories;
    private boolean isAVailableForAssignment;
    private double calculateImpact;
    private double calculateRisk;
    private double calculateEfficiency;
    private int daysToResolve;
    // constructor
     public Ticket(final int id, final String type, final String title,
                   final String businessPriority, final String status,
                   final String createdAt, final String expertiseArea,
                   final String reportedBy, final String description,
                   final double calculateImpact, final double calculateRisk) {
         this.id = id;
         this.type =  type;
         this.title = title;
         this.businessPriority = businessPriority;
         this.status = status;
         this.createdAt = createdAt;
         this.assignedAt = "";
         this.solvedAt = "";
         this.assignedTo = "";
         this.expertiseArea = expertiseArea;
         this.reportedBy = reportedBy;
         this.description = description;
         this.comments = null;
         this.comments = new ArrayList<String>();
         this.authors = new ArrayList<String>();
         this.date = new ArrayList<String>();
         this.histories = new ArrayList<>();
         this.isAVailableForAssignment = true;
         if (businessPriority.equals("LOW")) {
             this.businesspriorityCode = 1;
         } else if (businessPriority.equals("MEDIUM")) {
             this.businesspriorityCode = 2;
         } else if (businessPriority.equals("HIGH")) {
             this.businesspriorityCode = 3;
         } else if (businessPriority.equals("CRITICAL")) {
             this.businesspriorityCode = 4;
         }
         this.calculateImpact = calculateImpact;
         this.calculateRisk = calculateRisk;
    }
    // getters

    /**
     * Returneaza id-ul
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returneaza tipul de tichet
     * @return tip de tichet
     */
    public String getType() {
        return type;
    }

    /**
     * Returneaza titlul
     * @return titlul
     */
    public String getTitle() {
        return title;
    }

    /**
     * REturneaza prioritatea
     * @return prioritatea
     */
    public String getBusinessPriority() {
        return businessPriority;
    }

    /**
     * Returneaza statusul
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returneaza cand a fost creat.
     * @return creat
     */
    public String getCreatedAt() {
         return createdAt;
    }

    /**
     * Returneaza cand a fost asignat
     * @return asignat
     */
    public String getAssignedAt() {
         return assignedAt;
    }

    /**
     * Returneaz acand a fost salvata
     * @return salvat
     */
    public String getSolvedAt() {
         return solvedAt;
    }

    /**
     * Returneaza cui a fost asignat
     * @return cui a fost asignat
     */
    public String getAssignedTo() {
         return assignedTo;
    }

    /**
     * Returneaza lista de comentarii
     * @return comentarii
     */
    public List<String> getComments() {
         return comments;
    }

    /**
     * Adauga comentariu
     * @param comme
     */
    public void addComment(final String comme) {
        this.comments.add(comme);
    }

    /**
     * Returneaza lista de autori
     * @return
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * Adauga autor in lista de autori.
     * @param auth
     */
    public void addAuthor(final String auth) {
        this.authors.add(auth);
    }

    /**
     * Returneaza lista de timestamps
     * @return
     */
    public List<String> getDate() {
        return date;
    }

    /**
     * Adauga timestamp in lista de timestamp
     * @param dat
     */
    public void addDate(final String dat) {
        this.date.add(dat);
    }

    /**
     *  Retruneaza zona de experienta
     * @return zona de expertiza
     */
    public String getExpertiseArea() {
        return expertiseArea;
    }

    /**
     * Rturneaza de cine a fost raportat
     * @return de cine a fost raportat
     */
    public String getReportedBy() {
        return reportedBy;
    }

    /**
     * Returneaza descrierea
     * @return descrierea
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returneaza istoricul
     * @return
     */
    public ArrayList<History> getHistories() {
        return histories;
    }

    /**
     * Vad daca tichetul e asignat unui developer.
     * @return
     */
    public boolean isAvailableForAssignment() {
        return isAVailableForAssignment;
    }
    public double getCalculateImpact() {
        return calculateImpact;
    }
    public double getCalculateRisk() {
        return calculateRisk;
    }
    public double getCalculateEfficiency() {
        return calculateEfficiency;
    }
    public int getDaysToResolve() {
        return daysToResolve;
    }
    public int getBusinessPriorityCode() {
        return businesspriorityCode;
    }
    // setteri

    /**
     * Seteaza id
     * @param id
     */
    public void setId(final int id) {
         this.id = id;
    }

    /**
     * Seteaza type
     * @param type
     */
    public void setType(final String type) {
         this.type = type;
    }

    /**
     * Seteaza titlul
     * @param title
     */
    public void setTitle(final String title) {
         this.title = title;
    }

    /**
     * Seteaza prioritatea
     * @param businessPriority
     */
    public void setBusinessPriority(final String businessPriority) {
         this.businessPriority = businessPriority;
    }

    /**
     * Seteaza statusul
     * @param status
     */
    public void setStatus(final String status) {
         this.status = status;
    }
    /**
     * Seteaza cand a fost creat
     * @param createdAt
     */
    public void setCreatedAt(final String createdAt) {
         this.createdAt = createdAt;
    }

    /**
     * Seteaza cand a fost asignat
     * @param assignedAt
     */
    public void setAssignedAt(final String assignedAt) {
         this.assignedAt = assignedAt;
    }

    /**
     * Seteaza cand a fost rezolvat
     * @param solvedAt
     */
    public void setSolvedAt(final String solvedAt) {
         this.solvedAt = solvedAt;
    }

    /**
     * Seteaza cui a fost asignat
     * @param assignedTo
     */
    public void setAssignedTo(final String assignedTo) {
         this.assignedTo = assignedTo;
    }

    /**
     * Seteaza ce zona de expertiza
     * @param expertiseArea
     */
    public void setExpertiseArea(final String expertiseArea) {
         this.expertiseArea = expertiseArea;
    }

    /**
     * Seteaza de cine a fost raportat
     * @param reportedBy
     */
    public void setReportedBy(final String reportedBy) {
         this.reportedBy = reportedBy;
    }

    /**
     * Seteaza descrierea
     * @param description
     */
    public void setDescription(final String description) {
         this.description = description;
    }

    /**
     * Seteaza lista de istoric.
     * @param histories
     */
    public void setHistories(final ArrayList<History> histories) {
        this.histories = histories;
    }

    /**
     * Setez daca tichetul a fost asignat unui developer.
     * @param isAVailableForAssignment
     */
    public void setIsAVailableForAssignment(final boolean isAVailableForAssignment) {
        this.isAVailableForAssignment = isAVailableForAssignment;
    }
    public void setCalculateImpact(final double calculateImpact) {
        this.calculateImpact = calculateImpact;
    }

    /**
     * Adauga un istoric.
     * @param history
     */
    public void addHistories(final History history) {
        this.histories.add(history);
    }
    public void setCalculateRisk(final double calculateRisk) {
        this.calculateRisk = calculateRisk;
    }
    public void setCalculateEfficiency(final double calculateEfficiency) {
        this.calculateEfficiency = calculateEfficiency;
    }
    public void setDaysToResolve(final int daysToResolve) {
        this.daysToResolve = daysToResolve;
    }

    /**
     * Cazul 1 pentru viewHistory.
     * @param by
     * @param timestamp
     */
    public void assignTicket(final String by,
                             final String timestamp) {
        History history = new History.Builder("ASSIGNED", by, timestamp)
                .build();
        histories.add(history);
    }

    /**
     * Cazul 2 pentru viewHistory.
     * @param by
     * @param timestamp
     */
    public void deAssignTicket(final String by,
                               final String timestamp) {
        History history = new History.Builder("DE-ASSIGNED", by, timestamp)
                .build();
        histories.add(history);
    }

    /**
     * Cazul 3 pentru viewHistory.
     * @param esteUndo verific la ce comanda face asta-
     *                 ori la undochangestatus ori la changestatus
     * @param to
     * @param by
     * @param timestamp
     */
    public void changeStatus(final int esteUndo,
                             final String to, final String by, final String timestamp) {
        String from = "OPEN";
        if (esteUndo == 0) {
            if (to.equals("IN_PROGRESS")) {
                from = "OPEN";
            } else if (to.equals("RESOLVED")) {
                from = "IN_PROGRESS";
            } else if (to.equals("CLOSED")) {
                from = "RESOLVED";
            }
        } else {
            if (to.equals("IN_PROGRESS")) {
                from = "RESOLVED";
            } else if (to.equals("RESOLVED")) {
                from = "CLOSED";
            } else if (to.equals("OPEN")) {
                from = "IN_PROGRESS";
            }
        }
        History history = new History.Builder("STATUS_CHANGED", by, timestamp)
                .from(from)
                .to(to)
                .build();
        histories.add(history);
    }

    /**
     * Cazul 4 pentru viewHistory.
     * @param milestone
     * @param by
     * @param timestamp
     */
    public void addToMilestone(final Milestone milestone,
                               final String by, final String timestamp) {
        History history = new History.Builder("ADDED_TO_MILESTONE", by, timestamp)
                .milestone(milestone.getName())
                .build();
        histories.add(history);
    }

    /**
     * Cazul 5 pentru viewHistory.
     * @param from
     * @param timestamp
     */
    public void removeFromDev(final String from, final String timestamp) {
        History history = new History.Builder("REMOVED_FROM_DEV", "system", timestamp)
                .from(from)
                .build();
        histories.add(history);
    }
    public abstract boolean isBUG();
    public abstract boolean isUI();
    public abstract boolean isFeature();
}
