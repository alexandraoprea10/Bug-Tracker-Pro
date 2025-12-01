package main.User;

public abstract class Developer extends Users {
    private String date;
    private String expertiseArea;
    private String seniority;
    // constructor
    public Developer(final String username, final String mail, final String role,
                     final String dt, final String expertiseA, final String sen) {
        super(username, mail, role);
        this.date = dt;
        this.expertiseArea = expertiseA;
        this.seniority = sen;
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
    public int prioritateTichet(String priority) {
        if (priority.equals("LOW")) {
            return 1;
        }
        if (priority.equals("MEDIUM")) {
            return 2;
        }
        if (priority.equals("HIGH")) {
            return 3;
        }
        return 4;
    }
    public int tipTicket(String type) {
        if (type.equals("BUG")) {
            return 1;
        }
        if (type.equals("UI_FEEDBACK")) {
            return 2;
        }
        return 3;
    }
    /**
     * Verific daca poate rezolva tichetul.
     * @return
     */
    public boolean eokPrioritatea(String priority) {
        int prioritate = 0;
        if (this.seniority.equals("JUNIOR")) {
            prioritate = 2;
        }
        if (this.seniority.equals("MID")) {
            prioritate = 3;
        }
        if (this.seniority.equals("SENIOR")) {
            prioritate = 4;
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
    public boolean eokTichetul(String type) {
        int prioritate = 0;
        if (seniority.equals("JUNIOR")) {
            prioritate = 2;
        }
        if (seniority.equals("MID")) {
            prioritate = 3;
        }
        if (seniority.equals("SENIOR")) {
            prioritate = 3;
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
                                   final String expertiseAr, final String priority, final String type) {
        if (eokPrioritatea(priority) && eokTichetul(type)
                && eokSpecializarea(expertiseAr)) {
            return true;
        }
        return false;
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
}
