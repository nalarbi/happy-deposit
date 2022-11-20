package walidjek.glady.deposit;

import org.joda.time.DateTime;
import walidjek.glady.company.Company;
import walidjek.glady.user.User;

import java.util.Date;

public class MealDeposit extends Deposit {

    public MealDeposit(int amount, User user, Company company, Date creationDate) {
        super(amount, user, company, creationDate);
    }

    @Override
    public Date getExpirationDate() {
        int month = new DateTime(getCreationDate()).monthOfYear().get();
        int yearToAdd = 0;
        if (month > 2) {
            yearToAdd = 1;
        }

        return new DateTime(getCreationDate())
                .plusYears(yearToAdd)
                .withMonthOfYear(2)
                .dayOfMonth()
                .withMaximumValue()
                .toDate();

    }

    @Override
    public DepositType getDepositType() {
        return DepositType.MEAL;
    }
}
