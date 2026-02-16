package main.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.helpers.HelperMethods;
import main.magicNumbers.MagicNumbersInt;
import main.milestones.InfoMilestones;
import main.milestones.Milestone;
import main.searching.DevelopersSearch;
import main.searching.TicketSearch;
import main.ticket.Ticket;
import main.ticket.VeziTichete;
import main.user.Users;

import java.util.ArrayList;
import java.util.List;

import static main.helpers.CheckingHelpers.wellAssigned;
import static main.helpers.CheckingHelpers.wellReported;
import static main.helpers.PrintingHelpers.printWhatINeed;
import static main.helpers.ReturnHelpers.returnTicket;
import static main.helpers.ReturnHelpers.returnUser;

public class AddComment implements Command {
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
        String comment  = inputJson.get(i).get("comment").asText();
        Ticket ticket = returnTicket(inventarTichete, ticketID);
        Users usr = returnUser(useri, username);
        if (ticket != null && ticket.getReportedBy().equals("")) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Comments are not allowed on anonymous tickets.");
            outputs.add(node);
        } else if (comment.length() < MagicNumbersInt.zece.getValue()
                && ticket != null) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Comment must be at least 10 characters long.");
            outputs.add(node);
        } else if (usr.getRole().equals("REPORTER")
                && ticket != null
                && ticket.getStatus().equals("CLOSED")
                && ticket != null) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Reporters cannot comment on CLOSED tickets.");
            outputs.add(node);
        } else if (usr.getRole().equals("REPORTER")
                && ticket != null
                && wellReported(ticket, username) == 0
                && ticket != null) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Reporter "
                    + username + " cannot comment on ticket "
                    + ticketID + ".");
            outputs.add(node);
        } else if (wellAssigned(useri, username, ticketID) == 0
                && usr.getRole().equals("DEVELOPER")
                && ticket != null) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Ticket "
                    + ticketID + " is not assigned to the developer "
                    + username + ".");
            outputs.add(node);
        } else if (ticket != null) {
            ticket.addComment(comment);
            ticket.addAuthor(username);
            ticket.addDate(timestamp);
        }
    }
}
