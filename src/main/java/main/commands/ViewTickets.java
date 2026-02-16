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

import static main.helpers.CheckingHelpers.checkIfTheDeveloperCanResolveTheTicket;
import static main.helpers.PrintingHelpers.printWhatINeed;
import static main.helpers.ReturnHelpers.returnUser;

public class ViewTickets implements Command {
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
        checkIfTheDeveloperCanResolveTheTicket(inventarTichete, useri,
                timestamp, username, milestones);
        Users usr = returnUser(useri, username);
        ObjectNode node = printWhatINeed(command, username, timestamp);
        if (usr.getRole().equals("MANAGER")) {
            ObjectNode printTickets = veziTichete.viewTicketsManager();
            node.set("tickets", printTickets.get("tickets"));
        } else if (usr.getRole().equals("REPORTER")) {
            ObjectNode printTickets = veziTichete.viewTicketsReporter(username);
            node.set("tickets", printTickets.get("tickets"));
        } else if (usr.getRole().equals("DEVELOPER")) {
            ObjectNode printTickets = veziTichete.
                    viewTicketsDeveloper(inventarTichete,
                            milestones, username, helpers);
            node.set("tickets", printTickets.get("tickets"));
        }
        outputs.add(node);
    }
}
