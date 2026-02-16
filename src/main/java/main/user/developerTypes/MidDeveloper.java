package main.user.developerTypes;

import main.magicNumbers.MagicNumbersInt;

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
        return MagicNumbersInt.trei.getValue();
    }

    /**
     * Prioritate acces tichete
     * @return
     */
    @Override
    public int getTicketType() {
        return MagicNumbersInt.trei.getValue();
    }
}
