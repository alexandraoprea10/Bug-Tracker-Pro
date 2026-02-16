package main.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;
import main.milestones.Milestone;
import main.ticket.Ticket;
import main.user.developerTypes.Developer;
import main.user.developerTypes.DeveloperFactory;
import main.user.Manager;
import main.user.Reporter;
import main.user.Users;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static main.helpers.ReturnHelpers.*;

public class HelperMethods {

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
     * Creeaza database ul pentru useri.
     * @param usersNode
     * @param useri
     */
    public static void createUsers(final JsonNode usersNode,
                                   final ArrayList<Users> useri) {
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
    }
}
