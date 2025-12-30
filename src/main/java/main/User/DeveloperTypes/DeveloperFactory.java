package main.User.DeveloperTypes;

public abstract class DeveloperFactory {
    /**
     * Creaza developerul(depinde de tip)
     * @param username
     * @param mail
     * @param hireDate
     * @param expertiseArea
     * @param seniority
     * @return
     */
    public static Developer createDeveloper(final String username,
                                            final String mail,
                                            final String hireDate, final String expertiseArea,
                                            final String seniority) {
        if (seniority.equals("JUNIOR")) {
            return new JuniorDeveloper(username, mail, "DEVELOPER", hireDate,
                    expertiseArea, seniority);
        } else if (seniority.equals("MID")) {
            return new MidDeveloper(username, mail, "DEVELOPER", hireDate,
                    expertiseArea, seniority);
        }
        return new SeniorDeveloper(username, mail, "DEVELOPER", hireDate,
                expertiseArea, seniority);
    }
}
