package main;

import com.fasterxml.jackson.databind.JsonNode;
import main.Ticket.Ticket;
import main.User.Developer;
import main.User.Users;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static main.App.returnTicket;
import static main.App.returnUser;

public class TicketSearch {
    /**
     * Cauta tichetele care respecta regulile(din persp developerului).
     * @param milestones
     * @param filters
     * @param username
     * @param inventarTichete
     * @param useri
     * @return
     */
    public List<Ticket> searchTicketsDeveloper(final ArrayList<Milestone> milestones,
                                               final JsonNode filters,
                                               final String username,
                                               final ArrayList<Ticket> inventarTichete,
                                               final List<Users> useri) {
        List<Ticket> tichete =  new LinkedList<>();
        String businessPriority = null;
        String type = null;
        String createdAt = null;
        String createdBefore = null;
        String createdAfter = null;
        boolean availableForAssignment = false;
        if (filters.get("businessPriority") != null) {
            businessPriority = filters.get("businessPriority").asText();
        }
        if (filters.get("type") != null) {
            type = filters.get("type").asText();
        }
        if (filters.get("createdBefore") != null) {
            createdBefore = filters.get("createdBefore").asText();
        }
        if (filters.get("createdAfter") != null) {
            createdAfter = filters.get("createdAfter").asText();
        }
        if (filters.get("availableForAssignment") != null) {
            availableForAssignment = filters.get("availableForAssignment").asBoolean();
        }

        for (int i = 0; i < milestones.size(); i++) {
            Milestone milestone = milestones.get(i);
            String[] assignedDev = milestone.getAssignedDevs();
            for (int j = 0; j < assignedDev.length; j++) {
                if (assignedDev[j].equals(username)) {
                    int[] tickets = milestone.getTickets();
                    for (int k = 0;  k < tickets.length; k++) {
                        Ticket t = returnTicket(inventarTichete, tickets[k]);
                        if (t.getStatus().equals("OPEN")) {
                            if (businessPriority != null
                                    && !t.getBusinessPriority().equals(businessPriority)) {
                                continue;
                            }
                            if (type != null && !t.getType().equals(type)) {
                                continue;
                            }
                            if (createdAt != null && !t.getCreatedAt().equals(createdAt)) {
                                continue;
                            }
                            if (createdBefore != null) {
                                LocalDate created = LocalDate.parse(t.getCreatedAt());
                                LocalDate before = LocalDate.parse(createdBefore);
                                int daysBetween = (int) ChronoUnit.DAYS.between(before, created);
                                if (daysBetween >= 0) {
                                    continue;
                                }
                            }
                            if (createdAfter != null) {
                                LocalDate created = LocalDate.parse(t.getCreatedAt());
                                LocalDate after = LocalDate.parse(createdAfter);
                                int daysBetween = (int) ChronoUnit.DAYS.between(after, created);
                                if (daysBetween <= 0) {
                                    continue;
                                }
                            }
                            if (t.isAvailableForAssignment() != availableForAssignment
                                    && filters.get("availableForAssignment") != null) {
                                continue;
                            }
                            if (t.isAvailableForAssignment() == availableForAssignment
                                    && filters.get("availableForAssignment") != null) {
                                Developer developer = (Developer) returnUser(useri, username);
                                if (!developer.rezolvaTichetul(developer.getSeniority(),
                                        t.getExpertiseArea(),
                                        t.getBusinessPriority(), t.getType())) {
                                    continue;
                                }
                            }
                            tichete.add(t);
                        }
                    }
                    break;
                }
            }
        }
        return tichete;
    }

    /**
     * Cauta tichtele care respecta regula(din persp managerului).
     * @param filters
     * @param inventarTichete
     * @return
     */
    public List<Ticket> searchTicketsManager(final JsonNode filters,
                                             final ArrayList<Ticket> inventarTichete) {
        List<Ticket> tichete =  new LinkedList<>();
        String businessPriority = null;
        String type = null;
        String createdAt = null;
        String createdBefore = null;
        String createdAfter = null;
        boolean availableForAssignment = false;
        String[] keywords = null;
        if (filters.get("businessPriority") != null) {
            businessPriority = filters.get("businessPriority").asText();
        }
        if (filters.get("type") != null) {
            type = filters.get("type").asText();
        }
        if (filters.get("createdBefore") != null) {
            createdBefore = filters.get("createdBefore").asText();
        }
        if (filters.get("createdAfter") != null) {
            createdAfter = filters.get("createdAfter").asText();
        }
        if (filters.get("availableForAssignment") != null) {
            availableForAssignment = filters.get("availableForAssignment").asBoolean();
        }
        if (filters.get("keywords") != null) {
            for (int j = 0; j <  filters.get("keywords").size(); j++) {
                JsonNode keyword = filters.get("keywords").get(j);
                String key = keyword.asText();
                keywords = key.split(" ");
            }
        }
        for (int i = 0; i < inventarTichete.size(); i++) {
            Ticket t = inventarTichete.get(i);
            if (businessPriority != null
                    && !t.getBusinessPriority().equals(businessPriority)) {
                continue;
            }
            if (type != null && !t.getType().equals(type)) {
                continue;
            }
            if (createdAt != null && !t.getCreatedAt().equals(createdAt)) {
                continue;
            }
            if (createdBefore != null) {
                LocalDate created = LocalDate.parse(t.getCreatedAt());
                LocalDate before = LocalDate.parse(createdBefore);
                int daysBetween = (int) ChronoUnit.DAYS.between(before, created);
                if (daysBetween >= 0) {
                    continue;
                }
            }
            if (createdAfter != null) {
                LocalDate created = LocalDate.parse(t.getCreatedAt());
                LocalDate after = LocalDate.parse(createdAfter);
                int daysBetween = (int) ChronoUnit.DAYS.between(after, created);
                if (daysBetween <= 0) {
                    continue;
                }
            }
            if (t.isAvailableForAssignment() != availableForAssignment
                    && filters.get("availableForAssignment") != null) {
                continue;
            }
            if (keywords != null) {
                int ok = 0;
                if (!t.getTitle().contains(keywords[0])) {
                    ok = 1;
                }
                if (t.getDescription() != null && !t.getDescription().contains(keywords[0])) {
                    ok = 1;
                }
                if (ok == 1) {
                    continue;
                }
            }
            tichete.add(t);
        }
        return tichete;
    }
}
