package main.ticket;

import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;

import java.util.ArrayList;
import java.util.List;

public final class UIFeedback extends Ticket {
    private String uiElementId;
    private String businessValue;
    private int businessvalueCode;
    private int usabilityScore;
    private String screenshotUrl;
    private String suggestedFix;
    // builder
    public static class Builder {
        private int id;
        private String type;
        private String title;
        private String businessPriority;
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
        private double calculateImpact;
        private double calculateRisk;

        private String uiElementId;
        private String businessValue;
        private int businessvalueCode;
        private int usabilityScore;
        private String screenshotUrl;
        private String suggestedFix;
        public Builder(final int id, final String title, final String businessPriority,
                       final String createdAt, final String expertiseArea,
                       final String reportedBy,
                       final String businessValue, final int usabilityScore) {
            this.id = id;
            this.title = title;
            this.businessPriority = businessPriority;
            this.status = "OPEN";
            this.createdAt = createdAt;
            this.assignedAt = "";
            this.solvedAt = "";
            this.assignedTo = "";
            this.expertiseArea = expertiseArea;
            this.reportedBy = reportedBy;
            this.businessValue = businessValue;
            this.usabilityScore = usabilityScore;
            this.isAVailableForAssignment = false;
            if (businessValue.equals("S")) {
                this.businessvalueCode = 1;
            } else if (businessValue.equals("M")) {
                this.businessvalueCode = MagicNumbersInt.trei.getValue();
            } else if (businessValue.equals("L")) {
                this.businessvalueCode = MagicNumbersInt.sase.getValue();
            } else if (businessValue.equals("XL")) {
                this.businessvalueCode = MagicNumbersInt.zece.getValue();
            }
            double inm = businessvalueCode * usabilityScore;
            double res = (inm * MagicNumbersDouble.osuta.getValue())
                    / MagicNumbersDouble.osuta.getValue();
            this.calculateImpact = res;
            double inm2 = (MagicNumbersInt.unsprezece.getValue() - usabilityScore)
                    * businessvalueCode;
            double res2 = (inm * MagicNumbersDouble.osuta.getValue())
                    / MagicNumbersDouble.osuta.getValue();
            this.calculateRisk = res2;
        }

        /**
         * uiElementId
         * @param uiElement parametru optional
         * @return builder
         */
        public Builder uiElementId(final String uiElement) {
            this.uiElementId = uiElement;
            return this;
        }
        /**
         * Descriere
         * @param descr parametru optional
         * @return builder
         */
        public Builder description(final String descr) {
            this.description = descr;
            return this;
        }

        /**
         * Screenshoturl
         * @param screenshot parametru optional
         * @return builder
         */
        public Builder screenshotUrl(final String screenshot) {
            this.screenshotUrl = screenshot;
            return this;
        }

        /**
         * SUggested fix
         * @param suggested parametru optional
         * @return builder
         */
        public Builder suggestedFix(final String suggested) {
            this.suggestedFix = suggested;
            return this;
        }

        /**
         * Creez builder
         * @return tichetul
         */
        public UIFeedback build() {
            return new UIFeedback(this);
        }

    }
    // constructor
    private UIFeedback(final Builder builder) {
        super(builder.id, "UI_FEEDBACK", builder.title,
                builder.businessPriority, "OPEN",  builder.createdAt,
                builder.expertiseArea, builder.reportedBy,
                builder.description, builder.calculateImpact, builder.calculateRisk);
        this.uiElementId = builder.uiElementId;
        this.businessValue = builder.businessValue;
        this.usabilityScore = builder.usabilityScore;
        this.screenshotUrl = builder.screenshotUrl;
        this.suggestedFix = builder.suggestedFix;
        this.businessvalueCode = builder.businessvalueCode;
        setCalculateImpact(builder.calculateImpact);
        setCalculateRisk(builder.calculateRisk);
    }
    // getteri
    public String getUiElementId() {
        return uiElementId;
    }
    public String getBusinessValue() {
        return businessValue;
    }
    public int getUsabilityScore() {
        return usabilityScore;
    }
    public String getScreenshotUrl() {
        return screenshotUrl;
    }
    public String getSuggestedFix() {
        return suggestedFix;
    }

    /**
     * Returneaza codul de la businessvalue.
     * @return
     */
    public int getbusinessvalueCode() {
        return businessvalueCode;
    }
    // setteri
    public void setUiElementId(final String uiElementId) {
        this.uiElementId = uiElementId;
    }
    public void setBusinessValue(final String businessValue) {
        this.businessValue = businessValue;
    }
    public void setUsabilityScore(final int usabilityScore) {
        this.usabilityScore = usabilityScore;
    }
    public void setScreenshotUrl(final String screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }
    public void setSuggestedFix(final String suggestedFix) {
        this.suggestedFix = suggestedFix;
    }
    @Override
    public boolean isBUG() {
        return false;
    }
    @Override
    public boolean isUI() {
        return true;
    }
    @Override
    public boolean isFeature() {
        return false;
    }
}
