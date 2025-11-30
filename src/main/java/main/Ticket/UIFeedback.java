package main.Ticket;

public final class UIFeedback extends Ticket {
    private String uiElementId;
    private String businessValue;
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
        private String comments;
        private String expertiseArea;
        private String reportedBy;
        private String description;

        private String uiElementId;
        private String businessValue;
        private int usabilityScore;
        private String screenshotUrl;
        private String suggestedFix;
        public Builder(final int id, final String title, final String businessPriority,
                       final String createdAt, final String expertiseArea,
                       final String reportedBy, final String uiElementId,
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
            this.uiElementId = uiElementId;
            this.businessValue = businessValue;
            this.usabilityScore = usabilityScore;
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
                builder.description);
        this.uiElementId = builder.uiElementId;
        this.businessValue = builder.businessValue;
        this.usabilityScore = builder.usabilityScore;
        this.screenshotUrl = builder.screenshotUrl;
        this.suggestedFix = builder.suggestedFix;
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
}
