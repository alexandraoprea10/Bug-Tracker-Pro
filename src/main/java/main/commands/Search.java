package main.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
import java.util.List;

import static main.helpers.PrintingHelpers.printWhatINeed;

public class Search implements Command {
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
        JsonNode filters = inputJson.get(i).get("filters");
        String searchType = filters.get("searchType").asText();
        ObjectNode node = printWhatINeed(command, username, timestamp);
        node.put("searchType", searchType);
        if (user.getRole().equals("DEVELOPER")) {
            List<Ticket> ticheteGasite =
                    ticketSearch.searchTicketsDeveloper(milestones,
                            filters, username, inventarTichete, useri, helpers);
            ObjectNode printTickets =
                    veziTichete.printFoundTicketsDeveloper(ticheteGasite);
            node.set("results", printTickets.get("results"));
        } else if (user.getRole().equals("MANAGER")) {
            if (searchType.equals("TICKET")) {
                List<Ticket> ticheteGasite =
                        ticketSearch.searchTicketsManager(filters, inventarTichete);
                JsonNode keywords = filters.get("keywords");
                ObjectNode printTickets =
                        veziTichete.printFoundTicketsManager(ticheteGasite,
                                keywords);
                node.set("results", printTickets.get("results"));
            } else if (searchType.equals("DEVELOPER")) {
                ArrayNode arrayNode = mapper.createArrayNode();
                List<Developer> developeriGasiti =
                        developersSearch.searchDevelopers(user, filters, useri, helpers);
                if (!developeriGasiti.isEmpty()) {
                    ObjectNode printDevs = veziTichete.printFoundDevs(developeriGasiti);
                    node.set("results", printDevs.get("results"));
                } else {
                    node.set("results", arrayNode);
                }
            }
        }
        outputs.add(node);
    }
}
