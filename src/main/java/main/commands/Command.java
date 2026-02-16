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

public interface Command {
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
    public void executeCommand(JsonNode params, ArrayList<Users> useri,
                               ArrayList<Ticket> inventarTichete,
                               ArrayList<Milestone> milestones,
                               HelperMethods helpers,
                               List<ObjectNode> outputs,
                               String timestampTesting,
                               String lastTimestamp,
                               int[] id, String command,
                               String username, String timestamp,
                               VeziTichete veziTichete,
                               int i, JsonNode inputJson,
                               InfoMilestones infoMilestones, Users user,
                               TicketSearch ticketSearch,
                               DevelopersSearch developersSearch,
                               ObjectMapper mapper);
}
