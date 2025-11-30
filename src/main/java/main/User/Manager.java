package main.User;

import java.util.List;

public class Manager extends Users {
    private String hireDate;
    private List<String> subordinates;
    // constructor
    public Manager(final String username, final String mail, final String role,
                   final String hireD, final List<String> subord) {
        super(username, mail, role);
        this.hireDate = hireD;
        this.subordinates = subord;
    }
    // getteri

    /**
     * Returneaza ziua angajarii
     * @return ziua angajarii
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * Reyurneaza subordonatii
     * @return subodrdonatii
     */
    public List<String> getSubordinates() {
        return subordinates;
    }
    // setteri

    /**
     * Seteaza ziua de angajare
     * @param hireDate ziua de angajare
     */
    public void setHireDate(final String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Subordonatii
     * @param subordinates subordonatii
     */
    public void setSubordinates(final List<String> subordinates) {
        this.subordinates = subordinates;
    }
}
