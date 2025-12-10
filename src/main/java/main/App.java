package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Ticket.*;
import main.User.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * main.App represents the main application logic that processes input commands,
 * generates outputs, and writes them to a file
 */
public class App {
    private static final String inputuserFile = "input/database/users.json";

    private static final ObjectWriter writer =
            new ObjectMapper().writer().withDefaultPrettyPrinter();

    /**
     * Returneaza milestone-ul
     * @param milestones
     * @param name
     * @return
     */
    public static Milestone returnMilestone(final List<Milestone> milestones,
                                            final String name) {
        for (int i =  0; i < milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            if (milestone.getName().equals(name)) {
                return milestone;
            }
        }
        return null;
    }

    /**
     * Returneaza milestone-ul din care face parte developerul.
     * @param milestones
     * @param usename
     * @return
     */
    public static Milestone returnByUserame(final List<Milestone> milestones,
                                            final String usename) {
        for (int i = 0; i < milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            String[] assignedDev = milestone.getAssignedDevs();
            for (int j = 0; j < assignedDev.length; j++) {
                if (assignedDev[j].equals(usename)) {
                    return milestone;
                }
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
     * Caut in toate milestoneurile unde e asignat userul.
     * @param milestones
     * @param username
     * @return
     */
    public static int checkforUser(final List<Milestone> milestones,
                                    final String username) {
        for (int i = 0; i <  milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            String[] assignedDevs = milestone.getAssignedDevs();
            for (int j = 0; j < assignedDevs.length; j++) {
                if (assignedDevs[j].equals(username)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * Returnez tichetul cu id-ul respectiv.
     * @param inventarTichete
     * @param id
     * @return
     */
    public static Ticket returnTicket(final ArrayList<Ticket> inventarTichete,
                                      final int id) {
        for (int i = 0; i < inventarTichete.size(); i++) {
            Ticket ticket = inventarTichete.get(i);
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Caut daca exista developerul in milestone.
     * @param milestone
     * @param username
     * @return
     */
    public static int checkForDevInMilestone(final Milestone milestone,
                                             final String username) {
        String[] assignedDevs = milestone.getAssignedDevs();
        for (int j = 0; j < assignedDevs.length; j++) {
            if (username.equals(assignedDevs[j])) {
                return 1;
            }
        }
        return 0;
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
     * Verific daca expertiza e ok.
     * @param expertiseArea
     * @return
     */
    public static String printExpertiseArea(final String expertiseArea) {
        if (expertiseArea.equals("DB")) {
            return "BACKEND, DB, FULLSTACK";
        } else if (expertiseArea.equals("FRONTEND")) {
            return "DESIGN, FRONTEND, FULLSTACK";
        } else if (expertiseArea.equals("BACKEND")) {
            return "BACKEND, FULLSTACK";
        } else if (expertiseArea.equals("DEVOPS")) {
            return "DEVOPS, FULLSTACK";
        } else if (expertiseArea.equals("DESIGN")) {
            return "DESIGN, FRONTEND, FULLSTACK";
        }
        return null;
    }

    /**
     * Verific ce fel de expertiza poate rezolva.
     * @param priority
     * @return
     */
    public static String printPriority(final String priority) {
        if (priority.equals("LOW")) {
            return "JUNIOR, MID, SENIOR";
        } else if (priority.equals("MEDIUM")) {
            return "MID, SENIOR";
        } else if (priority.equals("HIGH")) {
            return "MID, SENIOR";
        } else if (priority.equals("CRITICAL")) {
            return "SENIOR";
        }
        return null;
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
     * Verific daca reporterul este cel cu numele de username
     * @param ticket
     * @param username
     * @return
     */
    public static int araportatBine(final Ticket ticket,
                                    final String username) {
        if (ticket.getReportedBy().equals(username)) {
            return 1;
        }
        return 0;
    }

    /**
     * Verific daca se gaseste in lista de useri
                        tichetul cu id-ul respectiv.
     * @param users
     * @param username
     * @param ticketID
     * @return
     */
    public static int eAsignataBine(final List<Users> users,
                                    final String username,
                                    final int ticketID) {
        Users user = returnUser(users, username);
        ArrayList<Ticket> tickets = user.getTickets();
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == ticketID) {
                return 1;
            }
        }
        return 0;
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
        if (daysBetween >= MagicNumbersInt.cincisprezece.getValue()) {
            return 1;
        }
        return 0;
    }

    /**
     * Setez statusul urmator(pentru changestatus).
     * @param ticket
     */
    public static void nextStatus(final Ticket ticket, final String timestamp) {
        if (ticket.getStatus().equals("OPEN")) {
            ticket.setStatus("IN_PROGRESS");
        } else if (ticket.getStatus().equals("IN_PROGRESS")) {
            ticket.setStatus("RESOLVED");
            ticket.setUltimulTimestampCR(timestamp);
            ticket.setSolvedAt(timestamp);
        } else if (ticket.getStatus().equals("RESOLVED")) {
            ticket.setUltimulTimestampCR(timestamp);
            ticket.setStatus("CLOSED");
        }
    }

    /**
     * Setez statusul anterior(pentru UNDOChangeStatus).
     * @param ticket
     */
    public static void previousStatus(final Ticket ticket) {
         if (ticket.getStatus().equals("RESOLVED")) {
            ticket.setSolvedAt("");
            ticket.setStatus("IN_PROGRESS");
        } else if (ticket.getStatus().equals("CLOSED")) {
            ticket.setUltimulTimestampCR(ticket.getSolvedAt());
            ticket.setStatus("RESOLVED");
        }
    }
    public static void nextPriority(final Ticket t) {
        if (t.getBusinessPriority().equals("LOW")) {
            t.setBusinessPriority("MEDIUM");
        } else if (t.getBusinessPriority().equals("MEDIUM")) {
            t.setBusinessPriority("HIGH");
        } else if (t.getBusinessPriority().equals("HIGH")) {
            t.setBusinessPriority("CRITICAL");
        }
    }

    /**
     * Calculeaza nr de tichete closed
     * @param milestone
     * @param inventarTichete
     * @return
     */
    public static int calculateClosedTickets(final Milestone milestone,
                                             final ArrayList<Ticket> inventarTichete) {
        int bune = 0;
        if (!milestone.isBlocking()) {
            int[] ticketsID = milestone.getTickets();
            for (int i = 0; i < ticketsID.length; i++) {
                Ticket t = returnTicket(inventarTichete, ticketsID[i]);
                if (t != null && (t.getStatus().equals("CLOSED"))) {
                    bune++;
                }
            }
        }
        if (bune == milestone.getTickets().length) {
            return 1;
        }
        return 0;
    }

    /**
     * Actualizez vectorii de tichete open si close.
     * @param milestone
     * @param ticket
     */
    public static void openAndClose(final Milestone milestone,
                                    final Ticket ticket) {
        int[] openTickets = milestone.getOpenTickets();
        int[] closedTickets = milestone.getClosedTickets();
        ArrayList<Integer> openT = new ArrayList<>();
        ArrayList<Integer> closedT = new ArrayList<>();
        if (ticket.getStatus().equals("CLOSED")) {
            for (int i = 0; i < openTickets.length; i++) {
                if (openTickets[i] != ticket.getId()) {
                    openT.add(openTickets[i]);
                }
            }
            int ok = 0;
            for (int i = 0; i < closedTickets.length; i++) {
                    if (closedTickets[i] == ticket.getId()) {
                        ok = 1;
                    }
                    closedT.add(closedTickets[i]);
                }
            if (ok == 0) {
                closedT.add(ticket.getId());
            }
            int[] copieOpen = new int[openT.size()];
            int[] copieClose = new int[closedT.size()];
            // System.out.println("copie close" + copieClose.length);
            for (int i = 0; i < openT.size(); i++) {
                    copieOpen[i] = openT.get(i);
            }
            for (int i = 0; i < closedT.size(); i++) {
                copieClose[i] = closedT.get(i);
            }
            int nrTicheteClosed = copieClose.length;
            int nrTicheteTotal = milestone.getTickets().length;
            double completion = (double) nrTicheteClosed /  (double) nrTicheteTotal;
            double result = Math.round(completion * MagicNumbersDouble.osuta.getValue())
                    / MagicNumbersDouble.osuta.getValue();
            milestone.setOpenTickets(copieOpen);
            milestone.setClosedTickets(copieClose);
            milestone.setCompletionPercentage(result);
        }
    }

    /**
     * Returneaza indexul la care se afla ticket-ul cu id-ul id.
     * @param tickets
     * @param id
     * @return
     */
    public static int returnIndex(final Vector<Integer> tickets,
                                  final int id) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i) == id) {
                return i;
            }
        }
        return -1;
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
        String lastTimestamp = null;
        File usrFile = new File(inputuserFile);
        try {
            JsonNode usersNode = mapper.readTree(usrFile);
            for (int i = 0; i < usersNode.size(); i++) {
                JsonNode user = usersNode.get(i);
                String username =  user.get("username").asText();
                String mail = user.get("email").asText();
                String role  = user.get("role").asText();
                if (role.equals("DEVELOPER")) {
                    String hireDate = user.get("hireDate").asText();
                    String seniority = user.get("seniority").asText();
                    String expertiseArea = user.get("expertiseArea").asText();
                    Developer usr = DeveloperFactory.createDeveloper(
                            username, mail, hireDate,
                            expertiseArea, seniority);
                    useri.add(usr);
                } else if (role.equals("REPORTER")) {
                    Reporter rep = new Reporter(username, mail, role);
                    useri.add(rep);
                } else if (role.equals("MANAGER")) {
                    String hireDate = user.get("hireDate").asText();
                    List<String> subordinates =  new ArrayList<>();
                    JsonNode subordonati =  user.get("subordinates");
                    if (subordonati != null) {
                        for (int p = 0; p < subordonati.size(); p++) {
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
        // printUser(useri)
        int id = 0;
        try {
            inputFile = new File(inputPath);
            inputJson = mapper.readTree(inputFile);
            ArrayList<Ticket> inventarTichete = new ArrayList<>();
            ArrayList<Milestone> milestones = new ArrayList<>();
            InfoMilestones infoMilestones = new InfoMilestones();
            VeziTichete veziTichete = new VeziTichete();
            TicketSearch ticketSearch = new TicketSearch();
            DevelopersSearch developersSearch = new DevelopersSearch();
            for (int i = 0; i < inputJson.size(); i++) {
                System.out.println("==============INCEPUTCOMANDA==============");
                String command = inputJson.get(i).get("command").asText();
                System.out.println("COMANDA ESTE: " + command);
                String username = inputJson.get(i).get("username").asText();
                Users user = returnUser(useri, username);
                String timestamp = inputJson.get(i).get("timestamp").asText();
                if (okstartTesting == 0) {
                    okstartTesting = 1;
                    timestampTesting = timestamp;
                }
                // System.out.println("=======COMANDA NOUA==========");
                for (int p = 0; p < milestones.size(); p++) {
                    Milestone milestone =  milestones.get(p);
                    if (calculateClosedTickets(milestone, inventarTichete) == 1) {
                        System.out.println("INTRA AICI");
                        milestone.setInactivity(true);
                    }
                    // System.out.println("PENTRU MILESTONE " + milestone.getName()
                      //      + milestone.getDueDate());
                    if (!timestamp.equals(lastTimestamp)
                            && !command.equals("generatePerformanceReport")) {
                        milestone.interactiuniTicket(timestamp);
                    }
                    LocalDate currentDate = LocalDate.parse(timestamp);
                    LocalDate dateMilestone = LocalDate.parse(milestone.getDueDate());
                    int daysBetween = (int) ChronoUnit.DAYS.between(currentDate, dateMilestone) + 1;
                    if (daysBetween == 0 && !milestone.isBlocking()) {
                        milestone.vineDueDate();
                        for (int k = 0; k < milestone.getTickets().length; k++) {
                            int ticketID =  milestone.getTickets()[k];
                            Ticket t = returnTicket(inventarTichete, ticketID);
                            if (t != null && !t.getStatus().equals("RESOLVED")) {
                                t.setBusinessPriority("CRITICAL");
                            }
                        }
                    }
                    if (daysBetween < 0 && milestone.isBlocking()) {
                        milestone.aTrecutDue();
                        for (int k = 0; k < milestone.getTickets().length; k++) {
                            int ticketID =  milestone.getTickets()[k];
                            Ticket t = returnTicket(inventarTichete, ticketID);
                            if (t != null && !t.getStatus().equals("RESOLVED")) {
                                t.setBusinessPriority("CRITICAL");
                            }
                        }
                    }
                }
                for (int k = 0; k < milestones.size(); k++) {
                    Milestone milestone =  milestones.get(k);
                    int[] tickets = milestone.getTickets();
                    for (int j = 0; j < tickets.length; j++) {
                        Ticket t = returnTicket(inventarTichete, tickets[j]);
                        if (t != null) {
                            openAndClose(milestone, t);
                        }
                    }
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
                                int errorCode =
                                        MagicNumbersInt.minuscinci.getValue();
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
                                String uiElementId = null;
                                String businessValue = params.get("businessValue").asText();
                                int usabilityScore = Integer.parseInt(
                                        params.get("usabilityScore").asText());
                                String screenshotUrl = null;
                                if (params.get("uiElementId") != null) {
                                    uiElementId = params.get("uiElementId").asText();
                                }
                                if (params.get("screenshotUrl") != null) {
                                    screenshotUrl = params.get("screenshotUrl").asText();
                                }
                                String suggestedFix = null;
                                if (params.get("suggestedFix") != null) {
                                    suggestedFix = params.get("suggestedFix").asText();
                                }
                                UIFeedback uiFeedback = new UIFeedback.Builder(id, title,
                                        businessPriority, timestamp, expertiseArea,
                                        reportedBy,
                                        businessValue, usabilityScore)
                                        .uiElementId(uiElementId)
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
                        } else if (usr.getRole().equals("DEVELOPER")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            ObjectNode printTickets = veziTichete.
                                    viewTicketsDeveloper(inventarTichete,
                                    milestones, username);
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
                    } else if (command.equals("viewMilestones")) {
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        Users userul = returnUser(useri, username);
                        if (userul.getRole().equals("MANAGER")) {
                            ObjectNode printMilestones = infoMilestones.viewMilestonesManager(
                                    milestones, timestamp, username, inventarTichete);
                            node.set("milestones", printMilestones.get("milestones"));
                            // outputs.add(node);
                        } else if (userul.getRole().equals("DEVELOPER")) {
                            ObjectNode printMilestones = infoMilestones.viewMilestonesDeveloper(
                                    milestones, timestamp, username);
                            node.set("milestones", printMilestones.get("milestones"));
                        }
                        outputs.add(node);
                    } else if (command.equals("assignTicket")) {
                        int ticketID = inputJson.get(i).get("ticketID").asInt();
                        Users usrAT = returnUser(useri, username);
                        Developer developer = (Developer) usrAT;
                        Ticket ticket = returnTicket(inventarTichete, ticketID);
                        String milestoneName = checkforTicket(milestones, ticketID);
                        Milestone milestone = returnMilestone(milestones, milestoneName);
                        if (ticket != null) {
                            if (developer.rezolvaTichetul(developer.getSeniority(),
                                    ticket.getExpertiseArea(),
                                    ticket.getBusinessPriority(), ticket.getType())
                                    && checkForDevInMilestone(milestone, username) == 1
                                    && ticket.getStatus().equals("OPEN")
                                    && !milestone.isBlocking()) {
                                ticket.setStatus("IN_PROGRESS");
                                ticket.setAssignedAt(timestamp);
                                ticket.setIsAVailableForAssignment(false);
                                developer.addTicket(ticket);
                                LinkedHashMap<String, Vector<Integer>>
                                        repartition = milestone.getRepartition();
//                            System.out.println(milestoneName);
//                            System.out.println(repartition.keySet());
                                Vector<Integer> tickets = repartition.get(username);
                                tickets.add(ticketID);
                                if (!ticket.isNuMaiPuneInHistory()) {
                                    ticket.assignTicket(username, timestamp);
                                }
                                    ticket.changeStatus(ticket, 0,
                                            ticket.getStatus(), username, timestamp);
                            } else {
                                if (!developer.eokSpecializarea(ticket.getExpertiseArea())) {
                                    ObjectNode node = printwhatiNeed(command, username, timestamp);
                                    Users usr = returnUser(useri, username);
                                    Developer dev = (Developer) usr;
                                    node.put("error", "Developer "
                                            + username + " cannot assign ticket "
                                            + ticketID + " due to expertise area. Required: "
                                            + printExpertiseArea(ticket.getExpertiseArea())
                                            + "; Current: " + dev.getExpertiseArea() + ".");
                                    outputs.add(node);
                                } else if (!developer.eokPrioritatea(
                                        ticket.getBusinessPriority())) {
                                    ObjectNode node = printwhatiNeed(command, username, timestamp);
                                    Users usr = returnUser(useri, username);
                                    Developer dev = (Developer) usr;
                                    node.put("error", "Developer "
                                            + username + " cannot assign ticket "
                                            + ticketID + " due to seniority level. Required: "
                                            + printPriority(ticket.getBusinessPriority())
                                            + "; Current: " + dev.getSeniority() + ".");
                                    outputs.add(node);
                                } else if (checkForDevInMilestone(milestone, username) == 0) {
                                    ObjectNode node = printwhatiNeed(command, username, timestamp);
                                    Users usr = returnUser(useri, username);
                                    Developer dev = (Developer) usr;
                                    node.put("error", "Developer "
                                            + username + " is not assigned to milestone "
                                            + milestoneName + ".");
                                    outputs.add(node);
                                } else if (!ticket.getStatus().equals("OPEN")) {
                                    ObjectNode node = printwhatiNeed(command, username, timestamp);
                                    node.put("error", "Only OPEN tickets can be assigned.");
                                    outputs.add(node);
                                } else if (milestone.isBlocking()) {
                                    ObjectNode node = printwhatiNeed(command, username, timestamp);
                                    node.put("error", "Cannot assign ticket "
                                            + ticketID + " from blocked milestone "
                                            + milestoneName + ".");
                                    outputs.add(node);
                                }
                            }
                        }
                    } else if (command.equals("viewAssignedTickets")) {
                        Users usrAT = returnUser(useri, username);
                        ArrayList<Ticket> tickets = usrAT.getTickets();
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        if (user.getRole().equals("DEVELOPER")) {
                            ObjectNode printTickets = veziTichete.printTickets(tickets);
                            node.set("assignedTickets", printTickets.get("assignedTickets"));
                        } else {
                            node.put("error:", "The user does not have permission to execute this "
                                    + "command: required role DEVELOPER; user role "
                                    + user.getRole() + ".");
                        }
                        outputs.add(node);
                    } else if (command.equals("undoAssignTicket")) {
                        int ticketID = inputJson.get(i).get("ticketID").asInt();
                        Users usrAT = returnUser(useri, username);
                        Developer developer = (Developer) usrAT;
                        Ticket ticket = returnTicket(inventarTichete, ticketID);
                        String milestoneName = checkforTicket(milestones, ticketID);
                        Milestone milestone = returnMilestone(milestones, milestoneName);
                        LinkedHashMap<String, Vector<Integer>>
                                repartition = milestone.getRepartition();
                        if (repartition != null) {
                            Vector<Integer> tickets = repartition.get(username);
                            if (tickets != null && !tickets.isEmpty()) {
                                int index = returnIndex(tickets, ticketID);
                                tickets.remove(index);
                            }
                        }
                        if (ticket != null) {
                            ticket.setAssignedTo("");
                            ticket.setAssignedAt("");
                            ticket.setNuMaiPuneInHistory(true);
                            ticket.setIsAVailableForAssignment(true);
                            ticket.setStatus("OPEN");
                            ticket.deAssignTicket(username, timestamp);
                            usrAT.getTickets().remove(ticket);
                            usrAT.getGaveupTickets().add(ticket);
                        }
                    } else if (command.equals("addComment")) {
                        int ticketID = inputJson.get(i).get("ticketID").asInt();
                        String comment  = inputJson.get(i).get("comment").asText();
                        Ticket ticket = returnTicket(inventarTichete, ticketID);
                        Users usr = returnUser(useri, username);
                        if (ticket != null && ticket.getReportedBy().equals("")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Comments are not allowed on anonymous tickets.");
                            outputs.add(node);
                        } else if (comment.length() < MagicNumbersInt.zece.getValue()
                                && ticket != null) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Comment must be at least 10 characters long.");
                            outputs.add(node);
                        } else if (usr.getRole().equals("REPORTER")
                                && ticket != null
                                && ticket.getStatus().equals("CLOSED")
                                && ticket != null) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Reporters cannot comment on CLOSED tickets.");
                            outputs.add(node);
                        } else if (usr.getRole().equals("REPORTER")
                                && ticket != null
                                && araportatBine(ticket, username) == 0
                                && ticket != null) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Reporter "
                                    + username + " cannot comment on ticket "
                                    + ticketID + ".");
                            outputs.add(node);
                        } else if (eAsignataBine(useri, username, ticketID) == 0
                                && usr.getRole().equals("DEVELOPER")
                                && ticket != null) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Ticket "
                                    + ticketID + " is not assigned to the developer "
                                    + username + ".");
                            outputs.add(node);
                        } else if (ticket != null) {
                            ticket.addComment(comment);
                            ticket.addAuthor(username);
                            ticket.addDate(timestamp);
                        }
                    } else if (command.equals("undoAddComment")) {
                        int ticketID = inputJson.get(i).get("ticketID").asInt();
                        Ticket ticket = returnTicket(inventarTichete, ticketID);
                        if (ticket != null && ticket.getReportedBy().equals("")) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Comments are not allowed on anonymous tickets.");
                            outputs.add(node);
                        } else if (ticket != null && !ticket.getComments().isEmpty()) {
                            int indexUsername = ticket.getAuthors().indexOf(username);
                            ticket.getComments().remove(indexUsername);
                            ticket.getAuthors().remove(indexUsername);
                            ticket.getDate().remove(indexUsername);
                        }
                    } else if (command.equals("changeStatus")) {
                        int ticketID = inputJson.get(i).get("ticketID").asInt();
                        Ticket ticket = returnTicket(inventarTichete, ticketID);
                        Users usr = returnUser(useri, username);
                        if (eAsignataBine(useri, username, ticketID) == 0
                                && usr.getRole().equals("DEVELOPER")
                                && ticket != null) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Ticket "
                                    + ticketID + " is not assigned to developer "
                                    + username + ".");
                            outputs.add(node);
                        } else {
                            if (ticket != null) {
                                String nimName = checkforTicket(milestones, ticketID);
                                Milestone milestone = returnMilestone(milestones, nimName);
                                if (!milestone.isBlocking()) {
                                    System.out.println("tichetul cu id " + ticketID + " care se afla in milestone-ul " + nimName + " care e " + milestone.isBlocking() + " i se schimba prioritatea de la " + ticket.getBusinessPriority());
                                    nextStatus(ticket, timestamp);
                                }
                                if (!ticket.isNuMaiPuneInHistory()) {
                                    ticket.changeStatus(ticket, 0,
                                            ticket.getStatus(), username, timestamp);
                                }
                            }
                        }
                    } else if (command.equals("undoChangeStatus")) {
                        int ticketID = inputJson.get(i).get("ticketID").asInt();
                        Ticket ticket = returnTicket(inventarTichete, ticketID);
                        Users usr = returnUser(useri, username);
                        if (eAsignataBine(useri, username, ticketID) == 0
                                && usr.getRole().equals("DEVELOPER")
                                && ticket != null) {
                            ObjectNode node = printwhatiNeed(command, username, timestamp);
                            node.put("error", "Ticket "
                                    + ticketID + " is not assigned to developer "
                                    + username + ".");
                            outputs.add(node);
                        } else {
                            if (ticket != null) {
                                String nimName = checkforTicket(milestones, ticketID);
                                Milestone milestone = returnMilestone(milestones, nimName);
                                if (!milestone.isBlocking()) {
                                    System.out.println("tichetul cu id " + ticketID + " care se afla in milestone-ul " + nimName + " care e " + milestone.isBlocking() + " i se schimba prioritatea LA UNDO de la " + ticket.getBusinessPriority());
                                    previousStatus(ticket);
                                }
                                if (!ticket.isNuMaiPuneInHistory()) {
                                    ticket.changeStatus(ticket, 1,
                                            ticket.getStatus(), username, timestamp);
                                }
                            }
                        }

                    } else if (command.equals("viewTicketHistory")) {
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        if (user.getRole().equals("DEVELOPER")) {
                            ArrayList<Ticket> allTickets = new ArrayList<>();
                            for (int m = 0;
                                 m < returnUser(useri, username).getTickets().size(); m++) {
                                Ticket t = returnUser(useri, username).getTickets().get(m);
                                allTickets.add(t);
                            }
                            for (int m = 0;
                                 m < returnUser(useri, username).getGaveupTickets().size(); m++) {
                                Ticket t = returnUser(useri, username).getGaveupTickets().get(m);
                                allTickets.add(t);
                            }
                            ObjectNode printTickets = veziTichete.printHistoryTickets(allTickets);
                            node.set("ticketHistory", printTickets.get("ticketHistory"));
                        } else if (user.getRole().equals("MANAGER")) {
                            ArrayList<Ticket> tickets = new ArrayList<>();
                            Manager manager = (Manager) user;
                            for (int k = 0; k < milestones.size(); k++) {
                                Milestone milestone = milestones.get(k);
                                if (milestone.getCreatedBy().equals(username)) {
                                    String[] assignedDev = milestone.getAssignedDevs();
                                    for (int p = 0; p < assignedDev.length; p++) {
                                        for (int m = 0;
                                             m < returnUser(useri,
                                                     assignedDev[p]).getTickets().size();
                                        m++) {
                                            Ticket t = returnUser(useri,
                                                    assignedDev[p]).getTickets().get(m);
                                            tickets.add(t);
                                        }
                                        for (int m = 0;
                                             m < returnUser(useri,
                                                     assignedDev[p]).getGaveupTickets().size(); m++) {
                                            Ticket t = returnUser(useri,
                                                    assignedDev[p]).getGaveupTickets().get(m);
                                            tickets.add(t);
                                        }
                                    }
                                }
                            }
                            ObjectNode printTickets = veziTichete.printHistoryTickets(tickets);
                            node.set("ticketHistory", printTickets.get("ticketHistory"));
                        } else {
                            node.put("error", "The user does not have permission "
                                    + "to execute this command: required role DEVELOPER, MANAGER; "
                                    + "user role " + user.getRole() + ".");
                        }
                        outputs.add(node);
                    } else if (command.equals("search")) {
                        JsonNode filters = inputJson.get(i).get("filters");
                        String searchType = filters.get("searchType").asText();
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        node.put("searchType", searchType);
                       // System.out.println(filters);
                        if (user.getRole().equals("DEVELOPER")) {
                            List<Ticket> ticheteGasite =
                                    ticketSearch.searchTicketsDeveloper(milestones,
                                            filters, username, inventarTichete, useri);
                            ObjectNode printTickets =
                                    veziTichete.printFoundTicketsDeveloper(ticheteGasite);
                            node.set("results", printTickets.get("results"));
                        } else if (user.getRole().equals("MANAGER")) {
                            if (searchType.equals("TICKET")) {
                                List<Ticket> ticheteGasite =
                                        ticketSearch.searchTicketsManager(filters, inventarTichete);
                                JsonNode keywords = filters.get("keywords");
                                // System.out.println(keywords);
                                ObjectNode printTickets =
                                        veziTichete.printFoundTicketsManager(ticheteGasite,
                                                keywords);
                                node.set("results", printTickets.get("results"));
                            } else if (searchType.equals("DEVELOPER")) {
                                ArrayNode arrayNode = mapper.createArrayNode();
                                // System.out.println(user.getUsername() + " " + command);
                                List<Developer> developeriGasiti =
                                        developersSearch.searchDevelopers(user, filters, useri);
                                if (!developeriGasiti.isEmpty()) {
                                ObjectNode printDevs = veziTichete.printFoundDevs(developeriGasiti);
                                    node.set("results", printDevs.get("results"));
                                } else {
                                    node.set("results", arrayNode);
                                }
                            }
                            // System.out.println("printeaza developersi/tichete");
                        }
                        outputs.add(node);
                    } else if (command.equals("viewNotifications")) {
                        ObjectNode node =  printwhatiNeed(command, username, timestamp);
                        Developer dev = (Developer) user;
                        ArrayNode printNotif = mapper.createArrayNode();
                        for (int k = 0; k < dev.getNotifications().size(); k++) {
                            Notifications notification =  dev.getNotifications().get(k);
                            if (!notification.isSeen()) {
                                notification.setSeen(true);
                                printNotif.add(notification.getNotification());
                            }
                        }
                        node.set("notifications", printNotif);
                        outputs.add(node);
                    } else if (command.equals("generateCustomerImpactReport")) {
                        ObjectNode node =  printwhatiNeed(command, username, timestamp);
                        veziTichete.setImpactandOthers();
                        ObjectNode printImpact = veziTichete.generateCustomerImpact();
                        node.set("report", printImpact);
                        outputs.add(node);
                    } else if (command.equals("generateTicketRiskReport")) {
                        ObjectNode node =  printwhatiNeed(command, username, timestamp);
                        veziTichete.setImpactandOthers();
                        ObjectNode printImpact = veziTichete.generateTicketsRisk();
                        node.set("report", printImpact);
                        outputs.add(node);
                    } else if (command.equals("generateResolutionEfficiencyReport")) {
                        ObjectNode node =  printwhatiNeed(command, username, timestamp);
                        ObjectNode printEfficiency = veziTichete.generateEfficiency();
                        node.set("report", printEfficiency);
                        outputs.add(node);
                    } else if (command.equals("appStabilityReport")) {
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        veziTichete.setImpactandOthers();
                        ObjectNode printStability = veziTichete.generateImpactandTicketsRisk();
                        node.set("report", printStability);
                        outputs.add(node);
                    } else if (command.equals("generatePerformanceReport")) {
                        ObjectNode node = printwhatiNeed(command, username, timestamp);
                        Manager manager = (Manager) user;
                        ArrayList<PerformanceReport> rep =
                                veziTichete.calculatePerformance(useri, manager);
                        ObjectNode printPerf =
                                veziTichete.generatePerformanceReport(rep);
                        node.set("report", printPerf.get("report"));
                        outputs.add(node);
                    } else if (command.equals("startTestingPhase")) {
                        timestampTesting = timestamp;
                    }
                    lastTimestamp = timestamp;
                    for (int k = 0; k < milestones.size(); k++) {
                        int nrBune = 0;
                        int total = 0;
                        Milestone milestone = milestones.get(k);
                        if (!milestone.getIsBlockedBy().isEmpty() && milestone.isBlocking()) {
                            String block = milestone.getIsBlockedBy().get(0);
                            Milestone blockedMilestone = returnMilestone(milestones, block);
                            if (blockedMilestone != null) {
                                if (calculateClosedTickets(blockedMilestone,
                                        inventarTichete) == 1) {
                                    milestone.ticheteClosed(blockedMilestone, inventarTichete);
                                milestone.setBlocking(false);
                            }
                            }
                        }
                    }
                    for (int p = 0; p < inventarTichete.size(); p++) {
                        Ticket t = inventarTichete.get(p);
                        System.out.println("tichetul cu id ul " + t.getId()
                                + " are prioritatea " + t.getBusinessPriority()
                                + " si status " + t.getStatus());
                    }
                    veziTichete.setInventarTichete(inventarTichete);
                }
                System.out.println("==============SFARSITCOMANDA==============");
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
