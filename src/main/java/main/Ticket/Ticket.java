package main.Ticket;

import java.util.List;


public class Ticket {
    private int id;
    private String type;
    private String title;
    private String businessPriority;
    private String status;
    private String createdAt;
    private String assignedAt;
    private String solvedAt;
    private String assignedTo;
    private List<String> comments;
    private String expertiseArea;
    private String reportedBy;
    private String description;
    // constructor
     public Ticket(final int id, final String type, final String title,
                   final String businessPriority, final String status,
                   final String createdAt, final String expertiseArea,
                   final String reportedBy, final String description) {
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
}
