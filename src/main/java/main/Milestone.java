package main;

import main.Ticket.Ticket;
import main.User.NextPriority;
import main.User.SpecialMention;
import main.User.TransformCritical;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;

import static java.lang.Math.abs;

public class Milestone {
    private String name;
    private String[] blockingFor;
    private boolean blocking;
    private String dueDate;
    private String createdAt;
    private int[] tickets;
    private String[] assignedDevs;
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
    }

    /**
     * INteractiunile cu tichetele din MILESTONE.
     * STRATEGY METHOD
     * @param date
     */
    public void interactiuniTicket(final String date) {
        System.out.println("suntem la comanda cu timestamp" + date);
        if (nextPriorityStrategy(date)) {
            System.out.println("SE FACE NEXT PRIORITY");
            this.specialMention = new NextPriority();
        } else if (setCriticalStragegy(date)) {
            System.out.println("SE FACE CRITICAL STRAGEGY");
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
        int daysBetween = (int) ChronoUnit.DAYS.between(now, due) + 1;
        if ((daysBetween - 1) % 3 == 0 && daysBetween >= 3 && !this.blocking) {
            return true;
        }
        return false;
    }

    /**
     * Verificare daca trebuie setate tichetele la critical.
     * @param date
     * @return
     */
    private boolean setCriticalStragegy(String date) {
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

}
