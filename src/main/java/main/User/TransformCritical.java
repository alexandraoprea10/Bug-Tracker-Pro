package main.User;

import main.Milestone;
import main.Ticket.Ticket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TransformCritical implements SpecialMention {
    /**
     * Transforma tichetele in critical.
     * @param milestone
     * @param date
     * @param inventarTichete
     */
    @Override
    public void interactiuniTichete(final Milestone milestone,
                                    final String date,
                                    final ArrayList<Ticket> inventarTichete) {
        LocalDate dataMilestone = LocalDate.parse(milestone.getDueDate());
        LocalDate data = LocalDate.parse(date);
        int daysBetween = (int) ChronoUnit.DAYS.between(data, dataMilestone) + 1;
        // System.out.println(daysBetween);
        if (daysBetween == 2) {
            // System.out.println("INTRU IN CRITICAL STRATEGY");
            int[] idTicket = milestone.getTickets();
            for (int i = 0; i < idTicket.length; i++) {
            for (int j = 0; j < inventarTichete.size(); j++) {
                Ticket ticket = inventarTichete.get(j);
                if (ticket.getId() == idTicket[i]
                        && !ticket.getStatus().equals("CLOSED")) {
                    System.out.println("INTRU AICI BAAAAA LA TRANSFORM CRTICAL");
                    // System.out.println(ticket.getId());
                    ticket.setBusinessPriority("CRITICAL");
                }
            }
            }
        }
    }
}
