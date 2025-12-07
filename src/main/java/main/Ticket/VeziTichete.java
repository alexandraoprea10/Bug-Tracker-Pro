package main.Ticket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.MagicNumbersInt;
import main.Milestone;
import main.User.Developer;
import main.User.Users;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static main.App.returnTicket;

public class VeziTichete {
    private List<Ticket> inventarTichete;
    private int BUGTickets;
    private int UITickets;
    private int FEATURETickets;
    private int LOWPriority;
    private int MEDIUMPriority;
    private int HIGHPriority;
    private int CRITICALPriority;
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
    public int getBUGTickets() {
        return BUGTickets;
    }
    public int getUITickets() {
        return UITickets;
    }
    public int getFEATURETickets() {
        return FEATURETickets;
    }
    public int getLOWPriority() {
        return LOWPriority;
    }
    public int getMEDIUMPriority() {
        return MEDIUMPriority;
    }
    public int getHIGHPriority() {
        return HIGHPriority;
    }
    public int getCRITICALPriority() {
        return CRITICALPriority;
    }
    public double getImpactForBUG() {
        return impactForBUG;
    }
    public double getImpactForUI() {
        return impactForUI;
    }
    public double getImpactForFeature() {
        return impactForFeature;
    }
    public double getRiskForBUG() {
        return riskForBug;
    }
    public double getRiskForUI() {
        return riskForUI;
    }
    public double getRiskForFeature() {
        return riskForFeature;
    }
    public  double getEfficiencyForBUG() {
        return efficiencyForBUG;
    }
    public  double getEfficiencyForUI() {
        return efficiencyForUI;
    }
    public  double getEfficiencyForFeature() {
        return efficiencyForFeature;
    }
    public void setBUGTickets(int BUGTickets) {
        this.BUGTickets = BUGTickets;
    }
    public void setUITickets(int UITickets) {
        this.UITickets = UITickets;
    }
    public void setFEATURETickets(int FEATURETickets) {
        this.FEATURETickets = FEATURETickets;
    }
    public void setLOWPriority(int LOWPriority) {
        this.LOWPriority = LOWPriority;
    }
    public void setMEDIUMPriority(int MEDIUMPriority) {
        this.MEDIUMPriority = MEDIUMPriority;
    }
    public void setHIGHPriority(int HIGHPriority) {
        this.HIGHPriority = HIGHPriority;
    }
    public void setCRITICALPriority(int CRITICALPriority) {
        this.CRITICALPriority = CRITICALPriority;
    }
    public void setImpactForBUG(double impactForBUG) {
        this.impactForBUG = impactForBUG;
    }
    public void setImpactForUI(double impactForUI) {
        this.impactForUI = impactForUI;
    }
    public void setImpactForFeature(double impactForFeature) {
        this.impactForFeature = impactForFeature;
    }
    public void setRiskForBUG(double riskForBUG) {
        this.riskForBug = riskForBUG;
    }
    public void setRiskForUI(double riskForUI) {
        this.riskForUI = riskForUI;
    }
    public void setRiskForFeature(double riskForFeature) {
        this.riskForFeature = riskForFeature;
    }
    public void setEfficiencyForBUG(double efficiencyForBUG) {
        this.efficiencyForBUG = efficiencyForBUG;
    }
    public void setEfficiencyForUI(double efficiencyForUI) {
        this.efficiencyForUI = efficiencyForUI;
    }
    public void setEfficiencyForFeature(double efficiencyForFeature) {
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
    private String calificativ(double risk) {
        if (risk >= 0 && risk <= 24.0) {
            return "NEGLIGIBLE";
        } else if (risk >= 25.0 && risk <= 49.0) {
            return "MODERATE";
        } else if (risk >= 50.0 && risk <= 74.0) {
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
    public void setImpactandOthers() {
        double sumimpactBUG = 0.0;
        double sumimpactUI = 0.0;
        double sumimpactFeature = 0.0;
        double sumRiskBUG = 0.0;
        double sumRiskUI = 0.0;
        double sumRiskFeature = 0.0;
        for (int m = 0 ; m < inventarTichete.size(); m++) {
            Ticket t = inventarTichete.get(m);
            if (t.getStatus().equals("OPEN") || t.getStatus().equals("IN_PROGRESS")) {
                if (t.isBUG()) {
                    this.setBUGTickets(this.getBUGTickets() + 1);
                    sumimpactBUG = sumimpactBUG + t.getCalculateImpact();
                    sumRiskBUG = sumRiskBUG + t.getCalculateRisk();
                } else if (t.isUI()) {
                    this.setUITickets(this.getUITickets() + 1);
                    sumimpactUI = sumimpactUI + t.getCalculateImpact();
                    sumRiskUI = sumRiskUI + t.getCalculateRisk();
                } else if (t.isFeature()) {
                    this.setFEATURETickets(this.getFEATURETickets() + 1);
                    sumimpactFeature = sumimpactFeature + t.getCalculateImpact();
                    sumRiskFeature = sumRiskFeature + t.getCalculateRisk();
                }
                if (t.getBusinessPriority().equals("LOW")) {
                    this.setLOWPriority(this.getLOWPriority() + 1);
                } else if (t.getBusinessPriority().equals("MEDIUM")) {
                    this.setMEDIUMPriority(this.getMEDIUMPriority() + 1);
                } else if (t.getBusinessPriority().equals("HIGH")) {
                    this.setHIGHPriority(this.getHIGHPriority() + 1);
                } else if (t.getBusinessPriority().equals("CRITICAL")) {
                    this.setCRITICALPriority(this.getCRITICALPriority() + 1);
                }
            }
        }
        double resBUG = sumimpactBUG / this.getBUGTickets();
        double resUI =  sumimpactUI / this.getUITickets();
        double resFeature = sumimpactFeature / this.getFEATURETickets();
        double resRBUG =  sumRiskBUG / this.getBUGTickets();
        double resRiskUI =  sumRiskUI / this.getUITickets();
        double resRiskFeature =  sumRiskFeature / this.getFEATURETickets();
        this.setImpactForBUG(Math.round(resBUG * 100.0) / 100.0);
        this.setImpactForUI(Math.round(resUI * 100.0) / 100.0);
        this.setImpactForFeature(Math.round(resFeature * 100.0) / 100.0);
        this.setRiskForBUG(Math.round(resRBUG * 100.0) / 100.0);
        this.setRiskForUI(Math.round(resRiskUI * 100.0) / 100.0);
        this.setRiskForFeature(Math.round(resRiskFeature * 100.0) / 100.0);
    }
    public ObjectNode generateCustomerImpact() {
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalTickets", this.getBUGTickets() + this.getUITickets() + this.getFEATURETickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getBUGTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getFEATURETickets());
        ticketsByType.put("UI_FEEDBACK", this.getUITickets());
        nrTickets.set("ticketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getLOWPriority());
        ticketsbyPriority.put("MEDIUM", this.getMEDIUMPriority());
        ticketsbyPriority.put("HIGH", this.getHIGHPriority());
        ticketsbyPriority.put("CRITICAL", this.getCRITICALPriority());
        nrTickets.set("ticketsByPriority", ticketsbyPriority);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", this.getImpactForBUG());
        customerImpact.put("FEATURE_REQUEST", this.getImpactForFeature());
        customerImpact.put("UI_FEEDBACK", this.getImpactForUI());
        nrTickets.set("customerImpactByType", customerImpact);
        return nrTickets;
    }
    public ObjectNode generateTicketsRisk() {
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalTickets", this.getBUGTickets() + this.getUITickets() + this.getFEATURETickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getBUGTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getFEATURETickets());
        ticketsByType.put("UI_FEEDBACK", this.getUITickets());
        nrTickets.set("ticketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getLOWPriority());
        ticketsbyPriority.put("MEDIUM", this.getMEDIUMPriority());
        ticketsbyPriority.put("HIGH", this.getHIGHPriority());
        ticketsbyPriority.put("CRITICAL", this.getCRITICALPriority());
        nrTickets.set("ticketsByPriority", ticketsbyPriority);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", calificativ(this.getRiskForBUG()));
        customerImpact.put("FEATURE_REQUEST", calificativ(this.getRiskForFeature()));
        customerImpact.put("UI_FEEDBACK", calificativ(this.getRiskForUI()));
        nrTickets.set("riskByType", customerImpact);
        return nrTickets;
    }
    public void calculateEfficiency() {
        int bugt = 0;
        int uit = 0;
        int feature = 0;
        double sumBUG = 0.0;
        double sumFEATURE = 0.0;
        double sumUI = 0.0;
        for (int m = 0 ; m < inventarTichete.size(); m++) {
            Ticket t = inventarTichete.get(m);
            if (t.getStatus().equals("CLOSED") || t.getStatus().equals("RESOLVED")) {
                LocalDate date1 = LocalDate.parse(t.getAssignedAt());
                LocalDate date2 = LocalDate.parse(t.getSolvedAt());
                int daysBetween = (int) ChronoUnit.DAYS.between(date1, date2) + 1;
                t.setDaysToResolve(daysBetween);
                if (t.isBUG()) {
                    bugt++;
                    BUG bug = (BUG) t;
                    double value = (bug.getBusinessPriorityCode() + bug.getSeverityCode()) * 10.0 / bug.getDaysToResolve();
                    double res = (value * 100.0) / 70.0;
                    sumBUG = sumBUG + res;
                    t.setCalculateEfficiency(res);
                } else if (t.isUI()) {
                    uit++;
                    UIFeedback ui = (UIFeedback) t;
                    double value = (ui.getUsabilityScore() + ui.getbusinessvalueCode()) / ui.getDaysToResolve();
                    double res = (value * 100.0) / 20.0;
                    sumUI = sumUI + res;
                    t.setCalculateEfficiency(res);
                } else if (t.isFeature()) {
                    feature++;
                    FeatureRequest fr =  (FeatureRequest) t;
                    double value = (fr.getBusinessvalueCode() + fr.getCustomerdemandCode()) / fr.getDaysToResolve();
                    double res = (value * 100.0) / 20.0;
                    sumFEATURE = sumFEATURE + res;
                    t.setCalculateEfficiency(res);
                }
                if (t.getBusinessPriority().equals("LOW")) {
                    this.setLOWPriority(this.getLOWPriority() + 1);
                } else if (t.getBusinessPriority().equals("MEDIUM")) {
                    this.setMEDIUMPriority(this.getMEDIUMPriority() + 1);
                } else if (t.getBusinessPriority().equals("HIGH")) {
                    this.setHIGHPriority(this.getHIGHPriority() + 1);
                } else if (t.getBusinessPriority().equals("CRITICAL")) {
                    this.setCRITICALPriority(this.getCRITICALPriority() + 1);
                }
            }
        }
        double resBUG = sumBUG /  bugt;
        double resFEATURE = sumFEATURE / feature;
        double resUI = sumUI /  uit;
        this.setBUGTickets(bugt);
        this.setUITickets(uit);
        this.setFEATURETickets(feature);
        this.setEfficiencyForBUG(Math.round(resBUG * 100.0) / 100.0);
        this.setEfficiencyForUI(Math.round(resUI * 100.0) / 100.0);
        this.setEfficiencyForFeature(Math.round(resFEATURE * 100.0) / 100.0);
    }
    public ObjectNode generateEfficiency() {
        calculateEfficiency();
        ObjectNode finalNode = mapper.createObjectNode();
        ObjectNode nrTickets =  mapper.createObjectNode();
        nrTickets.put("totalTickets", this.getBUGTickets() + this.getUITickets() + this.getFEATURETickets());
        ObjectNode ticketsByType = mapper.createObjectNode();
        ticketsByType.put("BUG", this.getBUGTickets());
        ticketsByType.put("FEATURE_REQUEST", this.getFEATURETickets());
        ticketsByType.put("UI_FEEDBACK", this.getUITickets());
        nrTickets.set("ticketsByType", ticketsByType);
        ObjectNode ticketsbyPriority =  mapper.createObjectNode();
        ticketsbyPriority.put("LOW", this.getLOWPriority());
        ticketsbyPriority.put("MEDIUM", this.getMEDIUMPriority());
        ticketsbyPriority.put("HIGH", this.getHIGHPriority());
        ticketsbyPriority.put("CRITICAL", this.getCRITICALPriority());
        nrTickets.set("ticketsByPriority", ticketsbyPriority);
        ObjectNode customerImpact = mapper.createObjectNode();
        customerImpact.put("BUG", this.getEfficiencyForBUG());
        customerImpact.put("FEATURE_REQUEST", this.getEfficiencyForFeature());
        customerImpact.put("UI_FEEDBACK", this.getEfficiencyForUI());
        nrTickets.set("efficiencyByType", customerImpact);
        return nrTickets;
    }
}
