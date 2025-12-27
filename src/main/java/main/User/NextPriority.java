package main.User;

import main.MagicNumbersInt;
import main.Milestone;
import main.Ticket.Ticket;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class NextPriority implements SpecialMention {
    /**
     * Creste prioritatea dupa 3 zile.
     * @param milestone
     * @param date
     * @param inventarTichete
     */
    @Override
    public void interactiuniTichete(final Milestone milestone,
                                    final String date,
                                    final ArrayList<Ticket> inventarTichete) {
        LocalDate dataMilestone = LocalDate.parse(milestone.getLastTimestampOfTicket());
        LocalDate data = LocalDate.parse(date);
        int daysBetween = (int) ChronoUnit.DAYS.between(dataMilestone, data);
        if (milestone.getLast3days() != (daysBetween) / MagicNumbersInt.trei.getValue()
                && daysBetween > 0
                && !milestone.isBlocking()) {
            System.out.println(daysBetween + " zile intre");
            System.out.println(milestone.getLast3days());
            System.out.println("Intra aici sa schimbe prioritatea");
            int[] idTick = milestone.getTickets();
            for (int i = 0; i < idTick.length; i++) {
                for (int j = 0; j < inventarTichete.size(); j++) {
                    Ticket t = inventarTichete.get(j);
                    if (t.getId() == idTick[i] && !t.getStatus().equals("CLOSED")) {
                        System.out.println("SCHIMBA PRIORITATEA TICHETULUI " + t.getId());
                        if (t.getBusinessPriority().equals("LOW")) {
                            t.setBusinessPriority("MEDIUM");
                        } else if (t.getBusinessPriority().equals("MEDIUM")) {
                            t.setBusinessPriority("HIGH");
                        } else if (t.getBusinessPriority().equals("HIGH")) {
                            t.setBusinessPriority("CRITICAL");
                        }
                    }
                }
            }
            milestone.setLast3days(daysBetween
                    / MagicNumbersInt.trei.getValue());
        }
    }
}
