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

import java.util.ArrayList;
import java.util.List;

import static main.helpers.CheckingHelpers.checkforTicket;
import static main.helpers.CheckingHelpers.wellAssigned;
import static main.helpers.PrintingHelpers.printWhatINeed;
import static main.helpers.ReturnHelpers.*;

public class UndoChangeStatus implements Command {
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
        Ticket ticket = returnTicket(inventarTichete, ticketID);
        Users usr = returnUser(useri, username);
        if (wellAssigned(useri, username, ticketID) == 0
                && usr.getRole().equals("DEVELOPER")
                && ticket != null) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Ticket "
                    + ticketID + " is not assigned to developer "
                    + username + ".");
            outputs.add(node);
        } else {
            if (ticket != null) {
                String nimName = checkforTicket(milestones, ticketID);
                Milestone milestone = returnMilestone(milestones, nimName);
                if (!milestone.isBlocking()) {
                    helpers.previousStatus(ticket);
                }
                if (!ticket.isNuMaiPuneInHistory()) {
                    ticket.changeStatus(ticket, 1,
                            ticket.getStatus(), username, timestamp);
                }
            }
        }
    }
}
