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

import static main.helpers.CheckingHelpers.checkforTicket;
import static main.helpers.PrintingHelpers.printWhatINeed;
import static main.helpers.ReturnHelpers.*;

public class UndoAssignedTicket implements Command {
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
        if (ticket.getStatus().equals("IN_PROGRESS")) {
            String milestoneName = checkforTicket(milestones, ticketID);
            Milestone milestone = returnMilestone(milestones, milestoneName);
            LinkedHashMap<String, Vector<Integer>>
                    repartition = milestone.getRepartition();
            if (repartition != null) {
                Vector<Integer> tickets = repartition.get(username);
                if (tickets != null && !tickets.isEmpty()) {
                    int index = returnIndex(tickets, ticketID);
                    tickets.remove(index);
                }
            }
            if (ticket != null) {
                ticket.setAssignedTo("");
                ticket.setAssignedAt("");
                ticket.setNuMaiPuneInHistory(true);
                ticket.setIsAVailableForAssignment(true);
                ticket.setStatus("OPEN");
                ticket.deAssignTicket(username, timestamp);
                usrAT.getTickets().remove(ticket);
                usrAT.getGaveupTickets().add(ticket);
            }
        } else {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Only IN_PROGRESS tickets can be unassigned.");
            outputs.add(node);
        }
    }
}
