package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Ticket.*;
import main.User.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * main.App represents the main application logic that processes input commands,
 * generates outputs, and writes them to a file
 */
public class App {
    private static final String inputuserFile = "input/database/users.json";

    private static final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

    /**
     * Returneaza milestone-ul
     * @param milestones
     * @param name
     * @return
     */
    public static Milestone returnMilestone(final List<Milestone> milestones, String name) {
        for (int i =  0; i < milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            if (milestone.getName().equals(name)) {
                return milestone;
            }
        }
        return null;
    }

    /**
     * Verifica daca tichetul a fost deja asignat altui milestone.
     * @param milestones
     * @param id
     * @return
     */
    public static String checkforTicket(final List<Milestone> milestones,
                                        final int id) {
        for (int i = 0; i <  milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            int[] tickets =  milestone.getTickets();
            for (int j = 0; j < tickets.length; j++) {
                if (tickets[j] == id) {
                    return milestone.getName();
                }
            }
        }
        return null;
    }

    /**
     * Printeaza comanda, username si timestamp(mereu).
     * @param command
     * @param username
     * @param timestamp
     * @return
     */
    public static ObjectNode printwhatiNeed(final String command,
                                            final String username, final String timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", command);
        node.put("username", username);
        node.put("timestamp", timestamp);
        return node;
    }
    /**
     * Caut user-ul daca exista in lista de useri
     * @param useri lista de useri
     * @param username numele userului
     * @return referinta la userul cautat
     */
    public static Users returnUser(final List<Users> useri, final String username) {
        for (int i = 0; i < useri.size(); i++) {
            Users user = useri.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Printeaza userii
     * @param useri
     */
    public static void printUser(final List<Users> useri) {
        for (int i = 0; i < useri.size(); i++) {
            Users user = useri.get(i);
            System.out.println(user.getUsername());
            System.out.println(user.getMail());
            System.out.println(user.getRole());
            if (user.getRole().equals("DEVELOPER")) {
                Developer dev = (Developer) user;
                System.out.println(dev.getDate());
                System.out.println(dev.getSeniority());
                System.out.println(dev.getExpertiseArea());
            } else if (user.getRole().equals("MANAGER")) {
                Manager manager = (Manager) user;
                System.out.println(manager.getHireDate());
                System.out.println(manager.getSubordinates());
            }
        }
    }

    /**
     * Verific daca a trecut perioada de testare
     * @param startTimestamp inceput perioada de testare
     * @param currentTimestamp perioada curenta
     * @return 1 daca e trecut, altfel 0
     */
    public static int overdueTestingPeriod(final String startTimestamp,
                                           final String currentTimestamp) {
        LocalDate start = LocalDate.parse(startTimestamp);
        LocalDate current = LocalDate.parse(currentTimestamp);
        int daysBetween = (int) ChronoUnit.DAYS.between(start, current) + 1;
        if (daysBetween >= 12) {
            return 1;
        }
        return 0;

    }
    /**
     * Runs the application: reads commands from an input file,
     * processes them, generates results, and writes them to an output file
     *
     * @param inputPath path to the input file containing commands
     * @param outputPath path to the file where results should be written
     */

    public static void run(final String inputPath, final String outputPath) {
        List<ObjectNode> outputs = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = null;
        JsonNode inputJson = null;
        ArrayList<Users> useri = new ArrayList<>();
        int okstartTesting = 0;
        String timestampTesting = "";
        File usrFile = new File(inputuserFile);
        try {
            JsonNode users = mapper.readTree(usrFile);
            for (int i = 0; i < users.size(); i++) {
                JsonNode user = users.get(i);
                String username =  user.get("username").asText();
                String mail = user.get("email").asText();
                String role  = user.get("role").asText();
                if (role.equals("DEVELOPER")) {
                    String hireDate = user.get("hireDate").asText();
                    String seniority = user.get("seniority").asText();
                    String expertiseArea = user.get("expertiseArea").asText();
                    Developer usr = DeveloperFactory.createDeveloper(
                            username, mail, expertiseArea,
                            hireDate, seniority);
                    useri.add(usr);
                } else if (role.equals("REPORTER")) {
                    Reporter rep = new Reporter(username, mail, role);
                    useri.add(rep);
                } else if (role.equals("MANAGER")) {
                    String hireDate = user.get("hireDate").asText();
                    List<String> subordinates =  new ArrayList<>();
                    JsonNode subordonati =  user.get("subordinates");
                    if (subordonati != null) {
                        for (int p = 0; p < subordinates.size(); p++) {
                            JsonNode sub = subordonati.get(p);
                            String numeSubordonat = sub.asText();
                            subordinates.add(numeSubordonat);
                        }
                    }
                    Manager manager = new Manager(username, mail, role, hireDate, subordinates);
                    useri.add(manager);
                }
            }
        } catch (IOException e) {
            return;
        }
        // printUser(useri);
        int id = 0;
        try {
            inputFile = new File(inputPath);
            inputJson = mapper.readTree(inputFile);
            ArrayList<Ticket> inventarTichete = new ArrayList<>();
            ArrayList<Milestone> milestones = new ArrayList<>();
            InfoMilestones infoMilestones = new InfoMilestones();
            VeziTichete veziTichete = new VeziTichete();
            for (int i = 0; i < inputJson.size(); i++) {
                String command = inputJson.get(i).get("command").asText();
                String username = inputJson.get(i).get("username").asText();
                Users user = returnUser(useri, username);
                String timestamp = inputJson.get(i).get("timestamp").asText();
                if (okstartTesting == 0) {
                    okstartTesting = 1;
                    timestampTesting = timestamp;
                }
                System.out.println("=======COMANDA NOUA==========");
                for (int p = 0; p < milestones.size(); p++) {
                    Milestone milestone =  milestones.get(p);
                    System.out.println("PENTRU MILESTONE " + milestone.getName()
                            + milestone.getDueDate());
                    milestone.interactiuniTicket(timestamp);
                }
                if (user == null) {
                    ObjectNode node = printwhatiNeed(command, username, timestamp);
                    node.put("error", "The user " + username + " does not exist.");
                    outputs.add(node);
                } else {
                    if (command.equals("reportTicket")) {
                        JsonNode params = inputJson.get(i).get("params");
                        String type = params.get("type").asText();
                        String title = params.get("title").asText();
                        String businessPriority = params.get("businessPriority").asText();
                        String reportedBy = params.get("reportedBy").asText();
                        String expertiseArea = params.get("expertiseArea").asText();
                        String description = null;
                        if (params.get("description") != null) {
                            description = params.get("description").asText();
                        }
                        if (reportedBy.equals("") && !type.equals("BUG")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Anonymous reports are "
                                    + "only allowed for tickets of type BUG.");
                            outputs.add(node);
                        } else if (overdueTestingPeriod(timestampTesting, timestamp) == 1) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Tickets can only be "
                                    + "reported during testing phases.");
                            outputs.add(node);
                        } else {
                            if (type.equals("BUG")) {
                                String expectedBehavior = params.get("expectedBehavior").asText();
                                String actualBehavior = params.get("actualBehavior").asText();
                                String frequency = params.get("frequency").asText();
                                String severity = params.get("severity").asText();
                                String environment = null;
                                if (params.get("environment") != null) {
                                    environment = params.get("environment").asText();
                                }
                                int errorCode = -5;
                                if (params.get("errorCode") != null) {
                                    errorCode = Integer.parseInt(params.get("errorCode").asText());
                                }
                                BUG bug = new BUG.Builder(id, title, businessPriority, timestamp,
                                        expertiseArea, description, expectedBehavior,
                                        actualBehavior, frequency, severity, reportedBy)
                                        .environment(environment)
                                        .errorCode(errorCode)
                                        .build();
                                inventarTichete.add(bug);
                                id++;
                            } else if (type.equals("FEATURE_REQUEST")) {
                                String businessValue = params.get("businessValue").asText();
                                String customerDemand = params.get("customerDemand").asText();
                                FeatureRequest featureR = new FeatureRequest(id,
                                        title, businessPriority,
                                        timestamp, expertiseArea, description,
                                        businessValue, customerDemand, reportedBy);
                                inventarTichete.add(featureR);
                                id++;
                            } else if (type.equals("UI_FEEDBACK")) {
                                String uiElementId = params.get("uiElementId").asText();
                                String businessValue = params.get("businessValue").asText();
                                int usabilityScore = Integer.parseInt(
                                        params.get("usabilityScore").asText());
                                String screenshotUrl = null;
                                if (params.get("screenshotUrl") != null) {
                                    screenshotUrl = params.get("screenshotUrl").asText();
                                }
                                String suggestedFix = null;
                                if (params.get("suggestedFix") != null) {
                                    suggestedFix = params.get("suggestedFix").asText();
                                }
                                UIFeedback uiFeedback = new UIFeedback.Builder(id, title,
                                        businessPriority, timestamp, expertiseArea,
                                        reportedBy, uiElementId,
                                        businessValue, usabilityScore)
                                        .description(description)
                                        .screenshotUrl(screenshotUrl)
                                        .suggestedFix(suggestedFix)
                                        .build();
                                inventarTichete.add(uiFeedback);
                                id++;
                            }
                        }
                    } else if (command.equals("viewTickets")) {
                        Users usr = returnUser(useri, username);
                        if (usr.getRole().equals("MANAGER")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            ObjectNode printTickets = veziTichete.viewTicketsManager();
                            node.set("tickets", printTickets.get("tickets"));
                            outputs.add(node);
                        } else if (usr.getRole().equals("REPORTER")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            ObjectNode printTickets = veziTichete.viewTicketsReporter(username);
                            node.set("tickets", printTickets.get("tickets"));
                            outputs.add(node);
                        }
                    } else if (command.equals("createMilestone")) {
                        Users userCM = returnUser(useri, username);
                        if (!userCM.getRole().equals("MANAGER")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
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
                                    }
                                }
                            }
                            JsonNode tichete = inputJson.get(i).get("tickets");
                            int[] tickets = new int[tichete.size()];
                            if (tichete != null) {
                                for (int j = 0; j < tichete.size(); j++) {
                                    if (checkforTicket(milestones,
                                            tichete.get(j).asInt()) != null) {
                                        ObjectNode node = printwhatiNeed(command,
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
                                }
                            }
                            if (nuCrea == 0) {
                                Milestone milestone = new Milestone(name, blockingFor, dueDate,
                                        tickets, assignedDevs, username,
                                        timestamp, inventarTichete);
                                milestones.add(milestone);
                            }
                        }
                    } else if (command.equals("viewMilestones")) {
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        Users userul = returnUser(useri, username);
                        if (userul.getRole().equals("MANAGER")) {
                            ObjectNode printMilestones = infoMilestones.viewMilestonesManager(
                                    milestones, timestamp, username);
                            node.set("milestones", printMilestones.get("milestones"));
                            // outputs.add(node);
                        } else if (userul.getRole().equals("DEVELOPER")) {
                            ObjectNode printMilestones = infoMilestones.viewMilestonesDeveloper(
                                    milestones, timestamp, username);
                            node.set("milestones", printMilestones.get("milestones"));
                        }
                        outputs.add(node);
                    }
                    veziTichete.setInventarTichete(inventarTichete);
                }
            }
        } catch (IOException e) {
            return;
        }

        /*
            TODO 1 :
            Load initial user data and commands. we strongly recommend using jackson library.
            you can use the reading from hw1 as a reference.
            however you can use some of the more advanced features of
            jackson library, available here: https://www.baeldung.com/jackson-annotations
        */

        // TODO 2: process commands.

        // TODO 3: create objectnodes for output, add them to outputs list.
        try {
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();
            writer.withDefaultPrettyPrinter().writeValue(outputFile, outputs);
        } catch (IOException e) {
            System.out.println("error writing to output file: " + e.getMessage());
        }
    }
}
