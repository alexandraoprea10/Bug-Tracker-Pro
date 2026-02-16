package main.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PrintingHelpers {
    /**
     * Printeaza comanda, username si timestamp(mereu).
     * @param command
     * @param username
     * @param timestamp
     * @return
     */
    public static ObjectNode printWhatINeed(final String command,
                                            final String username, final String timestamp) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("command", command);
        node.put("username", username);
        node.put("timestamp", timestamp);
        return node;
    }

    /**
     * Verific daca expertiza e ok.
     * @param expertiseArea
     * @return
     */
    public static String printExpertiseArea(final String expertiseArea) {
        if (expertiseArea.equals("DB")) {
            return "BACKEND, DB, FULLSTACK";
        } else if (expertiseArea.equals("FRONTEND")) {
            return "DESIGN, FRONTEND, FULLSTACK";
        } else if (expertiseArea.equals("BACKEND")) {
            return "BACKEND, FULLSTACK";
        } else if (expertiseArea.equals("DEVOPS")) {
            return "DEVOPS, FULLSTACK";
        } else if (expertiseArea.equals("DESIGN")) {
            return "DESIGN, FRONTEND, FULLSTACK";
        }
        return null;
    }

    /**
     * Verific ce fel de expertiza poate rezolva.
     * @param priority
     * @return
     */
    public static String printPriority(final String priority) {
        if (priority.equals("LOW")) {
            return "JUNIOR, MID, SENIOR";
        } else if (priority.equals("MEDIUM")) {
            return "MID, SENIOR";
        } else if (priority.equals("HIGH")) {
            return "MID, SENIOR";
        } else if (priority.equals("CRITICAL")) {
            return "SENIOR";
        }
        return null;
    }
}
