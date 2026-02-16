package main.user.developerTypes;

import main.magicNumbers.MagicNumbersInt;
import main.Notifications;
import main.user.Users;

import java.util.ArrayList;

public abstract class Developer extends Users {
    private String date;
    private String expertiseArea;
    private String seniority;
    private double performanceScore;
    private ArrayList<Notifications> notifications;
    // constructor
    public Developer(final String username, final String mail, final String role,
                     final String dt, final String expertiseA, final String sen) {
        super(username, mail, role);
        this.date = dt;
        this.expertiseArea = expertiseA;
        this.seniority = sen;
        this.performanceScore = 0.0;
        this.notifications = new ArrayList<>();
    }
    // FACTORY METHOD
    // CODURI:
    // PENTRU JUNIOR - COD 2(POATE LOW/MEDIUM SI BUG + UI)
    // PENTRU MID - COD 3(POATE LOW/MEDIUM/HIGHT SI BUG + UI + FR)
    // PENTRU SENIOR - COD 4 SI 3(POATE LOW/MED/HIGH/CRITICAL SI BUG+ UI+ FR)

    /**
     * Verificam accesul.
     * @return
     */
    public abstract int getAccessPriority();

    /**
     * Verificam tipul de tichet.
     * @return
     */
    public abstract int getTicketType();
    /**
     * Verific prioritatea tichetului
     * @param priority
     * @return
     */
    public int prioritateTichet(final String priority) {
        if (priority.equals("LOW")) {
            return 1;
        }
        if (priority.equals("MEDIUM")) {
            return MagicNumbersInt.doi.getValue();
        }
        if (priority.equals("HIGH")) {
            return MagicNumbersInt.trei.getValue();
        }
        return MagicNumbersInt.patru.getValue();
    }

    /**
     * Returneaza codul tichetului
     * @param type
     * @return
     */
    public int tipTicket(final String type) {
        if (type.equals("BUG")) {
            return 1;
        }
        if (type.equals("UI_FEEDBACK")) {
            return MagicNumbersInt.doi.getValue();
        }
        return MagicNumbersInt.trei.getValue();
    }
    /**
     * Verific daca poate rezolva tichetul.
     * @return
     */
    public boolean eokPrioritatea(final String priority) {
        int prioritate = 0;
        if (this.seniority.equals("JUNIOR")) {
            prioritate = MagicNumbersInt.doi.getValue();
        }
        if (this.seniority.equals("MID")) {
            prioritate = MagicNumbersInt.trei.getValue();
        }
        if (this.seniority.equals("SENIOR")) {
            prioritate = MagicNumbersInt.patru.getValue();
        }
        // System.out.println(this.getUsername() + "are prioritatea cu codu" + prioritate);
        if (prioritate >= prioritateTichet(priority)) {
            return true;
        }
        return false;
    }

    /**
     * Verific daca poate rezolva tichetul.
     * @return
     */
    public boolean eokTichetul(final String type) {
        int prioritate = 0;
        if (seniority.equals("JUNIOR")) {
            prioritate = MagicNumbersInt.doi.getValue();
        }
        if (seniority.equals("MID")) {
            prioritate = MagicNumbersInt.trei.getValue();
        }
        if (seniority.equals("SENIOR")) {
            prioritate = MagicNumbersInt.trei.getValue();
        }
//        System.out.println("tichetul are codul " + tipTicket(type));
//        System.out.println("ce prioritate are tichetul" + prioritate);
        if (prioritate >= getTicketType()) {
            return true;
        }
        return false;
    }

    /**
     * Verific daca poate rezolva tichetul.
     * @param expertiseAr
     * @return
     */
    public boolean eokSpecializarea(final String expertiseAr) {
        if (this.expertiseArea.equals("FRONTEND")) {
            if (expertiseAr.equals("FRONTEND") || expertiseAr.equals("DESIGN")) {
                return true;
            }
        }
        if (this.expertiseArea.equals("BACKEND")) {
            if (expertiseAr.equals("BACKEND") || expertiseAr.equals("DB")) {
                return true;
            }
        }
        if (this.expertiseArea.equals("FULLSTACK")) {
            if (expertiseAr.equals("FRONTEND") || expertiseAr.equals("DESIGN")
                    || expertiseAr.equals("DEVOPS") || expertiseAr.equals("BACKEND")
                    || expertiseAr.equals("DB")) {
                return true;
            }
        }
        if (this.expertiseArea.equals("DEVOPS")) {
            if (expertiseAr.equals("DEVOPS")) {
                return true;
            }
        }
        if (this.expertiseArea.equals("DESIGN")) {
            if (expertiseAr.equals("DESIGN") || expertiseAr.equals("FRONTEND")) {
                return true;
            }
        }
        if (this.expertiseArea.equals("DB")) {
            if (expertiseAr.equals("DB")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vedem daca se poate rezolva tichetul.
     * @param sen
     * @param expertiseAr
     * @return
     */
    public boolean rezolvaTichetul(final String sen,
                                   final String expertiseAr,
                                   final String priority, final String type) {
        if (eokPrioritatea(priority) && eokTichetul(type)
                && eokSpecializarea(expertiseAr)) {
            return true;
        }
        return false;
    }

    /**
     * Primeste notificarea si o adauga in lista de notoficari(daca nu exista deja).
     * @param message
     */
    public void primesteNotificare(final String message) {
        int ok = 0;
        for (int i = 0; i < notifications.size(); i++) {
            Notifications notif =  notifications.get(i);
            if (notif.getNotification().equals(message)) {
                ok = 1;
            }
        }
        if (ok == 0) {
            Notifications notif = new Notifications(message);
            this.notifications.add(notif);
        }
    }
    // getteri

    /**
     * Ziua
     * @return ziua
     */
    public String getDate() {
        return date;
    }

    /**
     * Zona de expertiza
     * @return zona de expertiza
     */
    public String getExpertiseArea() {
        return expertiseArea;
    }

    /**
     * Specializarea
     * @return specializare
     */
    public String getSeniority() {
        return seniority;
    }

    /**
     * Returneaza performanta developerului.
     * @return
     */
    public double getPerformanceScore() {
        return performanceScore;
    }

    /**
     * Returneaza lista de notificari.
     * @return
     */
    public ArrayList<Notifications> getNotifications() {
        return notifications;
    }
    // setteri

    /**
     * Seteaza data
     * @param date data
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Seteaza expertiseArea
     * @param expertiseArea
     */
    public void setExpertiseArea(final String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    /**
     * Seteaza specializarea
     * @param seniority specializarea
     */
    public void setSeniority(final String seniority) {
        this.seniority = seniority;
    }

    /**
     * Seteaza performanta developerului.
     * @param performanceScore
     */
    public void setPerformanceScore(final double performanceScore) {
        this.performanceScore = performanceScore;
    }

    /**
     * Seteaza lista de notificari.
     * @param notifications
     */
    public void setNotifications(final ArrayList<Notifications> notifications) {
        this.notifications = notifications;
    }

    /**
     * Adauga notoficare
     * @param notification
     */
    public void addNotification(final Notifications notification) {
        this.notifications.add(notification);
    }
}
