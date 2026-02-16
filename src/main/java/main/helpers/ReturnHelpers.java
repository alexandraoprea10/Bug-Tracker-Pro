package main.helpers;

import main.milestones.Milestone;
import main.ticket.Ticket;
import main.user.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ReturnHelpers {
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
}
