package main.User.ModifyTickets;

import main.Milestones.Milestone;
import main.Ticket.Ticket;

import java.util.ArrayList;

public interface SpecialMention {
    /**
     * Vad ce interactiuni am de facut cu tichetele din milestone.
     * @param milestone
     * @param date
     * @param inventarTichete
     */
    void interactiuniTichete(Milestone milestone,
                             String date,
                             ArrayList<Ticket> inventarTichete);
}
