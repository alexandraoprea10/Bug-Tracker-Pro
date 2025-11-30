package main.User;

import main.Ticket.Ticket;

import java.util.ArrayList;

public class Users {
    private String username;
    private String mail;
    private String role;
    private ArrayList<Ticket> tickets;
    // constructori
    public Users(final String username, final String mail, final String role) {
        this.username = username;
        this.mail = mail;
        this.role = role;
        this.tickets = new ArrayList<>();
    }
    // getteri

    /**
     * Returneaza username-ul
     * @return username-ul
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returneaza mail
     * @return mail-ul
     */
    public String getMail() {
        return mail;
    }

    /**
     * Returneaza rolul
     * @return rolul
     */
    public String getRole() {
        return role;
    }

    /**
     * Returneaza tichetul
     * @return tichetul
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    // setteri

    /**
     * Seteaza username-ul
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Seteaza mail-ul
     * @param mail mailul
     */
    public void setMail(final String mail) {
        this.mail = mail;
    }

    /**
     * Seteaza rolul
     * @param role rolul
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * Seteaza intreaga lista de tichete
     * @param tickets tichete
     */
    public void setTickets(final ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * Adauga un tichet in array ul de tichete
     * @param ticket adauga tichet
     */
    public void addTicket(final Ticket ticket) {
        this.tickets.add(ticket);
    }
}
