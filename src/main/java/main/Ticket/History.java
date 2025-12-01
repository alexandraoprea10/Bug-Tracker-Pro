package main.Ticket;

public final class History {
    private String action;
    private String from;
    private String to;
    private String by;
    private String milestone;
    private String timestamp;
    public static class Builder {
        private String action;
        private String from;
        private String to;
        private String by;
        private String milestone;
        private String timestamp;
        public Builder(final String act, final String b,
                       final String time) {
            this.action = act;
            this.by = b;
            this.timestamp = time;
        }

        /**
         * Camp suplimentar
         * @param f
         * @return
         */
        public Builder from(final String f) {
            this.from = f;
            return this;
        }

        /**
         * Camp suplimentar
         * @param t
         * @return
         */
        public Builder to(final String t) {
            this.to = t;
            return this;
        }

        /**
         * Camp suplimentar
         * @param miles
         * @return
         */
        public Builder milestone(final String miles) {
            this.milestone = miles;
            return this;
        }

        /**
         * Creeaza builder-ul.
         * @return
         */
        public History build() {
            return new History(this);
        }
    }
    private History(final Builder builder) {
        this.action = builder.action;
        this.from = builder.from;
        this.to = builder.to;
        this.by = builder.by;
        this.timestamp = builder.timestamp;
        this.milestone = builder.milestone;
    }
    // getteri

    /**
     * Returneaza comanda facuta.
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * Returneaza statusul precedent.
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returneaza statusul curent.
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     * Returneaza de cine a fost facuta actiunea.
     * @return
     */
    public String getBy() {
        return by;
    }

    /**
     * Returneaza timsetamp-ul.
     * @return
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Returneaza milestone-ul
     * @return
     */
    public String getMilestone() {
        return milestone;
    }
    // setteri

    /**
     * Seteaza actiunea
     * @param action
     */
    public void setAction(final String action) {
        this.action = action;
    }

    /**
     * Seteaza statusul precedent,
     * @param from
     */
    public void setFrom(final String from) {
        this.from = from;
    }

    /**
     * Seteaza statusul curent.
     * @param to
     */
    public void setTo(final String to) {
        this.to = to;
    }

    /**
     * Seteaza de cine a fost facuta actiunea.
     * @param by
     */
    public void setBy(final String by) {
        this.by = by;
    }

    /**
     * Seteaza timsetampul.
     * @param timestamp
     */
    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Seteaza milestone ul.
     * @param milestone
     */
    public void setMilestone(final String milestone) {
        this.milestone = milestone;
    }
}
