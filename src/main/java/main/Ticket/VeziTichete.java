package main.Ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.experimental.NonFinal;
import main.Milestone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static main.App.returnTicket;

public class VeziTichete {
    private List<Ticket> inventarTichete;
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
            public int compare(Ticket o1, Ticket o2) {
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
        Collections.sort(this.inventarTichete, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
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
    public ObjectNode viewTicketsDeveloper(final ArrayList<Ticket> inventarTichete,
                                           final ArrayList<Milestone> milestones, final String username) {
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
                        Ticket ticket = returnTicket(inventarTichete, ticketsID[t]);
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
    private int codPrioritate(String businessPriority) {
        if (businessPriority.equals("LOW")) {
            return 1;
        }
        else if (businessPriority.equals("MEDIUM")) {
            return 2;
        }
        else if (businessPriority.equals("HIGH")) {
            return 3;
        }
        return 4;
    }
    public ObjectNode printTickets(ArrayList<Ticket> tickets) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
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
    public ObjectNode printHistoryTickets(ArrayList<Ticket> tickets) {
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
}
