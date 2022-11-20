package walidjek.glady.api.deposit;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class DistributionRequest {

    private int amount;
    private int companyId;
    private int userId;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @JsonProperty("company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
