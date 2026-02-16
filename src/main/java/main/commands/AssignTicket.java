package main.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.helpers.HelperMethods;
import main.milestones.InfoMilestones;
import main.milestones.Milestone;
import main.searching.DevelopersSearch;
import main.searching.TicketSearch;
import main.ticket.Ticket;
import main.ticket.VeziTichete;
import main.user.Users;
import main.user.developerTypes.Developer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import static main.helpers.CheckingHelpers.checkForDevInMilestone;
import static main.helpers.CheckingHelpers.checkforTicket;
import static main.helpers.PrintingHelpers.*;
import static main.helpers.ReturnHelpers.*;

public class AssignTicket implements Command {
    /**
     * Executa comanda
     * @param params
     * @param useri
     * @param inventarTichete
     * @param milestones
     * @param helpers
     * @param outputs
     * @param timestampTesting
     * @param lastTimestamp
     * @param id
     * @param command
     * @param username
     * @param timestamp
     * @param veziTichete
     * @param i
     * @param inputJson
     * @param infoMilestones
     * @param user
     * @param ticketSearch
     * @param developersSearch
     * @param mapper
     */
    @Override
    public void executeCommand(final JsonNode params, final ArrayList<Users> useri,
                               final ArrayList<Ticket> inventarTichete,
                               final ArrayList<Milestone> milestones,
                               final HelperMethods helpers,
                               final List<ObjectNode> outputs,
                               final String timestampTesting,
                               final String lastTimestamp,
                               final int[] id, final String command,
                               final String username, final String timestamp,
                               final VeziTichete veziTichete,
                               final int i, final JsonNode inputJson,
                               final InfoMilestones infoMilestones, final Users user,
                               final TicketSearch ticketSearch,
                               final DevelopersSearch developersSearch,
                               final ObjectMapper mapper) {
        int ticketID = inputJson.get(i).get("ticketID").asInt();
        Users usrAT = returnUser(useri, username);
        Developer developer = (Developer) usrAT;
        Ticket ticket = returnTicket(inventarTichete, ticketID);
        String milestoneName = checkforTicket(milestones, ticketID);
        Milestone milestone = returnMilestone(milestones, milestoneName);
        if (ticket != null) {
            if (developer.rezolvaTichetul(developer.getSeniority(),
                    ticket.getExpertiseArea(),
                    ticket.getBusinessPriority(), ticket.getType())
                    && checkForDevInMilestone(milestone, username) == 1
                    && ticket.getStatus().equals("OPEN")
                    && !milestone.isBlocking()) {
                ticket.setStatus("IN_PROGRESS");
                ticket.setAssignedAt(timestamp);
                ticket.setIsAVailableForAssignment(false);
                developer.addTicket(ticket);
                ticket.setAssignedTo(username);
                LinkedHashMap<String, Vector<Integer>>
                        repartition = milestone.getRepartition();
                Vector<Integer> tickets = repartition.get(username);
                tickets.add(ticketID);
                if (!ticket.isNuMaiPuneInHistory()) {
                    ticket.assignTicket(username, timestamp);
                }
                ticket.changeStatus(ticket, 0,
                        ticket.getStatus(), username, timestamp);
            } else {
                if (!developer.eokSpecializarea(ticket.getExpertiseArea())) {
                    ObjectNode node = printWhatINeed(command, username, timestamp);
                    Users usr = returnUser(useri, username);
                    Developer dev = (Developer) usr;
                    node.put("error", "Developer "
                            + username + " cannot assign ticket "
                            + ticketID + " due to expertise area. Required: "
                            + printExpertiseArea(ticket.getExpertiseArea())
                            + "; Current: " + dev.getExpertiseArea() + ".");
                    outputs.add(node);
                } else if (!developer.eokPrioritatea(
                        ticket.getBusinessPriority())) {
                    ObjectNode node = printWhatINeed(command, username, timestamp);
                    Users usr = returnUser(useri, username);
                    Developer dev = (Developer) usr;
                    node.put("error", "Developer "
                            + username + " cannot assign ticket "
                            + ticketID + " due to seniority level. Required: "
                            + printPriority(ticket.getBusinessPriority())
                            + "; Current: " + dev.getSeniority() + ".");
                    outputs.add(node);
                } else if (!ticket.getStatus().equals("OPEN")) {
                    ObjectNode node = printWhatINeed(command, username, timestamp);
                    node.put("error", "Only OPEN tickets can be assigned.");
                    outputs.add(node);
                } else if (checkForDevInMilestone(milestone, username) == 0) {
                    ObjectNode node = printWhatINeed(command, username, timestamp);
                    Users usr = returnUser(useri, username);
                    Developer dev = (Developer) usr;
                    node.put("error", "Developer "
                            + username + " is not assigned to milestone "
                            + milestoneName + ".");
                    outputs.add(node);
                } else if (milestone.isBlocking()) {
                    ObjectNode node = printWhatINeed(command, username, timestamp);
                    node.put("error", "Cannot assign ticket "
                            + ticketID + " from blocked milestone "
                            + milestoneName + ".");
                    outputs.add(node);
                }
            }
        }
    }
}
