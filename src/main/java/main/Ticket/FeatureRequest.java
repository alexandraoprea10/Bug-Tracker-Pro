package main.Ticket;

public class FeatureRequest extends Ticket {
    private String businessValue;
    private String customerDemand;
    public FeatureRequest(final int id, final String title,
                          final String businessPriority, final String createdAt,
                          final String expertiseArea, final String description,
                          final String businessV, final String costumerD,
                          final String reportedBy) {
        super(id, "FEATURE_REQUEST", title, businessPriority,
                "OPEN", createdAt, expertiseArea, reportedBy, description);
        this.businessValue = businessV;
        this.customerDemand = costumerD;
    }
    // getteri

    /**
     * Returneaza business value
     * @return business value
     */
    public String getBusinessValue() {
        return businessValue;
    }

    /**
     * Seteaza customer demand
     * @return customer demand
     */
    public String getCustomerDemand() {
        return customerDemand;
    }
    // setteri

    /**
     * Seteaza business value
     * @param businessV
     */
    public void setBusinessValue(final String businessV) {
        this.businessValue = businessV;
    }

    /**
     * Seteaza customer demand
     * @param customerDemand
     */
    public void setCustomerDemand(final String customerDemand) {
        this.customerDemand = customerDemand;
    }
}
