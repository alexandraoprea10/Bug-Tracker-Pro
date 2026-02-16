package main.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.helpers.HelperMethods;
import main.magicNumbers.MagicNumbersInt;
import main.milestones.InfoMilestones;
import main.milestones.Milestone;
import main.searching.DevelopersSearch;
import main.searching.TicketSearch;
import main.ticket.*;
import main.user.Users;

import java.util.ArrayList;
import java.util.List;

import static main.helpers.PrintingHelpers.printWhatINeed;

public class ReportTicket implements Command {
    /**
     * Executa comanda
     * @param params
     * @param useri
     * @param inventarTichete
     * @param milestones
     * @param helpers
     * @param outputs
     * @param timestampTesting
     * @param lastTimestamp
     * @param id
     * @param command
     * @param username
     * @param timestamp
     * @param veziTichete
     * @param i
     * @param inputJson
     * @param infoMilestones
     * @param user
     * @param ticketSearch
     * @param developersSearch
     * @param mapper
     */
    @Override
    public void executeCommand(final JsonNode params, final ArrayList<Users> useri,
                               final ArrayList<Ticket> inventarTichete,
                               final ArrayList<Milestone> milestones,
                               final HelperMethods helpers,
                               final List<ObjectNode> outputs,
                               final String timestampTesting,
                               final String lastTimestamp,
                               final int[] id, final String command,
                               final String username, final String timestamp,
                               final VeziTichete veziTichete,
                               final int i, final JsonNode inputJson,
                               final InfoMilestones infoMilestones, final Users user,
                               final TicketSearch ticketSearch,
                               final DevelopersSearch developersSearch,
                               final ObjectMapper mapper) {
        String type = params.get("type").asText();
        String title = params.get("title").asText();
        String businessPriority = params.get("businessPriority").asText();
        String reportedBy = params.get("reportedBy").asText();
        String expertiseArea = params.get("expertiseArea").asText();
        String description = null;
        if (params.get("description") != null) {
            description = params.get("description").asText();
        }
        if (reportedBy.equals("") && !type.equals("BUG")) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Anonymous reports are "
                    + "only allowed for tickets of type BUG.");
            outputs.add(node);
        } else if (helpers.overdueTestingPeriod(timestampTesting, timestamp) == 1) {
            ObjectNode node = printWhatINeed(command, username, timestamp);
            node.put("error", "Tickets can only be "
                    + "reported during testing phases.");
            outputs.add(node);
        } else {
            if (type.equals("BUG")) {
                String expectedBehavior = params.get("expectedBehavior").asText();
                String actualBehavior = params.get("actualBehavior").asText();
                String frequency = params.get("frequency").asText();
                String severity = params.get("severity").asText();
                String environment = null;
                if (params.get("environment") != null) {
                    environment = params.get("environment").asText();
                }
                int errorCode =
                        MagicNumbersInt.minuscinci.getValue();
                if (params.get("errorCode") != null) {
                    errorCode = Integer.parseInt(params.get("errorCode").asText());
                }
                BUG bug = new BUG.Builder(id[0], title, businessPriority, timestamp,
                        expertiseArea, description, expectedBehavior,
                        actualBehavior, frequency, severity, reportedBy)
                        .environment(environment)
                        .errorCode(errorCode)
                        .build();
                inventarTichete.add(bug);
                id[0]++;
            } else if (type.equals("FEATURE_REQUEST")) {
                String businessValue = params.get("businessValue").asText();
                String customerDemand = params.get("customerDemand").asText();
                FeatureRequest featureR = new FeatureRequest(id[0],
                        title, businessPriority,
                        timestamp, expertiseArea, description,
                        businessValue, customerDemand, reportedBy);
                inventarTichete.add(featureR);
                id[0]++;
            } else if (type.equals("UI_FEEDBACK")) {
                String uiElementId = null;
                String businessValue = params.get("businessValue").asText();
                int usabilityScore = Integer.parseInt(
                        params.get("usabilityScore").asText());
                String screenshotUrl = null;
                if (params.get("uiElementId") != null) {
                    uiElementId = params.get("uiElementId").asText();
                }
                if (params.get("screenshotUrl") != null) {
                    screenshotUrl = params.get("screenshotUrl").asText();
                }
                String suggestedFix = null;
                if (params.get("suggestedFix") != null) {
                    suggestedFix = params.get("suggestedFix").asText();
                }
                UIFeedback uiFeedback = new UIFeedback.Builder(id[0], title,
                        businessPriority, timestamp, expertiseArea,
                        reportedBy,
                        businessValue, usabilityScore)
                        .uiElementId(uiElementId)
                        .description(description)
                        .screenshotUrl(screenshotUrl)
                        .suggestedFix(suggestedFix)
                        .build();
                inventarTichete.add(uiFeedback);
                id[0]++;
            }
        }
    }
}
