package main.helpers;

import main.milestones.Milestone;
import main.ticket.History;
import main.ticket.Ticket;
import main.user.Users;
import main.user.developerTypes.Developer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import static main.helpers.ReturnHelpers.returnByUserame;
import static main.helpers.ReturnHelpers.returnUser;

public class CheckingHelpers {
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
     * Verific daca reporterul este cel cu numele de username
     * @param ticket
     * @param username
     * @return
     */
    public static int wellReported(final Ticket ticket,
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
    public static int wellAssigned(final List<Users> users,
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
     * Verifica daca toate tichetele din milestone au fost rezolvate.
     * @param milestones
     * @return
     */
    public static int verifyIfAllMilestonesAreCompleted(final
                                                        ArrayList<Milestone> milestones) {
        for (int  i = 0; i < milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            if (milestone.getOpenTickets().length != 0) {
                return 0;
            }

        }
        return 1;
    }
    /**
     * Verfica daca developerii mai pot rezolva tichetul asignat.
     * @param inventarTichete
     * @param useri
     * @param timestamp
     * @param username
     * @param milestones
     */
    public static void checkIfTheDeveloperCanResolveTheTicket(final ArrayList<Ticket>
                                                                      inventarTichete,
                                                              final ArrayList<Users> useri,
                                                              final String timestamp,
                                                              final String username,
                                                              final ArrayList<Milestone>
                                                                      milestones) {
        for (int p = 0; p < inventarTichete.size(); p++) {
            Ticket t = inventarTichete.get(p);
            Users usr2 = returnUser(useri, t.getAssignedTo());
            Developer dev = (Developer) usr2;
            if (dev != null
                    && !dev.rezolvaTichetul(dev.getSeniority(),
                    t.getExpertiseArea(), t.getBusinessPriority(),
                    t.getType())
                    && (t.getStatus().equals("OPEN")
                    || t.getStatus().equals("IN_PROGRESS")
                    || t.getStatus().equals("RESOLVED"))) {
                dev.getTickets().remove(t);
                dev.getGaveupTickets().add(t);
                t.setIsAVailableForAssignment(true);
                t.setStatus("OPEN");
                t.setAssignedTo("");
                t.setAssignedAt("");
                t.setSolvedAt("");
                Milestone milestone = returnByUserame(milestones, dev.getUsername());
                LinkedHashMap<String, Vector<Integer>>
                        repartition = milestone.getRepartition();
                Vector<Integer> ticheteAsignate =
                        repartition.get(dev.getUsername());
                if (ticheteAsignate != null) {
                    ticheteAsignate.remove(Integer.valueOf(t.getId()));
                }
                History history = new History.Builder("REMOVED_FROM_DEV",
                        "system", timestamp)
                        .from(username)
                        .build();
                t.getHistories().add(history);
            }
        }
    }
}
