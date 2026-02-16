package main.helpers;

import main.milestones.Milestone;
import main.ticket.Ticket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static main.helpers.ReturnHelpers.returnMilestone;
import static main.helpers.ReturnHelpers.returnTicket;

public class WorkingWithMilestones {
    /**
     * Se fac anumite modificari inainte de executarea unei comenzi.
     * Spre exemplu se verifica daca milestone-ul are deadline maine.
     * @param milestones
     * @param inventarTichete
     * @param timestamp
     * @param lastTimestamp
     */
    public static void workingWithMilestonesBeforeCommand(final ArrayList<Milestone> milestones,
                                                          final ArrayList<Ticket> inventarTichete,
                                                          final String timestamp,
                                                          final String lastTimestamp,
                                                          final HelperMethods helpers) {
        for (int p = 0; p < milestones.size(); p++) {
            Milestone milestone =  milestones.get(p);
            int[] tickets = milestone.getTickets();
            if (helpers.calculateClosedTickets(milestone, inventarTichete) == 1) {
                milestone.setInactivity(true);
            }
            if (!timestamp.equals(lastTimestamp)) {
                milestone.interactiuniTicket(timestamp);
            }
            LocalDate currentDate = LocalDate.parse(timestamp);
            LocalDate dateMilestone = LocalDate.parse(milestone.getDueDate());
            int daysBetween = (int) ChronoUnit.DAYS.between(currentDate, dateMilestone) + 1;
            if (daysBetween == 2  && !milestone.isBlocking()) {
                milestone.vineDueDate();
                for (int k = 0; k < milestone.getTickets().length; k++) {
                    int ticketID =  milestone.getTickets()[k];
                    Ticket t = returnTicket(inventarTichete, ticketID);
                    if (t != null && !t.getStatus().equals("CLOSED")) {
                        t.setBusinessPriority("CRITICAL");
                    }
                }
            }
            if (daysBetween < 0 && milestone.isBlocking()) {
                milestone.aTrecutDue();
                for (int k = 0; k < milestone.getTickets().length; k++) {
                    int ticketID =  milestone.getTickets()[k];
                    Ticket t = returnTicket(inventarTichete, ticketID);
                    if (t != null && !t.getStatus().equals("CLOSED")) {
                        t.setBusinessPriority("CRITICAL");
                    }
                }
            }
            for (int j = 0; j < tickets.length; j++) {
                Ticket t = returnTicket(inventarTichete, tickets[j]);
                if (t != null) {
                    helpers.openAndClose(milestone, t);
                }
            }
        }
    }

    /**
     * Se fac anumite modificari inainte de executarea unei comenzi.
     * Spre exemplu se verifica daca milestone-ul se poate debloca
     * Se verifica daca milestone-ul devine complete.
     * @param milestones
     * @param inventarTichete
     */
    public static void workingWithMilestonesAfterCommand(final ArrayList<Milestone> milestones,
                                                         final ArrayList<Ticket> inventarTichete,
                                                         final HelperMethods helpers) {
        for (int k = 0; k < milestones.size(); k++) {
            int nrBune = 0;
            int total = 0;
            Milestone milestone = milestones.get(k);
            if (!milestone.getIsBlockedBy().isEmpty() && milestone.isBlocking()) {
                String block = milestone.getIsBlockedBy().get(0);
                Milestone blockedMilestone = returnMilestone(milestones, block);
                if (blockedMilestone != null) {
                    if (helpers.calculateClosedTickets(blockedMilestone,
                            inventarTichete) == 1) {
                        milestone.ticheteClosed(blockedMilestone, inventarTichete, helpers);
                        milestone.setBlocking(false);
                    }
                }
            }
            if (milestone.getTickets().length == 0
                    || milestone.getCompletionPercentage() == 1.0) {
                milestone.setCompletionPercentage(1.0);
                milestone.setStatus("COMPLETED");
            }
        }
    }
}
