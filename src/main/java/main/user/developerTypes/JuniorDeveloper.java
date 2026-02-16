package main.user.developerTypes;

public class JuniorDeveloper extends Developer {
    public JuniorDeveloper(final String username, final String mail,
                           final String role, final String date,
                           final String expertiseArea,
                           final String seniority) {
        super(username, mail, role, date, expertiseArea, seniority);
    }

    /**
     * Prioritate de acces
     * @return
     */
    @Override
    public int getAccessPriority() {
        return 2;
    }

    /**
     * Prirotate pentru tichet
     * @return
     */
    @Override
    public int getTicketType() {
        return 2;
    }
}
