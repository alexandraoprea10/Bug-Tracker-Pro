package main.searching;

import com.fasterxml.jackson.databind.JsonNode;
import main.helpers.HelperMethods;
import main.user.developerTypes.Developer;
import main.user.Manager;
import main.user.Users;

import java.util.ArrayList;
import java.util.List;

import static main.helpers.ReturnHelpers.returnUser;


public class DevelopersSearch {
    /**
     * Cauta developerii.
     * @param user
     * @param filter
     * @param useri
     * @return
     */
    public List<Developer> searchDevelopers(final Users user,
                                            final JsonNode filter,
                                            final List<Users> useri, final HelperMethods helpers) {
        Manager manager = (Manager) user;
        List<Developer> developersFound = new ArrayList<>();
        String expertiseArea = null;
        String seniority = null;
        double performanceScoreAbove = 0.0;
        double performanceScoreBelow = 0.0;
        if (filter.get("expertiseArea") != null) {
            expertiseArea = filter.get("expertiseArea").asText();
        }
        if (filter.get("seniority") != null) {
            seniority = filter.get("seniority").asText();
        }
        if (filter.get("performanceScoreAbove") != null) {
            performanceScoreAbove = filter.get("performanceScoreAbove").asDouble();
        }
        if (filter.get("performanceScoreBelow") != null) {
            performanceScoreBelow = filter.get("performanceScoreBelow").asDouble();
        }
        List<String> subordinates = manager.getSubordinates();
        for (int i = 0; i < subordinates.size(); i++) {
            String userC = subordinates.get(i);
            Users usr = returnUser(useri, userC);
            if (usr.getRole().equals("DEVELOPER")) {
                Developer developer = (Developer) usr;
                if (expertiseArea != null
                        && !expertiseArea.equals(developer.getExpertiseArea())) {
                    continue;
                }
                if (seniority != null && !seniority.equals(developer.getSeniority())) {
                   continue;
                }
                if (filter.get("performanceScoreAbove") != null
                        && developer.getPerformanceScore() < performanceScoreAbove) {
                    continue;
                }
                if (filter.get("performanceScoreBelow") != null
                        && developer.getPerformanceScore() > performanceScoreBelow) {
                    continue;
                }
                developersFound.add(developer);
            }
        }
        return developersFound;
    }
}
