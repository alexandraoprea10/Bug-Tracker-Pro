package main.Ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.MagicNumbersDouble;
import main.MagicNumbersInt;
import main.Milestone;
import main.User.Developer;
import main.User.Users;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
                    int[] ticketsID = milestone.getTickets();
                    for (int t = 0;  t < ticketsID.length; t++) {
                        ObjectNode node = mapper.createObjectNode();
                        Ticket ticket = returnTicket(inventarTich, ticketsID[t]);
                        if (ticket.getStatus().equals("OPEN")) {
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
                    } else if (action.equals("ADDED_TO_MILESTONE")) {
                        printHistory.put("milestone",  history.getMilestone());
                    }
                    printHistory.put("by", history.getBy());
                    printHistory.put("timestamp", history.getTimestamp());
                    printHistory.put("action", history.getAction());
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
            node.put("matchingWords", keywords);
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
    public ObjectNode printFoundDevs(final List<Users> users) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(users, new Comparator<Users>() {
            @Override
            public int compare(final Users o1, final Users o2) {
                Developer d1 = (Developer) o1;
                Developer d2 = (Developer) o2;
                int dataC = d1.getDate().compareTo(d2.getDate());
                if (dataC != 0) {
                    return dataC;
                }
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
                    this.setbugTickets(this.getbugTickets() + 1);
                    sumimpactBUG = sumimpactBUG + t.getCalculateImpact();
                    sumRiskBUG = sumRiskBUG + t.getCalculateRisk();
                } else if (t.isUI()) {
                    this.setuiTickets(this.getuiTickets() + 1);
                    sumimpactUI = sumimpactUI + t.getCalculateImpact();
                    sumRiskUI = sumRiskUI + t.getCalculateRisk();
                } else if (t.isFeature()) {
                    this.setfeatureTickets(this.getfeatureTickets() + 1);
                    sumimpactFeature = sumimpactFeature + t.getCalculateImpact();
                    sumRiskFeature = sumRiskFeature + t.getCalculateRisk();
                }
                if (t.getBusinessPriority().equals("LOW")) {
                    this.setlowPriority(this.getlowPriority() + 1);
                } else if (t.getBusinessPriority().equals("MEDIUM")) {
                    this.setmediumPriority(this.getmediumPriority() + 1);
                } else if (t.getBusinessPriority().equals("HIGH")) {
                    this.sethighPriority(this.gethighPriority() + 1);
                } else if (t.getBusinessPriority().equals("CRITICAL")) {
                    this.setcriticalPriority(this.getcriticalPriority() + 1);
                }
            }
        }
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
        for (int m = 0; m < inventarTichete.size(); m++) {
            Ticket t = inventarTichete.get(m);
            if (t.getStatus().equals("CLOSED") || t.getStatus().equals("RESOLVED")) {
                LocalDate date1 = LocalDate.parse(t.getAssignedAt());
                LocalDate date2 = LocalDate.parse(t.getSolvedAt());
                int daysBetween = (int) ChronoUnit.DAYS.between(date1, date2) + 1;
                t.setDaysToResolve(daysBetween);
                if (t.isBUG()) {
                    bugt++;
                    BUG bug = (BUG) t;
                    double value = (bug.getBusinessPriorityCode() + bug.getSeverityCode())
                            * MagicNumbersDouble.zece.getValue()
                            / bug.getDaysToResolve();
                    double res = (value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.saptezeci.getValue();
                    sumBUG = sumBUG + res;
                    t.setCalculateEfficiency(res);
                } else if (t.isUI()) {
                    uit++;
                    UIFeedback ui = (UIFeedback) t;
                    double value = (ui.getUsabilityScore() + ui.getbusinessvalueCode())
                            / ui.getDaysToResolve();
                    double res = (value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.douazeci.getValue();
                    sumUI = sumUI + res;
                    t.setCalculateEfficiency(res);
                } else if (t.isFeature()) {
                    feature++;
                    FeatureRequest fr =  (FeatureRequest) t;
                    double value = (fr.getBusinessvalueCode() + fr.getCustomerdemandCode())
                            / fr.getDaysToResolve();
                    double res = (value * MagicNumbersDouble.osuta.getValue())
                            / MagicNumbersDouble.douazeci.getValue();
                    sumFEATURE = sumFEATURE + res;
                    t.setCalculateEfficiency(res);
                }
                if (t.getBusinessPriority().equals("LOW")) {
                    this.setlowPriority(this.getlowPriority() + 1);
                } else if (t.getBusinessPriority().equals("MEDIUM")) {
                    this.setmediumPriority(this.getmediumPriority() + 1);
                } else if (t.getBusinessPriority().equals("HIGH")) {
                    this.sethighPriority(this.gethighPriority() + 1);
                } else if (t.getBusinessPriority().equals("CRITICAL")) {
                    this.setcriticalPriority(this.getcriticalPriority() + 1);
                }
            }
        }
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
}
