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

public class ViewTicketHistory implements Command {
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
        ObjectNode node = printWhatINeed(command, username, timestamp);
        if (user.getRole().equals("DEVELOPER")) {
            ArrayList<Ticket> allTickets = new ArrayList<>();
            veziTichete.createListTicketForDeveloper(useri, username, allTickets);
            ObjectNode printTickets = veziTichete.printHistoryTickets(allTickets);
            node.set("ticketHistory", printTickets.get("ticketHistory"));
        } else if (user.getRole().equals("MANAGER")) {
            ArrayList<Ticket> tickets = new ArrayList<>();
            veziTichete.createListTicketForManager(milestones,
                    username, useri, tickets);
            ObjectNode printTickets = veziTichete.printHistoryTickets(tickets);
            node.set("ticketHistory", printTickets.get("ticketHistory"));
        } else {
            node.put("error", "The user does not have permission "
                    + "to execute this command: required role DEVELOPER, MANAGER; "
                    + "user role " + user.getRole() + ".");
        }
        outputs.add(node);
    }
}
