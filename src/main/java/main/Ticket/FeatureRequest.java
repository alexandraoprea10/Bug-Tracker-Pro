package main.Ticket;

import main.MagicNumbers.MagicNumbersDouble;
import main.MagicNumbers.MagicNumbersInt;

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
            this.customerdemandCode = MagicNumbersInt.trei.getValue();
        } else if (costumerD.equals("HIGH")) {
            this.customerdemandCode = MagicNumbersInt.sase.getValue();
        } else if (costumerD.equals("VERY_HIGH")) {
            this.customerdemandCode = MagicNumbersInt.zece.getValue();
        }
        if (businessValue.equals("S")) {
            this.businessvalueCode = 1;
        } else if (businessValue.equals("M")) {
            this.businessvalueCode = MagicNumbersInt.trei.getValue();
        } else if (businessValue.equals("L")) {
            this.businessvalueCode = MagicNumbersInt.sase.getValue();
        } else if (businessValue.equals("XL")) {
            this.businessvalueCode = MagicNumbersInt.zece.getValue();
        }
        double inm = businessvalueCode * customerdemandCode;
        double res = (inm * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.osuta.getValue();
        setCalculateImpact(res);
        double inm1 = businessvalueCode + customerdemandCode;
        double res1 = (inm1 * MagicNumbersDouble.osuta.getValue())
                / MagicNumbersDouble.douazeci.getValue();
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

    /**
     * Returneaza codul pt costumerdemand.
     * @return
     */
    public int getCustomerdemandCode() {
        return customerdemandCode;
    }

    /**
     * Returneaza codul tichetului dpdv al business-value-ului.
     * @return
     */
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

    /**
     * Returnez true doar pentru feature request.
     * @return
     */
    @Override
    public boolean isBUG() {
        return false;
    }

    /**
     * Returnez true doar pentru feature request.
     * @return
     */
    @Override
    public boolean isUI() {
        return false;
    }

    /**
     * Returnez true doar pentru feature request.
     * @return
     */
    @Override
    public boolean isFeature() {
        return true;
    }
}
