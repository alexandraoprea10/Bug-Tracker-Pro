package main.Ticket;

public class FeatureRequest extends Ticket {
    private String businessValue;
    private int businessvalueCode;
    private String customerDemand;
    private int customerdemandCode;
    public FeatureRequest(final int id, final String title,
                          final String businessPriority, final String createdAt,
                          final String expertiseArea, final String description,
                          final String businessV, final String costumerD,
                          final String reportedBy) {
        super(id, "FEATURE_REQUEST", title, businessPriority,
                "OPEN", createdAt, expertiseArea, reportedBy, description, 0.0, 0.0);
        this.businessValue = businessV;
        this.customerDemand = costumerD;
        if (costumerD.equals("LOW")) {
            this.customerdemandCode = 1;
        } else if (costumerD.equals("MEDIUM")) {
            this.customerdemandCode = 3;
        } else if (costumerD.equals("HIGH")) {
            this.customerdemandCode = 6;
        } else if (costumerD.equals("VERY_HIGH")) {
            this.customerdemandCode = 10;
        }
        if (businessValue.equals("S")) {
            this.businessvalueCode = 1;
        } else if (businessValue.equals("M")) {
            this.businessvalueCode = 3;
        } else if (businessValue.equals("L")) {
            this.businessvalueCode = 6;
        } else if (businessValue.equals("XL")) {
            this.businessvalueCode = 10;
        }
        double inm = businessvalueCode * customerdemandCode;
        double res = (inm * 100.0) / 100.0;
        setCalculateImpact(res);
        double inm1 = businessvalueCode + customerdemandCode;
        double res1 = (inm1 * 100.0) / 20.0;
        setCalculateRisk(res1);
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
    public int getCustomerdemandCode() {
        return customerdemandCode;
    }
    public int getBusinessvalueCode() {
        return businessvalueCode;
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
    @Override
    public boolean isBUG() {
        return false;
    }
    @Override
    public boolean isUI() {
        return false;
    }
    @Override
    public boolean isFeature() {
        return true;
    }
}
