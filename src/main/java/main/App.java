package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.helpers.*;
import main.milestones.InfoMilestones;
import main.milestones.Milestone;
import main.searching.DevelopersSearch;
import main.searching.TicketSearch;
import main.ticket.*;
import main.user.*;
import main.commands.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static main.helpers.CheckingHelpers.checkIfTheDeveloperCanResolveTheTicket;

public class App {
    private static final String inputuserFile = "input/database/users.json";

    private static final ObjectWriter writer =
            new ObjectMapper().writer().withDefaultPrettyPrinter();

    public static void run(final String inputPath, final String outputPath) {
        List<ObjectNode> outputs = new ArrayList<>();
        HelperMethods helpers = new HelperMethods();
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = null;
        JsonNode inputJson = null;
        ArrayList<Users> useri = new ArrayList<>();
        int okstartTesting = 0;
        String timestampTesting = "";
        String lastTimestamp = null;
        File usrFile = new File(inputuserFile);
        try {
            JsonNode usersNode = mapper.readTree(usrFile);
            helpers.createUsers(usersNode, useri);
        } catch (IOException e) {
            return;
        }
        // printUser(useri)
        int[] id = {0};
        try {
            inputFile = new File(inputPath);
            inputJson = mapper.readTree(inputFile);
            ArrayList<Ticket> inventarTichete = new ArrayList<>();
            ArrayList<Milestone> milestones = new ArrayList<>();
            InfoMilestones infoMilestones = new InfoMilestones();
            VeziTichete veziTichete = new VeziTichete();
            TicketSearch ticketSearch = new TicketSearch();
            DevelopersSearch developersSearch = new DevelopersSearch();
            WorkingWithMilestones workMilestone = new WorkingWithMilestones();
            PrintingHelpers printingHelpers = new PrintingHelpers();
            ReturnHelpers returnHelpers = new ReturnHelpers();
            CheckingHelpers checkingHelpers = new CheckingHelpers();
            for (int i = 0; i < inputJson.size(); i++) {
                String command = inputJson.get(i).get("command").asText();
                String username = inputJson.get(i).get("username").asText();
                Users user = returnHelpers.returnUser(useri, username);
                String timestamp = inputJson.get(i).get("timestamp").asText();
                if (okstartTesting == 0) {
                    okstartTesting = 1;
                    timestampTesting = timestamp;
                }
                workMilestone.workingWithMilestonesBeforeCommand(milestones,
                        inventarTichete, timestamp, lastTimestamp, helpers);
                if (user == null) {
                    ObjectNode node = printingHelpers.printWhatINeed(command, username, timestamp);
                    node.put("error", "The user " + username + " does not exist.");
                    outputs.add(node);
                } else {
                    JsonNode params = inputJson.get(i).get("params");
                    if (command.equals("reportTicket")) {
                        ReportTicket cmd = new ReportTicket();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);

                    } else if (command.equals("viewTickets")) {
                        ViewTickets cmd = new ViewTickets();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("createMilestone")) {
                        CreateMilestone cmd = new CreateMilestone();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("viewMilestones")) {
                        ViewMilestones cmd = new ViewMilestones();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("assignTicket")) {
                        AssignTicket cmd = new AssignTicket();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("viewAssignedTickets")) {
                        ViewAssignedTickets cmd = new ViewAssignedTickets();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("undoAssignTicket")) {
                        UndoAssignedTicket cmd = new UndoAssignedTicket();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("addComment")) {
                        AddComment cmd = new AddComment();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("undoAddComment")) {
                        UndoAddComment cmd = new UndoAddComment();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("changeStatus")) {
                        ChangeStatus cmd = new ChangeStatus();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("undoChangeStatus")) {
                        UndoChangeStatus cmd = new UndoChangeStatus();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("viewTicketHistory")) {
                        ViewTicketHistory cmd = new ViewTicketHistory();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("search")) {
                        Search cmd = new Search();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("viewNotifications")) {
                        ViewNotifications cmd = new ViewNotifications();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("generateCustomerImpactReport")) {
                        GenerateCustomerImpactReport cmd = new GenerateCustomerImpactReport();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("generateTicketRiskReport")) {
                        GenerateTicketRiskReport cmd = new GenerateTicketRiskReport();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("generateResolutionEfficiencyReport")) {
                        GenerateResolutionEfficiencyReport cmd =
                                new GenerateResolutionEfficiencyReport();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("appStabilityReport")) {
                        AppStabilityReport cmd = new AppStabilityReport();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("generatePerformanceReport")) {
                        GeneratePerformanceReport cmd = new GeneratePerformanceReport();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                    } else if (command.equals("startTestingPhase")) {
                        StartTestingPhase cmd = new StartTestingPhase();
                        cmd.executeCommand(params, useri, inventarTichete,
                                milestones, helpers, outputs, timestampTesting,
                                lastTimestamp, id, command, username, timestamp,
                                veziTichete, i, inputJson, infoMilestones, user,
                                ticketSearch, developersSearch, mapper);
                        timestampTesting = timestamp;
                    }
                    lastTimestamp = timestamp;
                    workMilestone.workingWithMilestonesAfterCommand(milestones, inventarTichete, helpers);
                    checkIfTheDeveloperCanResolveTheTicket(inventarTichete, useri,
                            timestamp, username, milestones);
                    veziTichete.setInventarTichete(inventarTichete);
                }
            }
        } catch (IOException e) {
            return;
        }
        try {
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();
            writer.withDefaultPrettyPrinter().writeValue(outputFile, outputs);
        } catch (IOException e) {
            System.out.println("error writing to output file: " + e.getMessage());
        }
    }
}
