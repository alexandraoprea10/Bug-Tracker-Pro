package main.User;

import main.Milestone;
import main.Ticket.Ticket;

import java.time.LocalDate;
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
