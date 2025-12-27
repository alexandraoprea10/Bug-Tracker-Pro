package main.Ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.MagicNumbersDouble;
import main.MagicNumbersInt;
import main.Milestone;
import main.PerformanceReport;
import main.User.Developer;
import main.User.Manager;
import main.User.Users;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;
import static main.App.returnTicket;

public class VeziTichete {
    private List<Ticket> inventarTichete;
    private int bugTickets;
    private int uiTickets;
    private int featureTickets;
    private int lowPriority;
    private int mediumPriority;
    private int highPriority;
    private int criticalPriority;
    private double impactForBUG;
    private double impactForUI;
    private double impactForFeature;
    private double riskForBug;
    private double riskForUI;
    private double riskForFeature;
    private double efficiencyForBUG;
    private double efficiencyForUI;
    private double efficiencyForFeature;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Getter pentru objectMapper
     * @return
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * Returneaza inventarul de tichete.
     * @return
     */
    public List<Ticket> getInventarTichete() {
        return inventarTichete;
    }

    /**
     * Returneaza nr de tichete de tip BUG.
     * @return
     */
    public int getbugTickets() {
        return bugTickets;
    }

    /**
     * Returneaza nr de tichete de tip UI_FEEDBACK.
     * @return
     */
    public int getuiTickets() {
        return uiTickets;
    }

    /**
     * Returneaza nr de tichete de tip Feature_Request.
     * @return
     */
    public int getfeatureTickets() {
        return featureTickets;
    }

    /**
     * Returneaza nr de tichete cu prioritatea LOW.
     * @return
     */
    public int getlowPriority() {
        return lowPriority;
    }

    /**
     * Returneaza nr de tichete cu prioritatea MEDIUM.
     * @return
     */
    public int getmediumPriority() {
        return mediumPriority;
    }

    /**
     * Returneaza nr de tichete cu prioritatea HIGH.
     * @return
     */
    public int gethighPriority() {
        return highPriority;
    }

    /**
     * REturneaza nr de tichete cu prioritatea CRITICAL.
     * @return
     */
    public int getcriticalPriority() {
        return criticalPriority;
    }

    /**
     * Returneaza impactul tichetelor BUG
     * @return
     */
    public double getImpactForBUG() {
        return impactForBUG;
    }

    /**
     * Returneaza impactul tichetelor UI_Feedback
     * @return
     */
    public double getImpactForUI() {
        return impactForUI;
    }

    /**
     * Returneaza impactul tichetelor FeatureRequest
     * @return
     */
    public double getImpactForFeature() {
        return impactForFeature;
    }

    /**
     * REturneaza riscul tichetelor BUG.
     * @return
     */
    public double getRiskForBUG() {
        return riskForBug;
    }

    /**
     * Returneaza riscul tichetelor UI_FEedback
     * @return
     */
    public double getRiskForUI() {
        return riskForUI;
    }

    /**
     * Returneaza riscul tichetelor FeatureRequest.
     * @return
     */
    public double getRiskForFeature() {
        return riskForFeature;
    }

    /**
     * Returneaza eficienta tichetelor BUG.
     * @return
     */
    public  double getEfficiencyForBUG() {
        return efficiencyForBUG;
    }

    /**
     * Returneaza eficienta tichetelor UI_Feedback.
     * @return
     */
    public  double getEfficiencyForUI() {
        return efficiencyForUI;
    }

    /**
     * Returneaza eficienta tichetelor FeatureRequest.
     * @return
     */
    public  double getEfficiencyForFeature() {
        return efficiencyForFeature;
    }

    /**
     * Seteaza nr de tichete de tip BUG
     * @param bug
     */
    public void setbugTickets(final int bug) {
        this.bugTickets = bug;
    }

    /**
     * Seteaza nr de tichete de tip UI_Feedback
     * @param ui
     */
    public void setuiTickets(final int ui) {
        this.uiTickets = ui;
    }

    /**
     * Seteaza nr de tichete de tip featurE_request.
     * @param feature
     */
    public void setfeatureTickets(final int feature) {
        this.featureTickets = feature;
    }

    /**
     * Seteaza nr de tichete cu prioritatea LOW
     * @param low
     */
    public void setlowPriority(final int low) {
        this.lowPriority = low;
    }

    /**
     * Seteaza nr de tichete cu prioritatea MEDIUM.
     * @param medium
     */
    public void setmediumPriority(final int medium) {
        this.mediumPriority = medium;
    }

    /**
     * Seteaza nr de tichete cu prioritatea HIGH.
     * @param high
     */
    public void sethighPriority(final int high) {
        this.highPriority = high;
    }

    /**
     * Seteaza nr de tichete cu prioritatea CRITICAL.
     * @param critical
     */
    public void setcriticalPriority(final int critical) {
        this.criticalPriority = critical;
    }

    /**
     * Seteaza impactul pt tichetele BUG.
     * @param impactForBUG
     */
    public void setImpactForBUG(final double impactForBUG) {
        this.impactForBUG = impactForBUG;
    }

    /**
     * Seteaza impactul pt tichetele UI_feedback.
     * @param impactForUI
     */
    public void setImpactForUI(final double impactForUI) {
        this.impactForUI = impactForUI;
    }

    /**
     * Seteaza impactul pt tichetele featureRequest.
     * @param impactForFeature
     */
    public void setImpactForFeature(final double impactForFeature) {
        this.impactForFeature = impactForFeature;
    }

    /**
     * Seteaza riscul pt tichetele BUG.
     * @param riskForBUG
     */
    public void setRiskForBUG(final double riskForBUG) {
        this.riskForBug = riskForBUG;
    }

    /**
     * Seteaza riscul pt tichetele UI_Feedback.
     * @param riskForUI
     */
    public void setRiskForUI(final double riskForUI) {
        this.riskForUI = riskForUI;
    }

    /**
     * Seteaza riscul pt tichetele featureRequest.
     * @param riskForFeature
     */
    public void setRiskForFeature(final double riskForFeature) {
        this.riskForFeature = riskForFeature;
    }

    /**
     * Seteaza eficienta pt tichetele BUG.
     * @param efficiencyForBUG
     */
    public void setEfficiencyForBUG(final double efficiencyForBUG) {
        this.efficiencyForBUG = efficiencyForBUG;
    }

    /**
     * Seteaza eficienta pt tichetele UI_Feature.
     * @param efficiencyForUI
     */
    public void setEfficiencyForUI(final double efficiencyForUI) {
        this.efficiencyForUI = efficiencyForUI;
    }

    /**
     * Seteaza eficienta pt tichetele featureRequest.
     * @param efficiencyForFeature
     */
    public void setEfficiencyForFeature(final double efficiencyForFeature) {
        this.efficiencyForFeature = efficiencyForFeature;
    }
    /**
     * Seteaza inventarul de tichete
     * @param inventarTichete
     */
    public void setInventarTichete(final List<Ticket> inventarTichete) {
        this.inventarTichete = inventarTichete;
    }

    /**
     * Printeaza tichetele
     * @return nod ajutator pentru printare
     */
    public ObjectNode viewTicketsManager() {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(this.inventarTichete, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int dataC = o1.getCreatedAt().compareTo(o2.getCreatedAt());
                if (dataC != 0) {
                    return dataC;
                }
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        for (int i = 0; i < this.inventarTichete.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Ticket ticket = inventarTichete.get(i);
            node.put("id", ticket.getId());
            node.put("type", ticket.getType());
            node.put("title", ticket.getTitle());
            node.put("businessPriority", ticket.getBusinessPriority());
            node.put("status", ticket.getStatus());
            node.put("createdAt", ticket.getCreatedAt());
            node.put("assignedAt", ticket.getAssignedAt());
            node.put("solvedAt", ticket.getSolvedAt());
            node.put("assignedTo", ticket.getAssignedTo());
            node.put("reportedBy", ticket.getReportedBy());
            ArrayNode commentsNode = mapper.createArrayNode();
            if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
                for (int j = 0; j < ticket.getComments().size(); j++) {
                    String comment = ticket.getComments().get(j);
                    String author = ticket.getAuthors().get(j);
                    String date = ticket.getDate().get(j);
                    ObjectNode printComment = mapper.createObjectNode();
                    printComment.put("author", author);
                    printComment.put("content", comment);
                    printComment.put("createdAt", date);
                    commentsNode.add(printComment);
                }
            }
            node.set("comments", commentsNode);
            arrayNode.add(node);
        }
        finalNode.set("tickets", arrayNode);
        return finalNode;
    }

    /**
     * Printeaza tichete vizibile pentru reporteri.
     * @param username
     * @return
     */
    public ObjectNode viewTicketsReporter(final String username) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        /**
         * Sortare
         */
        Collections.sort(this.inventarTichete, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int dataC = o1.getCreatedAt().compareTo(o2.getCreatedAt());
                if (dataC != 0) {
                    return dataC;
                }
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        for (int i = 0; i < this.inventarTichete.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Ticket ticket = inventarTichete.get(i);
            if (ticket.getReportedBy().equals(username)) {
                node.put("id", ticket.getId());
                node.put("type", ticket.getType());
                node.put("title", ticket.getTitle());
                node.put("businessPriority", ticket.getBusinessPriority());
                node.put("status", ticket.getStatus());
                node.put("createdAt", ticket.getCreatedAt());
                node.put("assignedAt", ticket.getAssignedAt());
                node.put("solvedAt", ticket.getSolvedAt());
                node.put("assignedTo", ticket.getAssignedTo());
                node.put("reportedBy", ticket.getReportedBy());
                ArrayNode commentsNode = mapper.createArrayNode();
                if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
                    for (int j = 0; j < ticket.getComments().size(); j++) {
                        String comment = ticket.getComments().get(j);
                        String author = ticket.getAuthors().get(j);
                        String date = ticket.getDate().get(j);
                        ObjectNode printComment = mapper.createObjectNode();
                        printComment.put("author", author);
                        printComment.put("content", comment);
                        printComment.put("createdAt", date);
                        commentsNode.add(printComment);
                    }
                }
                node.set("comments", commentsNode);
                arrayNode.add(node);
            }
        }
        finalNode.set("tickets", arrayNode);
        return finalNode;
    }

    /**
     * Printeaza tichtele din perspectiva developerilor.
     * @param inventarTich
     * @param milestones
     * @param username
     * @return
     */
    public ObjectNode viewTicketsDeveloper(final ArrayList<Ticket> inventarTich,
                                           final ArrayList<Milestone> milestones,
                                           final String username) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (int p = 0; p < milestones.size(); p++) {
            Milestone milestone =  milestones.get(p);
            String[] assignedDev = milestone.getAssignedDevs();
            for (int k =  0; k < assignedDev.length; k++) {
                if (assignedDev[k].equals(username)) {
                    System.out.println("HAIDE CU DEVELOPERII " + assignedDev[k]);
                    int[] ticketsID = milestone.getTickets();
                    for (int t = 0;  t < ticketsID.length; t++) {
                        ObjectNode node = mapper.createObjectNode();
                        Ticket ticket = returnTicket(inventarTich, ticketsID[t]);
                        if (ticket != null && ticket.getStatus().equals("OPEN")) {
                            System.out.println("SE ADAUGA TICHETUL CU ID " + ticket.getId());
                            node.put("id", ticket.getId());
                            node.put("type", ticket.getType());
                            node.put("title", ticket.getTitle());
                            node.put("businessPriority", ticket.getBusinessPriority());
                            node.put("status", ticket.getStatus());
                            node.put("createdAt", ticket.getCreatedAt());
                            node.put("assignedAt", ticket.getAssignedAt());
                            node.put("solvedAt", ticket.getSolvedAt());
                            node.put("assignedTo", ticket.getAssignedTo());
                            node.put("reportedBy", ticket.getReportedBy());
                            ArrayNode commentsNode = mapper.createArrayNode();
                            if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
                                for (int j = 0; j < ticket.getComments().size(); j++) {
                                    String comment = ticket.getComments().get(j);
                                    String author = ticket.getAuthors().get(j);
                                    String date = ticket.getDate().get(j);
                                    ObjectNode printComment = mapper.createObjectNode();
                                    printComment.put("author", author);
                                    printComment.put("content", comment);
                                    printComment.put("createdAt", date);
                                    commentsNode.add(printComment);
                                }
                            }
                            node.set("comments", commentsNode);
                            arrayNode.add(node);
                        }
                    }
                }
            }
        }
        finalNode.set("tickets", arrayNode);
        return finalNode;
    }

    /**
     * Cod pentru prioritate(pentru strategy method).
     * @param businessPriority
     * @return
     */
    private int codPrioritate(final String businessPriority) {
        if (businessPriority.equals("LOW")) {
            return 1;
        } else if (businessPriority.equals("MEDIUM")) {
            return MagicNumbersInt.doi.getValue();
        } else if (businessPriority.equals("HIGH")) {
            return MagicNumbersInt.trei.getValue();
        }
        return MagicNumbersInt.patru.getValue();
    }

    /**
     * Returnez calificativul(in functie de risc).
     * @param risk
     * @return
     */
    private String calificativ(final double risk) {
        if (risk >= 0 && risk <= MagicNumbersDouble.douazecisipatru.getValue()) {
            return "NEGLIGIBLE";
        } else if (risk >= MagicNumbersDouble.douazecisicinci.getValue()
                && risk <= MagicNumbersDouble.patruzecisinoua.getValue()) {
            return "MODERATE";
        } else if (risk >= MagicNumbersDouble.cincizeci.getValue()
                && risk <= MagicNumbersDouble.saptezecisipatru.getValue()) {
            return "SIGNIFICANT";
        }
        return "MAJOR";
    }

    /**
     * Printeaza tichetele.
     * @param tickets
     * @return
     */
    public ObjectNode printTickets(final ArrayList<Ticket> tickets) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int codO1 = codPrioritate(o1.getBusinessPriority());
                int codO2 = codPrioritate(o2.getBusinessPriority());
                int prioritateC = codO2 - codO1;
                if (prioritateC != 0) {
                    return prioritateC;
                }
                int dataC = o1.getCreatedAt().compareTo(o2.getCreatedAt());
                if (dataC != 0) {
                    return dataC;
                }
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        for (int i = 0; i < tickets.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Ticket ticket = tickets.get(i);
            node.put("id", ticket.getId());
            node.put("type", ticket.getType());
            node.put("title", ticket.getTitle());
            node.put("businessPriority", ticket.getBusinessPriority());
            node.put("status", ticket.getStatus());
            node.put("createdAt", ticket.getCreatedAt());
            node.put("assignedAt", ticket.getAssignedAt());
            node.put("reportedBy", ticket.getReportedBy());
            ArrayNode commentsNode = mapper.createArrayNode();
            if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
                for (int j = 0; j < ticket.getComments().size(); j++) {
                    String comment = ticket.getComments().get(j);
                    String author = ticket.getAuthors().get(j);
                    String date = ticket.getDate().get(j);
                    ObjectNode printComment = mapper.createObjectNode();
                    printComment.put("author", author);
                    printComment.put("content", comment);
                    printComment.put("createdAt", date);
                    commentsNode.add(printComment);
                }
            }
            node.set("comments", commentsNode);
            arrayNode.add(node);
        }
        finalNode.set("assignedTickets", arrayNode);
        return finalNode;
    }

    /**
     * Printeaza istoricul tichetelor.
     * @param tickets
     * @return
     */
    public ObjectNode printHistoryTickets(final ArrayList<Ticket> tickets) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int dataC = o1.getCreatedAt().compareTo(o2.getCreatedAt());
                if (dataC != 0) {
                    return dataC;
                }
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        for (int i = 0; i < tickets.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Ticket ticket = tickets.get(i);
            node.put("id", ticket.getId());
            node.put("title", ticket.getTitle());
            node.put("status", ticket.getStatus());
            ArrayNode historyNode = mapper.createArrayNode();
            if (!ticket.getHistories().isEmpty()) {
                for (int j = 0; j < ticket.getHistories().size(); j++) {
                    ObjectNode printHistory =  mapper.createObjectNode();
                    History history =  ticket.getHistories().get(j);
                    String action =  history.getAction();
                    if (action.equals("STATUS_CHANGED")) {
                        printHistory.put("from",  history.getFrom());
                        printHistory.put("to",  history.getTo());
                        printHistory.put("by", history.getBy());
                        printHistory.put("timestamp", history.getTimestamp());
                        printHistory.put("action", history.getAction());
                    } else if (action.equals("ADDED_TO_MILESTONE")) {
                        printHistory.put("milestone",  history.getMilestone());
                        printHistory.put("by", history.getBy());
                        printHistory.put("timestamp", history.getTimestamp());
                        printHistory.put("action", history.getAction());
                    } else if (action.equals("REMOVED_FROM_DEV")) {
                        printHistory.put("from", history.getFrom());
                        printHistory.put("timestamp", history.getTimestamp());
                        printHistory.put("action", history.getAction());
                        printHistory.put("by", history.getBy());
                    } else {
                        printHistory.put("by", history.getBy());
                        printHistory.put("timestamp", history.getTimestamp());
                        printHistory.put("action", history.getAction());
                    }
                    historyNode.add(printHistory);
                }
            }
            ArrayNode commentsNode = mapper.createArrayNode();
            if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
                for (int j = 0; j < ticket.getComments().size(); j++) {
                    String comment = ticket.getComments().get(j);
                    String author = ticket.getAuthors().get(j);
                    String date = ticket.getDate().get(j);
                    ObjectNode printComment = mapper.createObjectNode();
                    printComment.put("author", author);
                    printComment.put("content", comment);
                    printComment.put("createdAt", date);
                    commentsNode.add(printComment);
                }
            }
            node.set("actions", historyNode);
            node.set("comments", commentsNode);
            arrayNode.add(node);
        }
        finalNode.set("ticketHistory", arrayNode);
        return finalNode;
    }

    /**
     * Printeaza tihctele gasite(din persp developerului).
     * @param tickets
     * @return
     */
    public ObjectNode printFoundTicketsDeveloper(final List<Ticket> tickets) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int dataC = o1.getCreatedAt().compareTo(o2.getCreatedAt());
                if (dataC != 0) {
                    return dataC;
                }
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        for (int i = 0; i < tickets.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Ticket ticket = tickets.get(i);
            node.put("id", ticket.getId());
            node.put("type", ticket.getType());
            node.put("title", ticket.getTitle());
            node.put("businessPriority", ticket.getBusinessPriority());
            node.put("status", ticket.getStatus());
            node.put("createdAt", ticket.getCreatedAt());
            node.put("solvedAt", ticket.getSolvedAt());
            node.put("reportedBy", ticket.getReportedBy());
            arrayNode.add(node);
        }
        finalNode.set("results", arrayNode);
        return finalNode;
    }

    /**
     * Printeaza tichetele gasite(din persp manager-ului).
     * @param tickets
     * @param keywords
     * @return
     */
    public ObjectNode printFoundTicketsManager(final List<Ticket> tickets,
                                               final JsonNode keywords) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int dataC = o1.getCreatedAt().compareTo(o2.getCreatedAt());
                if (dataC != 0) {
                    return dataC;
                }
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        for (int i = 0; i < tickets.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Ticket ticket = tickets.get(i);
            node.put("id", ticket.getId());
            node.put("type", ticket.getType());
            node.put("title", ticket.getTitle());
            node.put("businessPriority", ticket.getBusinessPriority());
            node.put("status", ticket.getStatus());
            node.put("createdAt", ticket.getCreatedAt());
            node.put("solvedAt", ticket.getSolvedAt());
            node.put("reportedBy", ticket.getReportedBy());
            ArrayNode nod = mapper.createArrayNode();
            if (!ticket.getMatch().isEmpty()) {
                ArrayNode printKey = mapper.createArrayNode();
                for (int p = 0; p < ticket.getMatch().size(); p++) {
                    String key = ticket.getMatch().get(p);
                    printKey.add(key);
                }
                node.set("matchingWords", printKey);
            } else {
                node.put("matchingWords", nod);
            }
            arrayNode.add(node);
        }
        finalNode.set("results", arrayNode);
        return finalNode;
    }

    /**
     * Printez developerii gasiti.
     * @param users
     * @return
     */
    public ObjectNode printFoundDevs(final List<Developer> users) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(users, new Comparator<Users>() {
            @Override
            public int compare(final Users o1, final Users o2) {
                Developer d1 = (Developer) o1;
                Developer d2 = (Developer) o2;
                return d1.getUsername().compareTo(d2.getUsername());
            }
        });
        for (int i = 0; i < users.size(); i++) {
            ObjectNode node = mapper.createObjectNode();
            Developer user = (Developer) users.get(i);
            node.put("username", user.getUsername());
            node.put("expertiseArea", user.getExpertiseArea());
            node.put("seniority", user.getSeniority());
            node.put("performanceScore", user.getPerformanceScore());
            node.put("hireDate", user.getDate());
            arrayNode.add(node);
        }
        finalNode.set("results", arrayNode);
        return finalNode;
    }

    /**
     * Seteaza nr de tichete, in functie de tip.
     */
    public void setImpactandOthers() {
        int bug = 0;
        int ui = 0;
        int fr = 0;
        int low = 0;
        int medium = 0;
        int high = 0;
        int critical = 0;
        double sumimpactBUG = 0.0;
        double sumimpactUI = 0.0;
        double sumimpactFeature = 0.0;
        double sumRiskBUG = 0.0;
        double sumRiskUI = 0.0;
        double sumRiskFeature = 0.0;
        for (int m = 0; m < inventarTichete.size(); m++) {
            Ticket t = inventarTichete.get(m);
            if (t.getStatus().equals("OPEN") || t.getStatus().equals("IN_PROGRESS")) {
                if (t.isBUG()) {
                    bug++;
                    System.out.println("tichetul are impactul " + t.getCalculateImpact());
                    sumimpactBUG = sumimpactBUG + t.getCalculateImpact();
                    sumRiskBUG = sumRiskBUG + t.getCalculateRisk();
                } else if (t.isUI()) {
                    ui++;
                    sumimpactUI = sumimpactUI + t.getCalculateImpact();
                    sumRiskUI = sumRiskUI + t.getCalculateRisk();
                } else if (t.isFeature()) {
                    fr++;
                    sumimpactFeature = sumimpactFeature + t.getCalculateImpact();
                    sumRiskFeature = sumRiskFeature + t.getCalculateRisk();
                }
                if (t.getBusinessPriority().equals("LOW")) {
                    low++;
                } else if (t.getBusinessPriority().equals("MEDIUM")) {
                    medium++;
                } else if (t.getBusinessPriority().equals("HIGH")) {
                    high++;
                } else if (t.getBusinessPriority().equals("CRITICAL")) {
                    critical++;
                }
            }
        }
        this.setbugTickets(bug);
        this.setuiTickets(ui);
        this.setfeatureTickets(fr);
        this.setlowPriority(low);
        this.setmediumPriority(medium);
        this.sethighPriority(high);
        this.setcriticalPriority(critical);
        double resBUG = sumimpactBUG / this.getbugTickets();
        double resUI =  sumimpactUI / this.getuiTickets();
        double resFeature = sumimpactFeature / this.getfeatureTickets();
        double resRBUG =  sumRiskBUG / this.getbugTickets();
        double resRiskUI =  sumRiskUI / this.getuiTickets();
        double resRiskFeature =  sumRiskFeature / this.getfeatureTickets();
        this.setImpactForBUG(Math.round(resBUG * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setImpactForUI(Math.round(resUI * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setImpactForFeature(Math.round(resFeature * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setRiskForBUG(Math.round(resRBUG * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setRiskForUI(Math.round(resRiskUI * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setRiskForFeature(Math.round(resRiskFeature * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
    }

    /**
     * Afiseaza nr de tichete, tipul lor si customer impact-ul fiecarui tip de tichet
     * @return
     */
    public ObjectNode generateCustomerImpact() {
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalTickets", this.getbugTickets()
                + this.getuiTickets() + this.getfeatureTickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getbugTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getfeatureTickets());
        ticketsByType.put("UI_FEEDBACK", this.getuiTickets());
        nrTickets.set("ticketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getlowPriority());
        ticketsbyPriority.put("MEDIUM", this.getmediumPriority());
        ticketsbyPriority.put("HIGH", this.gethighPriority());
        ticketsbyPriority.put("CRITICAL", this.getcriticalPriority());
        nrTickets.set("ticketsByPriority", ticketsbyPriority);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", this.getImpactForBUG());
        customerImpact.put("FEATURE_REQUEST", this.getImpactForFeature());
        customerImpact.put("UI_FEEDBACK", this.getImpactForUI());
        nrTickets.set("customerImpactByType", customerImpact);
        return nrTickets;
    }

    /**
     * Afiseaza nr de tichete, tipul lor si riscul total al fiecarui tip de tichet
     * @return
     */
    public ObjectNode generateTicketsRisk() {
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalTickets", this.getbugTickets()
                + this.getuiTickets() + this.getfeatureTickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getbugTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getfeatureTickets());
        ticketsByType.put("UI_FEEDBACK", this.getuiTickets());
        nrTickets.set("ticketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getlowPriority());
        ticketsbyPriority.put("MEDIUM", this.getmediumPriority());
        ticketsbyPriority.put("HIGH", this.gethighPriority());
        ticketsbyPriority.put("CRITICAL", this.getcriticalPriority());
        nrTickets.set("ticketsByPriority", ticketsbyPriority);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", calificativ(this.getRiskForBUG()));
        customerImpact.put("FEATURE_REQUEST", calificativ(this.getRiskForFeature()));
        customerImpact.put("UI_FEEDBACK", calificativ(this.getRiskForUI()));
        nrTickets.set("riskByType", customerImpact);
        return nrTickets;
    }
    /**
     * Calculeaza eficienta tichetului.
     */
    public void calculateEfficiency() {
        int bugt = 0;
        int uit = 0;
        int feature = 0;
        double sumBUG = 0.0;
        double sumFEATURE = 0.0;
        double sumUI = 0.0;
        int low = 0;
        int medium = 0;
        int high = 0;
        int critical = 0;
        for (int m = 0; m < inventarTichete.size(); m++) {
            Ticket t = inventarTichete.get(m);
            if (t.getStatus().equals("CLOSED") || t.getStatus().equals("RESOLVED")) {
                LocalDate date1 = LocalDate.parse(t.getAssignedAt());
                LocalDate date2 = LocalDate.parse(t.getSolvedAt());
                int daysBetween = (int) ChronoUnit.DAYS.between(date1, date2) + 1;
                t.setDaysToResolveEfficiency(daysBetween);
                if (t.isBUG()) {
                    BUG b = (BUG) t;
                    bugt++;
                    BUG bug = (BUG) t;
                    double value = (bug.getFreqCode() + bug.getSeverityCode())
                            * MagicNumbersDouble.zece.getValue()
                            / bug.getDaysToResolveEfficiency();
                    double res = (value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.saptezeci.getValue();
                    sumBUG = sumBUG + res;
                    t.setCalculateEfficiency(res);
                } else if (t.isUI()) {
                    uit++;
                    UIFeedback ui = (UIFeedback) t;
                    double value = (double) (ui.getUsabilityScore() + ui.getbusinessvalueCode())
                            / ui.getDaysToResolveEfficiency();
                    double res = (value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.douazeci.getValue();
                    sumUI = sumUI + res;
                    t.setCalculateEfficiency(res);
                } else if (t.isFeature()) {
                    feature++;
                    FeatureRequest fr =  (FeatureRequest) t;
                    double value = (double) (fr.getBusinessvalueCode() + fr.getCustomerdemandCode())
                            / fr.getDaysToResolveEfficiency();
                    double res = (value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.douazeci.getValue();
                    sumFEATURE = sumFEATURE + res;
                    t.setCalculateEfficiency(res);
                }
                if (t.getBusinessPriority().equals("LOW")) {
                    low++;
                } else if (t.getBusinessPriority().equals("MEDIUM")) {
                    medium++;
                } else if (t.getBusinessPriority().equals("HIGH")) {
                    high++;
                } else if (t.getBusinessPriority().equals("CRITICAL")) {
                    critical++;
                }
            }
        }
        this.setlowPriority(low);
        this.setmediumPriority(medium);
        this.sethighPriority(high);
        this.setcriticalPriority(critical);
        double resBUG = sumBUG /  bugt;
        double resFEATURE = sumFEATURE / feature;
        double resUI = sumUI /  uit;
        this.setbugTickets(bugt);
        this.setuiTickets(uit);
        this.setfeatureTickets(feature);
        this.setEfficiencyForBUG(Math.round(resBUG * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setEfficiencyForUI(Math.round(resUI * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
        this.setEfficiencyForFeature(Math.round(resFEATURE * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue());
    }

    /**
     * Afiseaza nr de tichete, tipul lor si eficienta fiecarui tip de tichet
     * @return
     */
    public ObjectNode generateEfficiency() {
        calculateEfficiency();
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalTickets", this.getbugTickets()
                + this.getuiTickets() + this.getfeatureTickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getbugTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getfeatureTickets());
        ticketsByType.put("UI_FEEDBACK", this.getuiTickets());
        nrTickets.set("ticketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getlowPriority());
        ticketsbyPriority.put("MEDIUM", this.getmediumPriority());
        ticketsbyPriority.put("HIGH", this.gethighPriority());
        ticketsbyPriority.put("CRITICAL", this.getcriticalPriority());
        nrTickets.set("ticketsByPriority", ticketsbyPriority);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", this.getEfficiencyForBUG());
        customerImpact.put("FEATURE_REQUEST", this.getEfficiencyForFeature());
        customerImpact.put("UI_FEEDBACK", this.getEfficiencyForUI());
        nrTickets.set("efficiencyByType", customerImpact);
        return nrTickets;
    }

    /**
     * Printeaza raportul pentru impact si risc al tichetelor.
     * @return
     */
    public ObjectNode generateImpactandTicketsRisk() {
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalOpenTickets", this.getbugTickets()
                + this.getuiTickets() + this.getfeatureTickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getbugTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getfeatureTickets());
        ticketsByType.put("UI_FEEDBACK", this.getuiTickets());
        nrTickets.set("openTicketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getlowPriority());
        ticketsbyPriority.put("MEDIUM", this.getmediumPriority());
        ticketsbyPriority.put("HIGH", this.gethighPriority());
        ticketsbyPriority.put("CRITICAL", this.getcriticalPriority());
        nrTickets.set("openTicketsByPriority", ticketsbyPriority);
        ObjectNode riskImpact = mapper.createObjectNode();
        riskImpact.put("BUG", calificativ(this.getRiskForBUG()));
        riskImpact.put("FEATURE_REQUEST", calificativ(this.getRiskForFeature()));
        riskImpact.put("UI_FEEDBACK", calificativ(this.getRiskForUI()));
        nrTickets.set("riskByType", riskImpact);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", this.getImpactForBUG());
        customerImpact.put("FEATURE_REQUEST", this.getImpactForFeature());
        customerImpact.put("UI_FEEDBACK", this.getImpactForUI());
        nrTickets.set("impactByType", customerImpact);
        String stability = null;
        if (this.getbugTickets()
                + this.getuiTickets() + this.getfeatureTickets() < 0) {
            stability = "STABLE";
        } else if (calificativ(this.getRiskForBUG()).equals("NEGLIGIBLE")
                && calificativ(this.getRiskForFeature()).equals("NEGLIGIBLE")
                && calificativ(this.getRiskForUI()).equals("NEGLIGIBLE")
                && this.getImpactForBUG()
                    <= MagicNumbersDouble.cincizeci.getValue()
                && this.getImpactForFeature()
                    <= MagicNumbersDouble.cincizeci.getValue()
                && this.getImpactForUI()
                    <= MagicNumbersDouble.cincizeci.getValue()) {
            stability = "STABLE";
        } else if (calificativ(this.getRiskForBUG()).equals("SIGNIFICANT")
                || calificativ(this.getRiskForFeature()).equals("SIGNIFICANT")
                || calificativ(this.getRiskForUI()).equals("SIGNIFICANT")) {
            stability = "UNSTABLE";
        } else {
            stability = "PARTIALLY STABLE";
        }
        nrTickets.put("appStability", stability);
        return nrTickets;
    }

    /**
     * Metoda helper pt calculul performantei.
     * @param bug
     * @param feature
     * @param ui
     * @return
     */
    public static double averageResolvedTicketType(final int bug,
                                                   final int feature, final int ui) {
        double res = (bug + feature + ui) / MagicNumbersDouble.trrei.getValue();
        return Math.round(res * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue();
    }

    /**
     * Metoda helper(pt calculul performantei).
     * @param bug
     * @param feature
     * @param ui
     * @return
     */
    public static double standardDeviation(final int bug,
                                           final int feature, final int ui) {
        double mean = averageResolvedTicketType(bug, feature, ui);
        double variance = (Math.pow(bug - mean, MagicNumbersInt.doi.getValue())
                + Math.pow(feature - mean, MagicNumbersInt.doi.getValue())
                + Math.pow(ui - mean, MagicNumbersInt.doi.getValue()))
                / MagicNumbersDouble.trrei.getValue();
        double res =  Math.sqrt(variance);
        return Math.round(res * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue();
    }

    /**
     * Metoda helper(pt calculul performantei).
     * @param bug
     * @param feature
     * @param ui
     * @return
     */
    public static double ticketDiversityFactor(final int bug,
                                               final int feature, final int ui) {
        double mean = averageResolvedTicketType(bug, feature, ui);

        if (mean == 0.0) {
            return 0.0;
        }

        double std = standardDeviation(bug, feature, ui);
        double res = std / mean;
        return Math.round(res * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue();
    }

    /**
     * Metoda helper pt a returna developer-ul.
     * @param useri
     * @param username
     * @return
     */
    public static Users returnUser(final List<Users> useri, final String username) {
        for (int i = 0; i < useri.size(); i++) {
            Users user = useri.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Calculeaza nr de tichete de tip closed.
     * @param tichete
     * @return
     */
    public int calculateclosedTickets(final ArrayList<Ticket> tichete,
                                      final String timestamp) {
        int count = 0;
        for (int i = 0; i < tichete.size(); i++) {
            Ticket t = tichete.get(i);
            if (t.getStatus().equals("CLOSED")) {
                LocalDate timpInchis = LocalDate.parse(t.getSolvedAt());
                LocalDate now = LocalDate.parse(timestamp);
                int daysBetween = (int) ChronoUnit.DAYS.between(timpInchis, now);
                System.out.println("TIMP INTRE ESTE DE " + daysBetween);
                if (daysBetween <= 32)
                    count++;
            }
        }
        return count;
    }
    /**
     * Calculeaza nr de tichete de tip BUG care sunt closed.
     * @param tichete
     * @return
     */
    public int calculateBUG(final ArrayList<Ticket> tichete) {
        int count = 0;
        for (int i = 0; i < tichete.size(); i++) {
            Ticket t = tichete.get(i);
            if (t.isBUG() && t.getStatus().equals("CLOSED")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculeaza nr de tichete de tip UIFeedback care sunt closed.
     * @param tichete
     * @return
     */
    public int calculateUI(final ArrayList<Ticket> tichete) {
        int count = 0;
        for (int i = 0; i < tichete.size(); i++) {
            Ticket t = tichete.get(i);
            if (t.isUI() && t.getStatus().equals("CLOSED")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculeaza nr de tichete de tip featureRequest care sunt closed.
     * @param tichete
     * @return
     */
    public int calculateFeature(final ArrayList<Ticket> tichete) {
        int count = 0;
        for (int i = 0; i < tichete.size(); i++) {
            Ticket t = tichete.get(i);
            if (t.isFeature() && t.getStatus().equals("CLOSED")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculeaza nr de tichete cu prioritatea HIGH/CRITICAL.
     * @param tichete
     * @return
     */
    public int calculateHighPriority(final ArrayList<Ticket> tichete,
                                     final String timestamp) {
        int count = 0;
        for (int i = 0; i < tichete.size(); i++) {
            Ticket t = tichete.get(i);
            if (t.getStatus().equals("CLOSED")
                    && (t.getBusinessPriority().equals("HIGH")
                    || t.getBusinessPriority().equals("CRITICAL"))) {
                LocalDate timpInchis = LocalDate.parse(t.getSolvedAt());
                LocalDate now = LocalDate.parse(timestamp);
                int daysBetween = (int) ChronoUnit.DAYS.between(timpInchis, now);
                System.out.println("TIMP INTRE ESTE DE " + daysBetween);
                if (daysBetween <= 32)
                    count++;
            }
        }
        return count;
    }

    /**
     * Seteaza timpul de rezolvare a unui tichet.
     * @param tickets
     */
    public void setAverageDate(final ArrayList<Ticket> tickets) {
        for (int i = 0; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            LocalDate createdAt = LocalDate.parse(t.getAssignedAt());
            LocalDate solvedAt = LocalDate.parse((t.getSolvedAt()));
            int days = (int) ChronoUnit.DAYS.between(createdAt, solvedAt) + 1;
            t.setAverageResolutionTime(days);
        }
    }

    /**
     * Calculeaza media de rezolvare a tichetelor.
     * @param ticket
     * @return
     */
    public double calculateAvgTime(final ArrayList<Ticket> ticket,
                                   final String timestamp) {
        double sum = 0;
        for (int i = 0; i < ticket.size(); i++) {
            if (ticket.get(i).getStatus().equals("CLOSED")) {
                LocalDate timpInchis = LocalDate.parse(ticket.get(i).getSolvedAt());
                LocalDate now = LocalDate.parse(timestamp);
                int daysBetween = (int) ChronoUnit.DAYS.between(timpInchis, now);
                System.out.println("TIMP INTRE ESTE DE " + daysBetween);
                if (daysBetween <= 32) {
                    System.out.println("IA ZI FRATE PENTRU TICHETUL " + ticket.get(i).getId() + " AVEM ASSIGNED AT " + ticket.get(i).getAssignedAt() + " SI REZOLVAT LA " + ticket.get(i).getUltimulTimestampCR() + " si zile intre " + ticket.get(i).getAverageResolutionTime());
                    sum = sum + ticket.get(i).getAverageResolutionTime();
                }
            }
        }
        int nr = calculateclosedTickets(ticket, timestamp);
        if (nr == 0) {
            return 0.0;
        }
        double res = (double) sum / nr;
        System.out.println("SUMA " + sum);
        System.out.println("NUMAR" + nr);
        System.out.println("HAIDE CU REZULTATUL " + res);
        return Math.round(res * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue();
    }

    /**
     * Calculeaza performanta pt fiecare tip de developer.
     * @param useri
     * @param manager
     * @return
     */
    public ArrayList<PerformanceReport> calculatePerformance(
            final List<Users> useri, final Manager manager, final String timestamp) {
        setImpactandOthers();
        ArrayList<PerformanceReport> rep = new ArrayList<>();
        for (int i = 0; i < manager.getSubordinates().size(); i++) {
            String usernameCurent = manager.getSubordinates().get(i);
            Developer dev = (Developer) returnUser(useri, usernameCurent);
            setAverageDate(dev.getTickets());
            if (dev.getSeniority().equals("JUNIOR")) {
                    // System.out.println(calculateclosedTickets(dev.getTickets()));
                    double averageResolvedTickets = averageResolvedTicketType(
                            calculateBUG(dev.getTickets()),
                            calculateFeature(dev.getTickets()),
                            calculateUI(dev.getTickets()));
                    double standardDeviation = standardDeviation(
                            calculateBUG(dev.getTickets()),
                            calculateFeature(dev.getTickets()),
                            calculateUI(dev.getTickets()));
                    double ticketDiveristyFactor = ticketDiversityFactor(
                            calculateBUG(dev.getTickets()),
                            calculateFeature(dev.getTickets()),
                            calculateUI(dev.getTickets()));
                    double performance = 0.0;
                    double avgRes = calculateAvgTime(dev.getTickets(), timestamp);
                    if (averageResolvedTickets != 0
                            || standardDeviation != 0 || ticketDiveristyFactor != 0) {
                        double value  = max(0.0, MagicNumbersDouble.zerocinci.getValue()
                                * calculateclosedTickets(dev.getTickets(), timestamp)
                                - ticketDiveristyFactor) + MagicNumbersInt.cinci.getValue();
                        performance = Math.round(value * MagicNumbersDouble.osuta.getValue())
                                / MagicNumbersDouble.osuta.getValue();
                    }
                    dev.setPerformanceScore(performance);
                    PerformanceReport report = new PerformanceReport(usernameCurent,
                            calculateclosedTickets(dev.getTickets(), timestamp),
                            avgRes, performance, dev.getSeniority());
                    rep.add(report);
            } else if (dev.getSeniority().equals("MID")) {
                double closedTickets = calculateclosedTickets(dev.getTickets(), timestamp);
                double highPriorityTicket = calculateHighPriority(dev.getTickets(), timestamp);
                double avgRes = calculateAvgTime(dev.getTickets(), timestamp);
                double performance = 0.0;
                if (closedTickets != 0 || highPriorityTicket != 0 || avgRes != 0) {
                    double value  = max(0, MagicNumbersDouble.zerocinci.getValue()
                            * closedTickets + MagicNumbersDouble.zerosapte.getValue()
                            * highPriorityTicket - MagicNumbersDouble.zerotrei.getValue()
                            * avgRes) + MagicNumbersInt.cincisprezece.getValue();
                    performance = Math.round(value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.osuta.getValue();
                }
                dev.setPerformanceScore(performance);
                PerformanceReport report = new PerformanceReport(usernameCurent,
                        calculateclosedTickets(dev.getTickets(), timestamp), avgRes,
                        performance, dev.getSeniority());
                rep.add(report);
            } else if (dev.getSeniority().equals("SENIOR")) {
                double closedTickets = calculateclosedTickets(dev.getTickets(), timestamp);
                double highPriorityTicket = calculateHighPriority(dev.getTickets(), timestamp);
                double avgRes = calculateAvgTime(dev.getTickets(), timestamp);
                double performance = 0.0;
                if (closedTickets != 0 || highPriorityTicket != 0 || avgRes != 0) {
                    double value = max(0, MagicNumbersDouble.zerocinci.getValue()
                            * closedTickets + 1.0 * highPriorityTicket
                            - MagicNumbersDouble.zerocinci.getValue() * avgRes)
                            + MagicNumbersInt.treizeci.getValue();
                    performance = Math.round(value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.osuta.getValue();
                }
                PerformanceReport report = new PerformanceReport(usernameCurent,
                        calculateclosedTickets(dev.getTickets(), timestamp), avgRes,
                        performance, dev.getSeniority());
                rep.add(report);
                dev.setPerformanceScore(performance);
            }
        }
        Collections.sort(rep, new Comparator<PerformanceReport>() {
            @Override
            public int compare(final PerformanceReport o1, final PerformanceReport o2) {
                return o1.getUsername().compareTo(o2.getUsername());
            }
        });
        return rep;
    }

    /**
     * Printeaza performanta.
     * @param rep
     * @return
     */
    public ObjectNode generatePerformanceReport(final ArrayList<PerformanceReport> rep) {
        ObjectNode node = mapper.createObjectNode();
        ArrayNode printPerformance = mapper.createArrayNode();
        for (int i = 0; i < rep.size(); i++) {
            PerformanceReport report = rep.get(i);
            ObjectNode printRep = mapper.createObjectNode();
            printRep.put("username", report.getUsername());
            printRep.put("closedTickets", report.getClosedTickets());
            printRep.put("averageResolutionTime", report.getAverageResolutionTime());
            printRep.put("performanceScore", report.getPerformanceScore());
            printRep.put("seniority", report.getSeniority());
            printPerformance.add(printRep);
        }
        node.set("report", printPerformance);
        return node;
    }
}
