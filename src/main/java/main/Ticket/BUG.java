package main.Ticket;

import main.MagicNumbersDouble;
import main.MagicNumbersInt;

import java.util.ArrayList;
import java.util.List;

public final class BUG extends Ticket {
    private String expectedBehaviour;
    private String actualBehaviour;
    private String frequency;
    private String severity;
    private String environment;
    private int errorCode;
    private int freqCode;
    private int severityCode;
    // builder
    public static final class Builder {
        private int id;
        private String type;
        private String title;
        private String businessPriority;
        private int businesspriorityCode;
        private String status;
        private String createdAt;
        private String assignedAt;
        private String solvedAt;
        private String assignedTo;
        private List<String> comments;
        private List<String> authors;
        private List<String> date;
        private String expertiseArea;
        private String reportedBy;
        private String description;
        private ArrayList<History> histories;
        private boolean isAVailableForAssignment;
        private int freqCode;
        private int severityCode;
        private double calculateImpact;
        private double calculateRisk;
        private double calculateEfficiency;
        private double daysToResolve;

        private String expectedBehaviour;
        private String actualBehaviour;
        private String frequency;
        private String severity;
        private String environment;
        private int errorCode;
        public Builder(final int id, final String title,
                       final String businessPriority,
                       final String createdAt, final String expertiseArea,
                       final String description, final String expectedBehaviour,
                       final String actualBehaviour, final String frequency,
                       final String severity, final String reportedBy) {
            this.id = id;
            this.title = title;
            if (reportedBy.isEmpty()) {
                this.businessPriority = "LOW";
            } else {
                this.businessPriority = businessPriority;
            }
            this.status = "OPEN";
            this.createdAt = createdAt;
            this.assignedAt = "";
            this.solvedAt = "";
            this.assignedTo = "";
            this.expertiseArea = expertiseArea;
            this.reportedBy = reportedBy;
            this.description = description;
            this.isAVailableForAssignment = true;
            this.expectedBehaviour = expectedBehaviour;
            this.actualBehaviour = actualBehaviour;
            this.frequency = frequency;
            this.severity = severity;
            if (frequency.equals("RARE")) {
                this.freqCode = 1;
            } else if (frequency.equals("OCCASIONAL")) {
                this.freqCode = MagicNumbersInt.doi.getValue();
            } else if (frequency.equals("FREQUENT")) {
                this.freqCode = MagicNumbersInt.trei.getValue();
            } else if (frequency.equals("ALWAYS")) {
                this.freqCode = MagicNumbersInt.patru.getValue();
            }
            if (businessPriority.equals("LOW")) {
                this.businesspriorityCode = 1;
            } else if (businessPriority.equals("MEDIUM")) {
                this.businesspriorityCode = MagicNumbersInt.doi.getValue();
            } else if (businessPriority.equals("HIGH")) {
                this.businesspriorityCode = MagicNumbersInt.trei.getValue();
            } else if (businessPriority.equals("CRITICAL")) {
                this.businesspriorityCode = MagicNumbersInt.patru.getValue();
            }
            if (reportedBy.equals("")) {
                this.businesspriorityCode = 1;
            }
            if (severity.equals("MINOR")) {
                this.severityCode = 1;
            } else if (severity.equals("MODERATE")) {
                this.severityCode = MagicNumbersInt.doi.getValue();
            } else if (severity.equals("SEVERE")) {
                this.severityCode = MagicNumbersInt.trei.getValue();
            }
            double inm = freqCode * businesspriorityCode * severityCode;
            double res = (inm * MagicNumbersDouble.osuta.getValue())
                    / MagicNumbersDouble.patruzecisiopt.getValue();
            this.calculateImpact = res;
            double risk = freqCode * severityCode;
            double res2 = (risk * MagicNumbersDouble.osuta.getValue())
                    / MagicNumbersDouble.doisprezece.getValue();
            this.calculateRisk = res2;
        }

        /**
         * Descriere
         * @param desc parametru optional
         * @return builder
         */
        public Builder description(final String desc) {
            this.description = desc;
            return this;
        }

        /**
         * ENvironment
         * @param env parametru optional
         * @return builder
         */
        public Builder environment(final String env) {
            this.environment = env;
            return this;
        }

        /**
         * errorCode
         * @param error parametru optional
         * @return builder
         */
        public Builder errorCode(final int error) {
            this.errorCode = error;
            return this;
        }

        /**
         * creere builder
         * @return build
         */
        public BUG build() {
            return new BUG(this);
        }
    }
    // constructor privat

    /**
     * Consstructor privat
     * @param builder
     */
    private BUG(final Builder builder) {
        super(builder.id, "BUG", builder.title, builder.businessPriority,
                builder.status, builder.createdAt, builder.expertiseArea,
                builder.reportedBy, builder.description,
                builder.calculateImpact, builder.calculateRisk);
        this.expectedBehaviour = builder.expectedBehaviour;
        this.actualBehaviour = builder.actualBehaviour;
        this.frequency = builder.frequency;
        this.severity = builder.severity;
        this.environment = builder.environment;
        this.errorCode = builder.errorCode;
        this.severityCode = builder.severityCode;
        this.freqCode = builder.freqCode;
        setCalculateImpact(builder.calculateImpact);
        setCalculateRisk(builder.calculateRisk);
    }
    // getteri
    public String getExpectedBehaviour() {
        return expectedBehaviour;
    }
    public String getActualBehaviour() {
        return actualBehaviour;
    }
    public String getFrequency() {
        return frequency;
    }
    public String getSeverity() {
        return severity;
    }
    public String getEnvironment() {
        return environment;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public int getSeverityCode() {
        return severityCode;
    }
    public int getFreqCode() {
        return freqCode;
    }
    // setteri
    public void setExpectedBehaviour(final String expectedB) {
        this.expectedBehaviour = expectedB;
    }
    public void setActualBehaviour(final String actualB) {
        this.actualBehaviour = actualB;
    }
    public void setFrequency(final String freq) {
        this.frequency = freq;
    }
    public void setSeverity(final String sev) {
        this.severity = sev;
    }
    public void setEnvironment(final String env) {
        this.environment = env;
    }
    public void setErrorCode(final int error) {
        this.errorCode = error;
    }
    public void setFreqCode(final int freqCode) {
        this.freqCode = freqCode;
    }

    @Override
    public boolean isBUG() {
        return true;
    }
    @Override
    public boolean isUI() {
        return false;
    }
    @Override
    public boolean isFeature() {
        return false;
    }
}
