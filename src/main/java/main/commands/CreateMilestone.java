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
import java.util.List;

import static main.helpers.CheckingHelpers.checkforTicket;
import static main.helpers.PrintingHelpers.printWhatINeed;
import static main.helpers.ReturnHelpers.*;

public class CreateMilestone implements Command {
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
        Users userCM = returnUser(useri, username);
        if (!userCM.getRole().equals("MANAGER")) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "The user does not have permission to"
                    + " execute this command: required role MANAGER; user role "
                    + userCM.getRole() + ".");
            outputs.add(node);
        } else {
                int nuCrea = 0;
                String name = inputJson.get(i).get("name").asText();
                String dueDate = inputJson.get(i).get("dueDate").asText();
                JsonNode milestonesBlocate = inputJson.get(i).get("blockingFor");
                String[] blockingFor = new String[milestonesBlocate.size()];
                if (milestonesBlocate != null) {
                    for (int j = 0; j < milestonesBlocate.size(); j++) {
                        blockingFor[j] = milestonesBlocate.get(j).asText();
                        Milestone celBlocat = returnMilestone(milestones,
                                blockingFor[j]);
                        if (celBlocat != null) {
                            celBlocat.setBlocking(true);
                            celBlocat.addBlockers(name);
                        }
                    }
                }
                JsonNode tichete = inputJson.get(i).get("tickets");
                int[] tickets = new int[tichete.size()];
                if (tichete != null) {
                    for (int j = 0; j < tichete.size(); j++) {
                        if (checkforTicket(milestones,
                                tichete.get(j).asInt()) != null) {
                            ObjectNode node = printWhatINeed(command,
                                    username, timestamp);
                            node.put("error", "Tickets " +  tichete.get(j).asInt()
                                    + " already assigned to milestone "
                                    +  checkforTicket(milestones,
                                    tichete.get(j).asInt()) + ".");
                            outputs.add(node);
                            nuCrea = 1;
                        } else {
                            tickets[j] = tichete.get(j).asInt();
                        }
                    }
                }
                JsonNode developers = inputJson.get(i).get("assignedDevs");
                String[] assignedDevs = new String[developers.size()];
                if (developers != null) {
                    for (int j = 0; j < developers.size(); j++) {
                        assignedDevs[j] = developers.get(j).asText();
                        Developer dev = (Developer) returnUser(useri, assignedDevs[j]);
                    }
                }
                if (nuCrea == 0) {
                    Milestone milestone = new Milestone(name, blockingFor, dueDate,
                            tickets, assignedDevs, username,
                            timestamp, inventarTichete);
                    int[] tick = milestone.getTickets();
                    for (int p = 0; p < tick.length; p++) {
                        Ticket t = returnTicket(inventarTichete, tick[p]);
                        if (t != null) {
                            t.addToMilestone(milestone, username, timestamp);
                        }
                    }
                    milestones.add(milestone);
                    for (int k = 0; k < milestone.getAssignedDevs().length; k++) {
                        Developer dev = (Developer) returnUser(useri, assignedDevs[k]);
                        milestone.addAssignedDeveloper(dev);
                    }
                    milestone.setObservatoriNotificari(
                            milestone.getAssignedDevelopers());
                    milestone.milestoneCreat();
                }
            }
        }
}

