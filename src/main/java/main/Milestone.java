package main;

import main.Ticket.Ticket;
import main.User.Developer;
import main.User.NextPriority;
import main.User.SpecialMention;
import main.User.TransformCritical;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.Math.abs;
import static main.App.returnTicket;

public class Milestone {
    private String name;
    private String[] blockingFor;
    private ArrayList<String> isBlockedBy;
    private int last3days;
    private boolean blocking;
    private String dueDate;
    private String createdAt;
    private int[] tickets;
    private String[] assignedDevs;
    private ArrayList<Developer> assigneddevelopers;
    private String createdBy;
    private int daysUntilDue;
    private int overdueBy;
    private int[] openTickets;
    private int[] closedTickets;
    private double completionPercentage;
    private LinkedHashMap<String, Vector<Integer>> repartition;
    private SpecialMention specialMention;
    private String status;
    private ArrayList<Ticket> inventarTichete;
    private boolean inactivity;
    private String lastTimestampOfTicket;

    private ArrayList<Developer> observatoriNotificari = new ArrayList<>();
    // constructor
    public Milestone(final String name, final String[] blockingFor,
                     final String dueDate, final int[] tickets,
                     final String[] assignedDevs, final String createdBy,
                     final String createdAt, final ArrayList<Ticket> inventarTichete) {
        this.name = name;
        this.blockingFor = blockingFor;
        this.dueDate = dueDate;
        this.tickets = tickets;
        this.assignedDevs = assignedDevs;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.openTickets = tickets;
        this.closedTickets = new int[0];
        this.completionPercentage = 0.0;
        this.status = "ACTIVE";
        this.repartition = new LinkedHashMap<>();
        this.specialMention = new NextPriority();
        this.inventarTichete = inventarTichete;
        LocalDate acum = LocalDate.parse(createdAt);
        LocalDate due = LocalDate.parse(dueDate);
        int daysBetween = (int) ChronoUnit.DAYS.between(acum, due) + 1;
        if (daysBetween < 0) {
            this.daysUntilDue = 0;
            this.overdueBy = -daysBetween;
        } else {
            this.daysUntilDue = daysBetween;
            this.overdueBy = 0;
        }
        for (int i = 0; i < assignedDevs.length; i++) {
            this.repartition.put(assignedDevs[i], new Vector<>());
        }
        this.assigneddevelopers = new ArrayList();
        this.isBlockedBy = new ArrayList<>();
        this.last3days = 0;
        this.inactivity = false;
        this.lastTimestampOfTicket = createdAt;
    }

    /**
     * OBSERVER PATTERN- TRimite notificari persoanelor din milestone-ul corespunzator.
     * @param message
     */
    public void notificareDevelopers(final String message) {
        for (int i = 0; i < this.observatoriNotificari.size(); i++) {
            // System.out.println("PRIMESTE ACEASTA NOTIFICARE"
            // + this.observatoriNotificari.get(i).getUsername());
            Developer dev = this.observatoriNotificari.get(i);
            dev.primesteNotificare(message);
        }
    }

    /**
     * Notificare ca s-a creat milestone-ul.
     */
    public void milestoneCreat() {
        String message = String.format("New milestone %s has been "
                + "created with due date %s.", name, dueDate);
        notificareDevelopers(message);
    }

    /**
     * Notificare ca maine e deadline-ul pentru milestone.
     */
    public void vineDueDate() {
        String message = String.format("Milestone %s is due tomorrow. "
                + "All unresolved tickets are now CRITICAL.", name);
        notificareDevelopers(message);
    }

    /**
     * Notofiicare ca a expirat deadline-ul pentru milestone.
     */
    public void aTrecutDue() {
        String message = String.format("Milestone %s was unblocked after due date. "
                + "All active tickets are now CRITICAL.", name);
        this.setBlocking(false);
        notificareDevelopers(message);
    }

    /**
     * Returneaza care e ultimul tichet setat CLOSE.
     * @param m
     * @param inventarTichet
     * @return
     */
    public int ultimulTichetAsignat(final Milestone m,
                                    final ArrayList<Ticket> inventarTichet) {
        int[] ids = m.getTickets();
        ArrayList<Ticket> ticks = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            Ticket tick = returnTicket(inventarTichet, id);
            ticks.add(tick);
        }
        Collections.sort(ticks, new Comparator<Ticket>() {
            @Override
            public int compare(final Ticket o1, final Ticket o2) {
               int comparSolved = o2.getUltimulTimestampCR().compareTo(o1.getUltimulTimestampCR());
               return comparSolved;
            }
        });
        this.lastTimestampOfTicket = ticks.get(0).getUltimulTimestampCR();
        return ticks.get(0).getId();
    }

    /**
     * Notificare daca se inchide si ultimul tichet.
     * @param m
     * @param inventarTiche
     */
    public void ticheteClosed(final Milestone m,
                              final ArrayList<Ticket> inventarTiche) {
        int ultimulTichet = ultimulTichetAsignat(m, inventarTiche);
        String message = String.format("Milestone %s is now unblocked "
                + "as ticket %d has been CLOSED.", name, ultimulTichet);
        notificareDevelopers(message);
    }
    /**
     * INteractiunile cu tichetele din MILESTONE.
     * STRATEGY METHOD
     * @param date
     */
    public void interactiuniTicket(final String date) {
        // System.out.println("suntem la comanda cu timestamp" + date);
        if (nextPriorityStrategy(date)) {
            // System.out.println("SE FACE NEXT PRIORITY");
            this.specialMention = new NextPriority();
        } else if (setCriticalStragegy(date)) {
            // System.out.println("SE FACE CRITICAL STRAGEGY");
            this.specialMention = new TransformCritical();
        }
        specialMention.interactiuniTichete(this, date, inventarTichete);
    }

    /**
     * Verificare daca trebuie crescuta prioritatea.
     * @param date
     * @return
     */
    private boolean nextPriorityStrategy(final String date) {
        LocalDate now = LocalDate.parse(date);
        LocalDate due = LocalDate.parse(dueDate);
        LocalDate createdAt = LocalDate.parse(getCreatedAt());
        int daysBetween = (int) ChronoUnit.DAYS.between(now, due) + 1;
        int db = (int) ChronoUnit.DAYS.between(createdAt, now) + 1;
        int before = this.getLast3days();
        // this.setLast3days(db / 3);
        if (before != this.getLast3days()
                && !this.blocking) {
            return true;
        }
        return false;
    }

    /**
     * Verificare daca trebuie setate tichetele la critical.
     * @param date
     * @return
     */
    private boolean setCriticalStragegy(final String date) {
        LocalDate now = LocalDate.parse(date);
        LocalDate due = LocalDate.parse(dueDate);
        int daysBetween = (int) ChronoUnit.DAYS.between(now, due) + 1;
        // System.out.println(daysBetween);
        if (abs(daysBetween) == 2) {
            return true;
        }
        return false;
    }
    // getteri

    /**
     * REturneaza numele
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * REturneaza daca e blocat
     * @return
     */
    public boolean isBlocking() {
        return blocking;
    }

    /**
     * REturneaza mileston-urile blocate
     * @return
     */
    public String[] getBlockingFor() {
        return blockingFor;
    }

    /**
     * REturneaza due date-ul
     * @return
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * REturneaza tihchetele
     * @return
     */
    public int[] getTickets() {
        return tickets;
    }

    /**
     * REturneaza statusul
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * REturneaza developerii asignati
     * @return
     */
    public String[] getAssignedDevs() {
        return assignedDevs;
    }

    /**
     * Returneaza de cine a fost creat
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * REturneaza unde afost creat
     * @return
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Returneaza tichetele deschise
     * @return
     */
    public int[] getOpenTickets() {
        return openTickets;
    }

    /**
     * Returneaza tichetele inchise
     * @return
     */
    public int[] getClosedTickets() {
        return closedTickets;
    }

    /**
     * Returneaza procentajul de rezolvare
     * @return
     */
    public double getCompletionPercentage() {
        return completionPercentage;
    }

    /**
     * Returneaza repartizarea
     * @return
     */
    public LinkedHashMap<String, Vector<Integer>> getRepartition() {
        return repartition;
    }

    /**
     * Returneaza nr zile pana la due
     * @return
     */
    public int  getDaysUntilDue() {
        return daysUntilDue;
    }

    /**
     * Returneaza overdue
     * @return
     */
    public int getOverdueBy() {
        return overdueBy;
    }

    /**
     * Returneaza ultimele 3 zile
     * @return
     */
    public int getLast3days() {
        return last3days;
    }

    /**
     * Returneaza lista de assigned developers.
     * @return
     */
    public ArrayList<Developer> getAssignedDevelopers() {
        return assigneddevelopers;
    }

    /**
     * Ultima data cand a fost asignat un tichet ca si "CLOSED".
     * @return
     */
    public String getLastTimestampOfTicket() {
        return lastTimestampOfTicket;
    }
    // setteri

    /**
     * Seteaza numele
     * @param name
     */
    public  void setName(final String name) {
        this.name = name;
    }

    /**
     * Seteaza daca e blocat
     * @param blocking
     */
    public  void setBlocking(final boolean blocking) {
        this.blocking = blocking;
    }

    /**
     * Returneaza lista de blocanti.
     * @return
     */
    public ArrayList<String> getIsBlockedBy() {
        return isBlockedBy;
    }

    /**
     * Returneaza inactivitatea.
     * @return
     */
    public boolean getInactivity() {
        return inactivity;
    }

    /**
     * Seteaza milestonurile blocate
     * @param blockingFor
     */
    public void setBlockingFor(final String[] blockingFor) {
        this.blockingFor = blockingFor;
    }

    /**
     * Seteaza due date.
     * @param dueDate
     */
    public void setDueDate(final String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Seteaza tichetele
     * @param tickets
     */
    public void setTickets(final int[] tickets) {
        this.tickets = tickets;
    }

    /**
     * Seteaza developerii asignati
     * @param assignedDevs
     */
    public void setAssignedDevs(final String[] assignedDevs) {
        this.assignedDevs = assignedDevs;
    }

    /**
     * Seteaza de cine a fost creat
     * @param createdBy
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Seteaza cand a fost creat
     * @param createdAt
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Seteaza tichetele deschise
     * @param openTickets
     */
    public void setOpenTickets(final int[] openTickets) {
        this.openTickets = openTickets;
    }

    /**
     * Seteaza tichetele inchise
     * @param closedTickets
     */
    public void setClosedTickets(final int[] closedTickets) {
        this.closedTickets = closedTickets;
    }

    /**
     * Seteaza completion percentage
     * @param completionPercentage
     */
    public void setCompletionPercentage(final double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    /**
     * Seteaza repartizarea.
     * @param repartition
     */
    public void setRepartition(final LinkedHashMap<String, Vector<Integer>> repartition) {
        this.repartition = repartition;
    }

    /**
     * Seteaza statusul
     * @param status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Seteaza cate zile pana la due.
     * @param daysUntilDue
     */
    public void setDaysUntilDue(final int daysUntilDue) {
        this.daysUntilDue = daysUntilDue;
    }

    /**
     * Seteaza cu cat e overduit milestone-ul.
     * @param overdueBy
     */
    public void setOverdueBy(final int overdueBy) {
        this.overdueBy = overdueBy;
    }

    /**
     * Seteaza lista de developeri asignati
     * @param assigneddevelopers
     */
    public void setAssigneddevelopers(final ArrayList<Developer> assigneddevelopers) {
        this.assigneddevelopers = assigneddevelopers;
    }

    /**
     * Lista cu toate milestone-urile blocante pentru milestone-ul curent.
     * @param isBlockedBy
     */
    public void setIsBlockedBy(final ArrayList<String> isBlockedBy) {
        this.isBlockedBy = isBlockedBy;
    }

    /**
     * Ultima data cand a fost asignat un tichet ca si "CLOSED".
     * @param lastTimestampOfTicket
     */
    public void setLastTimestampOfTicket(String lastTimestampOfTicket) {
        this.lastTimestampOfTicket = lastTimestampOfTicket;
    }

    /**
     * Adauga blockerii.
     * @param blocker
     */
    public void addBlockers(final String blocker) {
        this.isBlockedBy.add(blocker);
    }

    /**
     * Adauga un developer in lista de cei care vor fi notificati.
     * @param developer
     */
    public void addAssignedDeveloper(final Developer developer) {
        this.assigneddevelopers.add(developer);
    }

    /**
     * Seteaza lista de observatori care vor primi notificarea(Pentru Observer Pattern).
     * @param devs
     */
    public void setObservatoriNotificari(final ArrayList<Developer> devs) {
        this.observatoriNotificari = devs;
    }

    /**
     * Seteaza ultimele 3 zile
     * @param last3days
     */
    public void setLast3days(final int last3days) {
        this.last3days = last3days;
    }

    /**
     * Seteaza daca milestone-ul e inactiv.
     * @param inactivity
     */
    public void setInactivity(final boolean inactivity) {
        this.inactivity = inactivity;
    }
}
