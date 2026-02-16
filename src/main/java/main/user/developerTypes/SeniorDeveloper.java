package main.user.developerTypes;

import main.magicNumbers.MagicNumbersInt;

public class SeniorDeveloper extends Developer {
    public SeniorDeveloper(final String username, final String mail,
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
        return MagicNumbersInt.patru.getValue();
    }

    /**
     * Prioritate tichet
     * @return
     */
    @Override
    public int getTicketType() {
        return MagicNumbersInt.trei.getValue();
    }
}
