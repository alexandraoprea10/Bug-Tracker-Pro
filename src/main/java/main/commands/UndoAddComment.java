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

import static main.helpers.PrintingHelpers.printWhatINeed;
import static main.helpers.ReturnHelpers.returnTicket;

public class UndoAddComment implements Command {
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
        if (ticket != null && ticket.getReportedBy().equals("")) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Comments are not allowed on anonymous tickets.");
            outputs.add(node);
        } else if (ticket != null && !ticket.getComments().isEmpty()) {
            int indexUsername = ticket.getAuthors().indexOf(username);
            ticket.getComments().remove(indexUsername);
            ticket.getAuthors().remove(indexUsername);
            ticket.getDate().remove(indexUsername);
        }
    }
}
