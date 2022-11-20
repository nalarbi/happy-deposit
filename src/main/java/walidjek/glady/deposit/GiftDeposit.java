package walidjek.glady.deposit;

import java.util.Date;
import org.joda.time.*;
import walidjek.glady.company.Company;
import walidjek.glady.user.User;

public class GiftDeposit extends Deposit {

    public GiftDeposit(int amount, User user, Company company, Date creationDate) {
        super(amount, user, company, creationDate);
    }

    @Override
    public Date getExpirationDate() {
        return new DateTime(getCreationDate()).plusYears(1).minusDays(1).toDate();
    }

    @Override
    public DepositType getDepositType() {
        return DepositType.GIFT;
    }
}
