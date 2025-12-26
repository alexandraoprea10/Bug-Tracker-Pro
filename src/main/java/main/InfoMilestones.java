package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Ticket.Ticket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static main.App.returnTicket;

public class InfoMilestones {
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Getter pentru objectMapper
     * @return
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * Zile pana la deadline
     * @param now
     * @param due
     * @return
     */
    public int daystillDeadline(final String now, final String due) {
        LocalDate n = LocalDate.parse(now);
        LocalDate d = LocalDate.parse(due);
        int daysBetween = (int) ChronoUnit.DAYS.between(d, n) + 1;
       return daysBetween;
    }

    /**
     * Returnez id-ul ultimului tichet asignat CLOSED.
     * @param m
     * @param inventarTichete
     * @return
     */
    public int ultimulTichetAsignat(final Milestone m, final ArrayList<Ticket> inventarTichete) {
        int[] ids = m.getTickets();
        ArrayList<Ticket> ticks = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            Ticket tick = returnTicket(inventarTichete, id);
            ticks.add(tick);
        }
        Collections.sort(ticks, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
                int comparSolved = o2.getSolvedAt().compareTo(o1.getSolvedAt());
                return comparSolved;
            }
        });
        return ticks.get(0).getId();
    }
    /**
     * Printeaza milestonurile vizibile pentru manageri.
     * @param milestones
     * @param timestamp
     * @param username
     * @return
     */
    public ObjectNode viewMilestonesManager(final ArrayList<Milestone> milestones,
                                            final String timestamp, final String username,
                                            final ArrayList<Ticket> inventarTichete) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(milestones, new Comparator<Milestone>() {
            @Override
            public int compare(final Milestone o1, final Milestone o2) {
                int dueDate = o1.getDueDate().compareTo(o2.getDueDate());
                if (dueDate != 0) {
                    return dueDate;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (int p = 0; p < milestones.size(); p++) {
            ObjectNode node = mapper.createObjectNode();
            Milestone milestone = milestones.get(p);
            if (milestone.getCreatedBy().equals(username)) {
                node.put("name", milestone.getName());
                ArrayNode printBlock = mapper.createArrayNode();
                String[] block = milestone.getBlockingFor();
                for (int i = 0; i < block.length; i++) {
                    printBlock.add(block[i]);
                }
                node.set("blockingFor", printBlock);
                node.put("dueDate", milestone.getDueDate());
                node.put("createdAt", milestone.getCreatedAt());
                ArrayNode printTickets = mapper.createArrayNode();
                int[] tichete = milestone.getTickets();
                Arrays.sort(tichete);
                for (int i = 0; i < tichete.length; i++) {
                    printTickets.add(tichete[i]);
                }
                node.set("tickets", printTickets);
                ArrayNode printDevs = mapper.createArrayNode();
                String[] devs = milestone.getAssignedDevs();
                for (int i = 0; i < devs.length; i++) {
                    printDevs.add(devs[i]);
                }
                node.set("assignedDevs", printDevs);
                node.put("createdBy", milestone.getCreatedBy());
                if (milestone.getCompletionPercentage() == 1.0) {
                    milestone.setStatus("COMPLETED");
                }
                node.put("status", milestone.getStatus());
                node.put("isBlocked", milestone.isBlocking());
                System.out.println(milestone.getDueDate());
                System.out.println(timestamp);
                int days = daystillDeadline(milestone.getDueDate(), timestamp);
//                System.out.println("sunt " + days + "zile ");
//                System.out.println("milestone cu numele " + milestone.getName()
//                        + " are inactivitate " + milestone.getInactivity());
                    int ticketID = ultimulTichetAsignat(milestone, inventarTichete);
                    Ticket t = returnTicket(inventarTichete, ticketID);
                        if (days < 0) {
                            if (milestone.getInactivity()) {
//                                int db = daystillDeadline(t.getUltimulTimestampCR(),
//                                        milestone.getDueDate());
                                int db = daystillDeadline(t.getUltimulTimestampCR(),
                                        milestone.getDueDate());
                                if (db < 0) {
                                    db = daystillDeadline(milestone.getDueDate(),
                                            t.getUltimulTimestampCR());
                                    milestone.setOverdueBy(0);
                                    milestone.setDaysUntilDue(db);
                                } else {
                                    milestone.setOverdueBy(db);
                                    milestone.setDaysUntilDue(0);
                                }
                            } else {
                                int db = daystillDeadline(timestamp, milestone.getDueDate());
                                milestone.setOverdueBy(db);
                                milestone.setDaysUntilDue(0);
                            }
                        } else if (days > 0) {
                            if (milestone.getInactivity()) {
                                int db = daystillDeadline(t.getUltimulTimestampCR(),
                                        milestone.getDueDate());
                                milestone.setOverdueBy(0);
                                milestone.setDaysUntilDue(db);
                            } else {
                                int db = daystillDeadline(timestamp, milestone.getDueDate());
                                milestone.setOverdueBy(0);
                                milestone.setDaysUntilDue(days);
                            }
                        } else if (days == 0) {
                            int db = daystillDeadline(timestamp, milestone.getDueDate());
                            if (db != 0) {
                                milestone.setOverdueBy(db);
                                milestone.setDaysUntilDue(0);
                            }
                        }
                node.put("daysUntilDue", milestone.getDaysUntilDue());
                node.put("overdueBy", milestone.getOverdueBy());
                ArrayNode printOpenTickets = mapper.createArrayNode();
                int[] openTichete = milestone.getOpenTickets();
                Arrays.sort(openTichete);
                for (int i = 0; i < openTichete.length; i++) {
                    printOpenTickets.add(openTichete[i]);
                }
                node.set("openTickets", printOpenTickets);
                ArrayNode printClosedTickets = mapper.createArrayNode();
                int[] closedTichete = milestone.getClosedTickets();
                Arrays.sort(closedTichete);
                for (int i = 0; i < closedTichete.length; i++) {
                    printClosedTickets.add(closedTichete[i]);
                }
                node.set("closedTickets", printClosedTickets);
                node.put("completionPercentage", milestone.getCompletionPercentage());
                LinkedHashMap<String, Vector<Integer>> repartition = milestone.getRepartition();
                ArrayNode printRepartition = mapper.createArrayNode();
                String[] numeDeveloperi = repartition.keySet().toArray(new String[0]);
                Arrays.sort(numeDeveloperi, new Comparator<String>() {
                    @Override
                    public int compare(final String o1, final String o2) {
                        Vector<Integer> list1 = repartition.get(o1);
                        Vector<Integer> list2 = repartition.get(o2);
                        return Integer.compare(list1.size(), list2.size());
                    }
                });
                for (int i = 0; i < numeDeveloperi.length; i++) {
                    String dev = numeDeveloperi[i];
                    Vector<Integer> tich = repartition.get(dev);
                    Collections.sort(tich);
                    ObjectNode printDev = mapper.createObjectNode();
                    printDev.put("developer", dev);
                    ArrayNode setTichete = mapper.createArrayNode();
                    for (int j = 0; j < tich.size(); j++) {
                        setTichete.add(tich.get(j));
                    }
                    printDev.set("assignedTickets", setTichete);
                    printRepartition.add(printDev);
                }
                node.set("repartition", printRepartition);
                arrayNode.add(node);
            }
        }
            finalNode.set("milestones", arrayNode);
            return finalNode;
        }

    /**
     * Printeaza milestonurile vizibile pentru developeri
     * @param milestones
     * @param timestamp
     * @param username
     * @return
     */
    public ObjectNode viewMilestonesDeveloper(final ArrayList<Milestone> milestones,
                                              final String timestamp, final String username) {
        ObjectNode finalNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        Collections.sort(milestones, new Comparator<Milestone>() {
            @Override
            public int compare(final Milestone o1, final Milestone o2) {
                int dueDate = o1.getDueDate().compareTo(o2.getDueDate());
                if (dueDate != 0) {
                    return dueDate;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (int k = 0; k < milestones.size(); k++) {
            int ok = 0;
            ObjectNode node = mapper.createObjectNode();
            Milestone milestone = milestones.get(k);
            String[] devel = milestone.getAssignedDevs();
            for (int i = 0; i < devel.length; i++) {
                // System.out.println(devel[i]);
                if (devel[i].equals(username)) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 1) {
                node.put("name", milestone.getName());
                ArrayNode printBlock = mapper.createArrayNode();
                String[] block = milestone.getBlockingFor();
                for (int i = 0; i < block.length; i++) {
                    printBlock.add(block[i]);
                }
                node.set("blockingFor", printBlock);
                node.put("dueDate", milestone.getDueDate());
                node.put("createdAt", milestone.getCreatedAt());
                ArrayNode printTickets = mapper.createArrayNode();
                int[] tichete = milestone.getTickets();
                Arrays.sort(tichete);
                for (int i = 0; i < tichete.length; i++) {
                    printTickets.add(tichete[i]);
                }
                node.set("tickets", printTickets);
                ArrayNode printDevs = mapper.createArrayNode();
                String[] devs = milestone.getAssignedDevs();
                for (int i = 0; i < devs.length; i++) {
                    printDevs.add(devs[i]);
                }
                node.set("assignedDevs", printDevs);
                node.put("createdBy", milestone.getCreatedBy());
                node.put("status", milestone.getStatus());
                node.put("isBlocked", milestone.isBlocking());
                int days = daystillDeadline(milestone.getDueDate(), timestamp);
                if (days < 0) {
                    int db = daystillDeadline(timestamp, milestone.getDueDate());
                    milestone.setOverdueBy(db);
                    milestone.setDaysUntilDue(0);
                } else {
                    milestone.setOverdueBy(0);
                    milestone.setDaysUntilDue(days);
                }
                node.put("daysUntilDue", milestone.getDaysUntilDue());
                node.put("overdueBy", milestone.getOverdueBy());
                ArrayNode printOpenTickets = mapper.createArrayNode();
                int[] openTichete = milestone.getOpenTickets();
                Arrays.sort(openTichete);
                for (int i = 0; i < openTichete.length; i++) {
                    printOpenTickets.add(openTichete[i]);
                }
                node.set("openTickets", printOpenTickets);
                ArrayNode printClosedTickets = mapper.createArrayNode();
                int[] closedTichete = milestone.getClosedTickets();
                Arrays.sort(closedTichete);
                for (int i = 0; i < closedTichete.length; i++) {
                    printClosedTickets.add(closedTichete[i]);
                }
                node.set("closedTickets", printClosedTickets);
                node.put("completionPercentage", milestone.getCompletionPercentage());
                LinkedHashMap<String, Vector<Integer>> repartition = milestone.getRepartition();
                ArrayNode printRepartition = mapper.createArrayNode();
                String[] numeDeveloperi = repartition.keySet().toArray(new String[0]);
                for (int i = 0; i < numeDeveloperi.length; i++) {
                    String dev = numeDeveloperi[i];
                    Vector<Integer> tich = repartition.get(dev);
                    ObjectNode printDev = mapper.createObjectNode();
                    printDev.put("developer", dev);
                    ArrayNode setTichete = mapper.createArrayNode();
                    for (int j = 0; j < tich.size(); j++) {
                        setTichete.add(tich.get(j));
                    }
                    printDev.set("assignedTickets", setTichete);
                    printRepartition.add(printDev);
                }
                node.set("repartition", printRepartition);
                arrayNode.add(node);
            }
        }
        finalNode.set("milestones", arrayNode);
        return finalNode;
    }
}
