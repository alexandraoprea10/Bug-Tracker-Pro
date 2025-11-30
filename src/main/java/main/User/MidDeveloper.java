package main.User;

public class MidDeveloper extends Developer {
    public MidDeveloper(final String username, final String mail,
                        final String role, final String date,
                        final String expertiseArea, final String seniority) {
        super(username, mail, role, date, expertiseArea, seniority);
    }

    /**
     * Prioritate acces
     * @return
     */
    @Override
    public int getAccessPriority() {
        return 3;
    }

    /**
     * Prioritate acces tichete
     * @return
     */
    @Override
    public int getTicketType() {
        return 3;
    }
}
